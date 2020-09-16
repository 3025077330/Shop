package com.bw.shopping.home.fragment;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.common.view.ToolBar;
import com.bw.net.bean.HomeBean;
import com.bw.framwork.base.BaseMVPFragment;
import com.bw.shopping.R;
import com.bw.shopping.home.adapter.HomeAdapter;
import com.bw.shopping.home.contract.HomeContract;
import com.bw.shopping.home.presenter.HomePresenterImpl;

public class SouYeFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView {
    private ToolBar toolBar;
    private EditText edSearch;
    private RecyclerView rvHome;
    private ImageButton ibTop;
    private HomeBean.ResultBean resultBean;
    private HomeAdapter homeAdapter;

    @Override
    protected void initpresenter() {
        ihttpView = new HomePresenterImpl();
    }

    @Override
    protected void initpredata() {
        ihttpView.getHomeData();
    }

    @Override
    protected int bandlayout() {
        return R.layout.soye_fragm;
    }

    @Override
    protected void initview() {
        //toolBar = findViewById(R.id.tool_bar);
        edSearch = findViewById(R.id.ed_search);
        rvHome = findViewById(R.id.rv_home);
        ibTop = findViewById(R.id.ib_top);
        homeAdapter = new HomeAdapter();
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(homeAdapter);

    }

    @Override
    protected void initdata() {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        homeAdapter.addOneData(homeBean.getResult().getBanner_info());
        homeAdapter.addOneData(homeBean.getResult().getChannel_info());
        homeAdapter.addOneData(homeBean.getResult().getAct_info());
        homeAdapter.addOneData(homeBean.getResult().getHot_info());
        homeAdapter.addOneData(homeBean.getResult().getRecommend_info());
        homeAdapter.addOneData(homeBean.getResult().getSeckill_info());

    }
}
