package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;

import java.util.List;

public interface PermissionDao {
    void add(Permission permission);

    Page<Permission> findByCondition(String queryString);

    void deleteById(int id);

    Permission findById(int id);

    void update(Permission permission);

    List<Permission> findAll();
}
