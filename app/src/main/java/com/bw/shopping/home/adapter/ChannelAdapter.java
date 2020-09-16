package com.bw.shopping.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.HomeBean;
import com.bw.shopping.R;

public class ChannelAdapter extends BaseRVAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel;
    }

    @Override
    protected void convert(HomeBean.ResultBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView textView = baseViewHolder.getView(R.id.tv_channel);
        textView.setText(itemData.getChannel_name());
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + itemData.getImage()).into(imageView);
    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
