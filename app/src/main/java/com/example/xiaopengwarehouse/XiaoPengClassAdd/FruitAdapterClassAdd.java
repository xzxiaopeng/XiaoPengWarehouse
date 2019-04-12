package com.example.xiaopengwarehouse.XiaoPengClassAdd;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengDailyAdd.FruitAdd;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

public class FruitAdapterClassAdd extends RecyclerView.Adapter<FruitAdapterClassAdd.ViewHolder>{

    private List<FruitClassAdd> mFruitList;
    private XiaoPengAddClassActivity xiaoPengAddClassActivitys;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;

        EditText fruitnameclassadd;//名字
        EditText fruitunitnumberclassadd;//单位数量
        EditText fruitunitsnumberclassadd;//件制数量
        EditText fruitunitclassadd;//单位
        EditText fruitunistclassadd;//件制单位
        EditText fruitcostclassadd;//成本
        EditText fruitunitsnumbersclassadd;//件制个数
        EditText fruitclassclassadd;//分类

        TextView fruitimageclassaddbut;
        TextView fruitimageclassaddbutopen;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image_class_add);

            fruitnameclassadd = (EditText) view.findViewById(R.id.fruit_name_class_add);
            fruitunitnumberclassadd = (EditText) view.findViewById(R.id.fruit_unit_number_class_add);
            fruitunitsnumberclassadd = (EditText) view.findViewById(R.id.fruit_units_number_class_add);
            fruitunitclassadd = (EditText) view.findViewById(R.id.fruit_unit_class_add);
            fruitunistclassadd = (EditText) view.findViewById(R.id.fruit_unist_class_add);
            fruitcostclassadd = (EditText) view.findViewById(R.id.fruit_cost_class_add);
            fruitunitsnumbersclassadd = (EditText) view.findViewById(R.id.fruit_units_numbers_class_add);
            fruitclassclassadd = (EditText) view.findViewById(R.id.fruit_class_class_add);
            fruitimageclassaddbut = (TextView) view.findViewById(R.id.fruit_image_class_add_but);
            fruitimageclassaddbutopen = (TextView) view.findViewById(R.id.fruit_image_class_add_but_open);


        }
    }

    public FruitAdapterClassAdd(List<FruitClassAdd> fruitList  ,XiaoPengAddClassActivity xiaoPengAddClassActivity) {
        mFruitList = fruitList;
        xiaoPengAddClassActivitys=xiaoPengAddClassActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_adds, parent, false);
        final ViewHolder holder = new ViewHolder(view);



        xiaoPengAddClassActivitys.addclassactivity(holder);

        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        FruitClassAdd fruit = mFruitList.get(position);

        holder.fruitImage.setImageResource(R.drawable.add);
        holder.fruitnameclassadd.setText("");//名字
        holder.fruitunitnumberclassadd.setText("");//单位数量
        holder. fruitunitsnumberclassadd.setText("");//件制数量
        holder. fruitunitclassadd.setText("");//单位
        holder. fruitunistclassadd.setText("");//件制单位
        holder. fruitcostclassadd.setText("");//成本
        holder. fruitunitsnumbersclassadd.setText("");//件制个数
        holder. fruitclassclassadd.setText("");//分类


        xiaoPengAddClassActivitys.opensoftkeyboard();


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