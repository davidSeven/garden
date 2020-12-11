package com.sky.framework.words;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020-12-10 010 10:15
 */
public class TestSensitiveWordsSearch {


    @Test
    public void init() {
        SensitiveWordsSearch instance = SensitiveWordsSearch.getInstance();

        List<SensitiveWords> sensitiveWordsList = new ArrayList<>();
        sensitiveWordsList.add(new SensitiveWords(0L, "凌晨", "半夜"));
        sensitiveWordsList.add(new SensitiveWords(1L, "凌晨两点", "丑时三刻"));
        sensitiveWordsList.add(new SensitiveWords(2L, "国庆", "庆国"));
        sensitiveWordsList.add(new SensitiveWords(3L, "阅兵", "大阅"));
        sensitiveWordsList.add(new SensitiveWords(4L, "七点", "辰时"));
        sensitiveWordsList.add(new SensitiveWords(5L, "战地", "战场"));
        sensitiveWordsList.add(new SensitiveWords(6L, "维和军士", "和平使者"));
        sensitiveWordsList.add(new SensitiveWords(7L, "特警", "使者"));
        sensitiveWordsList.add(new SensitiveWords(8L, "小说", "软文"));
        instance.updateKeywords(sensitiveWordsList);

        System.out.println("已加载关键词：");
        System.out.println(JSON.toJSONString(sensitiveWordsList));

        String text = "凌晨凌晨两点毫无睡意，受国庆阅兵影响，七点爬起来看《白色橄榄树》这部小说。无论怎样评价玖月晞，战地记者与维和军士的配置简直招架不住。阿瓒又成为所看过小说里难以忘怀的一个名字，他是柔和淡定的排弹士兵，是机场突然出现的特警，也是在东国苏睿城郊5秒救下她的人。玖月晞轻描淡写，出来的故事却刻骨铭心，真的很喜欢这样力度的小说。";
        System.out.println("查询文本：\n" + text);

        SensitiveWordsReplace.init(sensitiveWordsList);
        String replace = SensitiveWordsReplace.findReplace(text);
        System.out.println(replace);
    }
}
