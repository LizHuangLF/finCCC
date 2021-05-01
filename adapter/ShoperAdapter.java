package com.exam.closet_f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.exam.closet_f.R;
import com.exam.closet_f.bean.Shoper;
import com.exam.closet_f.fragment.ShoperFragment;

import java.util.List;

public class ShoperAdapter extends BaseAdapter {
    private List<Shoper> list;
    private Context mContext;
    private ShoperFragment shoperFragment;

    public ShoperAdapter(ShoperFragment fragment,List<Shoper> list,Context mContext){
        shoperFragment = fragment;
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListitemView listView;
        if(convertView == null){
            listView = new ListitemView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listv_shoper,null);
            Shoper shoper = list.get(position);
            listView.ivPic = convertView.findViewById(R.id.iv_pic);
            listView.tvDate = convertView.findViewById(R.id.tv_date);
            listView.tvType = convertView.findViewById(R.id.tv_type);
            listView.tvItem = convertView.findViewById(R.id.tv_item);
            listView.tvWorks = convertView.findViewById(R.id.tv_works);
            listView.tvPrice = convertView.findViewById(R.id.tv_price);

            listView.ivPic.setBackgroundResource(shoper.getPic());
            listView.tvDate.setText(shoper.getDate());
            listView.tvType.setText(shoper.getType());
            listView.tvItem.setText(shoper.getItem());
            listView.tvWorks.setText(shoper.getWorks());
            listView.tvPrice.setText(shoper.getPrice());
            convertView.setTag(convertView);
        }
        return convertView;
    }

    private class ListitemView{
        ImageView ivPic;
        TextView tvDate;
        TextView tvFrom;
        TextView tvType;
        TextView tvItem;
        TextView tvWorks;
        TextView tvPrice;
    }
}
