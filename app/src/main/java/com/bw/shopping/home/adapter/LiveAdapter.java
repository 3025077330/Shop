package com.bw.shopping.home.adapter;

import android.view.View;
import android.widget.TextView;

import com.bw.framwork.base.BaseRVAdapter;
import com.bw.shopping.R;

public class LiveAdapter extends BaseRVAdapter<String> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.live_item;
    }

    @Override
    protected void convert(String itemData, BaseViewHolder baseViewHolder, int position) {
        TextView textView = baseViewHolder.getView(R.id.host_name);
        textView.setText(itemData);
    }

    @Override
    protected int getViewType(int postion) {
        return 0;
    }
}
