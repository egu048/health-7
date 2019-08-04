package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    /**
     * 通过手机号码查询会员
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 添加会员
     * @param member
     */
    void add(Member member);

    /**
     * 会员数量统计
     * @return
     * @param
     */
    Map<String,Object> getMemberReport(String startDate, String endDate);

    /**
     * 会员男女占比
     * @return
     */
    List<Map<String,Object>> getPackageReportBySex();


    /**
     * 会员年龄占比
     * @return
     */
    List<String> findBirthdays();
}
