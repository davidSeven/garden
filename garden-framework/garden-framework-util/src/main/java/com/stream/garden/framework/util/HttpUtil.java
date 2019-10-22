package com.stream.garden.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author garden
 * @date 2019-10-22 14:58
 */
public class HttpUtil {
    private static final String UTF_8 = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                result = getResponse(connection);
            }
        } catch (MalformedURLException e) {
            logger.error("URL解析失败，" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 关闭资源
            if (null != connection) {
                // 关闭远程连接
                connection.disconnect();
            }
        }

        return result;
    }

    public static String doPost(String httpUrl, String param, String auth) {
        HttpURLConnection connection = null;
        OutputStream os = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息
            if (null != auth) {
                connection.setRequestProperty("authorization", auth);
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                result = getResponse(connection);
            }
        } catch (MalformedURLException e) {
            logger.error("URL解析失败，" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 关闭资源
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                // 断开与远程地址url的连接
                connection.disconnect();
            }
        }
        return result;
    }

    private static String getResponse(HttpURLConnection connection) {
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            br = new BufferedReader(new InputStreamReader(is, UTF_8));
            StringBuilder sbf = new StringBuilder();
            String temp;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            return sbf.toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("读取流失败，" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8090/dictionary/dictionary/list";
        String auth = "bearer.eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJyb2xlSWQiOiIzMDMxMDY5NDg5NjQ3ODIwODAiLCJ1bmlxdWVfbmFtZSI6IuS9n-S4veWohSIsInVzZXJJZCI6IjMwMzEwNjk2NzkzNTYxOTA3MiIsImlzcyI6ImdhcmRlbl9hcGlfdXNlciIsImF1ZCI6IjZBRjQ1REUwOThBQTRFOUZBODQwMzQ3OTNGODY3M0NEIiwiZXhwIjoxNTcxOTAxMTg3LCJuYmYiOjE1NzE3MjgzODd9.H7Td0HFWopa7N-jZIsEuRh-EhBNO4aqgywok9L2wP3Y";
        String result = doPost(url, "", auth);
        System.out.println(result);
    }
}
