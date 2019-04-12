package com.example.xiaopengwarehouse.XiaoPengDaily;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaopengwarehouse.R;
import com.example.xiaopengwarehouse.XiaoPengClass.Fruit;
import com.example.xiaopengwarehouse.XiaoPengClass.FruitAdapter;
import com.example.xiaopengwarehouse.XiaoPengData.Data.Stock;
import com.example.xiaopengwarehouse.XiaoPengData.Data.StockDay;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{

    private List<Daily> mdailyList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View dailyView;
        ImageView dailyImage;
        TextView dailystock;
        TextView dailycashier;
        TextView dailyname;
        TextView dailytime;

        public ViewHolder(View view) {
            super(view);
            dailyView = view;
            dailyImage = (ImageView) view.findViewById(R.id.daily_image);
//            dailystock = (TextView) view.findViewById(R.id.daily_stock);
            dailyname = (TextView) view.findViewById(R.id.daily_name);
            dailycashier = (TextView) view.findViewById(R.id.daily_cashier);
            dailytime = (TextView) view.findViewById(R.id.daily_time);
        }
    }

    public DailyAdapter(List<Daily> dailyList) {
        mdailyList = dailyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);


        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Daily daily = mdailyList.get(position);



        List<StockDay> stockDays = LitePal.where("name == ? and time == ? and times == ?and number==?",daily.getNume(),daily.getTime()+"",daily.getTimes()+"",daily.getNumber()+"").find(StockDay.class);
        for(StockDay stockDayss : stockDays){

            if(daily.getNume().length()<=4){
                holder.dailyname.setText(daily.getNume());
            }else{
                holder.dailyname.setText(daily.getNume().replaceAll(" ","").substring(0,4));
            }


            CharSequence charSequence;


//            holder.dailytime.setText("时间:"+stockDayss.getTime()+"");
//            String content = "时间:"+"<small>"+stockDayss.getTime()+"</small>";
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
//            } else {
//                charSequence = Html.fromHtml(content);
//            }
//            holder.dailycashier.setText(charSequence);

            List<Stock> stock = LitePal.where("name == ?",daily.getNume()).find(Stock.class);
            for(Stock stocks : stock){

                if(stocks.getImageIds()!=null){
                    Bitmap bitmap = BitmapFactory.decodeFile(stocks.getImageIds());
                    holder.dailyImage.setImageBitmap(bitmap);
                }else{
                    holder.dailyImage.setImageResource(stocks.getImageId());
                }



                String content = stockDayss.getCashier()+": ";

                if(stockDayss.getNumber()/stocks.getUnivalent()!=0){
                    content+=(stockDayss.getNumber()/stocks.getUnivalent())+stocks.getUnits();
                }

                if((stockDayss.getNumber()-(stockDayss.getNumber()/stocks.getUnivalent())*stocks.getUnivalent())!=0){
                    content+=(stockDayss.getNumber()-(stockDayss.getNumber()/stocks.getUnivalent())*stocks.getUnivalent())+stocks.getUnit() ;
                }
                content+= ("<small>"+"("+stockDayss.getNumber()+stocks.getUnit()+")"+"</small>");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
                } else {
                    charSequence = Html.fromHtml(content);
                }
                holder.dailycashier.setText(charSequence);

                DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.

                content = "成本: "+(decimalFormat.format(stockDayss.getNumber()*stocks.getCost()))+"元    ";

                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%02d-%02d-%02d  %02d:%02d",
                        stockDayss.getTime()/10000-(stockDayss.getTime()/10000/10)*10,
                        (stockDayss.getTime()-(stockDayss.getTime()/10000)*10000)/100,
                        stockDayss.getTime()-(stockDayss.getTime()/10000)*10000-((stockDayss.getTime()-(stockDayss.getTime()/10000)*10000)/100)*100,
                        stockDayss.getTimes()/100,
                        stockDayss.getTimes()-stockDayss.getTimes()/100*100));

                content+="<small>"+"("+sb+")"+"</small>";


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    charSequence = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
                } else {
                    charSequence = Html.fromHtml(content);
                }
                holder.dailytime.setText(charSequence);
            }
        }
    }

    //  删除数据
    public void removeData(int position) {
        mdailyList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mdailyList.size();
    }





}