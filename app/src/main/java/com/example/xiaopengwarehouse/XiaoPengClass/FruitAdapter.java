package com.example.xiaopengwarehouse.XiaoPengClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengActiVity.MainActivity;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockClass;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;

import org.litepal.LitePal;
import org.litepal.exceptions.DataSupportException;

import java.util.Calendar;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    private List<Fruit> mFruitList;
    private MainActivity mMainActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitNumber;
        TextView fruitNumbers;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitNumber = (TextView) view.findViewById(R.id.fruit_number);
            fruitNumbers = (TextView) view.findViewById(R.id.fruit_numbers);
        }
    }

    public FruitAdapter(List<Fruit> fruitList, MainActivity mainActivity) {
        mFruitList = fruitList;
        mMainActivity = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
//         设置图片大小
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(9*25,
//                16*25);//两个400分别为添加图片的大小
//        view.setLayoutParams(params);

        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                shownume(fruit.getNume(),v);
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                shownume(fruit.getNume(),v);
            }
        });
        return holder;
    }

    private String nume=null;
    private int number=0;
    private boolean delete = false;
    //  删除数据
    public void shownume(String string,View v) {


        List<Stock> stock = LitePal.where("name == ?", string).find(Stock.class);
        for (Stock stocks : stock) {
            if(delete){
                if (nume == string) {
                    if (number < 5) {
                        number++;
                    }
                } else {
                    number = 0;
                    nume = null;
                }
                nume = string;
                if (number < 5) {
                    Toast.makeText(v.getContext(),

                                    "再点" + (5 - number) + "删除:" + string , Toast.LENGTH_LONG).show();
                } else {
                    number = 0;
                    Toast.makeText(v.getContext(),
                            stocks.getName() + "  已删除！！" ,Toast.LENGTH_LONG).show();
                    stocks.setDisplay(false);
                    stocks.setNumber(0);
                    stocks.save();
                    mMainActivity.removeDatas();
                }
            }else{
                Toast.makeText(v.getContext(),
                        "品名:" + stocks.getName() +
                                "\n成本:"+stocks.getCost()+"\n数量:" +
                                String.format("%d%s%d%s%d%s",
                                        stocks.getNumber(),
                                        "(" + stocks.getUnit() + ")\n合计:",
                                        (stocks.getNumber() / stocks.getUnivalent()),
                                        "(" + stocks.getUnits() + ")",
                                        (stocks.getNumber() - (stocks.getNumber() / stocks.getUnivalent()) * stocks.getUnivalent()),
                                        "(" + stocks.getUnit() + ")"), Toast.LENGTH_LONG).show();
            }

        }

    }

    //  删除数据
    public void removeDatasss() {

        if(delete){
            delete = false;
            Toast.makeText(mMainActivity,
                    "取消删除模式" ,Toast.LENGTH_LONG).show();
        }else {
            number = 0;
            nume = null;
            delete = true;
            Toast.makeText(mMainActivity,
                    "点击要删除的商品" ,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Fruit fruit = mFruitList.get(position);
        List<Stock> stock = LitePal.where("name == ?",fruit.getNume()).find(Stock.class);
        for(Stock stocks : stock){


            if(stocks.getImageIds()!=null){
                Bitmap bitmap = BitmapFactory.decodeFile(stocks.getImageIds());
                holder.fruitImage.setImageBitmap(bitmap);
            }else{
                holder.fruitImage.setImageResource(stocks.getImageId());
            }

            holder.fruitNumber.setText("("+stocks.getNumber()+stocks.getUnit()+")");
             int i=stocks.getUnivalent();
             i =(stocks.getNumber()/stocks.getUnivalent());

                i=(stocks.getNumber()-(stocks.getNumber()/stocks.getUnivalent())*stocks.getUnivalent());
             holder.fruitNumbers.setText(String.format("%d%s%d%s",
                     (stocks.getNumber()/stocks.getUnivalent()),
                     stocks.getUnits(),
                     (stocks.getNumber()-(stocks.getNumber()/stocks.getUnivalent())*stocks.getUnivalent()),
                     stocks.getUnit()));
        }


    }

    //  删除数据
    public void removeData(int position) {
        mFruitList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}