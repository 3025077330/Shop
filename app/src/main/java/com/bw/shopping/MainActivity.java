package com.bw.shopping;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.common.view.BottomBar;
import com.bw.found.FaXianFragment;
import com.bw.framwork.base.BaseActivity;
import com.bw.framwork.base.MainFragmentAdapter;
import com.bw.shoppcar.GouWuCarFragment;
import com.bw.shopping.home.fragment.FenLeiFragment;
import com.bw.shopping.home.fragment.SouYeFragment;
import com.bw.user.fragment.MyFragment;

import java.util.ArrayList;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity implements BottomBar.IBottomBarSelectListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MainFragmentAdapter mainFragmentAdapter;
    private BottomBar bottombar;

    String[] titles = new String[]{"首页", "分类", "发现", "购物车", "我的"};


    @Override
    protected int bandlayout() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initview() {
        initright();
        //ARouter注入
        ARouter.getInstance().inject(this);
        viewPager = findViewById(R.id.view_pager);
        bottombar = findViewById(R.id.bottombar);

        bottombar.setBottomBarSelectListener(this);
        bottombar.setTabTitle(titles);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onBottomForPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initright() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.INTERNET",
                    "android.permission.CHANGE_WIFI_STATE",
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_NETWORK_STATE",
                    "android.permission.SYSTEM_ALERT_WINDOW",
                    "android.permission.CAMERA",
                    "android.permission.RECORD_AUDIO",
                    "android.permission.ACCESS_WIFI_STATE",
                    "android.permission.MODIFY_AUDIO_SETTINGS",
                    "android.permission.BLUETOOTH",
                    "android.permission.READ_PHONE_STATE",
            }, 101);
        }
    }

    @Override
    protected void initdata() {
        fragments.add(new SouYeFragment());
        fragments.add(new FenLeiFragment());
        fragments.add(new FaXianFragment());
        fragments.add(new GouWuCarFragment());
        fragments.add(new MyFragment());
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    private void pagechangMyfragment() {
        viewPager.setCurrentItem(fragments.size());
    }


    @Override
    public void onBottomBarSelected(int selectIndex) {
        viewPager.setCurrentItem(selectIndex);
    }

    @Override
    public void onBottomForPagerSelected(int selectindex) {
        bottombar.FindIndex(selectindex);
    }
}
