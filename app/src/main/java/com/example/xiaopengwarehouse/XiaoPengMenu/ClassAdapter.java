package com.example.xiaopengwarehouse.XiaoPengMenu;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;

import java.util.List;

public  class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>{

    private List<Class> mFruitList;
    private int numb=0;

    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        TextView fruitNume;


        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitNume = (TextView) view.findViewById(R.id.aclass_nume);
        }
    }



    public ClassAdapter(List<Class> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        final ClassAdapter.ViewHolder holder = new ClassAdapter.ViewHolder(view);


        if((numb++) == 0){

//            views = view;
//            view.setBackgroundColor(Color.BLUE);
        }

        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Class aClass = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + aClass.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }




    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, final int position) {

        /**
         * 這裏是常 用/見  賦值的地方
         */
        ViewHolder viewViewHolder = (ViewHolder) holder;
        if (viewViewHolder != null) {
            viewViewHolder.fruitNume.setText(mFruitList.get(position) + "");

            ///////////////////////////////////點擊變色/////////////////////////////////////////////
            if (position == getthisPosition()) {
                //選中的顔色就設成了  黃色
                viewViewHolder.fruitNume.setBackgroundColor(Color.WHITE);
            } else {
                //未選中的顔色 就設成了 白色
                viewViewHolder.fruitNume.setBackgroundColor(Color.LTGRAY);
            }
            ////////////////////////////////////點擊變色////////////////////////////////////////////
        }

        /**
         * 這裏是設置  點擊/長按  的事件的地方
         */
        if (onRecyclerViewItemClickListener != null) {
            //點擊事件
            viewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 拿到上面暴露的接口  的點擊方法  裏面的值和點擊事件的position  相互賦值  保持一致
                     * 算了，越説越亂，自己去理解吧
                     */
                    onRecyclerViewItemClickListener.onClick(position);
                }
            });
            //長按事件
            viewViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onRecyclerViewItemClickListener.onLongClick(position);
                    return false;
                }
            });
            //
        }

        Class aClass = mFruitList.get(position);
        holder.fruitNume.setText(aClass.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    //  删除数据
    public void removeData(int position) {
        mFruitList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onRecyclerViewItemClickListener;

}