package com.exam.closet_f.util;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentHostCallback;

import java.util.HashMap;

public class PublicUtil {
    public final static String POP_Login = "login";
    public final static String POP_Register = "register";
    public final static String POP_AcExist = "acExist";
    public final static String WAY_Tel = "phone";
    public final static String WAY_Em= "email";
    public final static String  RESULT__AcExist = "acExist";        //账号已存在
    public final static String  RESULT__AcNotExist= "acNotExist";     //账号不存在
    public final static String  RESULT__RegisterSUCC= "RegisterSUCC";   //注册成功
    public final static String  RESULT__LoginSUCC= "LoginSUCC";      //登录成功
    public final static String  RESULT__PwdWRONG= "pwdWRONG";       //密码错误


    public static boolean isPhone(String phone) {
        return phone.matches("^(1)\\d{10}$");
    }

    public static boolean isEmail(String email){
        return email.matches("(\\w+)([\\w-\\.])*@(\\w+([\\w-])*.)+[a-zA-Z]{2,3}");
    }

    public static boolean isCode(String code){
        return code.matches("^\\d{6}$");
    }

    public static boolean isPwd(String pwd){
        return pwd.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$");
        //必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间
    }

    public static boolean isSame(String a,String b){
        return a.equals(b);
    }

    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.equals("") || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String maskEmail(String email) {
        if (email == null || email.equals("")) {
            return email;
        }
        return email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
    }

    public static HashMap<String,String> putParams(String account,String password,String way,String pop){
        final HashMap<String,String> params=new HashMap<String, String>();
        params.put("account",account);
        params.put("password",password);
        params.put("way",way);
        params.put("pop",pop);
        return params;
    }

    public static void getRespose(String response,Context context ){
        String str;
        switch (response){
            case RESULT__AcExist:      str = "账号存在";   break;
            case RESULT__AcNotExist:   str = "账号不存在";   break;
            case RESULT__RegisterSUCC: str = "注册成功";   break;
            case RESULT__LoginSUCC:    str = "登录成功";   break;
            case RESULT__PwdWRONG:     str = "密码错误";   break;
            default:                   str = "失败";     break;
        }
        Toast.makeText(context,""+str,Toast.LENGTH_SHORT).show();
    }

    public static Toast mTextToast = null;
    public static Toast mViewToast = null;

    public static void ToastShort(Context context, String str){
        if(null == mTextToast){
            mTextToast = new Toast(context);
        }
        mTextToast.setDuration(Toast.LENGTH_SHORT);
        mTextToast.setText(str);
        mTextToast.setView(null);
        mTextToast.show();
    }
    public static void ToastLong(Context context, String str){
        if(null == mTextToast){
            mTextToast = new Toast(context);
        }
        mTextToast.setDuration(Toast.LENGTH_LONG);
        mTextToast.setText(str);
        mTextToast.setView(null);

        mTextToast.show();
    }

    public static void showToast(Context context, View view){
        if(null == mViewToast){
            mViewToast = new Toast(context);
        }
        mViewToast.setDuration(Toast.LENGTH_SHORT);
        mViewToast.setView(view);
        mViewToast.show();
    }

//    public static void ToastShort(Toast toast,String str){
//        toast.setText(str);
//        toast.setDuration(Toast.LENGTH_SHORT);
////        toast.setView();
//        toast.show();
//    }
//
//    public static void ToastLong(Toast toast,String str){
//        toast.setText(str);
//        toast.setDuration(Toast.LENGTH_LONG);
////        toast.setView();
//        toast.show();
//    }
}
