package com.example.tom.one.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom.one.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class LazyFragment extends Fragment {

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            Log.e("===","===="+isVisible);
            onVisible();
        }else{
            isVisible=false;
            onInvisible();
        }
    }
    protected void onVisible(){
        lazyLoad();
        Log.e("===","===="+isVisible);
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}
}
