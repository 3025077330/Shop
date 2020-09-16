package com.bw.shopping.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.ClassifGoodBean;
import com.bw.shopping.R;

public class TyRigClaifyAdapter extends BaseRVAdapter<ClassifGoodBean.ResultBean.ChildBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.type_rightitem;
    }

    @Override
    protected void convert(ClassifGoodBean.ResultBean.ChildBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView image = baseViewHolder.getView(R.id.image);
        TextView tvtitle = baseViewHolder.getView(R.id.tvtitle);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL+itemData.getPic()).into(image);
        tvtitle.setText(itemData.getName());
    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
