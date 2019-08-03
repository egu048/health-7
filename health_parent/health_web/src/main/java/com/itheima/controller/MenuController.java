package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

   @PostMapping("/add")
   public Result add(@RequestBody Menu menu){
      menuService.add(menu);
       return new Result(true,MessageConstant.GET_MENU_SUCCESS);
   }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用业务服务查询
        PageResult pageResult =menuService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }
    @GetMapping("/findById")
    public Result findById(int id){
       Menu menu = menuService.findById(id);
        return new Result(true,MessageConstant.GET_MENU_SUCCESS,menu);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu){
       menuService.update(menu);
       return new Result(true,MessageConstant.GET_MENU_SUCCESS);
    }
    @PostMapping("/delete")
    public Result delete(int id){
       menuService.delete(id);
       return new Result(true,MessageConstant.DELETE_MENU_SUCCESS);
    }
}
