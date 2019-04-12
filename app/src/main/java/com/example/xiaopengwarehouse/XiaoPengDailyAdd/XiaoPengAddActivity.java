package com.example.xiaopengwarehouse.XiaoPengDailyAdd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.RoundImageView;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClassAdd.FruitAdapterClassAdd;
import com.example.xiaopengwarehouse.XiaoPengDaily.AailyActivity;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;
import com.example.xiaopengwarehouse.XiaoPengMenu.Class;
import com.example.xiaopengwarehouse.XiaoPengMenu.ClassAdapter;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class XiaoPengAddActivity extends AailyActivity {






    private View viewsadd;



    protected LinearLayout linearlayoutbutt;
    private com.example.xiaopengwarehouse.RoundImageView imagenumberadd;

    protected InputMethodManager imm;
    private TextView textnameadd;

    private int numbersadd = 0;
    private int egetxsadd = 0;
    private int egetxadd = 0;
    private int egetysadd = 0;
    private int actiondownsadd = 0;
    private int actionupsadd = 0;
    private int actionmovesadd = 0;
    private int ynumbersadd = 0;
    private int xnumbersadd = 0;
    private String updownsadd = "";
    private String leftrightsadd = "";
    private String stringaddclass;//添加页面分类

    private  int positionadd ;
    private   FruitAdapterAdd.ViewHolder holderadd;


    RecyclerView recyclerViewadd;
     RecyclerView recyclerViewssaddclass;



    private String toaststringadd = "";

    private View viewsaddclass;
    private EditText fruitNumberadd;
    private EditText fruitNumberadds;
    private String fruitunitradd;
    private String fruitunitradds;
    private int fruitunitraddsnumber;

    private int numbersaddclass = 0;
    private int egetxsaddclass = 0;
    private int egetxaddclass = 0;
    private int egetysaddclass = 0;
    private int actiondownsaddclass = 0;
    private int actionupsaddclass = 0;
    private int actionmovesaddclass = 0;
    private int ynumbersaddclass = 0;
    private int xnumbersaddclass = 0;
    private String updownsaddclass = "";
    private String leftrightsaddclass = "";
    private String selectnume="";


    protected FruitAdapterClassAdd adapterclassadds;//添加品类大图页面



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        recyclerViewadd = (RecyclerView) findViewById(R.id.recycler_view_add);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
        layoutManager.setReverseLayout(true);//列表翻转

        recyclerViewadd.setLayoutManager(layoutManager);

        adapteradd = new FruitAdapterAdd(addList);
        recyclerViewadd.setAdapter(adapteradd);

        recyclerViewssaddclass = (RecyclerView) findViewById(R.id.recycler_view_class_add);
        LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
        layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewssaddclass.setLayoutManager(layoutManagers);
        adapterssaddclass = new ClassAdapter(classList);
        recyclerViewssaddclass.setAdapter(adapterssaddclass);
        adapterssaddclass.notifyDataSetChanged();




        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout_class_add);
        linearLayout.setBackgroundColor(Color.LTGRAY);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout_class_bottom);
        linearLayout.setBackgroundColor(Color.LTGRAY);

        imagenumberadd = (com.example.xiaopengwarehouse.RoundImageView)findViewById(R.id.image_number_add);

        imagenumberadd.setType(RoundImageView.TYPE_ROUND);

        linearlayoutbutt = (LinearLayout) findViewById(R.id.linearlayout_butt);

        answerviewsaddclass();

        linearlayoutnumberadd = (LinearLayout) findViewById(R.id.linearlayout_number_add);

        fruitNumberadd = (EditText) findViewById(R.id.fruit_number_add);
        fruitNumberadds = (EditText) findViewById(R.id.fruit_number_adds);

        textnameadd = (TextView)findViewById(R.id.text_name_add) ;

        answerviewsadd();
        try{
            classyadd=classList.get(0).getName();
            stringaddclass = classyadd;
            List<Stock> stock = LitePal.where("nameclass == ?", classList.get(0).getName()).find(Stock.class);
            initclassadd(stock);
        }catch (Exception e){
            FruitAdd mangos = new FruitAdd("添加品类","");
            addList.add(mangos);
        }



    }

    @Override
    protected void initclassaddrefurbish() {

        while (addList.size() > 0&&adapteradd != null) {
            try {
                adapteradd.removeData(addList.size() - 1);
            } catch (Exception e) {

            }
        }

        List<Stock> stock = null;
        if("全部".equals(classyadd)||classyadd==null){
            stock = LitePal.findAll(Stock.class);

        }else{
            try{
                stock = LitePal.where("nameclass == ?", classyadd).find(Stock.class);
            }catch (Exception e){

            }

        }

        initclassadd(stock);

        //嫑忘记刷新适配器
        adapteradd.notifyDataSetChanged();


    }



    private void answerviewsaddclass() {


        adapterssaddclass.setOnRecyclerViewItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                Toast.makeText(MainActivity.this, "點擊了" + (position + 1), Toast.LENGTH_SHORT).show();
//                //  "\n"    是換行符 知道吧
//                Log.e("tag", "獲取到的左側點擊值為：" + (position + 1));
                //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                adapterssaddclass.setThisPosition(position);
                //嫑忘记刷新适配器
                adapterssaddclass.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(int position) {
//                Log.e("tag", "獲取到的左側長按點擊值為：" + (position + 1));
            }
        });

        recyclerViewssaddclass.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        egetxsaddclass = (int) e.getX();
                        egetysaddclass = (int) e.getY();
                        actiondownsaddclass++;
                        Log.e("TAG", "LinearLayout onTouch按住");
                        break;
                    case MotionEvent.ACTION_UP://1
                        View childView = recyclerViewssaddclass.findChildViewUnder(e.getX(), e.getY());
                        actionupaddclass(childView);
                        actionupsaddclass++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        actionmovesaddclass((int) e.getY(), (int) e.getX());
                        actionmovesaddclass++;
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

    private void actionupaddclass(final View childView) {
        if (childView != null) {
            viewsaddclass = childView;
            int position = recyclerViewssaddclass.getChildLayoutPosition(childView);
            Class fruit = classList.get(position);

             stringaddclass = fruit.getName();

            if (classyadd!=stringaddclass){
                classyadd=stringaddclass;

                while (addList.size() > 0&&adapteradd != null) {
                    try {
                        adapteradd.removeData(addList.size() - 1);
                    } catch (Exception e) {

                    }
                }


                List<Stock> stock = null;
                if("全部".equals(stringaddclass)){
                    stock = LitePal.findAll(Stock.class);

                }else{
                    stock = LitePal.where("nameclass == ?", stringaddclass).find(Stock.class);
                }

                initclassadd(stock);
//刷新适配器
                adapteradd.notifyDataSetChanged();
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
                            if (actiondownsaddclass == 1 && actiondownsaddclass == 1 && actionmovesaddclass == 0) {

                            }
                            actiondownsaddclass = 0;
                            actiondownsaddclass = 0;
                            actionmovesaddclass = 0;
                        }
                    });
                }
            }).start();
        }
    }

    private void actionmovesaddclass(int gety, int getx) {
        if ((gety - egetysaddclass) <= 0) {
            ynumbersaddclass = egetysaddclass - gety;
        } else {
            ynumbersaddclass = gety - egetysaddclass;
        }

        if (ynumbersaddclass > 300) {
            if (gety < egetysaddclass) {
                updownsaddclass = "up";
            } else {
                updownsaddclass = "down";
                if (ynumbersaddclass > 400 && numbersaddclass == 0) {
                    numbersaddclass++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            numbersaddclass = 0;
                        }
                    }).start();

                    updownsaddclass = "";
                }
            }
        }

        if ((getx - egetxsaddclass) >= 0) {
            xnumbersaddclass = getx - egetxsaddclass;
        } else {
            xnumbersaddclass = egetxsaddclass - getx;
        }
        if (xnumbersaddclass > 350) {
            if (xnumbersaddclass > 390) {
                if (getx < egetxaddclass) {
                    //大于0表示正在向右滚动
                    leftrightsaddclass = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsaddclass = "right";
                }
            } else {
                if (getx < egetxsaddclass) {
                    //大于0表示正在向右滚动
                    leftrightsaddclass = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsaddclass = "right";
                }
            }
        }
    }




    private void initclassadd(List<Stock>  stock) {


        for (Stock stocks : stock) {

            if(stocks.getDisplay()) {
                FruitAdd mango = new FruitAdd(stocks.getName(),"");
                addList.add(mango);
            }
        }

        FruitAdd mangos = new FruitAdd("添加品类","");
        addList.add(mangos);
    }
    protected void initclassaddrefurbishsactivity(){}


    protected void initclassaddrefurbishs() {




            while (addList.size() > 0&&adapteradd != null) {
                try {
                    adapteradd.removeData(addList.size() - 1);
                } catch (Exception e) {

                }
            }


            List<Stock> stock = null;
            if("全部".equals(stringaddclass)||stringaddclass==null){
                stock = LitePal.findAll(Stock.class);

            }else{
                stock = LitePal.where("nameclass == ?", stringaddclass).find(Stock.class);
            }

            initclassadd(stock);
//刷新适配器
            adapteradd.notifyDataSetChanged();
//                recyclerView.scrollToPosition(0);

    }





    private void adddata() {
        //动态添加string数组
//        String[] values = new String[]{};
//
//        for(int i=0;i<3;i++)    //在链接好数据库的实战中，这个地方2可以改为re.length
//        {
//
//            values = Arrays.copyOf(values, values.length+1);
//            values[values.length-1] = "第一行"; //在链接好数据库的实战中，这个地方第一行可以改为re.title
//
//        }


        fruitNumberadd.clearFocus(); //清除焦点
        try{
            //单位数据开关
            int numb=0;
            int numbs=0;
                try{
                numb = Integer.parseInt(fruitNumberadd.getText()+"");
                }catch (Exception e){ }
                try{
                    numbs = Integer.parseInt(fruitNumberadds.getText()+"")*fruitunitraddsnumber;
                }catch (Exception e){ }
            numb=numb+numbs;



            if(!"".equals(fruitNumberadd.getText()) && numb!=0){

                holderadd.fruitName.setText(numb+"("+fruitunitradd+")");
                holderadd.fruitView.setBackgroundColor(Color.GRAY);
                //修改数据开关
                boolean d = true;
                int i = 0;
                while (i < thisPosition.length) {

                    if (thisPosition[i].equals(toaststringadd)) {

                        thisPosition[++i] = numb + "";
                        d = false;
                        break;

                    }
                    i += 2;
                }

                if(d){

                    //记录名字
                    thisPosition = Arrays.copyOf(thisPosition, thisPosition.length+1);
                    thisPosition[thisPosition.length-1] = textnameadd.getText()+"";
                    //记录数字
                    thisPosition = Arrays.copyOf(thisPosition, thisPosition.length+1);
                    thisPosition[thisPosition.length-1] = numb+"";
                }

                adapteradd.setThisPosition(thisPosition);
            }else{
                holderadd.fruitView.setBackgroundColor(Color.WHITE);

                holderadd.fruitName.setText(toaststringadd);

                    int i = 0;
                    while (i < thisPosition.length) {

                        if (thisPosition[i].equals(toaststringadd)) {
                            thisPosition[i]="";
                            thisPosition[++i]="";
                            break;
                        }
                        i += 2;
                    }
                adapteradd.setThisPosition(thisPosition);
            }
        }catch(Exception e){

        }

        linearlayoutnumberadd.setVisibility(View.GONE);
        linearlayoutbutt.setVisibility(View.VISIBLE);
        LinearLayoutoverviewadd.setVisibility(View.VISIBLE);


    }
    private void answerviewsadd() {

        linearlayoutnumberadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//                linearlayoutnumberadd.setVisibility(View.GONE);
//                linearlayoutbutt.setVisibility(View.VISIBLE);
//                LinearLayoutoverviewadd.setVisibility(View.VISIBLE);
//                adddata();
            }
        });
        imagenumberadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                linearlayoutnumberadd.setVisibility(View.GONE);
                linearlayoutbutt.setVisibility(View.VISIBLE);
                LinearLayoutoverviewadd.setVisibility(View.VISIBLE);
//                adddata();
            }
        });

        recyclerViewadd.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        egetxsadd = (int) e.getX();
                        egetysadd = (int) e.getY();
                        actiondownsadd++;
                        Log.e("TAG", "LinearLayout onTouch按住");
                        break;
                    case MotionEvent.ACTION_UP://1
                        View childView = recyclerViewadd.findChildViewUnder(e.getX(), e.getY());
                        actionupadd(childView);
                        actionupsadd++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        actionmovesadd((int) e.getY(), (int) e.getX());
                        View childViews = recyclerViewadd.findChildViewUnder(e.getX(), e.getY());
                        actionmovesadd++;
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

    private void actionupadd(final View childView) {
        if (childView != null) {
            viewsadd = childView;
            positionadd = recyclerViewadd.getChildLayoutPosition(childView);
            holderadd = new FruitAdapterAdd.ViewHolder(childView);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int position = recyclerViewadd.getChildLayoutPosition(childView);
                            FruitAdd fruit = addList.get(position);

                            toaststringadd = fruit.getNume();

                            String toaststring = "";

                            if (fruit.getNume().length() > 8) {
                                toaststring = fruit.getNume().replaceAll(" ", "").substring(0, 8) + "...";
                            } else {
                                toaststring = fruit.getNume();
                            }


                            if (actiondownsadd == 1 && actiondownsadd == 1 && actionmovesadd == 0) {

                                if (selectnume.equals(fruit.getNume())) {
                                    selectnume = "";
                                    if ("添加品类".equals(fruit.getNume())) {
                                        Toast.makeText(childView.getContext(), "" + toaststring, Toast.LENGTH_SHORT).show();
                                    } else {
                                        if(fruit.getNume().equals(holderadd.fruitName.getText())){
                                            Toast.makeText(childView.getContext(), "点击图片取消选择：" + toaststring, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(childView.getContext(), "点击图片取消修改：" + toaststring, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    if ("添加品类".equals(fruit.getNume())) {
                                        LinearLayoutviewclassadds.setVisibility(View.VISIBLE);
                                        linearlayoutbutt.setVisibility(View.GONE);
                                        LinearLayoutoverviewadd.setVisibility(View.GONE);
                                        adapterclassadds.notifyDataSetChanged();
                                    } else {
                                        openmodify( fruit);
                                    }
                                } else {
                                    if ("添加品类".equals(fruit.getNume())) {
                                        Toast.makeText(childView.getContext(), "再次点击：" + toaststring, Toast.LENGTH_SHORT).show();
                                    } else {
                                        if(fruit.getNume().equals(holderadd.fruitName.getText())){
                                            Toast.makeText(childView.getContext(), "再次点击选择：" + toaststring, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(childView.getContext(), "再次点击修改：" + toaststring, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    selectnume = fruit.getNume();
                                }
                            }
                            actiondownsadd = 0;
                            actiondownsadd = 0;
                            actionmovesadd = 0;
                        }
                    });
                }
            }).start();
        }
//        linearlayoutnumberadd.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
////                        当键盘弹出隐藏的时候会 调用此方法。
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                //获取当前界面可视部分
//                XiaoPengAddActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//                //获取屏幕的高度
//                int screenHeight =  XiaoPengAddActivity.this.getWindow().getDecorView().getRootView().getHeight();
//                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
//                int heightDifference = screenHeight - r.bottom;
//                if(LinearLayoutoverviewadd.getVisibility()==View.VISIBLE&&screenHeight>0){
//                    //打开软键盘
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                }
//            }
//        });

        //按enter报错
        fruitNumberadd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //可以根据需求获取下一个焦点还是上一个
                View nextView = v.focusSearch(View.FOCUS_DOWN);
                if (nextView != null) {
                    nextView.requestFocus(View.FOCUS_DOWN);
                }
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                adddata();
                //这里一定要返回true
                return true;
            }
        });

    }
    public void openmodify(FruitAdd fruit){


        TextView textdataadd = (TextView) findViewById(R.id.text_data_add);
        fruitNumberadd.setText("");
        fruitNumberadds.setText("");

        linearlayoutnumberadd.setVisibility(View.VISIBLE);
        linearlayoutbutt.setVisibility(View.GONE);
        LinearLayoutoverviewadd.setVisibility(View.GONE);
        List<Stock> stock = LitePal.where("name == ?", fruit.getNume()).find(Stock.class);
        for (Stock stocks : stock) {
            textdataadd.setText("库存："+stocks.getNumber()/stocks.getUnivalent()+"("+stocks.getUnits()+")"+
                    (stocks.getNumber()-(stocks.getNumber()/stocks.getUnivalent())*stocks.getUnivalent())+ "("+stocks.getUnit()+")"+
                    "  合计："+stocks.getNumber()+"("+stocks.getUnit()+")");
            fruitNumberadd.setHint("修改的数量:单位("+stocks.getUnit()+")");
            fruitNumberadds.setHint("修改的数量:单位("+stocks.getUnits()+")");
            fruitunitradd=stocks.getUnit();
            fruitunitradds=stocks.getUnits();
            fruitunitraddsnumber=stocks.getUnivalent();
            if(stocks.getImageIds()!=null){
                Bitmap bitmap = BitmapFactory.decodeFile(stocks.getImageIds());
                imagenumberadd.setImageBitmap(bitmap);
            }else{
                imagenumberadd.setImageResource(stocks.getImageId());
            }
            break;
        }
        textnameadd.setText(fruit.getNume());
        //            adapteradd.setThisPosition(position);
        //            //嫑忘记刷新适配器
        //            adapteradd.notifyDataSetChanged();
        fruitNumberadd.requestFocus();//请求获取焦点
        //打开软键盘
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        recyclerViewadd.scrollToPosition(positionadd); // 将RecyclerView定位到最后一行
    }


//    public void hideInputWindow(Activity context){
//        if(context==null){
//            return;
//        }
//        final View v = ((Activity) context).getWindow().peekDecorView();
//        if (v != null && v.getWindowToken() != null) {
//            InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }
//    }




    private void actionmovesadd(int gety, int getx) {
        if ((gety - egetysadd) <= 0) {
            ynumbersadd = egetysadd - gety;
        } else {
            ynumbersadd = gety - egetysadd;
        }

        if (ynumbersadd > 300) {
            if (gety < egetysadd) {
                updownsadd = "up";
            } else {
                updownsadd = "down";
                if (ynumbersadd > 400 && numbersadd == 0) {
                    numbersadd++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            numbersadd = 0;
                        }
                    }).start();

                    updownsadd = "";
                }
            }
        }

        if ((getx - egetxsadd) >= 0) {
            xnumbersadd = getx - egetxsadd;
        } else {
            xnumbersadd = egetxsadd - getx;
        }
        if (xnumbersadd > 350) {
            if (xnumbersadd > 390) {
                if (getx < egetxadd) {
                    //大于0表示正在向右滚动
                    leftrightsadd = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsadd = "right";
                }
            } else {
                if (getx < egetxsadd) {
                    //大于0表示正在向右滚动
                    leftrightsadd = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsadd = "right";
                }
            }
        }
    }


    //筛选
    protected void aailyactivityscreenadd() {



        while (addList.size() > 0 && adapteradd != null) {
            try {
                adapteradd.removeData(addList.size() - 1);
            } catch (Exception e) {

            }
        }
        List<StockDay> stockDays = null;

//        if ("全部".equals(dailyclsa)) {
//            if ("全部".equals(cashier)) {
//                stockDays = LitePal.where("time >= ? ", sbi).find(StockDay.class);
//            } else {
//                stockDays = LitePal.where("time >= ?  and cashier == ?", sbi, cashier).find(StockDay.class);
//            }
//        } else {
//            if ("全部".equals(cashier)) {
//                stockDays = LitePal.where("time >= ?  and nameclass == ?", sbi, dailyclsa).find(StockDay.class);
//            } else {
//                stockDays = LitePal.where("time >= ? and nameclass == ? and cashier == ?", sbi, dailyclsa, cashier).find(StockDay.class);
//            }
//        }


        for (StockDay stockDay : stockDays) {

            FruitAdd mango = new FruitAdd(stockDay.getName(),"");
                addList.add(mango);
                List<Stock> stocks = LitePal.where("name == ?", stockDay.getName()).find(Stock.class);
                for (Stock stock : stocks) {

            }
        }

        //刷新适配器
        adapteradd.notifyDataSetChanged();

    }
}
