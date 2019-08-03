package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 查询用户信息，同时查询出用户所拥有的角色及权限
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据条件获取用户
     * @param queryString
     * @return
     */
    Page<User> findByCondition(String queryString);

    /**
     * 在user表添加用户
     * @param user
     */
    void add(User user);

    /**
     * 在中间表添加用户和角色的关系
     * @param userId
     * @param roleId
     */
    void addUserAndRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
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
     * @param
     * @return
     */
    void update(User user);

    /**
     * 根据用户id删除与角色的关系
     * @param userId
     */
    void deleteUserAndRoleByUserId(Integer userId);
}
