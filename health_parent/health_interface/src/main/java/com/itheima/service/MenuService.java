package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

import java.util.LinkedHashSet;
import java.util.List;

public interface MenuService {

    PageResult findPage(QueryPageBean queryPageBean);

   void add(Menu menu);

    Menu findById(int id);

    void update(Menu menu);

    void delete(int id);

    LinkedHashSet<Menu> getMenuByRoleId(int id);
    /**
     * 获取所有菜单信息
     * @return
     */
    List<Menu> findAll();
}
