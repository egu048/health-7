package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private PermissionService permissionService;

    @PostMapping("/add")
    public Result add(@RequestBody Permission permission) {
        // 调用业务服务
        permissionService.add(permission);
        // 返回结果给前端
        return new Result(true, MessageConstant.ADD_PERMISSION_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用业务服务查询
        PageResult<Permission> pageResult = permissionService.findPage(queryPageBean);
        // 返回给浏览器
        return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS, pageResult);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        permissionService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_PERMISSION_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(int id) {
        Permission permission = permissionService.findById(id);
        return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS, permission);
    }

    @PostMapping("/update")
    //@RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result update(@RequestBody Permission permission) {
        // 调用业务服务更新
        permissionService.update(permission);
        // 返回结果给前端
        return new Result(true, MessageConstant.EDIT_PERMISSION_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        // 查询所有检查项，供页面中的列表选择
        List<Permission> list = permissionService.findAll();
        return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,list);
    }
}
