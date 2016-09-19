package com.example.tom.one.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.one.R;
import com.example.tom.one.adapter.HomePageAdapter;
import com.example.tom.one.bean.HomePageNum;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp.OkUntils;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{

    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> numlist = new ArrayList<>();
    String path = "http://v3.wufazhuce.com:8000/api/hp/idlist/0";
    HomePageAdapter homePageAdapter;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                int lenth = numlist.size();
                for (int i = 0; i < lenth; i++) {
                    Fragment fragment = new ChildHomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("num",numlist.get(i));
                    fragment.setArguments(bundle);
                    fragmentList.add(fragment);
                }
                homePageAdapter = new HomePageAdapter(getActivity().getSupportFragmentManager(),
                        fragmentList);
                viewPager.setAdapter(homePageAdapter);
                homePageAdapter.notifyDataSetChanged();
            }
        }
    };
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.home_vp);
        TextView textView = (TextView) view.findViewById(R.id.tv_home_like);
        getdata(path,view);
//        textView.setText(getArguments().getInt("share"));
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    public void getdata(final String path,View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = OkUntils.getdata(path);
                Gson gson = new Gson();
                HomePageNum homePageNum = gson.fromJson(data, HomePageNum.class);
                numlist = homePageNum.getData();
                handler.obtainMessage(1).sendToTarget();
//                int size = list.size();
//                for (int i = 0; i < size; i++) {
//                    numlist.add(list.get(i));
//                }
            }
        }).start();
    }




//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("====","==onPageScrolled=="+position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("====","==onPageSelected=="+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
