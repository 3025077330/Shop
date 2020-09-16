package com.bw.user;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.common.view.BottomBar;
import com.bw.framwork.base.BaseActivity;
import com.bw.framwork.base.MainFragmentAdapter;
import com.bw.user.fragment.LoginFragment;
import com.bw.user.fragment.RegisterFragment;

import java.util.ArrayList;

public class RegiLoginActivity extends BaseActivity {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private MainFragmentAdapter mainFragmentAdapter;

    @Override
    protected int bandlayout() {
        return R.layout.activity_regilogin;
    }

    @Override
    protected void initview() {
        viewPager = findViewById(R.id.view_pager);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initdata() {
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        mainFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
        finish();
    }

    public void pagechange(int postion) {
        viewPager.setCurrentItem(postion);
    }
}
