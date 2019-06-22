package com.stream.garden.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author garden
 */
public class TemplateUtil {

    public static void main(String[] args) {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("key", "使用freemarker模板技术生产文件");

        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("date", new Date());
        bookMap.put("number", 33);

        dataMap.put("book", bookMap);
        parse(dataMap, "a.ftl", "F:/a.f");
    }

    public static void parse(Map<String, Object> dataMap, String ftlPath, String outPath) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDefaultEncoding("utf-8");
        cfg.setClassForTemplateLoading(TemplateUtil.class, "/template");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        Template template = null;
        Writer out = null;
        try {
            template = cfg.getTemplate(ftlPath);
            File outFile = new File(outPath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            template.process(dataMap, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
