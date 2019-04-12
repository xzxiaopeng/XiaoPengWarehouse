package com.example.xiaopengwarehouse.XiaoPengData.Data;

import android.graphics.Bitmap;

import org.litepal.crud.LitePalSupport;

public class Stock extends LitePalSupport {

    private int id;
    public  int getId()       { return id; }
    public void setId(int id) { this.id = id; }


    //名字
    private String name;
    public  String getName()            { return name; }
    public  void   setName(String name) { this.name = name; }

    //分类
    private String nameclass;
    public  String getNameclass()            { return nameclass; }
    public  void   setNameclass(String nameclass) { this.nameclass = nameclass; }

    //成本
    private float cost;
    public  float getCost()       { return cost; }
    public void setCost(float cost) { this.cost = cost; }

    //元素
    private String element;
    public  String getElement()            { return element; }
    public  void   setElement(String element) { this.element = element; }


    //显示
    private boolean display;
    public  boolean getDisplay()            { return display; }
    public  void   setDisplay(boolean display) { this.display = display; }

    //数量
    private int number;
    public  int getNumber()       { return number; }
    public void setNumber(int number) { this.number = number; }

    //图片
    private int imageId;
    public  int getImageId()       { return imageId; }
    public void setImageId(int imageId) { this.imageId = imageId; }

    private String imageIds;
    public  String getImageIds()       { return imageIds; }
    public void setImageIds(String imageIds) { this.imageIds = imageIds; }

    //件制单位乘制
    private int univalent;
    public  int getUnivalent()       { return univalent; }
    public void setUnivalent(int univalent) { this.univalent = univalent; }

    //单位
    private String unit;
    public  String getUnit()            { return unit; }
    public  void   setUnit(String unit) { this.unit = unit; }

    //件制单位
    private String units;
    public  String getUnits()            { return units; }
    public  void   setUnits(String units) { this.units = units; }

}
