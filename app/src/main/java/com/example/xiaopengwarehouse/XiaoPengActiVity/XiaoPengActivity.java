package com.example.xiaopengwarehouse.XiaoPengActiVity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClass.FruitAdapter;
import com.example.xiaopengwarehouse.XiaoPengClassAdd.XiaoPengAddClassActivity;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.FruitAdapterAdd;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.FruitAdd;
import com.example.xiaopengwarehouse.XiaoPengDaily.Daily;
import com.example.xiaopengwarehouse.XiaoPengDaily.DailyAdapter;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockClass;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;
import com.example.xiaopengwarehouse.XiaoPengMenu.Class;
import com.example.xiaopengwarehouse.XiaoPengMenu.ClassAdapter;


import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class XiaoPengActivity extends AppCompatActivity {



    protected List<Daily> daiyList = new ArrayList<Daily>();
    protected List<Class> classList = new ArrayList<Class>();
    protected List<Class> classListdaily = new ArrayList<Class>();
    protected List<FruitAdd> addList = new ArrayList<FruitAdd>();
    protected List<Fruit> fruitList = new ArrayList<Fruit>();

    protected ClassAdapter adaptersdaily;
    protected ClassAdapter adapterss;

    protected LinearLayout linearLayoutclasssdaily;
    protected LinearLayout linearlayouttextviewsbutt;

//    protected String dailybutton = "日志";
//    protected String dailybuttons = "总览";


    protected  LinearLayout linearlayoutnumberadd;

    protected String classy;
    protected FruitAdapter adapter;

    protected CharSequence charSequence;

    protected float costdaiy;
    protected float salesvolumedaiy;

    protected float costtotal;
    protected float salesvolumetotal;

    protected LinearLayout linearLayout;
    protected LinearLayout LinearLayoutoverviewadd;

    protected String cashier = "全部";
    protected String dailyclsa;
    protected RecyclerView recyclerViewsdaily;


    protected TextView buttons;
    protected TextView button;

    protected TextView textviewsbutt;
    protected TextView dailytextviewout;
    protected TextView dailytextviewenter;
    protected TextView dailytextviewall;
    protected TextView textviewsscreens;

    protected DailyAdapter adapters;
    protected String classyadd;
    protected RecyclerView recyclerViews;


    protected TextView textviewscreen;

    protected LinearLayout aailyactivitylinearLayouts;
    protected LinearLayout aailyactivitylinearLayoutsinstall;
    protected LinearLayout linearlayoutbutall;
    //添加品类class窗口
    protected LinearLayout LinearLayoutviewclassadds;

    protected DatePicker datePicker;
    protected DatePicker datePickers;
    protected NumberPicker numberPicker;
    protected NumberPicker numberPickers;
    protected NumberPicker minutepicker;
    protected NumberPicker minutepickers;

    protected LinearLayout linearLayoutoverview;
    protected LinearLayout linearLayoutdaily;


    protected String sbi;
    protected String sbis;

    protected int newVals;
    protected int newVals2;
    protected int oldVals;
    protected int oldVals2;

    protected int minute;
    protected int oldminute;
    protected int minute2;
    protected int oldminute2;


    protected int dayOfMonths;
    protected int days;
    protected int days2;
    protected int years;
    protected int monthOfYears;
    protected int years2;
    protected int monthOfYears2;
    protected int dayOfMonths2;

    protected FruitAdapterAdd adapteradd;
    protected ClassAdapter adapterssaddclass;

    protected String[] thisPosition=new String[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        gettime();

        datePicker = (DatePicker) findViewById(R.id.date_picker);
        datePickers = (DatePicker) findViewById(R.id.date_pickers);
        button = (TextView) findViewById(R.id.daily_but);
        button.setBackgroundColor(Color.LTGRAY);
        buttons = (TextView) findViewById(R.id.overview_but);


        LitePal.getDatabase();
        setStatusBar(Color.TRANSPARENT);
//        register();
        initFruitclass();
        initFruitdaily();

        linearLayoutoverview = (LinearLayout) findViewById(R.id.LinearLayout_overview);
        linearLayoutdaily = (LinearLayout) findViewById(R.id.LinearLayout_daily);

        textviewsbutt = (TextView) findViewById(R.id.textviews_butt);
        textviewsbutt.setBackgroundColor(Color.LTGRAY);

        dailytextviewall = (TextView) findViewById(R.id.daily_textview_all);
        dailytextviewall.setBackgroundColor(Color.WHITE);

        dailytextviewenter = (TextView) findViewById(R.id.daily_textview_enter);
        dailytextviewenter.setBackgroundColor(Color.LTGRAY);

        dailytextviewout = (TextView) findViewById(R.id.daily_textview_out);
        dailytextviewout.setBackgroundColor(Color.LTGRAY);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout_class);
        linearLayout.setBackgroundColor(Color.LTGRAY);

        linearlayoutbutall = (LinearLayout) findViewById(R.id.linearlayout_but_all);

        linearlayouttextviewsbutt = (LinearLayout) findViewById(R.id.linearlayout_textviews_butt);

        LinearLayoutoverviewadd = (LinearLayout) findViewById(R.id.LinearLayout_overview_add);

        textviewsscreens = (TextView) findViewById(R.id.textviews_screens);

        LinearLayoutviewclassadds = (LinearLayout) findViewById(R.id.LinearLayout_view_class_adds);


        switchcall();



//        for(int i =0 ; i<10;i++){
//            getdatastock();
//        }

    }




    protected boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = true;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置

    protected void setStatusBar(int colors ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTheme));
            } else {
                getWindow().setStatusBarColor(colors);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    //分类刷新
    protected void initFruitclass() {

        while (classList.size() > 0 && adapterss != null) {
            try {
                adapterss.removeData(classList.size() - 1);
            } catch (Exception e) {

            }
        }

        List<StockClass> stockClasses = LitePal.findAll(StockClass.class);
        for (StockClass stockClass : stockClasses) {

                Class mango = new Class(stockClass.getElement());
                classList.add(mango);

        }
        if (classList.size() != 0) {
            Class mango = new Class("全部");
            classList.add(mango);
        }

    }


    protected void initFruitdaily() {

        while (classListdaily.size() > 0 && adaptersdaily  != null) {
            try {
                adaptersdaily.removeData(classListdaily.size() - 1);
            } catch (Exception e) {

            }
        }


            Class mango = new Class("全部");
            classListdaily.add(mango);

        List<StockClass> stockClassess = LitePal.findAll(StockClass.class);
        for (StockClass stockClass : stockClassess) {

            Class mangos = new Class(stockClass.getElement());
            classListdaily.add(mangos);

        }

    }


    private void register() {

//        LitePal.deleteAll(Stock.class);
//        LitePal.deleteAll(StockClass.class);
//        LitePal.deleteAll(StockDay.class);
//        datastock(new String[]{
//                "太魔性", R.drawable.apple_pic + "", "饮料", "1245", "2.22", "24", "瓶", "箱",
//                "威龙", R.drawable.orange_pic + "", "小吃", "222", "1.22", "30", "个", "件",
//                "水趣多", R.drawable.banana_pic + "", "饮料", "12458", "3.22", "24", "瓶", "箱",
//                "阿桑木阿桑木111111111111111", R.drawable.taimoxing + "", "饮料", "12425", "4.11111111", "15", "个", "件"
//        });
//        for (int i = 0; i < 0; i++) {
//            datastock(new String[]{
//                    "太魔性"+i, R.drawable.apple_pic + "", "饮料", "1245", "2.22", "24", "瓶", "箱",
//                    "威龙"+i, R.drawable.orange_pic + "", "小吃", "222", "1.22", "30", "个", "件",
//                    "水趣多"+i, R.drawable.banana_pic + "", "饮料", "12458", "3.22", "24", "瓶", "箱",
//                    i+"阿桑木阿桑木111111111111111", R.drawable.taimoxing + "", "饮料", "12425", "4.11111111", "15", "个", "件"
//            });
//        }
    }

    protected void datastock(String[] data) {

        DecimalFormat decimalFormat = new DecimalFormat(".0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        for (int j = 0; j < data.length; j++) {
            Stock stock = new Stock();
            stock.setName(data[j++]);
            stock.setImageId(Integer.parseInt(data[j++]));
            List<StockClass> stockClasses = LitePal.where("element == ?", data[j]).find(StockClass.class);
            if (0 == stockClasses.size()) {
                StockClass stockClass = new StockClass();
                stockClass.setElement(data[j]);

                stockClass.save();
            }
            stock.setNameclass(data[j++]);
            stock.setNumber(Integer.parseInt(data[j++]));
            stock.setCost(Float.parseFloat(decimalFormat.format(Float.parseFloat(data[j++]))));
            stock.setUnivalent(Integer.parseInt(data[j++]));
            stock.setUnit(data[j++]);
            stock.setUnits(data[j]);
            stock.save();
        }
    }

    protected void getdatastock() {

        Calendar c = Calendar.getInstance();


        StockDay stockDay = new StockDay();
        stockDay.setName("阿桑木阿桑木111111111111111");
        stockDay.setCashier("出库");
        stockDay.setNumber(1111111111);
        stockDay.setNameclass("饮料");
        stockDay.setTime(Integer.parseInt(String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))));
        stockDay.setTimes(Integer.parseInt(String.format("%02d%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))));
        stockDay.save();

        StockDay stockDays = new StockDay();
        stockDays.setName("水趣多");
        stockDays.setCashier("入库");
        stockDays.setNumber(1122111111);
        stockDays.setNameclass("饮料");
        stockDays.setTime(Integer.parseInt(String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))));
        stockDays.setTimes(Integer.parseInt(String.format("%02d%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))));
        stockDays.save();


        StockDay stockDayss = new StockDay();
        stockDayss.setName("威龙");
        stockDayss.setCashier("出库");
        stockDayss.setNumber(112222);
        stockDayss.setNameclass("小吃");
        stockDayss.setTime(Integer.parseInt(String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))));
        stockDayss.setTimes(Integer.parseInt(String.format("%02d%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))));
        stockDayss.save();
    }



    protected void switchcall() {
        textviewsbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                if (linearLayoutdaily.getVisibility() == View.GONE) {
//                    linearLayout.setVisibility(View.VISIBLE);
//                    linearLayoutdaily.setVisibility(View.VISIBLE);
//                    textviewsbutt.setTextColor(Color.LTGRAY);
//                } else {
//                    linearLayout.setVisibility(View.GONE);
//                    linearLayoutdaily.setVisibility(View.GONE);
//                    textviewsbutt.setTextColor(Color.WHITE);
//                }
            }
        });


        dailytextviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailytextviewall.setBackgroundColor(Color.WHITE);
                dailytextviewenter.setBackgroundColor(Color.LTGRAY);
                dailytextviewout.setBackgroundColor(Color.LTGRAY);
                cashier = "全部";
                aailyactivityscreen();
            }
        });
        dailytextviewenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dailytextviewenter.setBackgroundColor(Color.WHITE);
                dailytextviewall.setBackgroundColor(Color.LTGRAY);
                dailytextviewout.setBackgroundColor(Color.LTGRAY);
                cashier = "入库";
                aailyactivityscreen();

            }
        });
        dailytextviewout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailytextviewout.setBackgroundColor(Color.WHITE);
                dailytextviewall.setBackgroundColor(Color.LTGRAY);
                dailytextviewenter.setBackgroundColor(Color.LTGRAY);
                cashier = "出库";
                aailyactivityscreen();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (button.getText()+"") {
                    case "日志":








                        button.setBackgroundColor(Color.WHITE);
                        buttons.setBackgroundColor(Color.LTGRAY);
                        if (("总览".equals(buttons.getText()))&&(linearLayoutoverview.getVisibility() == View.GONE) && (linearLayoutdaily.getVisibility() == View.VISIBLE)) {

                            linearlayouttextviewsbutt.setVisibility(View.VISIBLE);
                            LinearLayoutoverviewadd.setVisibility(View.VISIBLE);
                            linearLayoutdaily.setVisibility(View.GONE);

                            button.setBackgroundColor(Color.WHITE);
                            buttons.setBackgroundColor(Color.WHITE);

//                            setStatusBar(Color.LTGRAY);

                            initclassaddrefurbish();
                            adapteradd.notifyDataSetChanged();
                            button.setText("入库");
                            buttons.setText("出库");
                        } else {
                            linearLayoutclasssdaily.setVisibility(View.GONE);
                            linearlayouttextviewsbutt.setVisibility(View.GONE);

                            linearLayoutoverview.setVisibility(View.GONE);
                            linearLayoutdaily.setVisibility(View.VISIBLE);
                        }
                        //嫑忘记刷新适配器
                        adapters.notifyDataSetChanged();

                        break;
                    case "入库":
                        cashierbottu("入库");

                        break;
                    case "AailyActivity筛选出库":
                        break;
                }

            }
        });

        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (buttons.getText()+"") {
                    case "总览":
                        button.setBackgroundColor(Color.LTGRAY);
                        buttons.setBackgroundColor(Color.WHITE);
                        if (("日志".equals(button.getText()))&&(linearLayoutoverview.getVisibility() == View.VISIBLE) && (linearLayoutdaily.getVisibility() == View.GONE)) {
                            if (linearLayout.getVisibility() == View.GONE) {
                                linearLayout.setVisibility(View.VISIBLE);
                                linearlayouttextviewsbutt.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout.setVisibility(View.GONE);
                                linearlayouttextviewsbutt.setVisibility(View.GONE);
                            }
                            adapter.removeDatasss();
                        } else {
                            if (linearLayout.getVisibility() == View.GONE) {
                                linearlayouttextviewsbutt.setVisibility(View.GONE);
                            } else {
                                linearlayouttextviewsbutt.setVisibility(View.VISIBLE);
                            }

                            linearLayoutoverview.setVisibility(View.VISIBLE);
                            linearLayoutdaily.setVisibility(View.GONE);

                            //嫑忘记刷新适配器
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case "出库":
                        cashierbottu("出库");

                        break;
                }

            }
        });
    }
    //筛选
    protected void cashierbottu(String string) {
        button.setText("日志");
        buttons.setText("总览");
        buttons.setBackgroundColor(Color.LTGRAY);
        linearlayouttextviewsbutt.setVisibility(View.GONE);
        LinearLayoutoverviewadd.setVisibility(View.GONE);
        linearLayoutdaily.setVisibility(View.VISIBLE);
        linearlayoutnumberadd.setVisibility(View.GONE );

        String datadeliete="";
        String datanube="";
        String datadelietes="";
        if(thisPosition.length>1){
            for(int i=0;i<thisPosition.length;i++){
                Calendar c = Calendar.getInstance();
                List<Stock> stock = LitePal.where("name == ?",thisPosition[i]).find(Stock.class);
                for(Stock stocks : stock){
                    StockDay stockDay = new StockDay();
                    stockDay.setName(thisPosition[i]);
                    stockDay.setNameclass(stocks.getNameclass());
                    stockDay.setCashier(string);
                    stockDay.setNumber(Integer.parseInt(thisPosition[i+1]));
                    stockDay.setTime(Integer.parseInt(String.format("%d%02d%02d", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE))));
                    stockDay.setTimes(Integer.parseInt(String.format("%02d%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))));

                    if("入库".equals(string)){
                        try{
                            stocks.setNumber(stocks.getNumber()+Integer.parseInt(thisPosition[i+1]));
                            stocks.save();
                            stockDay.save();
                            datanube+="("+thisPosition[i]+string+":"+thisPosition[i+1]+stocks.getUnit()+")"+" ";
                        }catch (Exception e){
                            datadelietes="爆仓警告！！！";
                            datadeliete += "("+thisPosition[i]+":取消"+string+")"+"  ";
                        }
                    }
                    if("出库".equals(string)){
                        if(stocks.getNumber()-Integer.parseInt(thisPosition[i+1])>=0){
                            stocks.setNumber(stocks.getNumber()-Integer.parseInt(thisPosition[i+1]));
                            stocks.save();
                            stockDay.save();
                            datanube+="("+thisPosition[i]+string+":"+thisPosition[i+1]+stocks.getUnit()+")"+" ";
                        }else{
                            datadelietes="库存不足，请补足库存。";
                            datadeliete += "("+thisPosition[i]+":取消"+string+")"+"  ";
                        }
                    }
                    i++;
                    break;
                }

            }

            if(!"".equals(datadelietes)){
                datadelietes= datadeliete+"\n"+datadelietes+ "\n"+datanube;
            }else{
                datadelietes=datanube ;
            }
            if(!"".equals(datadelietes)){
                Toast.makeText(this, datadelietes , Toast.LENGTH_LONG).show();
            }

            modifydeadline();

            aailyactivityscreen();

            modifydeadlinetext();

            //嫑忘记刷新适配器
            adapter.notifyDataSetChanged();

            thisPosition = Arrays.copyOf(thisPosition, 0);
            adapteradd.setThisPosition(thisPosition);
            adapteradd.notifyDataSetChanged();
        }



    }


    protected void initclassaddrefurbish() { }
    ////修改截止时间
    protected void  modifydeadline() {}
    ////修改截止时间
    protected void  modifydeadlinetext() {}
    //筛选
    protected void aailyactivityscreen() {


        sbi = String.format("%d%02d%02d",
                datePicker.getYear(),
                datePicker.getMonth() + 1,
                datePicker.getDayOfMonth());

        String string = String.format("%02d%02d",
                newVals,
                minute);

        sbis = String.format("%d%02d%02d",
                datePickers.getYear(),
                datePickers.getMonth() + 1,
                datePickers.getDayOfMonth());


        String strings = String.format("%02d%02d",
                newVals2,
                minute2);


        while (daiyList.size() > 0 && adapters != null) {
            try {
                adapters.removeData(daiyList.size() - 1);
            } catch (Exception e) {

            }
        }
        List<StockDay> stockDays = null;

        if ("全部".equals(dailyclsa)||dailyclsa==null) {
            if ("全部".equals(cashier)||dailyclsa==null) {
                stockDays = LitePal.where("time >= ? ", sbi)
                        .order("id desc")
                        .find(StockDay.class);
            } else {
                stockDays = LitePal.where("time >= ?  and cashier == ?", sbi, cashier)
                        .order("id desc")
                        .find(StockDay.class);
            }
        } else {
            if ("全部".equals(cashier)) {
                stockDays = LitePal.where("time >= ?  and nameclass == ?", sbi, dailyclsa)
                        .order("id desc")
                        .find(StockDay.class);
            } else {
                stockDays = LitePal.where("time >= ? and nameclass == ? and cashier == ?", sbi, dailyclsa, cashier)
                        .order("id desc")
                        .find(StockDay.class);
            }
        }




        if (Integer.parseInt(sbis) * 10000 + Integer.parseInt(strings) < Integer.parseInt(sbi) * 10000 + Integer.parseInt(string)) {
            sbis = sbi;
            strings = string;
        }
        costdaiy = 0;
        for (StockDay stockDay : stockDays) {
            if (Integer.parseInt(sbis) * 10000 + Integer.parseInt(strings) < stockDay.getTime() * 10000 + stockDay.getTimes()) {
                break;
            }
            if((Integer.parseInt(sbi) * 10000 + Integer.parseInt(string)) < (stockDay.getTime() * 10000 + stockDay.getTimes())){
                Daily mango = new Daily(stockDay.getName(),stockDay.getTime(),stockDay.getTimes(),stockDay.getNumber(),stockDay.getCashier(),stockDay.getNameclass());
                daiyList.add(mango);
                List<Stock> stocks = LitePal.where("name == ?", stockDay.getName()).find(Stock.class);
                for (Stock stock : stocks) {
                    if("出库".equals(stockDay.getCashier())){
                        costdaiy -= stock.getCost() * stockDay.getNumber();
                    }else{
                        costdaiy += stock.getCost() * stockDay.getNumber();
                    }

                    break;
                }
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        String content = "<b>" + "总成本: " + "</b>" + (decimalFormat.format(costdaiy)) + "元    ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
        textviewsscreens.setText(charSequence);



        //刷新适配器
        adapters.notifyDataSetChanged();

    }


    /**
     * 获得某个月最大天数
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    public int getMaxDayByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year - 1);
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }


    private void gettime() {

        Calendar c = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%d - %02d - %02d",
                c.get(Calendar.YEAR),
                (c.get(Calendar.MONTH)+1 ),
                c.get(Calendar.DATE)));

        sbi = String.format("%d%02d%02d",
                c.get(Calendar.YEAR),
                (c.get(Calendar.MONTH)+1 ),
                c.get(Calendar.DATE));
        //赋值后面闹钟使用
        sbis = String.format("%d%02d%02d",
                c.get(Calendar.YEAR),
                (c.get(Calendar.MONTH)+1 ),
                c.get(Calendar.DATE));


        years = c.get(Calendar.YEAR);
        monthOfYears = c.get(Calendar.MONTH) ;
        dayOfMonths = c.get(Calendar.DATE);
        minute = 00;
        newVals = 8;
        if (c.get(Calendar.HOUR_OF_DAY) == 0) {
            newVals2 = 24;
        } else {
            newVals2 = c.get(Calendar.HOUR_OF_DAY);
        }

        minute2 = c.get(Calendar.MINUTE);
        years2 = c.get(Calendar.YEAR);
        monthOfYears2 = c.get(Calendar.MONTH) ;
        dayOfMonths2 = c.get(Calendar.DATE);


        if (((dayOfMonths * 100 + newVals) * 100 + minute) > ((dayOfMonths2 * 100 + newVals2) * 100 + minute2)) {
            dayOfMonths--;
        }


    }
}
