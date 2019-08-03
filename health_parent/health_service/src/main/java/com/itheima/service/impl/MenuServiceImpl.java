package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;


@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //判断数据是否为空
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            //不为空拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //调用Dao层的方法.进行分页
       Page<Menu> pageMenu = menuDao.findPageByMenu(queryPageBean.getQueryString());
        return new PageResult(pageMenu.getTotal(),pageMenu.getResult());
    }

    @Override
    public void add(Menu menu) {
       menuDao.add(menu);
    }

    @Override
    public Menu findById(int id) {

        return  menuDao.findById(id);
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }

    @Override
    public void delete(int id) {
        menuDao.delete(id);
    }

    @Override
    public LinkedHashSet<Menu> getMenuByRoleId(int id) {
        LinkedHashSet<Menu> menuone = menuDao.getMenuByRoleId(id,1);
        LinkedHashSet<Menu> menutwo = menuDao.getMenuByRoleId(id,2);
        for (Menu menuo : menuone) {
            for (Menu menut : menutwo) {
                if (menut.getParentMenuId().equals(menuo.getId())){
                    List<Menu> children = menuo.getChildren();
                    children.add(menut);
                }
            }
        }
        return menuone;
    }
    /**
     * 获取所有菜单信息
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }
}
