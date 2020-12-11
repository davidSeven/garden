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
        sensitiveWordsList.add(new SensitiveWords(1L, "中国", "华夏"));
        sensitiveWordsList.add(new SensitiveWords(2L, "中国人", "华夏人"));
        sensitiveWordsList.add(new SensitiveWords(3L, "中国人民", "华夏人民"));
        sensitiveWordsList.add(new SensitiveWords(4L, "中共", "共党"));
        sensitiveWordsList.add(new SensitiveWords(5L, "中共中央", "中国共党"));
        sensitiveWordsList.add(new SensitiveWords(6L, "共产", "供铲"));
        sensitiveWordsList.add(new SensitiveWords(7L, "中华", "华夏"));
        instance.updateKeywords(sensitiveWordsList);

        System.out.println("已加载关键词：");
        System.out.println(JSON.toJSONString(sensitiveWordsList));

        String text = "中国共产党是中国工人阶级的先锋队，同时是中国人民和中华民族的先锋队，是中国特色社会主义事业的领导核心，代表中国先进生产力的发展要求，代表中国先进文化的前进方向，代表中国最广大人民的根本利益。党的最高理想和最终目标是实现共产主义";
        System.out.println("查询文本：\n" + text);

        SensitiveWordsReplace.init(sensitiveWordsList);
        String replace = SensitiveWordsReplace.findReplace(text);
        System.out.println(replace);
    }
}
