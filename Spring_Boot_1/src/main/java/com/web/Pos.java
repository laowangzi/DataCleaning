package com.web;

import lombok.Data;

@Data
public class Pos {
    private String lnt,lag;
    public String id,time;
    Pos(){}
    public void setLat(String lat){
        this.lnt=lat;
    }
    public void setLng(String lng){
        this.lag=lng;
    }
    public String getLat(){
        return this.lnt;
    }
    public String getLng(){
        return this.lag;
    }
}
