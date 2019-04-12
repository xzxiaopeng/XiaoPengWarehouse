package com.example.xiaopengwarehouse.XiaoPengData.Data;

import org.litepal.crud.LitePalSupport;

public class StockClass extends LitePalSupport {

    private int id;
    public  int getId()       { return id; }
    public void setId(int id) { this.id = id; }

    //元素
    private String element;
    public  String getElement()            { return element; }
    public  void   setElement(String element) { this.element = element; }

}

