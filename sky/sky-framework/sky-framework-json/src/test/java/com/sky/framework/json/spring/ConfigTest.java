package com.sky.framework.json.spring;

import com.sky.framework.json.spring.server.ConfigServer;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public abstract class ConfigTest {

    protected static final Logger log = LoggerFactory.getLogger(ConfigTest.class);
    protected static ConfigServer server;
    protected static int port = -1;

    public static void start() throws Exception {
        //get random port
        ServerSocket sock = new ServerSocket(0);
        port = sock.getLocalPort();
        sock.close();

        //start server
        server.start(port);
        boolean ready = false;
        while (!ready) {
            try {
                new URL("http://localhost:" + port + "/ready").openStream();
                ready = true;
            } catch (Exception e) {
                Thread.sleep(500);
            }
        }
    }

    @AfterClass
    public static void stop() {
        server.stop();
    }

    @Test
    public void testSimple() throws IOException {
        String asString = Request.Get("http://localhost:" + port + "/bean").execute().returnContent().asString();
        System.out.println(asString);
    }

    @Test
    public void testNoninterference() throws Exception {
        String ret = Request.Post("http://localhost:" + port + "/bean").bodyString(
                "{\"name\":\"名称\",\"age\":30,\"birthday\":1604472757315,\"info\":{\"remark\":\"备注信息\",\"document\":\"文档信息\"}}", ContentType.APPLICATION_JSON)
                .execute().returnContent().asString(Charset.defaultCharset());
        System.out.println(ret);
    }

    @Test
    public void testMultithreading() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger errors = new AtomicInteger(0);

        log.info("Multithreading test starting");
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    int c = counter.addAndGet(1);
                    try {
                        log.debug("testNoninterference() " + c + " started");
                        testNoninterference();
                        log.debug("testNoninterference() " + c + " passed");
                    } catch (Throwable e) {
                        log.error("testNoninterference() " + c + " failed");
                        errors.addAndGet(1);
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(120L, TimeUnit.SECONDS);
        log.info("Multithreading test finished");
        assertEquals(100, counter.get());
        assertEquals(0, errors.get());
    }
}
