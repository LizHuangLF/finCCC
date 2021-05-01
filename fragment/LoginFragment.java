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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.exam.closet_f.R;
import com.exam.closet_f.activity.CCCActivity;
import com.exam.closet_f.activity.LogisterActivity;
import com.exam.closet_f.util.OkHttpUtils;
import com.exam.closet_f.util.PublicUtil;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment{
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.tv_idrepose)
    TextView tvIdrepose;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_pwdrepose)
    TextView tvPwdrepose;
    @BindView(R.id.tv_lobycode)
    TextView tvLobycode;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    String url;
    String account, password, way,pop = "login";
    private Unbinder unbinder;
    boolean acExist = false;
    Toast toast;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this,view);
        toast = new Toast(getActivity());
        init();
        url = getResources().getString(R.string.url_logister);
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

    @OnClick({R.id.tv_lobycode,R.id.tv_register})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_lobycode:
                Intent intentLo = new Intent(getActivity(), LogisterActivity.class);
                intentLo.putExtra("page", 2);
                intentLo.putExtra("pop",PublicUtil.POP_Login);
                startActivity(intentLo);
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.tv_register:
                Intent intentRe = new Intent(getActivity(), LogisterActivity.class);
                intentRe.putExtra("page", 2);
                intentRe.putExtra("pop",PublicUtil.POP_Register);
                startActivity(intentRe);
                Objects.requireNonNull(getActivity()).finish();
                break;

//
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        tvIdrepose.setText("");
        account = etId.getText().toString();
        password = etPwd.getText().toString();

        boolean phone,email;
//        phone= true;    email = true;
        phone = PublicUtil.isPhone(account);
        email = PublicUtil.isEmail(account);
        Log.i("----accountType", "phone-" + phone + "+email-" + email);

        if(account != null   && !"".equals(account)) {
            if(password != null && !"".equals(password)) {
                Log.i("----password","not null");
                if (phone || email) {
                    if (phone){ way = PublicUtil.WAY_Tel;   Log.i("----way", "way is phone");}
                    if (email){ way =  PublicUtil.WAY_Em;   Log.i("----way", "way is email");}

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
                    if(acExist) {//存在再登录
                        HashMap<String, String> params = PublicUtil.putParams(account,password,way,PublicUtil.POP_Login);
                        Log.i("---params",""+params);
                        try{
//                        OkHttpUtils.doPostSync(url,params,myHandler);
                            OkHttpUtils.doGetSync(url,params,myHandler);
                            Log.i("---tip","okHttpUtils");
//                    }catch (IOException e){
                        }catch (Exception e){
                            e.printStackTrace();
                            e.getMessage();
                        }
                    }
                } else {
                    tvIdrepose.setText("请输入正确格式的手机号码或邮箱");}
            }else {
                    Toast.makeText(getActivity(),"密码不能为空",Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(getActivity(),"账户不能为空",Toast.LENGTH_LONG).show();
    }

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
                        PublicUtil.getRespose(response,getActivity());
                        break;
                    case  PublicUtil.RESULT__AcExist:
                        acExist = true;
                        break;
                    default:    break;
                }
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String response = msg.obj.toString();
            Log.i("---response", response);
            if (msg.obj == null) {
                Toast.makeText(getActivity(),"网络连接失败，请稍后重试",Toast.LENGTH_SHORT).show();
            }else{
                PublicUtil.getRespose(response,getActivity());

                switch (response){
                    case PublicUtil.RESULT__LoginSUCC:
//                    Bundle bundle = new Bundle();
//                bundle.putString("uname",response);
                        Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.putExtra("username",response);//传值
                        intent.setClass(Objects.requireNonNull(getActivity()), CCCActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case PublicUtil.RESULT__PwdWRONG:
                        Log.i("----pwd"," is WRONG");
                        break;
                    default:    break;
                }
            }

        }
    };



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

    private void init() {
//        llAll.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                llAll.setFocusable(true);
//                llAll.setFocusableInTouchMode(true);
//                llAll.requestFocus();
//                InputMethodManager imm = getActivity().getSystemService(INPUT_METHOD_SERVICE);(InputMethodManager) getSystemService();
//                imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
//                return false;
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
