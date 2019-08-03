package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashSet;
import java.util.List;

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

    LinkedHashSet<Menu> getMenuByRoleId(@Param("id") int id, @Param("level") int level);

    List<Menu> findAll();

}
