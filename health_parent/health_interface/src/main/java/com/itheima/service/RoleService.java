package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 新增角色
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    void add(Role role, Integer[] permissionIds, Integer[] menuIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询角色信息
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 通过角色id查询选中的权限编号
     * @param roleId
     * @return
     */
    Map<String,List<Integer>> findPermissionIdsMenuIdsByRoleId(int roleId);

    /**
     * 更新角色信息
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    void update(Role role, Integer[] permissionIds, Integer[] menuIds);

    /**
     * 查询所有的权限信息
     * @return
     */
    List<Role> findPermission();

    /**
     * 查询所有的菜单信息
     * @return
     */
    List<Role> findMenu();

    void deleteRole(int id);
    /**
     * 获取所有角色信息
     * @return
     */
    List<Role> findAllToUser();

}
