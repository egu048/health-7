package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;

public interface MenuDao {

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Menu> findPageByMenu(String queryString);

    void add(Menu menu);

    Menu findById(int id);

    void update(Menu menu);

    void delete(int id);
}
