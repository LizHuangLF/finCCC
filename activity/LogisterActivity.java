package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.LoginFilter;

import com.exam.closet_f.R;
import com.exam.closet_f.fragment.CodeFragment;
import com.exam.closet_f.fragment.LoginFragment;
import com.exam.closet_f.fragment.RecordFragment;
import com.exam.closet_f.fragment.RegisterFragment;

public class LogisterActivity extends AppCompatActivity {
    private Fragment fragment;
    private String pop,account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logister);
        initFrag();


    }

    private void initFrag(){
//        登录lo、注册re都有验证
        pop = getIntent().getStringExtra("pop");
        account = getIntent().getStringExtra("account");

        //  Fragment 切换 0登录 1注册 2验证
        int page = getIntent().getIntExtra("page",0);

        switch (page){
            case 0: showLoginFrag();    break;
            case 1: showRegisterFrag(); break;
            case 2: showCodeFrag();     break;
        }
    }

    private void showLoginFrag(){
        LoginFragment loginFragment = new LoginFragment();
        changeFragment(loginFragment);
    }
    private void showRegisterFrag(){
        RegisterFragment registerFragment = new RegisterFragment();
        Bundle bundleRe = new Bundle();
        bundleRe.putString("account",account);
        registerFragment.setArguments(bundleRe);
        changeFragment(registerFragment);
    }
    private void showCodeFrag(){
        CodeFragment codeFragment = new CodeFragment();
        Bundle bundleCo = new Bundle();
        bundleCo.putString("pop",pop);
        codeFragment.setArguments(bundleCo);
        changeFragment(codeFragment);
    }
   //开启事务，加载fragment布局
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }


}
