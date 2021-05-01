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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    Toolbar toolbar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_pwdrepose)
    TextView tvPwdrepose;
    @BindView(R.id.et_repwd)
    EditText etRepwd;
    @BindView(R.id.tv_repwdrepose)
    TextView tvRepwdrepose;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    private Unbinder unbinder;
    String url,account,pwd,repwd,way;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
//        pwd = "";
//        repwd = "";
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this,view);
        initData(view);
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

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        tvPwdrepose.setText("");
        tvRepwdrepose.setText("");
        pwd = etPwd.getText().toString();
        repwd = etRepwd.getText().toString();

        boolean pwdf,repwdf,check;

        if(pwd != null  && !"".equals(pwd)){
            pwdf = PublicUtil.isPwd(pwd);
            if(pwdf){
                if(repwd != null    && !"".equals(repwd)){
                    repwdf = PublicUtil.isSame(pwd,repwd);
                    if(repwdf){
                        check = checkbox.isChecked();
                        if(check){
                            //请求数据库服务
                            HashMap<String, String> params = PublicUtil.putParams(account,pwd,way,PublicUtil.POP_Register);
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
                        }else Toast.makeText(getActivity(),"请勾选协议",Toast.LENGTH_SHORT).show();
                    }else tvRepwdrepose.setText("密码不一致");

                }else Toast.makeText(getActivity(),"请确认密码",Toast.LENGTH_SHORT).show();
            }else tvPwdrepose.setText("密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间");
        }else Toast.makeText(getActivity(),"请输入密码",Toast.LENGTH_SHORT).show();
    }


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

                if (PublicUtil.RESULT__RegisterSUCC.equals(response)) {
//                    Bundle bundle = new Bundle();
//                bundle.putString("uname",response);
                    Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.putExtra("username",response);//传值
                    intent.setClass(Objects.requireNonNull(getActivity()), LogisterActivity.class);
                    intent.putExtra("page",0);
                    startActivity(intent);
                    getActivity().finish();
                }else   Toast.makeText(getActivity(),"注册失败",Toast.LENGTH_SHORT).show();
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

    private void initData(View v) {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if(bundle != null){
            account = bundle.getString("account");
            Boolean way1;
            assert account != null;
            way1 = PublicUtil.isPhone(account);
            if(way1)    way = "phone";
            else way = "email";
        }

        toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("注册");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
