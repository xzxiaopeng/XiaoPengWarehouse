package com.example.xiaopengwarehouse.XiaoPengData.Data;

import org.litepal.crud.LitePalSupport;

public class StockDay extends LitePalSupport {

    private int id;
    public  int getId()       { return id; }
    public void setId(int id) { this.id = id; }


    //名字
    private String name;
    public  String getName()            { return name; }
    public  void   setName(String name) { this.name = name; }


    //出纳
    private String cashier;
    public  String getCashier()            { return cashier; }
    public  void   setCashier(String cashier) { this.cashier = cashier; }

    //数量
    private int number;
    public  int getNumber()       { return number; }
    public void setNumber(int number) { this.number = number; }

    //分类
    private String nameclass;
    public  String getNameclass()            { return nameclass; }
    public  void   setNameclass(String nameclass) { this.nameclass = nameclass; }

    //时间
    private int time;
    public  int getTime()       { return time; }
    public void setTime(int time) { this.time = time; }

    //时间
    private int times;
    public  int getTimes()       { return times; }
    public void setTimes(int times) { this.times = times; }

}

