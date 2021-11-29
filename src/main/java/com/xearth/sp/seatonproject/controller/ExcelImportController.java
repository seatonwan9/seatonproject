package com.xearth.sp.seatonproject.controller;

import com.alibaba.excel.EasyExcel;
import com.xearth.sp.seatonproject.pojo.Staff;
import com.xearth.sp.seatonproject.result.ResultData;
import com.xearth.sp.seatonproject.service.StaffService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName ExcelImportController
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:45
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/excel")
@AllArgsConstructor
@Api(value = "数据导入", tags = "数据导入--测试接口")
public class ExcelImportController {

    @Autowired
    StaffService staffService;

    @PostMapping("/import")
// @RequiresPermissions("staff:import")
    public ResultData importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 处理上传文件
        if (file.isEmpty()) {
            return ResultData.fail(1,"上传失败，请选择文件");
        }

        // 流导入
        InputStream inputStream = file.getInputStream();
        // 监听器
        ExcelListener listener = new ExcelListener(staffService);
        // 读取excel
        EasyExcel.read(inputStream, Staff.class, listener).sheet().doRead();

        // 查看是否有异常
        List<Map<String, String>> maps = listener.getMaps();
        String temp = "";
        if (maps.size() > 0) {
            for (Map<String, String> map : maps) {
                temp += map.get("msg")+"\n";
            }
        }

        // 返回信息
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("success",listener.getList().size()+"");
        hashMap.put("error",temp);

        return ResultData.data(hashMap);
    }

    @GetMapping("/test1")
    public ResultData test() {
        List<Staff> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Staff staff = new Staff();
            staff.setName("wxd" + i);
            list.add(staff);
        }
        staffService.emInsertBatch(list);
        return ResultData.success("ok");
    }

    @GetMapping("/test2")
    public ResultData jdbcBatch() {
        List<Staff> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Staff staff = new Staff();
            staff.setName("wxd");
            staff.setMobile("15101662208");
            list.add(staff);
        }
        staffService.jdbcInsertBatch(list);
        return ResultData.success("ok");
    }
}
