package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.pojo.Risk;
import com.xearth.sp.seatonproject.pojo.RiskDetails;
import com.xearth.sp.seatonproject.pojo.RiskMap;
import com.xearth.sp.seatonproject.service.RiskDetailsService;
import com.xearth.sp.seatonproject.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wangxudong
 * @date 2020/3/09 09:37
 */
@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    RiskService riskService;
    @Autowired
    RiskDetailsService riskDetailsService;

    /**
     * 查询评估记录表
     * @param parentId
     * @return
     */
    @GetMapping(value = "/findAllByParentId")
    public List<Risk> findAllById(@RequestParam(required = false) String parentId){
        List list = new ArrayList();
        if(parentId == null || parentId == "") {
            list.add("Please input parentId!");
            return list;
        }else{
            if(riskService.findAllByParentId(parentId).size() != 0) {
                return riskService.findAllByParentId(parentId);
            }else{
                list.add("Record does not exist!");
            }
        }
        return list;
    }

    /**
     * 查询评估明细表
     * @param riskId
     * @return
     */
    @GetMapping(value = "/findAllByRiskId")
    public List<RiskDetails> findAllByRiskId(@RequestParam(required = false) String riskId){
        List list = new ArrayList();
        if(riskId == null || riskId == "") {
            list.add("Please input riskId!");
            return list;
        }else{
            if(riskDetailsService.findAllByRiskId(riskId).size() != 0) {
                return riskDetailsService.findAllByRiskId(riskId);
            }else{
                list.add("Record does not exist!");
            }
        }
        return list;
    }

    /**
     * 新增风险评估记录和子表风险详情记录
     * @param riskMap
     */
    @PostMapping(value = "/insertRiskAndDetails")
    public String insertRiskAndDetails(@RequestBody RiskMap riskMap){
        String riskId = UUID.randomUUID().toString();
        riskMap.risk.setId(riskId);
        riskMap.risk.setDatetime(new Date());
        riskService.save(riskMap.risk);

        List<RiskDetails> list = riskMap.riskDetails;
        for (int i = 0; i < list.size(); i++) {
            String riskDetailsId = UUID.randomUUID().toString();
            list.get(i).setId(riskDetailsId);
            list.get(i).setRiskId(riskId);
            if(list.get(i).getScore() == 10) {
                list.get(i).setIsFull(0);
            }else{
                list.get(i).setIsFull(1);
            }
            list.get(i).setDatetime(new Date());
            riskDetailsService.save(list.get(i));
        }

        return "success";
    }

    /**
     * 修改风险评估记录和子表风险详情记录
     * @param riskMap
     */
    @PostMapping(value = "/updateRiskAndDetails")
    public String updateRiskAndDetails(@RequestBody RiskMap riskMap){
        String riskDesc = riskMap.risk.getRiskDesc();
        Integer score = riskMap.risk.getScore();
        Date date = new Date();
        String id = riskMap.risk.getId();
        riskService.updateRiskById(riskDesc, score, date, id);

        List<RiskDetails> list = riskMap.riskDetails;
        if(list != null){
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getScore() == 10) {
                    list.get(i).setIsFull(0);
                }else{
                    list.get(i).setIsFull(1);
                }
                list.get(i).setDatetime(new Date());
            riskDetailsService.save(list.get(i));
            }
        }

        return "Success";
    }

    /**
     * 删除风险评估记录和子表风险详情记录
     * @param parentId
     * @return
     */
    @GetMapping(value = "/deleteRiskAndDetails")
    public String deleteRiskAndDetails(@RequestParam(required = false) String parentId){
        List<Risk> list =  riskService.findAllByParentId(parentId);
        if(list.size() != 0){
            for (int i = 0; i < list.size(); i++) {
                riskService.deleteById(list.get(i).getId());
                riskDetailsService.deleteByRiskId(list.get(i).getId());
            }
        }else{
            return "The record has been deleted!";
        }
        return "Success";
    }

}
