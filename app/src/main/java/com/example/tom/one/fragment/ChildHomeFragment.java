package com.example.tom.one.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tom.one.R;
import com.example.tom.one.bean.HomePagePic;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import okhttp.OkUntils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildHomeFragment extends Fragment {


    HomePagePic.DataBean dataBean;
    ImageView imageView;
    TextView tv_child_left;
    TextView tv_child_author;
    TextView tv_child_text;
    TextView tv_child_time;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                dataBean = (HomePagePic.DataBean) msg.obj;
                Picasso.with(getActivity()).load(dataBean.getHp_img_original_url()).into(imageView);
                tv_child_left.setText(dataBean.getHp_title());
                tv_child_author.setText(dataBean.getHp_author());
                tv_child_text.setText(dataBean.getHp_content());
                tv_child_time.setText(dataBean.getHp_makettime());
//                int sharenum = dataBean.getSharenum();
//                Bundle bundle = new Bundle();
//                bundle.putInt("share",sharenum);
//                setArguments(bundle);
            }
        }
    };

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

        return view;
    }

    //页面可见加载数据，解决ViewPager预加载问题
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String num = getArguments().getString("num");
                String path = "http://v3.wufazhuce.com:8000/api/hp/detail/"+ num;
                String data = OkUntils.getdata(path);
                Gson gson = new Gson();
                HomePagePic homePagePic = gson.fromJson(data, HomePagePic.class);
                handler.obtainMessage(3, homePagePic.getData()).sendToTarget();
            }
        }).start();

    }
}
