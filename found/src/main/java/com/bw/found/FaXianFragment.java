package com.bw.found;

import android.view.View;
import android.widget.ImageView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.found.adapter.LiveAdapter;
import com.bw.framwork.base.BaseFragment;
import com.bw.framwork.base.BaseRVAdapter;

import java.util.ArrayList;

public class FaXianFragment extends BaseFragment implements View.OnClickListener {
    private ImageView recordBtn;
    private RecyclerView recyView;
    private LiveAdapter liveAdapter;
    private ArrayList<String> list = new ArrayList<>();


    @Override
    protected int bandlayout() {
        return R.layout.faxian_fragm;
    }

    @Override
    protected void initview() {
        recordBtn = (ImageView) findViewById(R.id.recordBtn);
        recordBtn.setOnClickListener(this);
        recyView = (RecyclerView) findViewById(R.id.recy_view);
        recyView.setLayoutManager(new LinearLayoutManager(getContext()));
        liveAdapter = new LiveAdapter();
        recyView.setAdapter(liveAdapter);
        liveAdapter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //观看直播


            }
        });
    }

    @Override
    protected void initdata() {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.recordBtn) {
            //发起直播
           // launActivity(UserBroadcasterActivity.class, null);
        }
    }
}
