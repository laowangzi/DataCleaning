package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/Pos")
public class PosHandler {
    @Autowired
    private HdfsUtils utils;

    @GetMapping("/FindPos")
    public List<Pos> FindPos() {
        try {
            return utils.readFileRedis("hdfs://localhost:9000//user/wcinput/CleanData.csv",2);

        }catch (Exception e)
        {
            LinkedList p=new LinkedList();
            return p;
        }
    }
    @GetMapping("/hello")
    @ResponseBody
    public String hello()
    {
        return "hello";
    }


    }
