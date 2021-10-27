package com.xearth.sp.seatonproject.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xearth.sp.seatonproject.pojo.Staff;
import com.xearth.sp.seatonproject.service.StaffService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:28
 * @Version 1.0
 **/
public class ExcelListener extends AnalysisEventListener<Staff> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelListener.class);

    /**
     * 存储验证通过的数据
     */
    @Getter
    private List<Staff> list = new ArrayList<>();

    private StaffService staffService;

    /**
     * 返回错误的集合
     */
    @Setter
    @Getter
    private List<Map<String,String>> maps = new ArrayList<>();


    private Map<String,String> map = new HashMap<>();

    /**
     * 头部占用的行数
     */
    @Setter
    public Integer headerInteger = 1;

    /**
     * 无参构造
     */
    public ExcelListener() {}

    /**
     * 有参构造
     * @param staffService
     */
    public ExcelListener(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * 每一条数据解析都会过滤判断
     * @param data
     * one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Staff data, AnalysisContext context) {
        // 开始计数,当前是多少行
        headerInteger++;
        if (data.getName() == null || data.getName().equals("")) {
            map.put("code","400");
            map.put("msg","第["+headerInteger+"]行,员工姓名不能为空");
            maps.add(map);
            return;
        }

        if (data.getNumber() == null || data.getNumber().equals("")) {
            map.put("code","400");
            map.put("msg","第["+headerInteger+"]行,员工工号不能为空");
            maps.add(map);
            return;
        }

        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成后调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // 此处可用于重新定义需要录入的数据
        List<Staff> staffList = new ArrayList<>();
        for (Staff s : list) {
            Staff staff = new Staff();
            staff.setName(s.getName());
            staff.setNumber(s.getNumber());
            staff.setTitle(s.getTitle());
            staff.setMobile(s.getMobile());
            staffList.add(staff);
        }
        staffService.saveBatch(staffList);
        LOGGER.info("存储数据库成功！");
    }
}
