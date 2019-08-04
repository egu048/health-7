package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    //新增角色
    void add(Role role);
//
    void addRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    void addRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    Page<Role> findByCondition(String queryString);

    Role findById(int id);


    void update(Role role);

    void deleteRolePermissionByRoleId(Integer roleId);

    List<Role> findPermission();

    List<Role> findMenu();

    void deleteRoleMenuByRoleId(Integer roleId);

    List<Integer> findMenuIdsByRoleId(int roleId);

    List<Integer> findPermissionIdsByRoleId(int roleId);

    void deleteRole(int id);

    List<Role> findAllToUser();

}
