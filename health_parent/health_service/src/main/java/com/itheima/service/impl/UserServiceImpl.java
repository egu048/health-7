package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户认证与授权
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        // 查询用户信息，同时查询出用户所拥有的角色及权限
        return userDao.findByUsername(username);
    }
    /**
     * 获取所有用户
     * @return
     * @param queryPageBean
     */
    @Override
    public PageResult<User> findPage(QueryPageBean queryPageBean) {
        String queryString = queryPageBean.getQueryString();
        if (!StringUtils.isEmpty(queryString)){
            queryString="%"+queryString+"%";
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<User> page=userDao.findByCondition(queryString);
        return new PageResult<User>(page.getTotal(),page.getResult());
    }
    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    @Override
    public void add(User user, Integer[] roleIds) {
        userDao.add(user);
        Integer userId = user.getId();
        if (roleIds!=null && roleIds.length>0){
            for (Integer roleId : roleIds) {
                userDao.addUserAndRole(userId,roleId);
            }
        }
    }
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
    /**
     * 根据用户id获取角色ids
     * @return
     */
    @Override
    public List<Integer> findroleIdsByUserId(Integer id) {
        return userDao.findroleIdsByUserId(id);
    }
    /**
     * 修改用户信息
     * @param user
     * @param roleIds
     * @return
     */
    @Override
    @Transactional
    public void update(User user, Integer[] roleIds) {
        userDao.update(user);
        Integer userId = user.getId();
        userDao.deleteUserAndRoleByUserId(userId);
        if (roleIds!=null && roleIds.length>0){
            for (Integer roleId : roleIds) {
                userDao.addUserAndRole(userId,roleId);
            }
        }
    }
    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        userDao.deleteUserAndRoleByUserId(id);
        userDao.deletById(id);
    }
}
