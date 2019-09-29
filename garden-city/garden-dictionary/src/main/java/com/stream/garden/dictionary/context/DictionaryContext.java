package com.stream.garden.dictionary.context;

import com.stream.garden.dictionary.model.Dictionary;
import com.stream.garden.dictionary.service.IDictionaryService;
import com.stream.garden.framework.api.interfaces.IApplicationRunner;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.web.util.ApplicationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author garden
 * @date 2019-09-29 14:24
 */
// @Component
public class DictionaryContext implements IApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(DictionaryContext.class);
    /**
     * 缓存数据
     */
    private static Map<String, TreeNode> cacheMap = new HashMap<>();
    /**
     * key数据缓存
     */
    private static Map<String, String> valueMap = new HashMap<>();

    /**
     * 刷新缓存
     */
    public static void refresh() {
        try {
            cacheMap.clear();
            valueMap.clear();
            IDictionaryService dictionaryService = ApplicationUtil.getBeans(IDictionaryService.class);
            if (null != dictionaryService) {
                Dictionary paramDictionary = new Dictionary();
                paramDictionary.asOrderBy("SORTS", OrderByObj.ASC);
                List<Dictionary> list = dictionaryService.list(paramDictionary);
                if (CollectionUtil.isNotEmpty(list)) {
                    // root
                    for (Dictionary dictionary : list) {
                        if (StringUtils.isEmpty(dictionary.getParentId())) {
                            cacheMap.put(dictionary.getCode(), new TreeNode(dictionary.getId(), dictionary.getCode(), dictionary.getValue()));
                        }
                    }
                    // child
                    if (!cacheMap.isEmpty()) {
                        for (Map.Entry<String, TreeNode> nodeEntry : cacheMap.entrySet()) {
                            setChild(nodeEntry.getValue(), list);
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void setChild(TreeNode treeNode, List<Dictionary> list) {
        List<Dictionary> child = getChild(treeNode.getId(), list);
        if (CollectionUtil.isNotEmpty(child)) {
            Map<String, TreeNode> treeNodeMap = treeNode.getTreeNodeMap();
            if (null == treeNodeMap) {
                treeNodeMap = new HashMap<>();
            }
            for (Dictionary dictionary : child) {
                TreeNode value = new TreeNode(dictionary.getId(), dictionary.getCode(), dictionary.getValue());
                setChild(value, list);
                treeNodeMap.put(dictionary.getCode(), value);

            }
            treeNode.setTreeNodeMap(treeNodeMap);
        }
    }

    private static List<Dictionary> getChild(String parentId, List<Dictionary> list) {
        List<Dictionary> child = new ArrayList<>();
        for (Dictionary dictionary : list) {
            if (parentId.equals(dictionary.getParentId())) {
                child.add(dictionary);
            }
        }
        return child;
    }

    /**
     * 获取值
     *
     * @param key key
     * @return string
     */
    public static String getValue(String key) {
        if (valueMap.containsKey(key)) {
            return valueMap.get(key);
        }
        String[] keys = key.split(".");
        String value = getValue(cacheMap, keys);
        valueMap.put(key, value);
        return value;
    }

    private static String getValue(Map<String, TreeNode> dataMap, String[] keys) {
        String value = null;
        String key = null;
        TreeNode currentNode = null;
        for (String key1 : keys) {
            key = key1;
            if (null == currentNode) {
                if (dataMap.containsKey(key)) {
                    currentNode = dataMap.get(key);
                }
            } else {
                Map<String, TreeNode> treeNodeMap = currentNode.getTreeNodeMap();
                if (null != treeNodeMap && treeNodeMap.containsKey(key)) {
                    currentNode = treeNodeMap.get(key);
                } else {
                    break;
                }
            }
        }
        if (null != currentNode && currentNode.getCode().equals(key)) {
            return currentNode.getValue();
        }
        return value;
    }

    @Override
    public void run() {
        refresh();
    }

    public static void main(String[] args) {
        List<Dictionary> list = new ArrayList<>();
        list.add(newDictionary("1","root", "", ""));
        list.add(newDictionary("2","config", "", "1"));
        list.add(newDictionary("3","name", "test", "2"));
        list.add(newDictionary("4","app", "", ""));
        list.add(newDictionary("5","login", "", "4"));
        list.add(newDictionary("6","failCount", "3", "5"));

        // root
        for (Dictionary dictionary : list) {
            if (StringUtils.isEmpty(dictionary.getParentId())) {
                cacheMap.put(dictionary.getCode(), new TreeNode(dictionary.getId(), dictionary.getCode(), dictionary.getValue()));
            }
        }
        // child
        if (!cacheMap.isEmpty()) {
            for (Map.Entry<String, TreeNode> nodeEntry : cacheMap.entrySet()) {
                setChild(nodeEntry.getValue(), list);
            }
        }

        System.out.println(getValue("root.config.name"));
        System.out.println(getValue("app.login.failCount"));
    }

    private static Dictionary newDictionary(String id, String code, String value, String parentId) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(id);
        dictionary.setCode(code);
        dictionary.setValue(value);
        dictionary.setParentId(parentId);
        return dictionary;
    }
}
