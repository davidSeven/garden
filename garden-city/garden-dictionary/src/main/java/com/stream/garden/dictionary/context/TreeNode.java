package com.stream.garden.dictionary.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author garden
 * @date 2019-09-29 14:47
 */
@Data
@NoArgsConstructor
public class TreeNode {
    private String id;
    private String code;
    private String value;
    private Map<String, TreeNode> treeNodeMap;

    public TreeNode(String id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }
}
