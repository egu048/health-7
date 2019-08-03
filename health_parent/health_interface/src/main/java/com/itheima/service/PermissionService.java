package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.HealthException;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 添加权限
     * @param permission
     */
    void add(Permission permission);

    /**
     * 权限分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<Permission> findPage(QueryPageBean queryPageBean);

    /**
     * 通过编号删除权限
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 通过编号查询权限
     * @param id
     * @return
     */
    Permission findById(int id);

    /**
     * 修改权限
     * @param permission
     */
    void update(Permission permission);

    /**
     * // 查询所有权限，供页面中的列表选择
     * @return
     */
    List<Permission> findAll();
}
