package com.example.tom.one.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.tom.one.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = (ImageView) findViewById(R.id.anim_start);
        final AnimationDrawable animation = (AnimationDrawable) imageView.getBackground();
        animation.start();
        int time = 0;
        int length = animation.getNumberOfFrames();
        for (int i = 0; i < length; i++) {
            time += animation.getDuration(i);
//            if (i%10==0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    animation.stop();
//                    animation= null;
                }
            }, time);
//            }
        }

    }
}
