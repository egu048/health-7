package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

public interface MenuService {

    PageResult findPage(QueryPageBean queryPageBean);

   void add(Menu menu);

    Menu findById(int id);

    void update(Menu menu);

    void delete(int id);
}
