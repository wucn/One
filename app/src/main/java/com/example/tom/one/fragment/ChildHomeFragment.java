package com.example.tom.one.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tom.one.R;
import com.example.tom.one.bean.HomePagePic;
import com.example.tom.one.callback.OkhttpCallBack;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import com.example.tom.one.okhttp.OkUntils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildHomeFragment extends LazyFragment implements OkhttpCallBack {


    HomePagePic.DataBean dataBean;
    ImageView imageView;
    TextView tv_child_left;
    TextView tv_child_author;
    TextView tv_child_text;
    TextView tv_child_time;
    TextView tv_home_like;
    RelativeLayout relativeLayout;
    OkUntils okUntils;
    private Boolean isPrepared;


    public ChildHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_home, container, false);
        imageView = (ImageView) view.findViewById(R.id.iv_child_home);
        tv_child_left = (TextView) view.findViewById(R.id.tv_child_left);
        tv_child_author = (TextView) view.findViewById(R.id.tv_child_author);
        tv_child_text = (TextView) view.findViewById(R.id.tv_child_text);
        tv_child_time = (TextView) view.findViewById(R.id.tv_child_time);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rela_home);
//        tv_home_like= (TextView) relativeLayout.findViewById(R.id.tv_home_like);
        tv_home_like = (TextView) view.findViewById(R.id.tv_home_love);
        isPrepared = true;
        lazyLoad();

        return view;
    }

    //页面可见加载数据，解决ViewPager预加载问题
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void lazyLoad() {
        if (isVisible && (isPrepared != null)) {
            if (isPrepared) {
                okUntils = OkUntils.getInstance();
                String num = getArguments().getString("num");
                String path = "http://v3.wufazhuce.com:8000/api/hp/detail/" + num;
                okUntils.getdata(path);
                okUntils.setOkhttpCallBack(this);
            }
        }
    }

    @Override
    public void setOnCallBack(String data) {
        Gson gson = new Gson();
        HomePagePic homePagePic = gson.fromJson(data, HomePagePic.class);
        dataBean = homePagePic.getData();
        Picasso.with(getActivity())
                .load(dataBean.getHp_img_original_url())
                .placeholder(R.mipmap.default_hp_image)
                .into(imageView);
        tv_child_left.setText(dataBean.getHp_title());
        tv_child_author.setText(dataBean.getHp_author());
        tv_child_text.setText(dataBean.getHp_content());
        tv_child_time.setText(dataBean.getHp_makettime());
        tv_home_like.setText(String.valueOf(dataBean.getSharenum()));
    }
}
