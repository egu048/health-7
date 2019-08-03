package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import com.itheima.util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Reference
    private PackageService packageService;

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/getPackage")
    public Result getPackage(){
        Jedis jedis = jedisPool.getResource();
        String str= jedis.get("list");
        if (str == null) {
            List<Package> list = packageService.findAll();
            list.forEach(pcg ->{
                pcg.setImg(QiNiuUtil.DOMAIN + "/" + pcg.getImg());
            });
            str =JSON.toJSONString(list);
            jedis.set("list",str);
            return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,list);
        }
        List<Package> packages = JSON.parseArray(str, Package.class);
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,packages);
    }
    @GetMapping("/getPackageDetail")
    public Result getPackageDetail(int id){
        Jedis jedis = jedisPool.getResource();
        String str2= jedis.get("list2");
        if (str2 == null) {
            //调用业务服务查询
            Package pcg = packageService.getPackageDetail(id);
            //拼接图片
            pcg.setImg(QiNiuUtil.DOMAIN + "/" +pcg.getImg());
            str2 = JSON.toJSONString(pcg);
            jedis.set("list2",str2);
            return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,pcg);
        }
        JSONObject jsonObject = JSON.parseObject(str2);
        String lists = jsonObject.getString("lists");
        return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,lists);
    }

    @GetMapping("/findById")
    public Result findById(int id){
        //调用业务服务
        Package pcg = packageService.findById(id);
        //拼接图片
        pcg.setImg(QiNiuUtil.DOMAIN + "/" +pcg.getImg());
        return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,pcg);
    }
}
