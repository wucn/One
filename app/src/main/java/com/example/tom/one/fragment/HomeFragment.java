package com.example.tom.one.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tom.one.R;
import com.example.tom.one.adapter.HomePageAdapter;
import com.example.tom.one.bean.HomePageNum;
import com.example.tom.one.callback.OkhttpCallBack;
import com.example.tom.one.content.ContentInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.example.tom.one.okhttp.OkUntils;


public class HomeFragment extends LazyFragment implements ViewPager.OnPageChangeListener, OkhttpCallBack {

    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> numlist = new ArrayList<>();
    OkUntils okUntils;
    HomePageAdapter homePageAdapter;

    private Boolean isPrepared;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.home_vp);
//        TextView textView = (TextView) view.findViewById(R.id.tv_home_like);
//        getdata(ContentInterface.HOME.HOME_PATH,view);
//        textView.setText(getArguments().getInt("share"));
        isPrepared = true;
        lazyLoad();
        return view;
    }

    public void getdata(final String path,View view){

    }


    @Override
    protected void lazyLoad() {
        if(isVisible&&(isPrepared!=null)) {
            if (isPrepared){
                okUntils = OkUntils.getInstance();
                okUntils.getdata(ContentInterface.HOME.HOME_PATH);
                okUntils.setOkhttpCallBack(this);
                viewPager.addOnPageChangeListener(this);
            }
        }

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
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setOnCallBack(String data) {
        Gson gson = new Gson();
        HomePageNum homePageNum = gson.fromJson(data, HomePageNum.class);
        numlist = homePageNum.getData();
        int lenth = numlist.size();
        for (int i = 0; i < lenth; i++) {
            Fragment fragment = new ChildHomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("num",numlist.get(i));
            fragment.setArguments(bundle);
            if (fragment!=null){
                fragmentList.add(fragment);
            }
        }
        homePageAdapter = new HomePageAdapter(getActivity().getSupportFragmentManager(),
                fragmentList);
        viewPager.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();
    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
