package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 通过手机号码查询会员
     * @param telephone
     * @return
     */
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    /**
     * 添加会员
     * @param member
     */
    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    /**
     * 会员数量统计
     * @return
     * @param
     */
    @Override
    public Map<String, Object> getMemberReport(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar beginCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Date begin = null;
        Date end = null;
        try {
            begin = sdf.parse(startDate);
            beginCal.setTime(begin);
            end=sdf.parse(endDate);
            endCal.setTime(end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int monthsCount=(endCal.get(Calendar.YEAR)-beginCal.get(Calendar.YEAR))*12+(endCal.get(Calendar.MONTH)-beginCal.get(Calendar.MONTH))+1;
        String month = startDate;
        List<String> months = new ArrayList<String>();
        List<Integer> memberCount = new ArrayList<Integer>();
        for (int i = 0; i < monthsCount; i++) {
            // 3. 封装到月份数所 months
            months.add(month);
            // 4. 封装每个月的会员总数到memberCount
            memberCount.add(memberDao.findMemberCountBeforeDate(month + "-31"));
            beginCal.add(Calendar.MONTH,1);
            month = sdf.format(beginCal.getTime());
        }
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("months", months);
        result.put("memberCount",memberCount);
        return result;
    }

    /**
     * 会员男女占比
     * @return
     */
    @Override
    public  List<Map<String,Object>> getPackageReportBySex() {
        //获取sexCount,集合
        List<Map<String,Object>> sexCount = memberDao.getPackageReportBySex();

        return sexCount;
    }


    /**
     * 会员年龄占比
     * @return
     */
    @Override
    public List<String> findBirthdays() {
        //查询所有会员的生日集合
        List<String> birthdays =  memberDao.findBirthdays();
        return birthdays;
    }
}
