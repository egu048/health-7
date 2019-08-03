package com.itheima.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.RedisConstant;
import com.itheima.service.OrderSettingService;
import com.itheima.util.DateUtils;
import com.itheima.util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Set;

@Component("cleanImgJob")
public class CleanImgJob {

    @Autowired
    private OrderSettingService orderSettingService;

    public void doJob(){
        // 统计的日期，今天
        String today = DateUtils.parseDate2String(new Date(), "yyyy-MM-dd");
        // 本月1号
        String firstDayOfThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDayOfThisMonth(), "yyyy-MM-dd");
        //删除本月之前的数据
        orderSettingService.clearBeforeThisMonth(firstDayOfThisMonth);
    }
}
