package com.example.xiaopengwarehouse.XiaoPengDaily;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.RoundImageView;
import com.example.xiaopengwarehouse.XiaoPengActiVity.MainActivity;
import com.example.xiaopengwarehouse.XiaoPengActiVity.XiaoPengActivity;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClass.FruitAdapter;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockClass;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;
import com.example.xiaopengwarehouse.XiaoPengMenu.Class;
import com.example.xiaopengwarehouse.XiaoPengMenu.ClassAdapter;

import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AailyActivity extends XiaoPengActivity implements NumberPicker.OnValueChangeListener, NumberPicker.OnScrollListener, NumberPicker.Formatter{





    LinearLayout linearLayout;



    LinearLayout linearLayoutinstall;
    LinearLayout dailylinearlayout;

    private View views;
    private int numbers = 0;
    private int egetxs = 0;
    private int egetx = 0;
    private int egetys = 0;
    private int actiondowns = 0;
    private int actionups = 0;
    private int actionmoves = 0;
    private int ynumbers = 0;
    private int xnumbers = 0;
    private String updowns = "";
    private String leftrights = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFruit();

        recyclerViews = (RecyclerView) findViewById(R.id.daily_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);//列表翻转
        recyclerViews.setLayoutManager(layoutManager);
        adapters = new DailyAdapter(daiyList);
        recyclerViews.setAdapter(adapters);




        aailyactivitylinearLayoutsinstall = (LinearLayout) findViewById(R.id.LinearLayout_screens_install);
        linearLayoutinstall = (LinearLayout) findViewById(R.id.LinearLayout_screen_install);
        aailyactivitylinearLayouts = (LinearLayout) findViewById(R.id.LinearLayout_screens);
        linearLayout = (LinearLayout) findViewById(R.id.LinearLayout_screen);

        dailylinearlayout =(LinearLayout)findViewById(R.id.LinearLayout_daily);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aailyactivitylinearLayouts.setVisibility(View.GONE);
                aailyactivitylinearLayoutsinstall.setVisibility(View.VISIBLE);
                linearlayoutbutall.setVisibility(View.VISIBLE);

                linearlayouttextviewsbutt.setVisibility(View.VISIBLE);
                linearLayoutclasssdaily.setVisibility(View.VISIBLE);




//                dailybutton = "AailyActivity筛选出库";
//                button.setText("出库");
//                dailybuttons = "AailyActivity筛选入库";
//                buttons.setText("入库");
            }
        });
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        resizePikcer(datePicker);//调整datepicker大小
        //设置为对当前值不可编辑
        datePicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

//        setNumberPickerTextSize(datePicker);//修改datePicker字体的大小
        setDatePickerDividerColor(datePicker);

        datePickers = (DatePicker) findViewById(R.id.date_pickers);
        resizePikcer(datePickers);//调整datepicker大小
        //设置为对当前值不可编辑
        datePickers.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
//        setNumberPickerTextSize(datePickers);//修改datePicker字体的大小
        setDatePickerDividerColor(datePickers);

        numberPicker = (NumberPicker)findViewById(R.id.number_picker);
        NumberPickerDividerColor(numberPicker);

        numberPickers = (NumberPicker)findViewById(R.id.number_pickers);
        NumberPickerDividerColor(numberPickers);


        minutepicker = (NumberPicker)findViewById(R.id.minute_picker);
        NumberPickerDividerColor(minutepicker);

        minutepickers = (NumberPicker)findViewById(R.id.minute_pickers);
        NumberPickerDividerColor(minutepickers);


        textviewscreen = (TextView) findViewById(R.id.textviews_screen);


        String string = String.format("%d - %02d - %02d  %02d:%02d",
                years,
                monthOfYears+1,
                dayOfMonths,
                newVals,
                minute);



        String strings = String.format("%d - %02d - %02d  %02d:%02d",
                years2,
                monthOfYears2+1,
                dayOfMonths2,
                newVals2,
                minute2);

        DecimalFormat decimalFormat=new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        String content = "<b>"+"总成本: "+"</b>"+(decimalFormat.format(costdaiy))+"元    ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
        textviewsscreens.setText(charSequence);

        textviewscreen.setText(charSequence +"\n"+string + "  —  " + strings);


        time();

        times(years2, monthOfYears2, dayOfMonths2);

        answerviewsdailycommodity();

//        textviewscreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        textviewsscreens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultshow();
            }
        });

        dailylinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultshow();
            }
        });


        init();
    }
//隐藏筛选
private void resultshow(){
    if((linearlayoutbutall.getVisibility()!=View.GONE)&&(aailyactivitylinearLayoutsinstall.getVisibility()!=View.GONE)&&(linearLayoutclasssdaily.getVisibility()!=View.GONE)){
        aailyactivitylinearLayouts.setVisibility(View.VISIBLE);
        aailyactivitylinearLayoutsinstall.setVisibility(View.GONE);

        linearlayoutbutall.setVisibility(View.GONE);
        recyclerViewsdaily.setVisibility(View.VISIBLE);

        linearLayoutclasssdaily.setVisibility(View.GONE);
        linearlayouttextviewsbutt.setVisibility(View.GONE);

//        dailybuttons = "总览";
//        buttons.setText("总览");
//
//        dailybutton = "+";
//        button.setText("+");

        //日期格式
        String string = String.format("%d - %02d - %02d   %02d:%02d",
                datePicker.getYear(),
                datePicker.getMonth() + 1,
                datePicker.getDayOfMonth(),
                newVals,
                minute);
//                        oplandate.setText(sb);

        //日期格式
        String strings = String.format("%d - %02d - %02d   %02d:%02d",
                datePickers.getYear(),
                datePickers.getMonth() + 1,
                datePickers.getDayOfMonth(),
                newVals2,
                minute2);
//                        oplandate.setText(sbs);

        DecimalFormat decimalFormat=new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        String content = "<b>"+"总成本: "+"</b>"+(decimalFormat.format(costdaiy))+"元    ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
        textviewsscreens.setText(charSequence);

        textviewscreen.setText(charSequence +"\n"+string + "  —  " + strings);
        //刷新适配器
        adapters.notifyDataSetChanged();
    }
}
    private void time(){
        //初始化DatePicker组件，初始化时指定监听器
        datePicker.init(years, monthOfYears, dayOfMonths, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                years = year;
                monthOfYears = monthOfYear;
                dayOfMonths = dayOfMonth;

                aailyactivityscreen();
                if (days != 1 && (((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+ newVals)*100+ minute)> (((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2 )*100+ minute2)) {
                    years2 = years;
                    monthOfYears2 = monthOfYears;
                    dayOfMonths2 = dayOfMonths;
                    times(years, monthOfYears, dayOfMonths);
                    minutepickers.setValue(minute);
                    numberPickers.setValue(newVals);
                }

                days = dayOfMonths;

//                SimpleDateFormat format = new SimpleDateFormat(
//                        "yyyy年MM月dd日");
//                Toast.makeText(AailyActivity.this,
//                        "初试时间："+format.format(calendar.getTime()), Toast.LENGTH_SHORT)
//                        .show();
            }
        });
    }



    private void times(int yearss, int monthOfYearss, int dayOfMonthss){
        datePickers.init(yearss, monthOfYearss, dayOfMonthss, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                years2=year;
                monthOfYears2=monthOfYear;
                dayOfMonths2=dayOfMonth;

                aailyactivityscreen();

                if((((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+ newVals)*100+ minute)>(((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2 )*100+ minute2)){
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy年MM月dd日");
                    Toast.makeText(AailyActivity.this,
                            "截止时间小于初始时间了", Toast.LENGTH_SHORT)
                            .show();
                    years2=years;
                    monthOfYears2=monthOfYears;
                    dayOfMonths2=dayOfMonths;
                    minute2=minute;
                    newVals2=newVals;
                    minutepickers.setValue(minute);
                    numberPickers.setValue(newVals);
                    times(years, monthOfYears, dayOfMonths);

                }



            }
        });
    }
    //修改截止时间
    @Override
    protected void  modifydeadline() {

        Calendar c = Calendar.getInstance();

        years2 = c.get(Calendar.YEAR);
        monthOfYears2 = c.get(Calendar.MONTH);
        dayOfMonths2 =  c.get(Calendar.DATE);
        newVals2=c.get(Calendar.HOUR_OF_DAY);
        minute2=c.get(Calendar.MINUTE);
        times(years2, monthOfYears2, dayOfMonths2);
        minutepickers.setValue(minute2);
        numberPickers.setValue(newVals2);

    }

    //修改截止时间
    @Override
    protected void  modifydeadlinetext() {

        String string = String.format("%d - %02d - %02d   %02d:%02d",
                years,
                monthOfYears + 1,
                dayOfMonths2,
                newVals,
                minute);
//                        oplandate.setText(sb);

        //日期格式
        String strings = String.format("%d - %02d - %02d   %02d:%02d",
                years2,
                monthOfYears2 + 1,
                dayOfMonths2,
                newVals2,
                minute2);
//                        oplandate.setText(sbs);

        DecimalFormat decimalFormat=new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        String content = "<b>"+"总成本: "+"</b>"+(decimalFormat.format(costdaiy))+"元    ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
        textviewsscreens.setText(charSequence);

        textviewscreen.setText(charSequence +"\n"+string + "  —  " + strings);
    }

    private void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }


    private void setNumberPickerTextSize(ViewGroup viewGroup) {
        List<NumberPicker> npList = findNumberPicker(viewGroup);
        if (null != npList)
        {
            for (NumberPicker np : npList)
            {
                EditText et = findEditText(np);
                et.setFocusable(false);
                et.setGravity(Gravity.CENTER);
                et.setTextSize(18);

            }
        }
    }


    private EditText findEditText(NumberPicker np)
    {
        if (null != np)
        {
            for (int i = 0; i < np.getChildCount(); i++)
            {
                View child = np.getChildAt(i);

                if (child instanceof EditText)
                {
                    return (EditText)child;
                }
            }
        }

        return null;
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0;i<viewGroup.getChildCount();i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /*
     * 调整numberpicker大小
     */
    private void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(85, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        np.setLayoutParams(params);
    }



    private void initFruit() {







        String string = String.format("%d%02d%02d",
                years,
                monthOfYears + 1,
                dayOfMonths);
        List<StockDay> stockDays = null;
        if ("全部".equals(classListdaily.get(0).getName())) {
            stockDays = LitePal.where("time >= ? ", string)
                    .order("id desc").find(StockDay.class);

        } else {
            stockDays = LitePal.where("time >= ? and nameclass == ?", string, classListdaily.get(0).getName())
                    .order("id desc")
                    .find(StockDay.class);

        }

        for (StockDay stocksday : stockDays) {
            Daily mango = new Daily(stocksday.getName(),stocksday.getTime(),stocksday.getTimes(),stocksday.getNumber(),stocksday.getCashier(),stocksday.getNameclass() );
            daiyList.add(mango);
            List<Stock> stocks = LitePal.where("name == ?", stocksday.getName()).find(Stock.class);
            for (Stock stock : stocks) {
                if("出库".equals(stocksday.getCashier())){
                    costdaiy -= stock.getCost() * stocksday.getNumber();
                }else{
                    costdaiy += stock.getCost() * stocksday.getNumber();
                }


                break;
            }

        }

        DecimalFormat decimalFormat = new DecimalFormat(".000");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        String content = "<b>" + "总成本: " + "</b>" + (decimalFormat.format(costdaiy)) + "元    ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
    }

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    private void setDatePickerDividerColor(DatePicker datePicker) {
        // Divider changing:

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#FFFFEF")));//设置分割线颜色
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    private void NumberPickerDividerColor(NumberPicker picker) {


            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#FFFFEF")));//设置分割线颜色
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
    }

    private void answerviewsdailycommodity() {



        recyclerViews.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        egetxs = (int) e.getX();
                        egetys = (int) e.getY();
                        actiondowns++;
                        Log.e("TAG", "LinearLayout onTouch按住");
                        break;
                    case MotionEvent.ACTION_UP://1
                        View childView = recyclerViews.findChildViewUnder(e.getX(), e.getY());
                        ActionUpdailycommodity(childView);
                        actionups++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        ActionMovesdailycommodity((int) e.getY(), (int) e.getX());
                        actionmoves++;
                        Log.e("TAG", "LinearLayout onTouch移动");
                        break;
                    default:
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

    }
    private String nume=null;
    private int modifydaiy=5;
    public void buttns(View childView) {
        int position = recyclerViews.getChildLayoutPosition(childView);
        Daily daily = daiyList.get(position);
        if((linearlayoutbutall.getVisibility()!=View.GONE)&&(aailyactivitylinearLayoutsinstall.getVisibility()!=View.GONE)&&(linearLayoutclasssdaily.getVisibility()!=View.GONE)){
            if(nume==daily.getNume()){
                if(modifydaiy>0){

                    Toast.makeText(this, "再点("+(modifydaiy-- )+")次删除:"+ daily.getNume(), Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(this, "已删除删除:"+ daily.getNume(), Toast.LENGTH_SHORT).show();

                    LitePal.deleteAll(StockDay.class,
                            "name==? and cashier ==? and number==? and time ==? and times == ? ",
                            daily.getNume(),daily.getCashier(),daily.getNumber()+"",daily.getTime()+"",daily.getTimes()+"");





                    //日志界面刷新适配器
                    aailyactivityscreen();
                    adapters.notifyDataSetChanged();

                    //日志界面刷新分类适配器
//                    initFruitdaily();
                    adaptersdaily.notifyDataSetChanged();
                    modifydaiy=5;
                }

            }else{
                modifydaiy=5;
            }
            nume=daily.getNume();

        }else{
//            Toast.makeText(this, "" + daily.getNume(), Toast.LENGTH_SHORT).show();
        }




    }
    private void ActionUpdailycommodity(final View childView) {


        if (childView != null) {
            views = childView;
//            int position = recyclerViews.getChildLayoutPosition(childView);
//            Class fruit = classList.get(position);

            final DailyAdapter.ViewHolder holder = new DailyAdapter.ViewHolder(childView);
            holder.dailyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttns(childView);
                }
            });
            holder.dailyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttns(childView);
                }
            });



            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (actiondowns == 1 && actiondowns == 1 && actionmoves == 0) {

                            }
                            actiondowns = 0;
                            actiondowns = 0;
                            actionmoves = 0;
                        }
                    });
                }
            }).start();
        }
    }

    private void ActionMovesdailycommodity(int gety, int getx) {
        if ((gety - egetys) <= 0) {
            ynumbers = egetys - gety;
        } else {
            ynumbers = gety - egetys;
        }

        if (ynumbers > 300) {
            if (gety < egetys) {
                updowns = "up";
            } else {
                updowns = "down";
                if (ynumbers > 400 && numbers == 0) {
                    numbers++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            numbers = 0;
                        }
                    }).start();

                    updowns = "";
                }
            }
        }


        if ((getx - egetxs) >= 0) {
            xnumbers = getx - egetxs;
        } else {
            xnumbers = egetxs - getx;
        }
        if (xnumbers > 350) {
            if (xnumbers > 390) {
                if (getx < egetx) {
                    //大于0表示正在向右滚动
                    leftrights = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrights = "right";

                }
            } else {
                if (getx < egetxs) {
                    //大于0表示正在向右滚动
                    leftrights = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrights = "right";
                }
            }
        }
    }


    private static final String TAG = "MainActivity";




    private void init() {
        numberPicker.setFormatter(this);
        numberPicker.setOnValueChangedListener(this);
        numberPicker.setOnScrollListener(this);
        numberPicker.setMaxValue(23);
        numberPicker.setMinValue(0);
        numberPicker.setValue(newVals);

        numberPickers.setFormatter(this);
        numberPickers.setOnValueChangedListener(this);
        numberPickers.setOnScrollListener(this);
        numberPickers.setMaxValue(23);
        numberPickers.setMinValue(0);
        numberPickers.setValue(newVals2);



        minutepicker.setFormatter(this);
        minutepicker.setOnValueChangedListener(this);
        minutepicker.setOnScrollListener(this);
        minutepicker.setMaxValue(59);
        minutepicker.setMinValue(0);
        minutepicker.setValue(minute);
//        minutepicker.setValue(00);

        minutepickers.setFormatter(this);
        minutepickers.setOnValueChangedListener(this);
        minutepickers.setOnScrollListener(this);
        minutepickers.setMaxValue(59);
        minutepickers.setMinValue(0);
        minutepickers.setValue(minute2);
//        minutepickers.setValue(00);

        //设置为对当前值不可编辑
        numberPicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickers.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        minutepicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        minutepickers.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

        //这里设置为不循环显示，默认值为true
        numberPicker.setWrapSelectorWheel(true);
        numberPickers.setWrapSelectorWheel(true);
        minutepicker.setWrapSelectorWheel(true);
        minutepickers.setWrapSelectorWheel(true);
    }

    @Override
    public String format(int value) {
        Log.i(TAG, "format: value");
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING:
                Log.i(TAG, "onScrollStateChange: 后续滑动(飞呀飞，根本停下来)");
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                Log.i(TAG, "onScrollStateChange: 不滑动");
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Log.i(TAG, "onScrollStateChange: 滑动中");
                break;
        }

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i(TAG, "onValueChange: 原来的值 " + oldVal + "--新值: "
                + newVal);


        if(picker==numberPicker){

            timennumberPicker(oldVal, newVal);
            timenumberPicker();

        }

        if(picker==numberPickers){
            timennumberPickers(oldVal, newVal);
            timesnumberPickers();
        }

        if(picker==minutepicker){
            minute=newVal;

            //减一小时
            if(oldVal==0&&newVal==59){
                oldVals=newVals;
                newVals--;
                if(newVals<0){
                    newVals=23;
                }
                timennumberPicker(oldVals, newVals);
            }
            //加一小时
            if(oldVal==59&&newVal==0){
                oldVals=newVals;
                newVals++;
                if(newVals>23){
                    newVals=0;
                }
                timennumberPicker(oldVals, newVals);
            }



            timenumberPicker();
            numberPicker.setValue(newVals);

        }


        if(picker==minutepickers){
            minute2=newVal;
            //减一小时
            if(oldVal==0&&newVal==59){
                oldVals2=newVals2;
                newVals2--;
                if((((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+newVals)*100+minute) > (((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2)*100+minute2)) {
                    newVals2=newVals;
                    minute2=minute;
                    minutepickers.setValue(minute2);
                    numberPickers.setValue(newVals2);
                }


                if(newVals2<0){
                    newVals2=23;
                }
                timennumberPickers(oldVals2, newVals2);
            }
            //加一小时
            if(oldVal==59&&newVal==0){
                oldVals2=newVals2;
                newVals2++;
                if(newVals2>23){
                    newVals2=0;
                }
                timennumberPickers(oldVals2, newVals2);
            }


            numberPickers.setValue(newVals2);

        }

    }

    private void timennumberPicker(int oldVal, int newVal){


        newVals=newVal;
        oldVals=oldVal;
            //减一天
            if(oldVal==0&&newVal==23){
                dayOfMonths--;
                if(dayOfMonths<1){
                    dayOfMonths=getMaxDayByYearMonth(years,monthOfYears-1);
                    monthOfYears--;
                    if(monthOfYears<0){
                        monthOfYears=11;
                        years--;
                    }
                }
            }
            //加一天
            if(oldVal==23&&newVal==0){
                dayOfMonths++;
                if(dayOfMonths>getMaxDayByYearMonth(years,monthOfYears)){
                    dayOfMonths=1;
                    monthOfYears++;
                    if(monthOfYears>11){
                        monthOfYears=0;
                        years++;
                    }
                }
            }

    }

    private void timennumberPickers(int oldVal, int newVal){

        newVals2=newVal;
        oldVals2=oldVal;
            //减一天
            if(oldVal==0&&newVal==23){
                dayOfMonths2--;
                if(dayOfMonths2<1){
                    dayOfMonths2=getMaxDayByYearMonth(years2,monthOfYears2-1);
                    monthOfYears2--;
                    if(monthOfYears2<0){
                        monthOfYears2=11;
                        years2--;
                    }
                }
            }
            //加一天
            if(oldVal==23&&newVal==0){
                dayOfMonths2++;
                if(dayOfMonths2>getMaxDayByYearMonth(years2,monthOfYears2)){
                    dayOfMonths2=1;
                    monthOfYears2++;
                    if(monthOfYears2>11){
                        monthOfYears2=0;
                        years2++;
                    }
                }
            }
            timesnumberPickers();

    }

    private void timenumberPicker(){




        time();

        aailyactivityscreen();

        if(days != 1 && (((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+newVals)*100+minute) > (((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2)*100+minute2)){
            minute2=minute;

        }

        if (days != 1 && ((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+newVals) > ((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2)) {
            years2 = years;
            monthOfYears2 = monthOfYears;
            dayOfMonths2 = dayOfMonths;

            if(newVals-23<0){
                newVals2 = 23+(newVals-23);
            }else{
                newVals2 = newVals-23;
            }
            times(years, monthOfYears, dayOfMonths);

        }
        minutepickers.setValue(minute2);
        numberPickers.setValue(newVals2);
        days = dayOfMonths;
    }


    private void timesnumberPickers(){
        times(years2, monthOfYears2, dayOfMonths2);
        aailyactivityscreen();
        if((((years * 10000 + (monthOfYears + 1) * 100 + dayOfMonths)*100+newVals)*100+minute) > (((years2 * 10000 + (monthOfYears2 + 1) * 100 + dayOfMonths2)*100+newVals2)*100+minute2)){
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy年MM月dd日");
            Toast.makeText(AailyActivity.this,
                    "截止时间小于初始时间了", Toast.LENGTH_SHORT)
                    .show();
            dayOfMonths2=dayOfMonths;
            minute2=minute;
            minutepickers.setValue(minute);
            numberPickers.setValue(newVals);
            times(years, monthOfYears, dayOfMonths);
        }

    }




}
