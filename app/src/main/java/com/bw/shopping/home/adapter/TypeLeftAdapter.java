package com.bw.shopping.home.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.ClassifLeftRecyBean;
import com.bw.shopping.R;

public class TypeLeftAdapter extends BaseRVAdapter<ClassifLeftRecyBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.type_left_item;
    }

    @Override
    protected void convert(ClassifLeftRecyBean itemData, BaseViewHolder baseViewHolder, int position) {
        final TextView tvTitle = baseViewHolder.getView(R.id.tv_title);
        tvTitle.setText(itemData.getTitle());
        if (itemData.isFlag()) {
            tvTitle.setTextColor(Color.RED);
        } else {
            tvTitle.setTextColor(Color.BLACK);
        }
    }


    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
