package com.example.xiaopengwarehouse.XiaoPengDaily;

public class Daily {

    private String nume;
    private int time;
    private int times;
    private int number;
    //出纳
    private String cashier;
    //分类
    private String nameclass;

    public Daily(String nume,int time ,int times,int number,String cashier,String nameclass) {
        this.nume = nume;
        this.time = time;
        this.times = times;
        this.number = number;
        this.cashier = cashier;
        this.nameclass = nameclass;
    }


    public  String getNameclass()            { return nameclass; }

    public  String getCashier()            { return cashier; }

    //数量

    public  int getNumber()       { return number; }


    public  int getTime()       { return time; }

    public  int getTimes()       { return times; }

    public String getNume() {
        return nume;
    }
}
