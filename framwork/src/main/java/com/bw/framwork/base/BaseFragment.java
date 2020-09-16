package com.bw.framwork.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bw.common.view.ToolBar;
import com.bw.framwork.R;


public abstract class BaseFragment extends Fragment {
    private String TAG;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = "WYF" + getClass().getSimpleName();
        rootView = inflater.inflate(bandlayout(), null);

        return rootView;
    }

    protected abstract int bandlayout();

    protected abstract void initview();

    protected abstract void initdata();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        initdata();
    }

    //注解。表示一个资源id，不能随便传递一个整型
    public <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    protected void printLog(String message) {
        Log.i(TAG, message);
    }

    protected void showMsg(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void launActivity(Class launActivity, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.setClass(getActivity(), launActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
