package com.example.xiaopengwarehouse.XiaoPengActiVity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClass.FruitAdapter;
import com.example.xiaopengwarehouse.XiaoPengClassAdd.XiaoPengAddClassActivity;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.XiaoPengAddActivity;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengMenu.Class;
import com.example.xiaopengwarehouse.XiaoPengMenu.ClassAdapter;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends XiaoPengAddClassActivity {







    private View views;

    protected RecyclerView recyclerViewss;



    FruitAdapter adapterdaily;

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

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);//列表翻转
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList,this);
        recyclerView.setAdapter(adapter);


        recyclerViewss = (RecyclerView) findViewById(R.id.recycler_view_class);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewss.setLayoutManager(layoutManagers);
        adapterss = new ClassAdapter(classList);
        recyclerViewss.setAdapter(adapterss);
        adapterss.notifyDataSetChanged();


        recyclerViewsdaily = (RecyclerView) findViewById(R.id.recycler_view_class_daily);
        LinearLayoutManager layoutManagersdaily = new LinearLayoutManager(this);
        layoutManagersdaily.setReverseLayout(true);//列表翻转
        layoutManagersdaily.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewsdaily.setLayoutManager(layoutManagersdaily);
        adaptersdaily = new ClassAdapter(classListdaily);
        recyclerViewsdaily.setAdapter(adaptersdaily);
        adaptersdaily.notifyDataSetChanged();




        answerviews();

        initclass();

        answerviewsdaily();



        linearLayoutclasssdaily = (LinearLayout) findViewById(R.id.linearlayout_classs_daily);
        linearLayoutclasssdaily.setBackgroundColor(Color.LTGRAY);



    }



    //删除品类
    public void removeDatas() {

        while (fruitList.size() > 0&&adapter != null) {
            try {
                adapter.removeData(fruitList.size() - 1);

            } catch (Exception e) {

            }
        }


        List<Stock> stock = null;
        if("全部".equals(classy)){
            stock = LitePal.findAll(Stock.class);

        }else{
            stock = LitePal.where("nameclass == ?", classy).find(Stock.class);
        }

        for (Stock stocks : stock) {
            if(stocks.getDisplay()) {
                Fruit mango = new Fruit(stocks.getName());
                fruitList.add(mango);
            }
        }

//刷新适配器
        adapter.notifyDataSetChanged();

        //总览分类刷新
        initFruitclass();

        adapterssaddclass.notifyDataSetChanged();

//总览分类
        adapterss.notifyDataSetChanged();
//添加页面刷新
        initclassaddrefurbishs();


    }


    private void initclass() {
        try{
            classy = classList.get(0).getName();
            dailyclsa = classList.get(classListdaily.size()-1).getName();

            List<Stock> stock = LitePal.where("nameclass == ?", classList.get(0).getName()).find(Stock.class);
            for (Stock stocks : stock) {
                if(stocks.getDisplay()){
                    Fruit mango = new Fruit(stocks.getName());
                    fruitList.add(mango);
                }
            }
        }catch (Exception e){

        }

    }

    private void answerviews() {

        adapterss.setOnRecyclerViewItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                Toast.makeText(MainActivity.this, "點擊了" + (position + 1), Toast.LENGTH_SHORT).show();
//                //  "\n"    是換行符 知道吧
//                Log.e("tag", "獲取到的左側點擊值為：" + (position + 1));
                //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                adapterss.setThisPosition(position);
                //嫑忘记刷新适配器
                adapterss.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(int position) {
//                Log.e("tag", "獲取到的左側長按點擊值為：" + (position + 1));
            }
        });




        recyclerViewss.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                        View childView = recyclerViewss.findChildViewUnder(e.getX(), e.getY());
                        ActionUp(childView);
                        actionups++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        ActionMoves((int) e.getY(), (int) e.getX());
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

    @Override
    protected void initclassaddrefurbishsactivity(){
        classy = classList.get(0).getName();
        dailyclsa = classList.get(classListdaily.size()-1).getName();

        List<Stock> stock = LitePal.where("nameclass == ?", classList.get(0).getName()).find(Stock.class);
        for (Stock stocks : stock) {
            if(stocks.getDisplay()){
                Fruit mango = new Fruit(stocks.getName());
                fruitList.add(mango);
            }
        }
    }

    private void ActionUp(final View childView) {
        if (childView != null) {
            views = childView;
            int position = recyclerViewss.getChildLayoutPosition(childView);
            Class fruit = classList.get(position);
            final String string = fruit.getName();

            if (classy!=string){
                classy=string;

                while (fruitList.size() > 0&&adapter != null) {
                    try {
                        adapter.removeData(fruitList.size() - 1);
                    } catch (Exception e) {

                    }
                }


                List<Stock> stock = null;
                if("全部".equals(string)){
                    stock = LitePal.findAll(Stock.class);

                }else{
                    stock = LitePal.where("nameclass == ?", string).find(Stock.class);
                }

                for (Stock stocks : stock) {
                    if(stocks.getDisplay()) {
                        Fruit mango = new Fruit(stocks.getName());
                        fruitList.add(mango);
                    }
                }

//刷新适配器
                adapter.notifyDataSetChanged();
//                recyclerView.scrollToPosition(0);
            }

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

    private void ActionMoves(int gety, int getx) {
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



    private void answerviewsdaily() {

        adaptersdaily.setOnRecyclerViewItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                Toast.makeText(MainActivity.this, "點擊了" + (position + 1), Toast.LENGTH_SHORT).show();
//                //  "\n"    是換行符 知道吧
//                Log.e("tag", "獲取到的左側點擊值為：" + (position + 1));
                //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                adaptersdaily.setThisPosition(position);
                //嫑忘记刷新适配器
                adaptersdaily.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(int position) {
//                Log.e("tag", "獲取到的左側長按點擊值為：" + (position + 1));
            }
        });




        recyclerViewsdaily.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                        View childView = recyclerViewsdaily.findChildViewUnder(e.getX(), e.getY());
                        ActionUpdaily(childView);
                        actionups++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        ActionMovesdaily((int) e.getY(), (int) e.getX());
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

    private void ActionUpdaily(final View childView) {


        if (childView != null) {
            views = childView;
            int position = recyclerViewsdaily.getChildLayoutPosition(childView);
            Class fruit = classListdaily.get(position);
            final String string = fruit.getName();

            if (classy!=string){
                classy=string;
                dailyclsa=string;

                aailyactivityscreen();

            }

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

    private void ActionMovesdaily(int gety, int getx) {
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

}
