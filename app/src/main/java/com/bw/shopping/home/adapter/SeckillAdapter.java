package com.bw.shopping.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.HomeBean;
import com.bw.shopping.R;

public class SeckillAdapter extends BaseRVAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.seckillrecy_item;
    }

    @Override
    protected void convert(HomeBean.ResultBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView ivFigure;
        TextView tvCoverPrice;
        TextView tvOriginPrice;

        ivFigure = baseViewHolder.getView(R.id.iv_figure);
        tvCoverPrice = baseViewHolder.getView(R.id.tv_cover_price);
        tvOriginPrice = baseViewHolder.getView(R.id.tv_origin_price);
        Glide.with(baseViewHolder.itemView.getContext())
                .load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + itemData.getFigure())
                .into(ivFigure);
        tvCoverPrice.setText( itemData.getCover_price());
        tvOriginPrice.setText( itemData.getOrigin_price());


    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
