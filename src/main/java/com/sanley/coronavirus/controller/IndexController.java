package com.sanley.coronavirus.controller;

import com.sanley.coronavirus.entity.IndexInfo;
import com.sanley.coronavirus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.*;

@Controller
public class IndexController {
    @Autowired
    MyUserDetailService userDetailService;
    @Autowired
    PatientService patientService;
    @Autowired
    TouchService touchService;
    @Autowired
    CureService cureService;
    @Autowired
    DeadService deadService;

    @RequestMapping(value = {"/indexpage", "/" },produces="application/json;charset=UTF-8")
    public String index(Model model){
        //查找数量
        int pN =  patientService.number();
        int pCN = patientService.currentNumber();
        int cN = cureService.number();
        int dN = deadService.number();
        int tN = touchService.number();
        int tCN = touchService.currentNumber();
        //计算百分比
        double cR=(double)Math.round((new Double(cN)/new Double(pN))*100);
        double dR=(double)Math.round((new Double(dN)/new Double(pN))*100);
        //放入indexInfo中
        IndexInfo indexInfo=new IndexInfo().setCureNumber(cN).
                setCureRate(cR).setCurrentPatientNumber(pCN).setCurrentTouchNumber(tCN).
                setDeadNumber(dN).setSumTouchNumber(tN).setDeadRate(dR).setSumPatientNumber(pN);
        patientDatas(indexInfo);

       model.addAttribute("indexInfo",indexInfo);
        return "index";
    }
    public void patientDatas(IndexInfo indexInfo){
        List dates=new ArrayList();
        List patientNums=new ArrayList();
        List cureNums=new ArrayList();
        for (int i=4;i>=0;i--){
          Date date= new Date(System.currentTimeMillis()-(i*86400000));
          dates.add(date.getTime());
            Date da= new Date(System.currentTimeMillis()-((i-1)*86400000));
            patientNums.add(patientService.beforeDay(da));
          cureNums.add(cureService.beforeDay(da));
        }
        indexInfo.setDates(dates).setPatientNums(patientNums).setCureNums(cureNums);
        }

    }

