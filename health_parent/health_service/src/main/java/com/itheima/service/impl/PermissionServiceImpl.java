package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.dao.PermissionDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.exception.HealthException;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;
import com.itheima.service.CheckItemService;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 添加检查项
     *
     * @param permission
     */
    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Permission> findPage(QueryPageBean queryPageBean) {
        // 条件处理，实现的是模糊，
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            // 查询条件不为空，null ""
            // 拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 使用分页插件
        //第二种，Mapper接口方式的调用，推荐这种使用方式。
        // 第一个参数是页码，第二个参数是大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 紧接着的查询语句会被分页
        Page<Permission> page = permissionDao.findByCondition(queryPageBean.getQueryString());
        // page.getResult获取分页的结果集
        return new PageResult<Permission>(page.getTotal(), page.getResult());
    }

    /**
     * 通过编号删除检查项
     *
     * @param id
     */
    @Override
    public void deleteById(int id) throws HealthException{
        // 删除
        permissionDao.deleteById(id);
    }

    /**
     * 通过编号查询检查项
     * @param id
     * @return
     */
    @Override
    public Permission findById(int id) {
        return permissionDao.findById(id);
    }

    /**
     * 修改检查项
     * @param permission
     */
    @Override
    @Transactional
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    /**
     * // 查询所有检查项，供页面中的列表选择
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
