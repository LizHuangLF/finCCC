package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;

public class MatchActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView title,over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //        添加自定义toolbar
        LinearLayout toolbarm=findViewById(R.id.toolbarm);
        View barView = LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm);
//        LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm,false);取消
//        toolbarm.setBackgroundColor(getResources().getColor(R.color.colorQOXPurple));
        initData(barView);
    }

    private void initData(View barView){
        ivBack = barView.findViewById(R.id.iv_back);
        title = barView.findViewById(R.id.tv_title);
        title.setText("搭配信息");
        over = barView.findViewById(R.id.tv_over);
        over.setText("编辑");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchActivity.this,CCCActivity.class));
                finish();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证是否可以进行提交
                //1 图片是否为空
//                2其余数据是否错误
                Toast.makeText(getApplication(),"完成",Toast.LENGTH_SHORT).show();
//                返回该衣服类别的全部图
            }
        });
    }
}
