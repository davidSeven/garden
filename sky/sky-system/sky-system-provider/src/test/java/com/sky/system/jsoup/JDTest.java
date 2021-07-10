package com.sky.system.jsoup;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2021/7/10 12:29
 */
public class JDTest {

    private Random random = new Random();

    @Test
    public void loadJdData() throws IOException {
        //1.创建链接
        MongoClient client = new MongoClient("localhost", 27017);
        //2.打开数据库test
        MongoDatabase db = client.getDatabase("sky");
        //3.获取集合
        MongoCollection<org.bson.Document> jdGoodsCollection = db.getCollection("jd_goods");

        // https://list.jd.com/list.html?cat=670%2C671%2C673&pvid=a044aad5adc64fcb8232f8456ebe4057&page=17&s=477&click=0
        String deCat = "670,671,672";
        String cat = "670%2C671%2C672";
        String pvid = "5e2f6e3a07c948d18d2895a8c9bf94e9";
        int page = 99;
        int s = page * 123;

        try {
            String url = "https://list.jd.com/list.html?cat=" + deCat;
            Connection connect = null;
            Document document = null;
            List<org.bson.Document> list = null;
            if (page == 3) {
                connect = Jsoup.connect(url);
                document = connect.maxBodySize(0).get();
                list = parseList(document);
                jdGoodsCollection.insertMany(list);
                System.out.println("第一批次数据保存 - " + list.size());
            }
            while (page < 100) {
                String url2 = "https://list.jd.com/list.html?cat={0}&pvid={1}&page={2}&s={3}&click=0";
                MessageFormat messageFormat = new MessageFormat(url2);
                Object[] objs = new Object[4];
                objs[0] = cat;
                objs[1] = pvid;
                objs[2] = page;
                objs[3] = s;
                connect = Jsoup.connect(messageFormat.format(objs));
                document = connect.maxBodySize(0).get();
                list = parseList(document);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                System.out.println("page:" + page + " - " + list.size());
                jdGoodsCollection.insertMany(list);
                page += 2;
                int random = getRandom();
                s += random;
                try {
                    TimeUnit.MILLISECONDS.sleep(random * 20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            client.close();
        }
    }

    private int getRandom() {
        return random.nextInt(100) + 50;
    }

    private List<org.bson.Document> parseList(Document document) {
        Elements ul = document.select("ul.gl-warp");
        Elements lis = ul.select("li.gl-item");
        List<org.bson.Document> documentList = new ArrayList<>();
        for (Element li : lis) {
            String sku = li.attr("data-sku");
            String spu = li.attr("data-spu");
            Double price = Double.valueOf(li.select("div.p-price i").text());
            Elements div = li.select("div.p-name");
            Elements em = div.select("a em");
            Elements span = em.select("span");
            if (null != span) {
                span.remove();
            }
            String name = em.text();
            Elements i = div.select("a i.promo-words");
            String words = "";
            if (null != i) {
                words = i.text();
            }
            List<String> attrs = new ArrayList<>();
            Elements attrSpan = div.select("span.p-attribute");
            if (null != attrSpan) {
                Elements attrSpans = attrSpan.select("span");
                if (null != attrSpans) {
                    for (Element attr : attrSpans) {
                        attrs.add(attr.text());
                    }
                }
            }
            org.bson.Document gd = new org.bson.Document();
            gd.put("sku", sku);
            gd.put("spu", spu);
            gd.put("price", price);
            gd.put("name", name);
            gd.put("words", words);
            gd.put("attrs", attrs);
            documentList.add(gd);
        }
        return documentList;
    }
}
