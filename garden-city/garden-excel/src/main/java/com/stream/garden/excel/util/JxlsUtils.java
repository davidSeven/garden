package com.stream.garden.excel.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出Excel工具类
 * <p>
 * 参考：http://blog.csdn.net/lnktoking/article/details/52932679
 */
public class JxlsUtils {
    private static Logger logger = LoggerFactory.getLogger(JxlsUtils.class);

    /**
     * 导出excel
     *
     * @param is    - excel文件流
     * @param os    - 生成模版输出流
     * @param kvMap - 模版中填充的数据
     * @throws IOException
     */
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> kvMap) throws Exception {
        Context context = new Context();
        if (kvMap != null) {
            for (String key : kvMap.keySet()) {
                context.putVar(key, kvMap.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("jx", new JxlsUtils());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.processTemplate(context, transformer);
    }

    /**
     * 导出excel
     *
     * @param xlsTemplatePath excel模板文件路径
     * @param os              输出文件流
     * @param kvMap           模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel(String xlsTemplatePath, OutputStream os, Map<String, Object> kvMap) throws Exception {
        exportExcel(getTemplate(xlsTemplatePath), os, kvMap);
    }

    /**
     * 导出excel
     *
     * @param xlsTemplatePath excel模板文件路径
     * @param os              输出文件流
     * @param kvMap           模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel2(String xlsTemplatePath, OutputStream os, Map<String, Object> kvMap) throws Exception {
        exportExcel(getTemplate(xlsTemplatePath), os, kvMap);
    }

    /**
     * 导出excel
     *
     * @param xlsTemplatePath excel模板文件路径
     * @param outPath         输出文件路径
     * @param kvMap           模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel(String xlsTemplatePath, String outPath, Map<String, Object> kvMap) throws Exception {
        exportExcel(getTemplate(xlsTemplatePath), new FileOutputStream(outPath), kvMap);
    }


    /**
     * 导入excel
     *
     * @param xmlTemplatePath 模块文件
     * @param file            导入文件
     * @param dataMap         对象
     * @return
     */

    public static void importExcle(String xmlTemplatePath, File file, Map<String, Object> dataMap) throws Exception {
        try {
            importExcle(xmlTemplatePath, new FileInputStream(file), dataMap);
        } catch (Exception e) {
            logger.error("importExcle", e);
            throw e;
        }
    }


    /**
     * 导入excel
     *
     * @param xmlTemplateStream 模块文件
     * @param inportStream      导入文件
     * @param dataMap           对象
     * @return
     */
    public static void importExcle(InputStream xmlTemplateStream, InputStream inportStream, Map<String, Object> dataMap) throws Exception {
//        InputStream inputStream = null;
        try {
            XLSReader mainReader = ReaderBuilder.buildFromXML(xmlTemplateStream);
//            inputStream = new BufferedInputStream(inportStream);
            XLSReadStatus readStatus = mainReader.read(inportStream, dataMap);
            if (readStatus.isStatusOK()) {
                logger.info("导入excel成功");
            } else {
                logger.info("导入excel失败");
            }
        } catch (IOException ioExcepion) {
            logger.error("importExcle", ioExcepion);
            throw ioExcepion;
        } catch (SAXException sAXException) {
            logger.error("ReaderBuilder.buildFromXML", sAXException);
            throw sAXException;
        } catch (InvalidFormatException invalidFormatException) {
            logger.error("mainReader.read", invalidFormatException);
            throw invalidFormatException;
        } finally {
            if (xmlTemplateStream != null) {
                try {
                    xmlTemplateStream.close();
                } catch (IOException e) {
                    logger.error("inputStream.close()", e);
                }
            }
            if (inportStream != null) {
                try {
                    inportStream.close();
                } catch (IOException e) {
                    logger.error("inportStream.close()", e);
                }
            }
        }
    }

    /**
     * 导入excel
     *
     * @param xmlTemplatePath 模块文件
     * @param inportStream    导入文件
     * @param dataMap         对象
     * @return
     */
    public static void importExcle(String xmlTemplatePath, InputStream inportStream, Map<String, Object> dataMap) throws Exception {
        importExcle(getTemplate(xmlTemplatePath), inportStream, dataMap);
    }

    /**
     * 导出excel
     *
     * @param xlsTemplateFile excel模板文件
     * @param outFile         输出文件
     * @param kvMap           模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel(File xlsTemplateFile, File outFile, Map<String, Object> kvMap) throws Exception {
        exportExcel(new FileInputStream(xlsTemplateFile), new FileOutputStream(outFile), kvMap);
    }

    /**
     * 获取jxls模版输入流
     */
    public static InputStream getTemplate(String path) {
        InputStream template = null;
        try {
            URL url = ResourceUtils.getURL("classpath:" + path);
            if (url != null) {
                template = url.openStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    // 日期格式化
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 返回第一个不为空的对象
    public Object defaultIfNull(Object... objs) {
        for (Object o : objs) {
            if (o != null)
                return o;
        }
        return null;
    }

    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }

}
