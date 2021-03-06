package com.exam.closet_f.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exam.closet_f.R;

import java.util.List;

public class imgPopupwindow extends PopupWindow {

//        private int mWidth;
//        private int mHeight;
//        private View mConvertView;
//        private ListView mListView;
//        private List<PicFolderBean> mDatas;
//        private OnDirSelectedListener mListener;
//
//        public imgPopupwindow(Context context, List<PicFolderBean> datas) {
//            calWidthAndHeight(context);
//            mConvertView = LayoutInflater.from(context).inflate(R.layout.popup_main,null);
//            mDatas = datas;
//            setContentView(mConvertView);
//            setWidth(mWidth);
//            setHeight(mHeight);
//            setFocusable(true);
//            setTouchable(true);
//            setOutsideTouchable(true);
//            setBackgroundDrawable(new BitmapDrawable());
//            setTouchInterceptor(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE){
//                        dismiss();
//                        return true;
//                    }
//                    return false;
//                }
//            });
//            initView(context);
//            initEvent();
//        }
//
//        private void initView(Context context) {
//            mListView = mConvertView.findViewById(R.id.id_list_dir);
//            mListView.setAdapter(new ListDirAdapter(context,mDatas));
//        }
//
//        private void initEvent(){
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if (mListener!=null){
//                        mListener.onSelected(mDatas.get(position));
//                    }
//                }
//            });
//        }
//
//        private void calWidthAndHeight(Context context) {
//            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            DisplayMetrics outMetrics = new DisplayMetrics();
//            wm.getDefaultDisplay().getMetrics(outMetrics);
//            mWidth = outMetrics.widthPixels;
//            mHeight = (int) (outMetrics.heightPixels*0.7);
//        }
//
//        private class ListDirAdapter extends ArrayAdapter<PicFolderBean> {
//
//            private LayoutInflater mInflater;
//            private List<PicFolderBean> mDatas;
//
//            public ListDirAdapter(Context context,List<PicFolderBean> objects) {
//                super(context,0,objects);
//                mInflater = LayoutInflater.from(context);
//            }
//
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                ViewHolder holder = null;
//                if (convertView == null){
//                    holder = new ViewHolder();
//                    convertView = mInflater.inflate(R.layout.item_popup_showall,parent,false);
//                    holder.mImg = convertView.findViewById(R.id.id_id_dir_item_image);
//                    holder.mDirName = convertView.findViewById(R.id.id_dir_item_name);
//                    holder.mDirCount = convertView.findViewById(R.id.id_dir_item_count);
//                    convertView.setTag(holder);
//                }else{
//                    holder = (ViewHolder) convertView.getTag();
//                }
//                PicFolderBean bean = getItem(position);
//                //??????
//                holder.mImg.setImageResource(R.mipmap.ic_noimg);
//                ImageLoader.getInstance().loadImage(bean.getFirstImgPath(),holder.mImg);
//                holder.mDirCount.setText(bean.getCount()+"");
//                holder.mDirName.setText(bean.getName());
//                return convertView;
//            }
//
//            private class ViewHolder{
//                ImageView mImg;
//                TextView mDirName;
//                TextView mDirCount;
//            }
//        }
//
//        public interface OnDirSelectedListener{
//            void onSelected(PicFolderBean folderBean);
//        }
//
//        public void setmListener(OnDirSelectedListener mListener) {
//            this.mListener = mListener;
//        }

}
