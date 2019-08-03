package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;
    @GetMapping("/getUser")
    public Result getUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        //request.getSession().setAttribute("user", user);
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user);
    }
    /**
     * 根据用户名获取用户信息
     * @return
     */
    @GetMapping("/getLoginUsername")
    public Result getLoginUsername(){
        // SecurityContextHolder, 存入上下文的内容，
        // getAuthentication.getPrincipal() 获取认证信息userDetail
        //getAuthentication().getName(); 获取登陆的用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = userDetails.getUsername();
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
    }

    /**
     * 获取所有用户
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<User> result=userService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_USER_SUCCESS,result);
    }

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user,Integer[] roleIds){
        userService.add(user,roleIds);
        return new Result(true,MessageConstant.ADD_USER_SUCCESS);

    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(Integer id){
        User user=userService.findById(id);
        return new Result(true,MessageConstant.GET_USER_SUCCESS,user);
    }

    /**
     * 修改用户信息
     * @param user
     * @param roleIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user,Integer[] roleIds){
        userService.update(user,roleIds);
        return new Result(true,MessageConstant.ADD_USER_SUCCESS);

    }

    /**
     * 根据用户id获取角色ids
     * @return
     */
    @GetMapping("/findroleIdsByUserId")
    public Result findroleIdsByUserId(Integer id){
        List<Integer> roleIds=userService.findroleIdsByUserId(id);
        return new Result(true,MessageConstant.GET_USER_SUCCESS,roleIds);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        userService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
    }
}
