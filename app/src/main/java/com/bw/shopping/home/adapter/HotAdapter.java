package com.bw.shopping.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.HomeBean;
import com.bw.shopping.R;

public class HotAdapter extends BaseRVAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.hotrecy_item;
    }

    @Override
    protected void convert(HomeBean.ResultBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView ivHot;
        TextView tvName;
        TextView tvPrice;
        ivHot = baseViewHolder.getView(R.id.iv_hot);
        tvName = baseViewHolder.getView(R.id.tv_name);
        tvPrice = baseViewHolder.getView(R.id.tv_price);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL+itemData.getFigure())
                .into(ivHot);

        tvName.setText(itemData.getName());
        tvPrice.setText("￥"+itemData.getCover_price());

    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
