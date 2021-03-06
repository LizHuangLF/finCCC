package com.exam.closet_f.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.exam.closet_f.R;
import com.exam.closet_f.fragment.InFragment;
import com.exam.closet_f.fragment.MineFragment;
import com.exam.closet_f.fragment.ModelFragment;
import com.exam.closet_f.fragment.RecordFragment;
import com.exam.closet_f.util.FragCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//https://blog.csdn.net/zl18603543572/article/details/108466070?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161590347516780255243996%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=161590347516780255243996&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-108466070.pc_search_result_before_js&utm_term=android+fragment%E4%B8%8Eactivity%E4%BC%A0%E5%80%BC

public class CCCActivity extends AppCompatActivity implements FragCallBack {
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.bo_in)
    RadioButton rbIn;
    @BindView(R.id.bo_record)
    RadioButton rbRecord;
    @BindView(R.id.bo_model)
    RadioButton rbModel;
    @BindView(R.id.bo_mine)
    RadioButton rbMine;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.bottom)
    LinearLayout bottom; RadioButton rb;
    String uname = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccc);
        ButterKnife.bind(this);
        initData();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            uname = bundle.getString("uname");
        }

//        fragment ??????
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case 0://??????
                setIndexSelected(0);rbIn.setChecked(true);      break;
            case 1://??????
                setIndexSelected(1);rbRecord.setChecked(true);  break;
            case 2:    //??????
                setIndexSelected(2);rbModel.setChecked(true);   break;
            case 3:    //??????
                setIndexSelected(3);rbMine.setChecked(true);    break;
        }
//        if (id == 0) {//??????
//            setIndexSelected(0);
//            rbIn.setChecked(true);
//        } else if (id == 1) {//??????
//            setIndexSelected(1);
//            rbRecord.setChecked(true);
//        } else if (id == 2) {//??????
//            setIndexSelected(2);
//            rbModel.setChecked(true);
//        } else {//??????
//            setIndexSelected(3);
//            rbMine.setChecked(true);
//        }
    }

    /*
    ?????????????????????fragment??????
     */
    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    /*
   ??????index??????????????????????????????
    */
    public void setIndexSelected(int index) {
        switch (index) {
            case 0: showInFragment();       break;
            case 1: showRecordFragmenr();   break;
            case 2: showModelFragment();    break;
            case 3: showMineFragment();     break;
            default: break;
        }
    }

    private void initData() {

        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = rbIn;
        rbs[1] = rbRecord;
        rbs[2] = rbModel;
        rbs[3] = rbMine;
        for (RadioButton rb : rbs) {
            //???????????????RadioButton??????drawable?????????????????????????????????
            Drawable[] drawables = rb.getCompoundDrawables();
            //??????drawables
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 3, drawables[1].getMinimumHeight() * 2 / 3);
            //????????????Rect??????
            drawables[1].setBounds(r);
            //????????????radiobutton??????drawable??????
//            if (rb.getId() == R.id.rb_more) {
//                r = new Rect(0, 0, drawables[1].getMinimumWidth(), drawables[1].getMinimumHeight());
//                drawables[1].setBounds(r);
//            }
            //?????????????????????
            rb.setCompoundDrawables(null, drawables[1], null, null);
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0;i<rg.getChildCount();i++){
                    rb = (RadioButton)group.getChildAt(i);
                    if(rb.isChecked()){
                        setIndexSelected(i);
                        break;
                    }
                }
            }
        });
    }

    private void showInFragment() {
        //?????? Fragment ??????
        InFragment in = new InFragment();
        //?????? Bundle
        Bundle bundleIN = new Bundle();
        //????????????
        bundleIN.putString("title", "??????????????????");
        //?????? Fragment
        in.setArguments(bundleIN);
        changeFragment(in);
    }

    private void showRecordFragmenr() {
        RecordFragment record = new RecordFragment();
//        recordFragment record = recordFragment.newInstance("????????????");
        changeFragment(record);

    }

    private void showModelFragment() {
//        dialogFrag frag = new dialogFrag();
//        frag.show(getSupportFragmentManager(),"show");
        ModelFragment model = new ModelFragment();
        changeFragment(model);
    }

    private void showMineFragment() {
        //?????? Fragment ??????
        MineFragment mine = new MineFragment();
        //?????? Bundle
        Bundle bundleMine = new Bundle();
        //????????????
        bundleMine.putString("uname", uname);
        //?????? Fragment
        mine.setArguments(bundleMine);
        changeFragment(mine);
    }

    @Override
    public void test(int flag) {
        Log.i("---------", "" + flag);
    }

    @OnClick(R.id.rg)
    public void onViewClicked() {

    }
//    private void showRecordFragmenr(){
//        //?????? Fragment ??????
//        // ??????????????????????????????????????????
//        RecordFragment record = RecordFragment.newInstance("????????????");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, record);
//        fragmentTransaction.commit();
//    }
}
