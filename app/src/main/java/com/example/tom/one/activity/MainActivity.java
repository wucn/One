package com.example.tom.one.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tom.one.rewrite.NoScrollViewPager;
import com.example.tom.one.R;
import com.example.tom.one.adapter.ViewPageAdapter;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    NoScrollViewPager viewPager;
    RadioButton rb_home;
    RadioButton rb_read;
    RadioButton rb_music;
    RadioButton rb_movie;
    RadioGroup radioGroup;
    ViewPageAdapter mAdapter;
    public static final int HOME = 0;
    public static final int READ = 1;
    public static final int MUSIC = 2;
    public static final int MOVIE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ViewPageAdapter(getSupportFragmentManager());
        bindView();

    }

    public void bindView() {
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPage);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_read = (RadioButton) findViewById(R.id.rb_read);
        rb_music = (RadioButton) findViewById(R.id.rb_music);
        rb_movie = (RadioButton) findViewById(R.id.rb_movie);
        radioGroup = (RadioGroup) findViewById(R.id.navbar);


        rb_home.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
//        viewPager.setHorizontalScrollBarEnabled(false);
//        viewPager.addOnPageChangeListener(this);
        viewPager.setNoScroll(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                rb_home.setChecked(true);
                viewPager.setCurrentItem(HOME);
                break;
            case R.id.rb_read:
                rb_read.setChecked(true);
                viewPager.setCurrentItem(READ);
                break;
            case R.id.rb_music:
                rb_music.setChecked(true);
                viewPager.setCurrentItem(MUSIC);
                break;
            case R.id.rb_movie:
                rb_movie.setChecked(true);
                viewPager.setCurrentItem(MOVIE);
                break;
        }

    }



    //    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////        Log.e("===", "==onPageScrolled==" + position);
//    }
//
//    @Override
//    public void onPageSelected(int position) {
////        Log.e("===", "==onPageSelected==" + position);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
////        Log.e("===", "==onPageScrollStateChanged==" + state);
//        if (state==2){
//            switch (viewPager.getCurrentItem()){
//                case HOME:
//                    rb_home.setChecked(true);
//                    break;
//                case READ:
//                    rb_read.setChecked(true);
//                    break;
//                case MUSIC:
//                    rb_music.setChecked(true);
//                    break;
//                case MOVIE:
//                    rb_movie.setChecked(true);
//                    break;
//            }
//
//        }
//    }
}
