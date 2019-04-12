package com.example.xiaopengwarehouse.XiaoPengDailyAdd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

public class FruitAdapterAdd extends RecyclerView.Adapter<FruitAdapterAdd.ViewHolder>{

    private List<FruitAdd> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image_add);
            fruitName = (TextView) view.findViewById(R.id.fruit_name_add);
        }
    }

    public FruitAdapterAdd(List<FruitAdd> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_add, parent, false);
        final ViewHolder holder = new ViewHolder(view);
//         设置图片大小
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(9*25,
//                16*25);//两个400分别为添加图片的大小
//        view.setLayoutParams(params);



        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FruitAdd fruit = mFruitList.get(position);

        if ("添加品类".equals(fruit.getNume())) {
            holder.fruitImage.setImageResource(R.drawable.add);
            holder.fruitName.setText(fruit.getNume());
        }
        ViewHolder viewViewHolder = (ViewHolder) holder;
        List<Stock> stock = LitePal.where("name == ?", fruit.getNume()).find(Stock.class);
        for (Stock stocks : stock) {

            if(stocks.getImageIds()!=null){
                Bitmap bitmap = BitmapFactory.decodeFile(stocks.getImageIds());
                holder.fruitImage.setImageBitmap(bitmap);
            }else{
                holder.fruitImage.setImageResource(stocks.getImageId());
            }


            if ("".equals(fruit.getNumber())) {
                holder.fruitName.setText(stocks.getName());
            } else {
                holder.fruitName.setText(fruit.getNumber());

                viewViewHolder.fruitView.setBackgroundColor(Color.GRAY);
            }
        }

        /**
         * 這裏是常 用/見  賦值的地方
         */
        if (viewViewHolder != null) {
            try {
                int i = 0;
                while (i < getthisPosition().length) {

                    if (getthisPosition()[i].equals(fruit.getNume())) {

                        break;
                    }
                    i += 2;
                }
                ///////////////////////////////////點擊變色/////////////////////////////////////////////

                if (Arrays.asList(getthisPosition()).contains(fruit.getNume())) {
                    //選中的顔色就設成了  黃色
                    viewViewHolder.fruitView.setBackgroundColor(Color.GRAY);
                    viewViewHolder.fruitName.setText(getthisPosition()[i + 1]);
                } else {
                    //未選中的顔色 就設成了 白色
                    viewViewHolder.fruitView.setBackgroundColor(Color.WHITE);
                    viewViewHolder.fruitName.setText(fruit.getNume());
                }
            } catch (Exception e) {
            }

            ////////////////////////////////////點擊變色////////////////////////////////////////////
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


    //先声明一个int成员变量
    private String[] thisPosition;

    //再定义一个int类型的返回值方法
    public String[] getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(String[] thisPosition) {
        this.thisPosition = thisPosition;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}