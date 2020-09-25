package com.forest.framework.utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CodeCounter {

    private static Integer code = 0;
    private static Integer codeComments = 0;
    private static Integer codeBlank = 0;
    private static Integer fileCounts = 0;
    // java,xml
    private static Set<String> filterFile = new HashSet<>();
    static {
        filterFile.add("java");
        // filterFile.add("xml");
    }

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        System.out.println(rootPath);
        File file = new File(rootPath);
        factFiles(file);
        System.out.println("代码行数" + code);
        System.out.println("空白行数" + codeBlank);
        System.out.println("注释行数" + codeComments);
        System.out.println("文件数量" + fileCounts);
    }

    public static void factFiles(File file) {
        BufferedReader br = null;
        String s = null;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isFile()) {
                    fileCounts++;
                }
                factFiles(f);
            }
        } else {

            int lastIndexOf = file.getName().lastIndexOf(".");
            if(lastIndexOf == -1) {
                return;
            }
            String suffix = file.getName().substring(lastIndexOf + 1);
            if(!filterFile.contains(suffix)) {
                return;
            }

            System.out.println(file.getName());

            try {
                br = new BufferedReader(new FileReader(file));
                boolean comm = false;
                while ((s = br.readLine()) != null) {
                    if (s.startsWith("/*") && s.endsWith("*/")) {
                        codeComments++;
                    } else if (s.trim().startsWith("//")) {
                        codeComments++;
                    } else if (s.startsWith("/*") && !s.endsWith("*/")) {
                        codeComments++;
                        comm = true;
                    } else if (!s.startsWith("/*") && s.endsWith("*/")) {
                        codeComments++;
                        comm = false;
                    } else if (comm) {
                        codeComments++;
                    } else if (s.trim().length() < 1) {
                        codeBlank++;
                    } else {
                        code++;
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
