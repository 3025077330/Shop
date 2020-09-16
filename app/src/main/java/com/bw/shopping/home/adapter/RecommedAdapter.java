package com.bw.shopping.home.adapter;

import android.provider.SyncStateContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.HomeBean;
import com.bw.shopping.R;

public class RecommedAdapter extends BaseRVAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.commrecyitem;
    }

    @Override
    protected void convert(HomeBean.ResultBean.RecommendInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_recommend);
        Glide.with(baseViewHolder.itemView.getContext())
                .load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + itemData.getFigure())
        .into(imageView);
        TextView tv_name = baseViewHolder.getView(R.id.tv_name);
        TextView tv_price = baseViewHolder.getView(R.id.tv_price);
        tv_name.setText(itemData.getName());
        tv_price.setText("￥" + itemData.getCover_price());
    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
