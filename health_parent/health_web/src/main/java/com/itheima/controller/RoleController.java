package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private RoleService roleService;

    /**
     * 查询所有角色信息分页展示
     * @param pageBean
     * @return
     */
    @RequestMapping("/findAll")
    public PageResult findAll(@RequestBody QueryPageBean pageBean) {
        PageResult<Role> pageResult = roleService.findPage(pageBean);
        return pageResult;
    }

    /**
     * 新建角色时提交信息
     * @param role
     * @param permissionIds
     * @param menuIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Role role, Integer[] permissionIds,Integer[] menuIds){
        // 调用套餐业务服务
        roleService.add(role, permissionIds,menuIds);
        return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
    }

    /**
     * 查询所有权限与菜单信息
     * @return
     */
    @GetMapping("/findMenuAndPermission")
    public Result findMenuAndPermission(){
        List<Role> menu = roleService.findMenu();
        List<Role> permission = roleService.findPermission();
        Map<String,List<Role>> map = new HashMap<>();
        map.put("permission",permission);
        map.put("menu",menu);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,map);
    }

    /**
     * 根据角色id查询所有权限和菜单id
     * @param id
     * @return
     */
    @GetMapping("/findRoleIdByAll")
    public Result findRoleIdByAll(int id){
        Map<String,List<Integer>> map = roleService.findPermissionIdsMenuIdsByRoleId(id);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,map);
    }

    /**
     * 修改角色信息
     * @param role
     * @param permissionIds
     * @param menuIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Role role, Integer[] permissionIds,Integer[] menuIds){
        // 调用套餐业务服务
        roleService.update(role, permissionIds,menuIds);
        return new Result(true, MessageConstant.UPDATE_ROLE_SUCCESS);
    }

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(int id) {
        roleService.deleteRole(id);
        return new Result(true,MessageConstant.DELETE_ROLE_SUCCESS);
    }
    /**
     * 获取所有角色信息
     * @return
     */
    @GetMapping("/findAllToUser")
    public Result findAllToUser(){
        List<Role> roles=roleService.findAllToUser();
        return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,roles);
    }
    @PostMapping("/findRoleById")
    public Result findRoleById(Integer id){
        Role role = roleService.findById(id);
        return new Result(true,MessageConstant.GET_USER_SUCCESS,role);
    }
}
