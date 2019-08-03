package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 用户认证与授权
     * @param username
     * @return
     */
    User findByUsername(String username);
    /**
     * 获取所有用户
     * @return
     * @param queryPageBean
     */
    PageResult<User> findPage(QueryPageBean queryPageBean);
    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    void add(User user, Integer[] roleIds);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User findById(Integer id);
    /**
     * 根据用户id获取角色ids
     * @return
     */
    List<Integer> findroleIdsByUserId(Integer id);
    /**
     * 修改用户信息
     * @param user
     * @param roleIds
     * @return
     */
    void update(User user, Integer[] roleIds);
}
