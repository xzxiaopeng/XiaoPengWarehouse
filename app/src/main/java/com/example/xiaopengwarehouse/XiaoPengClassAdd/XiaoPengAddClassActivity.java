package com.example.xiaopengwarehouse.XiaoPengClassAdd;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengActiVity.MainActivity;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClass.FruitAdapter;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.FruitAdapterAdd;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.FruitAdd;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.XiaoPengAddActivity;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockClass;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;
import com.example.xiaopengwarehouse.XiaoPengMenu.ClassAdapter;

import org.litepal.LitePal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XiaoPengAddClassActivity extends XiaoPengAddActivity {

    protected List<FruitClassAdd> fruitClassAdds = new ArrayList<FruitClassAdd>();
    protected RecyclerView recyclerviewclassadds;

    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;


    private Uri imageUri;

    private int numbersaddsclass = 0;
    private int egetxsaddsclass = 0;
    private int egetxaddsclass = 0;
    private int egetysaddsclass = 0;
    private int actiondownsaddsclass = 0;
    private int actionupsaddsclass = 0;
    private int actionmovesaddsclass = 0;
    private int ynumbersaddsclass = 0;
    private int xnumbersaddsclass = 0;
    private String updownsaddsclass = "";
    private String leftrightsaddsclass = "";
    private View viewsaddsclass;
    private int positionaddsclass;
    private FruitAdapterClassAdd.ViewHolder holders;
    private TextView recyclerviewclassaddstext;
    private TextView recyclerviewclassaddtext;
    private FruitAdapterClassAdd.ViewHolder holderaddsclass;

    private Bitmap bitmap;
    private File outputImage;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        recyclerviewclassadds = (RecyclerView) findViewById(R.id.recycler_view_class_adds);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);//列表翻转
        recyclerviewclassadds.setLayoutManager(layoutManager);
        adapterclassadds = new FruitAdapterClassAdd(fruitClassAdds, this);
        recyclerviewclassadds.setAdapter(adapterclassadds);


        FruitClassAdd mango = new FruitClassAdd(1);
        fruitClassAdds.add(mango);
        answerviewsadd();

        initPermission();

        recyclerviewclassaddtext = (TextView) findViewById(R.id.recycler_view_class_add_text);
        addclass();
        List<Stock> stock = LitePal.findAll(Stock.class);
        if (stock.size() == 0) {
            recyclerviewclassaddtext.setVisibility(View.GONE);
            LinearLayoutviewclassadds.setVisibility(View.VISIBLE);
            linearlayoutbutt.setVisibility(View.GONE);
            linearLayoutoverview.setVisibility(View.GONE);
        }

//        String inputText = load();
//        if (!TextUtils.isEmpty(inputText)) {
//            recyclerviewclassaddstext.setText(inputText);
//            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
//        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //cun chu wen jian
    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    //huo qu wen jian
    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    private void addclass() {

        recyclerviewclassaddtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Stock> stockss = LitePal.findAll(Stock.class);
                if (stockss.size() > 0) {
                    LinearLayoutviewclassadds.setVisibility(View.GONE);
                    linearlayoutbutt.setVisibility(View.VISIBLE);
                    LinearLayoutoverviewadd.setVisibility(View.VISIBLE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                }

            }
        });
        recyclerviewclassaddstext = (TextView) findViewById(R.id.recycler_view_class_adds_text);
        recyclerviewclassaddstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int i = 0;
                String name = "";
                List<Stock> stocks2 = LitePal.where("name == ?", holders.fruitnameclassadd.getText() + "").find(Stock.class);
                if ((stocks2.size() != 0)) {
                    for (Stock stock : stocks2) {
                        name = stock.getName().replaceAll("()", "");
                        if (!stock.getDisplay()) {
                            List<Stock> stocks1;

                            i = (Integer.parseInt(name.replaceAll(holders.fruitnameclassadd.getText() + "", "0")) + 1);
                            do {
                                name = (holders.fruitnameclassadd.getText() + "(" + (i++) + ")");
                                stocks1 = LitePal.where("name == ?", name).find(Stock.class);
                            } while (stocks1.size() != 0);

                        }
                    }
                }


                Stock stock = new Stock();
                String ok = "";
                int number = 0;

                try {


                    if (bitmap != null) {

                    } else {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "可以选择一张图片!  ";
                    }

                    try {

                        if (!"".equals(holders.fruitnameclassadd.getText() + "")) {


                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "商品还没有名字哦!  ";
                        }
                    } catch (Exception e) {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "商品的名字似乎有点问题  ";
                    }
                    try {
                        if (!"".equals(holders.fruitunitclassadd.getText() + "")) {
                            stock.setUnit(holders.fruitunitclassadd.getText() + "");//单位
                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "告诉我单位吧...";
                        }
                    } catch (Exception e) {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "你单位有点问题...";
                    }
                    try {

                        if (!"".equals(holders.fruitunistclassadd.getText() + "")) {
                            stock.setUnits(holders.fruitunistclassadd.getText() + "");//件制单位
                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "件制单位不见了！";
                        }
                    } catch (Exception e) {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "件制单位不可用！";
                    }
                    try {

                        if (!"".equals(holders.fruitcostclassadd.getText() + "")) {
                            stock.setCost(Float.parseFloat(holders.fruitcostclassadd.getText() + ""));//成本
                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "成本不能为零啊>>>";
                        }
                    } catch (Exception e) {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "成本不能为零啊>>>";
                    }
                    try {
                        if (!"".equals(holders.fruitclassclassadd.getText() + "")) {
                            //分类
                            List<StockClass> stockClasses = LitePal.where("element == ?", holders.fruitclassclassadd.getText() + "").find(StockClass.class);
                            if (0 == stockClasses.size()) {
                                StockClass stockClass = new StockClass();
                                stockClass.setElement(holders.fruitclassclassadd.getText() + "");
                                stockClass.save();
                            }
                            stock.setNameclass(holders.fruitclassclassadd.getText() + "");
                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "分类不能为空。。>>>";
                        }
                    } catch (Exception e) {

                    }
                    try {

                        if (!"".equals(holders.fruitunitsnumbersclassadd.getText() + "")) {
                            stock.setUnivalent(Integer.parseInt(holders.fruitunitsnumbersclassadd.getText() + ""));//件制个数
                        } else {
                            if (!"".equals(ok)) {
                                ok += "\n";
                            }
                            ok += "告诉我一件有几个数。。。";
                        }
                    } catch (Exception e) {
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "告诉我一件有几个数。。。";
                    }


                    try {
                        number += Integer.parseInt(holders.fruitunitnumberclassadd.getText() + "");//单位数量
                    } catch (Exception e) {

                    }
                    try {
                        //件制数量
                        number += Integer.parseInt(holders.fruitunitsnumberclassadd.getText() + "") * Integer.parseInt(holders.fruitunitsnumbersclassadd.getText() + "");
                    } catch (Exception e) {

                    }
                    try {
                        stock.setNumber(number);
                    } catch (Exception e) {

                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "爆仓了，注意控制总量。";
                    }


                } catch (Exception e) {
                    ok = "0";
                }
                if ("".equals(ok)) {
                    try {
                        imagePath = main(imagePath);
                        stock.setImageIds(imagePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (!"".equals(ok)) {
                            ok += "\n";
                        }
                        ok += "要重新选择一张图片了! ";
                    }

                }
                if ("".equals(ok)) {


                    List<Stock> stockss = LitePal.findAll(Stock.class);
                    if (stockss.size() == 0) {
                        LinearLayoutviewclassadds.setVisibility(View.GONE);
                        linearlayoutbutt.setVisibility(View.VISIBLE);
                        linearLayoutoverview.setVisibility(View.VISIBLE);
                    } else {
                        LinearLayoutviewclassadds.setVisibility(View.GONE);
                        linearlayoutbutt.setVisibility(View.VISIBLE);
                        LinearLayoutoverviewadd.setVisibility(View.VISIBLE);
                    }

                    if (recyclerviewclassaddtext.getVisibility() == View.GONE) {
                        recyclerviewclassaddtext.setVisibility(View.VISIBLE);
                    }
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                    stock.setDisplay(true);

                    if (stocks2.size() != 0) {
                        stock.setName(holders.fruitnameclassadd.getText() + "(" + (i - 1) + ")"); //名字
                        Toast.makeText(v.getContext(), "这个名字之前有用过呢！自动换成：" + (holders.fruitnameclassadd.getText() + "(" + (i - 1) + ")"), Toast.LENGTH_SHORT).show();
                    } else {
                        stock.setName(holders.fruitnameclassadd.getText() + ""); //名字
                    }

                    stock.save();


                    if (number > 0) {
                        Calendar c = Calendar.getInstance();
                        StockDay stockDay = new StockDay();
                        if (stocks2.size() != 0) {
                            stockDay.setName(holders.fruitnameclassadd.getText() + "(" + (i - 1) + ")"); //名字
                        } else {
                            stockDay.setName(holders.fruitnameclassadd.getText() + ""); //名字
                        }
                        stockDay.setNameclass(holders.fruitclassclassadd.getText() + "");
                        stockDay.setCashier("入库");
                        stockDay.setNumber(number);
                        stockDay.setTime(Integer.parseInt(String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE))));
                        stockDay.setTimes(Integer.parseInt(String.format("%02d%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))));
                        stockDay.save();


                        modifydeadline();

                        aailyactivityscreen();

                        modifydeadlinetext();

                    }


                    initFruitdaily();
                    initFruitclass();


                    initclassaddrefurbishs();
                    initclassaddrefurbishsactivity();
                    //刷新页面
                    adapters.notifyDataSetChanged();
                    answerviewsadds();
                    adapterssaddclass.notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(), ok, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void answerviewsadds() {
        while (fruitList.size() > 0 && adapter != null) {
            try {
                adapter.removeData(fruitList.size() - 1);

            } catch (Exception e) {

            }
        }


        List<Stock> stock = null;
        if ("全部".equals(classy)) {
            stock = LitePal.findAll(Stock.class);

        } else {
            stock = LitePal.where("nameclass == ?", classy).find(Stock.class);
        }

        for (
                Stock stocks : stock) {
            if (stocks.getDisplay()) {
                Fruit mango = new Fruit(stocks.getName());
                fruitList.add(mango);
            }
        }

//刷新适配器
        adapter.notifyDataSetChanged();
    }

    private void answerviewsadd() {


        recyclerviewclassadds.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        egetxsaddsclass = (int) e.getX();
                        egetysaddsclass = (int) e.getY();
                        actiondownsaddsclass++;
                        Log.e("TAG", "LinearLayout onTouch按住");
                        break;
                    case MotionEvent.ACTION_UP://1
                        View childView = recyclerviewclassadds.findChildViewUnder(e.getX(), e.getY());
                        actionupaddsclass(childView);
                        actionupsaddsclass++;
                        Log.e("TAG", "LinearLayout onTouch抬起");
                        break;
                    case MotionEvent.ACTION_MOVE://2
                        actionmovesaddsclass((int) e.getY(), (int) e.getX());
                        View childViews = recyclerviewclassadds.findChildViewUnder(e.getX(), e.getY());
                        actionmovesaddsclass++;
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


    private void actionupaddsclass(final View childView) {
        if (childView != null) {
            viewsaddsclass = childView;
            positionaddsclass = recyclerviewclassadds.getChildLayoutPosition(childView);
            holderaddsclass = new FruitAdapterClassAdd.ViewHolder(childView);
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
                            if (actiondownsaddsclass == 1 && actiondownsaddsclass == 1 && actionmovesaddsclass == 0) {

                            }
                            actiondownsaddsclass = 0;
                            actiondownsaddsclass = 0;
                            actionmovesaddsclass = 0;
                        }
                    });
                }
            }).start();
        }
    }

    private void actionmovesaddsclass(int gety, int getx) {
        if ((gety - egetysaddsclass) <= 0) {
            ynumbersaddsclass = egetysaddsclass - gety;
        } else {
            ynumbersaddsclass = gety - egetysaddsclass;
        }

        if (ynumbersaddsclass > 300) {
            if (gety < egetysaddsclass) {
                updownsaddsclass = "up";
            } else {
                updownsaddsclass = "down";
                if (ynumbersaddsclass > 400 && numbersaddsclass == 0) {
                    numbersaddsclass++;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            numbersaddsclass = 0;
                        }
                    }).start();

                    updownsaddsclass = "";
                }
            }
        }

        if ((getx - egetxsaddsclass) >= 0) {
            xnumbersaddsclass = getx - egetxsaddsclass;
        } else {
            xnumbersaddsclass = egetxsaddsclass - getx;
        }
        if (xnumbersaddsclass > 350) {
            if (xnumbersaddsclass > 390) {
                if (getx < egetxaddsclass) {
                    //大于0表示正在向右滚动
                    leftrightsaddsclass = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsaddsclass = "right";

                }
            } else {
                if (getx < egetxsaddsclass) {
                    //大于0表示正在向右滚动
                    leftrightsaddsclass = "left";
                } else {
                    //小于等于0表示停止或向左滚动
                    leftrightsaddsclass = "right";
                }
            }
        }
    }




    public void addclassactivity(FruitAdapterClassAdd.ViewHolder holder) {
        holders = holder;


        //打开相机
        holder.fruitimageclassaddbutopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPermission();


// 创建File对象，用于存储拍照后的图片
                outputImage = new File(getExternalCacheDir(), "output_image.jpg");


                imagePath = outputImage.getPath();

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(XiaoPengAddClassActivity.this, "com.example.xiaopengwarehouse.fileprovider", outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            }
        });
        //选择照片
        holder.fruitimageclassaddbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPermission();
                if (ContextCompat.checkSelfPermission(XiaoPengAddClassActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(XiaoPengAddClassActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });


    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }


    public void opensoftkeyboard() {
        //打开软键盘
        holders.fruitnameclassadd.requestFocus();//请求获取焦点
        if (imm == null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }

        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }







    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        holders.fruitImage.setImageBitmap(bitmap);
                        addclassshowkeyboard(imm);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void addclassshowkeyboard(final InputMethodManager imms) {
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
                        imms.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                });
            }
        }).start();



//            EditText fruitunitnumberclassadd;//单位数量
//            EditText fruitunitsnumberclassadd;//件制数量
//            EditText fruitunitclassadd;//单位
//            EditText fruitunistclassadd;//件制单位
//            EditText fruitcostclassadd;//成本
//            EditText fruitunitsnumbersclassadd;//件制个数
//            EditText fruitclassclassadd;//分类


        if ("".equals(holders.fruitnameclassadd.getText() +"") ) {
            holders.fruitnameclassadd.requestFocus();//请求获取焦点 名字
        } else
        if ("".equals(""+holders.fruitunitnumberclassadd.getText())) {
            holders.fruitunitnumberclassadd.requestFocus();//单位数量
        } else
        if ("".equals(""+holders.fruitunitsnumberclassadd.getText())) {
            holders.fruitunitsnumberclassadd.requestFocus();//件制数量
        } else
        if ("".equals(""+holders.fruitunitclassadd.getText())) {
            holders.fruitunitclassadd.requestFocus();//单位
        } else
        if ("".equals(""+holders.fruitunistclassadd.getText())) {
            holders.fruitunistclassadd.requestFocus();//件制单位
        } else
        if ("".equals(""+holders.fruitcostclassadd.getText()) ) {
            holders.fruitcostclassadd.requestFocus();//成本
        } else
        if ("".equals(""+holders.fruitunitsnumbersclassadd.getText())) {
            holders.fruitunitsnumbersclassadd.requestFocus();//件制个数
        }else
        if ("".equals(holders.fruitclassclassadd.getText()+"") ) {
            holders.fruitclassclassadd.requestFocus();//分类
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {

            addclassshowkeyboard(imm);

            bitmap = BitmapFactory.decodeFile(imagePath);



            holders.fruitImage.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }









        public static String main(String string) throws IOException {






        Calendar c = Calendar.getInstance();

// 创建File对象，用于存储拍照后的图片
        String data="/storage/emulated/0/Android/data/com.example.xiaopengwarehouse/image/"+
                "output_image" +
                c.get(Calendar.YEAR) +
                (c.get(Calendar.MONTH)+1 )+
                c.get(Calendar.DATE) +
                c.get(Calendar.HOUR_OF_DAY) +
                c.get(Calendar.MINUTE) +
                c.get(Calendar.SECOND) + ".jpg";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            //找到目标文件
            File file = new File( string );
            File descFile = new File( data);

            //如果没有该目录就新建一个目录
            File fileParent = descFile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            descFile.createNewFile();


            //建立数据输入输出通道
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(descFile);
            //建立缓冲输入输出通道
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //边读边写
            int content = 0;
            while((content = bufferedInputStream.read()) != -1){//read()方法返回值是读取到的内容
                bufferedOutputStream.write(content);
//                bufferedOutputStream.flush();  //这里加了flush()的话效率就会变低、但后面必须加close方法，把内存中的数据输出
            }
        }catch(IOException e){
            System.out.println("打开资源文件出现异常。。。。");
            throw new RuntimeException(e);
        }finally{
            try {
                if(bufferedOutputStream != null){
                    bufferedOutputStream.close();
                    System.out.println("缓冲输出字节流关闭成功。。。。");
                }
            } catch (IOException e) {
                System.out.println("缓冲输出字节流关闭失败。。。。");
                throw new RuntimeException(e);
            }finally{
                try{
                    if(bufferedOutputStream != null){
                        bufferedInputStream.close();
                        System.out.println("缓冲输入字节流关闭成功。。。。");
                    }
                }catch(IOException e){
                    System.out.println("缓冲输入字节流关闭失败。。。。");
                    throw new RuntimeException(e);
                }
            }
        }




        return data;
    }



    /**
     * android 6.0 以上需要动态申请权限
     */
    protected void initPermission() {

        String[] permissions = {

                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
        };

        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


}
