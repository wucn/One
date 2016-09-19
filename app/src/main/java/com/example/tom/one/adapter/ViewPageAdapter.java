package com.example.tom.one.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.tom.one.activity.MainActivity;
import com.example.tom.one.fragment.HomeFragment;
import com.example.tom.one.fragment.MovieFragment;
import com.example.tom.one.fragment.MusicFragment;
import com.example.tom.one.fragment.ReadFragment;

/**
 * Created by tom on 2016/9/19.
 * //
 */
public class ViewPageAdapter extends FragmentPagerAdapter {

    private final int PAGECOUNT = 4;
    private HomeFragment homeFragment = null;
    private ReadFragment readFragment = null;
    private MusicFragment musicFragment = null;
    private MovieFragment movieFragment = null;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        homeFragment = new HomeFragment();
        readFragment = new ReadFragment();
        musicFragment = new MusicFragment();
        movieFragment = new MovieFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position) {
            case MainActivity.HOME:
                fragment=homeFragment;
                break;
            case MainActivity.READ:
                fragment=readFragment;
                break;
            case MainActivity.MUSIC:
                fragment=musicFragment;
                break;
            case MainActivity.MOVIE:
                fragment=movieFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGECOUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }
}
