package com.exam.closet_f.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exam.closet_f.R;
import com.exam.closet_f.adapter.ShoperAdapter;
import com.exam.closet_f.bean.Shoper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoperFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int[] pics = new int[]{R.drawable.up_shirt_xin_o,R.drawable.up_short_white};
    private String[] dates = new String[]{"2021/03/30","2021/04/01"};
    private String[] froms = new String[]{"搭配","搭配"};
    private String[] types = new String[]{"上装","上装"};
    private String[] items = new String[]{"衬衫","短袖"};
    private String[] works = new String[]{"内搭","内搭"};
    private String[] prices = new String[]{"99","65"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShoperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoperFragment newInstance(String param1, String param2) {
        ShoperFragment fragment = new ShoperFragment();
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
        View view = inflater.inflate(R.layout.fragment_shoper, container, false);
        initData(view);
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

    private void initData(View v){
        ListView listView = v.findViewById(R.id.lv_shoper);

        List<Shoper> solist = new ArrayList<>();
        for (int i = 0; i < pics.length;i++){
            Shoper shoper = new Shoper();
            shoper.setPic(pics[i]);
            shoper.setDate(dates[i]);
            shoper.setFrom(froms[i]);
            shoper.setType(types[i]);
            shoper.setItem(items[i]);
            shoper.setWorks(works[i]);
            shoper.setPrice(prices[i]);

            solist.add(shoper);
        }
        ShoperAdapter adapter = new ShoperAdapter(this,solist,getContext());
        listView.setAdapter(adapter);

    }
}
