package com.exam.closet_f.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.exam.closet_f.R;
import com.exam.closet_f.activity.CCCActivity;
import com.exam.closet_f.activity.LogisterActivity;
import com.exam.closet_f.util.OkHttpUtils;
import com.exam.closet_f.util.PublicUtil;
import com.mob.MobSDK;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.wrapper.TokenVerifyResult;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodeFragment extends Fragment {
    Toolbar toolbar;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.tv_idrepose)
    TextView tvIdrepose;
    @BindView(R.id.tv_sendcode)
    TextView tvSendcode;
    @BindView(R.id.et_getcode)
    EditText etGetcode;
    @BindView(R.id.tv_coderepose)
    TextView tvCoderepose;
    @BindView(R.id.btn_sure)
    Button btnSure;

    private Unbinder unbinder;
    String account,code,way,reCode,accountO,url,pop;
    int change;     boolean LoorRe;
    boolean acExist,afterCode;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CodeFragment() {
        // Required empty public constructor
        //acExist = false; //账户不存在
        change = 0;
        accountO = "";
        afterCode = false;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CodeFragment newInstance(String param1, String param2) {
        CodeFragment fragment = new CodeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        url = getResources().getString(R.string.url_logister);

        init(view);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


    public void onDestroy() {
        super.onDestroy();
        // 使用完EventHandler需注销，否则可能出现内存泄漏
        SMSSDK.unregisterEventHandler(eh);
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick({R.id.btn_sure,R.id.tv_sendcode})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.tv_sendcode:  sendCode(); break;
            case R.id.btn_sure:     forSure();  break;
        }
    }

    private void sendCode(){
        tvIdrepose.setText("");
        tvCoderepose.setText("");
        account = etId.getText().toString();
        boolean phone = PublicUtil.isPhone(account);
        boolean email = PublicUtil.isEmail(account);

        if(account != null   && !"".equals(account) ){
            Log.i("----------","yes");
            if (phone || email){
                if (phone) { way = "phone";  Log.i("----way", "way is phone"); }
                if (email) { way = "email";  Log.i("----way", "way is email");}

                //请求账号是否存在
                HashMap<String,String> paramsAC = PublicUtil.putParams(account,"",way,PublicUtil.POP_AcExist);
                Log.i("---params",""+paramsAC);
                try{
                    OkHttpUtils.doGetSync(url,paramsAC,myHandlerAC);
                    Log.i("---tip","okHttpUtils");
                }catch (Exception e){
                    e.printStackTrace();
                    e.getMessage();
                }

                switch (pop) {
                    case PublicUtil.POP_Login:
                        if(acExist){
                            //允许发送验证码
                            allowCode(account);
                        }else{
                            Toast.makeText(getActivity(),"该账号未注册",Toast.LENGTH_SHORT).show();
                            //写一个dialog是否进行注册
                            //确定 toolbar.title改成注册验证  LoorRe = false; 直接发验证码进行注册
                            //否 停在当前界面
                        }
                        break;
                    case PublicUtil.POP_Register:
                        if(!acExist){
//                            允许发送验证码
                            allowCode(account);
                        }else Toast.makeText(getActivity(),"该账号已注册",Toast.LENGTH_SHORT).show();
                        break;

                    default:    break;
                }

//                if(!acExist && LoorRe){//账号不存在进行登录
//                    Toast.makeText(getActivity(),"账户尚未注册，是否注册",Toast.LENGTH_SHORT).show();
//
//
//                }
//                if (acExist && !LoorRe){//账号存在进行注册
//                    Toast.makeText(getActivity(),"该账号已注册",Toast.LENGTH_SHORT).show();
//                    tvIdrepose.setText("该账号已注册");
//                }
//                if((acExist && LoorRe) || (!acExist && !LoorRe)){//账号存在进行登录 或 账号不存在进行注册
//                    //在使用SDK功能之前调用即可  发送即表示同意隐私协议
//                    MobSDK.submitPolicyGrantResult(true, null);
//                    //    注册一个事件回调监听，用于处理SMSSDK接口请求的结果
//                    SMSSDK.registerEventHandler(eh);
//                    SMSSDK.getVerificationCode("86",account);
////                    Log.i("***","验证短信发送成功");
//                    accountO = account;
//                }

            }else   tvIdrepose.setText("请输入正确格式的手机号码或邮箱");

        }else   Toast.makeText(getActivity(),"验证账号不能为空",Toast.LENGTH_SHORT).show();

    }

    private void forSure(){
        code = etGetcode.getText().toString();
        if("".equals(accountO)){
            Toast.makeText(getActivity(),"请发送验证码",Toast.LENGTH_SHORT).show();
        }else {
            if(!"".equals(code)){
//            在这里完成验证码验证
                SMSSDK.registerEventHandler(eh);
                SMSSDK.submitVerificationCode("86",accountO,code);

            }else   Toast.makeText(getActivity(),"请完成验证码填写",Toast.LENGTH_SHORT).show();
        }
        if(afterCode){
            Toast.makeText(getActivity(),"验证成功",Toast.LENGTH_SHORT).show();
            switch (pop){
                case PublicUtil.POP_Login:
                    Log.i("loorre---","登录");
                    Intent intentLo = new Intent(getActivity(),CCCActivity.class);
                    intentLo.putExtra("account",accountO);
//                                intentLo.putExtra("page",0);
                    startActivity(intentLo);
                    Objects.requireNonNull(getActivity()).finish();
                    break;
                case PublicUtil.POP_Register:
                    Log.i("loorre---","注册");
                    Intent intentRe = new Intent(getActivity(),LogisterActivity.class);
                    intentRe.putExtra("account",accountO);
                    intentRe.putExtra("page",1);
                    startActivity(intentRe);
                    Objects.requireNonNull(getActivity()).finish();
                    break;
                default:    break;
            }
        }
    }

    //    注册一个事件回调监听，用于处理SMSSDK接口请求的结果
    Handler msHandler = new Handler();
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
//            // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            msHandler.sendMessage(msg);
            Log.i("*******","event"+event);
            Log.i("*******","result"+result);
            Log.i("*******","data"+data);

            switch (result){
                case SMSSDK.RESULT_COMPLETE://成功回调
                    Log.i("***","成功回调");
                    switch (event){
                        case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                            //提交短信、语音验证码成功
                            Log.i("***","提交短信验证成功");
                            afterCode = true;
                            break;
                        case  SMSSDK.EVENT_GET_VERIFICATION_CODE:
                            //获取短信验证码成功
                            Log.i("***", "获取短信验证成功");
                            Toast.makeText(getActivity(),"验证码已发送",Toast.LENGTH_SHORT).show();
                            boolean smart = (Boolean) data;
                            if (smart) {//通过Mob云验证
                                Log.i("***", "mob");
                            } else {//依然走短信验证
                                Log.i("***", "短信验证");
                            }break;
                        default:
                            Log.i("***", "验证失败");
                            Toast.makeText(getActivity(),"验证失败",Toast.LENGTH_SHORT).show();
                            break;
                    }break;
                case  SMSSDK.RESULT_ERROR://失败回调
                    Log.i("***","回调失败");
                    break;
                default://其他失败回调
                    ((Throwable) data).printStackTrace();
                    Log.i("***","其他回调失败");
                    break;
            }
        }
    };

//            if (result == SMSSDK.RESULT_COMPLETE) {
//                //成功回调
//                Log.i("***","成功回调");
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    //提交短信、语音验证码成功
//                    Log.i("***","提交短信验证成功");
//                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                    //获取短信验证码成功
//                    Log.i("***","获取短信验证成功");
//
//                    boolean smart = (Boolean) data;
//                    if (smart) {
//                        //通过Mob云验证
//                        Log.i("***","mob");
//
//                    } else {
//                        //依然走短信验证
//                        Log.i("***","短信验证");
//
//                    }
//                } else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
                    //获取语音验证码成功
//                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
//                }else if (event == SMSSDK.EVENT_GET_VERIFY_TOKEN_CODE) {
                    //本机验证获取token成功
//                    TokenVerifyResult tokenVerifyResult = (TokenVerifyResult) data;
                    //SMSSDK.login(phoneNum,tokenVerifyResult);
//                }else if (event == SMSSDK.EVENT_VERIFY_LOGIN) {
                    //本机验证登陆成功
//                }
//            } else if (result == SMSSDK.RESULT_ERROR) {
//                //失败回调
//                Log.i("***","回调失败");
//
//            } else {
//                //其他失败回调
//                ((Throwable) data).printStackTrace();
//                Log.i("***","其他回调失败");
//
//            }


//    @SuppressLint("HandlerLeak")
//    final Handler myHandler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            String response = msg.obj.toString();
//            Log.i("---response", response);
//            if (msg.obj == null) {
//                Toast.makeText(getActivity(), "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
//            } else if ("id||pwd WRONG".equals(response)) {
//                Toast.makeText(getActivity(), "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
////            } else if ("LoginSUCC".equals(response)) {
//            }else{//
//                Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
////                Bundle bundle = new Bundle();
////                bundle.putString("uname",response);
//                Intent intent = new Intent();
////                intent.putExtras(bundle);
////                intent.putExtra("username",response);//传值
//                intent.setClass(Objects.requireNonNull(getActivity()), CCCActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        }
//    };

    @SuppressLint("HandlerLeak")
    private final Handler myHandlerAC = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String response = msg.obj.toString();
            Log.i("---response", response);
            if (msg.obj == null) {
                Toast.makeText(getActivity(),"网络连接失败，请稍后重试",Toast.LENGTH_SHORT).show();
            }else{
                switch (response){
                    case PublicUtil.RESULT__AcNotExist:
                        acExist = false;
                        break;
                    case  PublicUtil.RESULT__AcExist:
                        acExist = true;
                        break;
                    default:    break;
                }
            }
        }
    };

    public void allowCode(String account){
        //在使用SDK功能之前调用即可  发送即表示同意隐私协议
        MobSDK.submitPolicyGrantResult(true, null);
        //    注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eh);
        SMSSDK.getVerificationCode("86",account);
//                    Log.i("***","验证短信发送成功");
        Toast.makeText(getActivity(),"验证码已发送",Toast.LENGTH_SHORT).show();
        accountO = account;
    }

    private void init(View v) {
        toolbar = v.findViewById(R.id.toolbar);
//        toolbar.setBackgroundResource(R.color.Deepblue);
//        toolbar.setTitleTextColor();

        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            pop = bundle.getString("pop");Log.i("***************",pop);
            switch (pop){
                case PublicUtil.POP_Login:     toolbar.setTitle("登录验证");    break;
                case PublicUtil.POP_Register:  toolbar.setTitle("注册验证");    break;
                default:    toolbar.setTitle("验证");       break;
            }
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentLo = new Intent(getActivity(), LogisterActivity.class);
                intentLo.putExtra("page", 0);
                startActivity(intentLo);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

    }
}
