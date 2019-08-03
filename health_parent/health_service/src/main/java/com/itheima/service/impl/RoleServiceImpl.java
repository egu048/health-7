package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 新增角色
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    @Override
    @Transactional
    public void add(Role role, Integer[] permissionIds, Integer[] menuIds) {
        // 新增角色
        roleDao.add(role);
        // 获取角色的编号
        Integer roleId = role.getId();
        // 遍历权限的ID集合，循环插入
        if(null != permissionIds){
            for (Integer permissionId : permissionIds) {
                roleDao.addRolePermission(roleId, permissionId);
            }
        }
        // 遍历菜单的ID集合，循环插入
        if(null != menuIds){
            for (Integer menuId : menuIds) {
                roleDao.addRoleMenu(roleId, menuIds);
            }
        }
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        // 模糊查询，拼接%%
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 使用pageHelper
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页
        Page<Role> page = roleDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Role>(page.getTotal(),page.getResult());
    }

    /**
     * 通过id查询角色信息
     * @param id
     * @return
     */
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    /**
     * 通过角色id查询选中的权限和菜单编号
     * @param roleId
     * @return
     */
    @Override
    public Map<String,List<Integer>> findPermissionIdsMenuIdsByRoleId(int roleId) {
        List<Integer> menuIds = roleDao.findMenuIdsByRoleId(roleId);
        List<Integer> permissionsIds =roleDao.findPermissionIdsByRoleId(roleId);
        Map<String,List<Integer>> map = new HashMap<>();
        map.put("menuIds",menuIds);
        map.put("permissionIds",permissionsIds);
        return map;
    }

    /**
     * 更新角色信息
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    @Override
    @Transactional
    public void update(Role role, Integer[] permissionIds, Integer[] menuIds) {
        // 更新角色信息
        roleDao.update(role);
        // 获得角色的编号
        Integer roleId = role.getId();
        // 维护关系，先删除后添加
        //删除角色,权限关系表中对应数据
        roleDao.deleteRolePermissionByRoleId(roleId);
        //删除角色,菜单关系表中对应数据
        roleDao.deleteRoleMenuByRoleId(roleId);
        // 再添加关系
        if(null != permissionIds){
            // 循环添加新关系
            for (Integer permissionId : permissionIds) {
                roleDao.addRolePermission(roleId, permissionId);
            }
        }

        if(null != menuIds){
            // 循环添加新关系
            for (Integer menuId : menuIds) {
                roleDao.addRolePermission(roleId, menuId);
            }
        }
    }

    /**
     * 查询所有的权限信息
     * @return
     */
    @Override
    public List<Role> findPermission() {
        return roleDao.findPermission();
    }

    /**
     * 查询所有的菜单信息
     * @return
     */
    @Override
    public List<Role> findMenu() {
        return roleDao.findMenu();
    }

    @Override
    public void deleteRole(int id) {
        roleDao.deleteRole(id);
    }
}
