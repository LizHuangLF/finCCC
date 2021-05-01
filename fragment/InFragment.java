package com.exam.closet_f.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.exam.closet_f.R;
import com.exam.closet_f.activity.ItemAddActivity;
import com.exam.closet_f.activity.ItemAlbumActivity;
import com.exam.closet_f.activity.ItemShowActivity;
import com.exam.closet_f.util.FragCallBack;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InFragment extends Fragment {


//    static InFragment iff;
//    private TextView tv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public InFragment() {
          final String ARG_PARAM1 = "param1";
          final String ARG_PARAM2 = "param2";
        // Required empty public constructor
    }

//    public static InFragment getInFragment(){
//
//        if(iff == null){
//            iff = new InFragment();
//        }
//        return iff;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InFragment newInstance(String param1, String param2) {
        InFragment fragment = new InFragment();
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
        View view = inflater.inflate(R.layout.fragment_in, container, false);
        initData(view);
//        tv = view.findViewById(R.id.tv);
//        //获取Bundle 然后获取数据
//        Bundle bundle = this.getArguments();//得到从Activity传来的数据
//        String title = null;
//        if (bundle != null) {
//            title = bundle.getString("title");
//        }
//        ///设置显示数据
//        tv.setText(title);
        if (mFragCallBack != null) {
            mFragCallBack.test(111);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    //监听回调
    FragCallBack mFragCallBack;

    ///onAttach 当 Fragment 与 Activity 绑定时调用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ///获取绑定的监听
        if (context instanceof FragCallBack) {
            mFragCallBack = (FragCallBack) context;
        }
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    ///onDetach 当 Fragment 与 Activity 解除绑定时调用
    @Override
    public void onDetach() {
        super.onDetach();
        mFragCallBack = null;
//        mListener = null;
    }

    @OnClick({R.id.tv_all, R.id.tv_dall, R.id.lt_all, R.id.btn_in,
            R.id.iv_upper,R.id.iv_bottom, R.id.iv_suit, R.id.iv_footgear, R.id.iv_bagcap, R.id.iv_other})
    public void onViewClicked(View view) {
        int itemID;
        switch (view.getId()) {
            case R.id.tv_all:
                break;
            case R.id.tv_dall:
                break;
            case R.id.lt_all:
                startActivity(new Intent(getActivity(), ItemAlbumActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.btn_in:
                startActivity(new Intent(getActivity(), ItemAddActivity.class));
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.iv_upper:
                itemID = 0;
                goItemActivity(itemID);
                break;
            case R.id.iv_bottom:
                break;
            case R.id.iv_suit:
                break;
            case R.id.iv_footgear:
                break;
            case R.id.iv_bagcap:
                break;
            case R.id.iv_other:
                break;
        }
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

    private void initData(View v) {
//        btnIn = v.findViewById(R.id.btn_in);
//        btnIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ItemAddActivity.class));
//                Objects.requireNonNull(getActivity()).finish();
//            }
//        });

//        ltAll = v.findViewById(R.id.lt_all);
//        ltAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ItemAlbumActivity.class));
//                Objects.requireNonNull(getActivity()).finish();
//            }
//        });

//        tvUpper = v.findViewById(R.id.tv_upper);
//        tvUpper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemID = 0;
//                goItemActivity(itemID);
//            }
//        });

//        tvBottom = v.findViewById(R.id.tv_bottom);
//        tvFootgear = v.findViewById(R.id.tv_footgear);
//        tvBagcap = v.findViewById(R.id.tv_bagcap);
//        tvSuit = v.findViewById(R.id.tv_suit);
//        tvOther = v.findViewById(R.id.tv_other);


    }


    public void goItemActivity(int ID) {
        Intent intent = new Intent(getActivity(), ItemShowActivity.class);
        intent.putExtra("itemID", ID);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }


}
