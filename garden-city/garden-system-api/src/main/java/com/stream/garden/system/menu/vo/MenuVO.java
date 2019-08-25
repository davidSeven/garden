package com.stream.garden.system.menu.vo;

import com.stream.garden.system.menu.model.Menu;

import java.util.List;

/**
 * @author garden
 * @date 2019-06-28 17:17
 */
public class MenuVO extends Menu {
    private static final long serialVersionUID = 7515218615352151554L;

    private List<MenuVO> children;

    public MenuVO() {
    }

    public MenuVO(Menu menu) {
        super.setId(menu.getId());
        super.setName(menu.getName());
        super.setParentId(menu.getParentId());

        super.setPath(menu.getPath());
        super.setIcon(menu.getIcon());
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

}
