package com.bw.shopping.home.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.common.view.ToolBar;
import com.bw.framwork.base.BaseMVPFragment;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.ClassifGoodBean;
import com.bw.net.bean.ClassifLeftRecyBean;
import com.bw.shopping.R;
import com.bw.shopping.home.adapter.TyRigClaifyAdapter;
import com.bw.shopping.home.adapter.TyRigrecomAdapter;
import com.bw.shopping.home.adapter.TypeLeftAdapter;
import com.bw.shopping.home.contract.ClassifGoodContract;
import com.bw.shopping.home.presenter.ClassifPresenterImpl;
import com.bw.shopping.product.view.ProductDetailActivity;

import java.util.ArrayList;

public class FenLeiFragment extends BaseMVPFragment<ClassifPresenterImpl, ClassifGoodContract.ClassifView> implements TypeLeftAdapter.IRecyclerViewItemClickListener, ClassifGoodContract.ClassifView {
    private RecyclerView typeRecy;
    private RecyclerView fenleiRecy;
    private RecyclerView recommRecy;
    private ToolBar toolbar;
    private TypeLeftAdapter typeLeftAdapter;
    private TyRigClaifyAdapter tyRigClaifyAdapter;
    private TyRigrecomAdapter tyRigrecomAdapter;
    private ArrayList<ClassifLeftRecyBean> classifLeftRecyBeans = new ArrayList<>();


    @Override
    protected void initpresenter() {
        ihttpView = new ClassifPresenterImpl();
    }

    @Override
    protected void initpredata() {
        ihttpView.getSkirt();
    }

    @Override
    protected int bandlayout() {
        return R.layout.fenlei_fragm;
    }

    @Override
    protected void initview() {
        typeRecy = findViewById(R.id.type_recy);
        fenleiRecy = findViewById(R.id.fenlei_recy);
        recommRecy = findViewById(R.id.recomm_recy);
        toolbar = findViewById(R.id.toolbar);
        typeRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        fenleiRecy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recommRecy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        typeLeftAdapter = new TypeLeftAdapter();
        typeLeftAdapter.setiRecyclerViewItemClickListener(this);
        tyRigClaifyAdapter = new TyRigClaifyAdapter();
        tyRigrecomAdapter = new TyRigrecomAdapter();
        typeRecy.setAdapter(typeLeftAdapter);
        fenleiRecy.setAdapter(tyRigClaifyAdapter);
        recommRecy.setAdapter(tyRigrecomAdapter);
        tyRigrecomAdapter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("productId", tyRigrecomAdapter.getItemData(position).getProduct_id());
                intent.putExtra("productName", tyRigrecomAdapter.getItemData(position).getName());
                intent.putExtra("productPrice", tyRigrecomAdapter.getItemData(position).getCover_price());
                intent.putExtra("url", tyRigrecomAdapter.getItemData(position).getFigure());
                intent.setClass(getContext(), ProductDetailActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void initdata() {
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("小裙子", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("上衣", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("下装", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("外套", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("配件", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("包包", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("扮装", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("居家宅品", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("办公文具", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("数码周边", false));
        classifLeftRecyBeans.add(new ClassifLeftRecyBean("游戏专区", false));
        typeLeftAdapter.updataData(classifLeftRecyBeans);
    }

    @Override
    public void onItemClick(int position) {

        for (int i = 0; i < classifLeftRecyBeans.size(); i++) {
            if (position == i) {
                classifLeftRecyBeans.get(i).setFlag(true);
            } else {
                classifLeftRecyBeans.get(i).setFlag(false);
            }
        }
        typeLeftAdapter.updataData(classifLeftRecyBeans);
        final String title = typeLeftAdapter.getItemData(position).getTitle();
        switch (title) {
            case "小裙子":
                ihttpView.getSkirt();
                break;
            case "上衣":
                ihttpView.getJacket();
                break;
            case "下装":
                ihttpView.getPants();
                break;
            case "外套":
                ihttpView.getOvercoat();
                break;
            case "配件":
                ihttpView.getAccessory();
                break;
            case "包包":
                ihttpView.getBig();
                break;
            case "扮装":
                ihttpView.getDressUp();
                break;
            case "居家宅品":
                ihttpView.getHomeProducts();
                break;
            case "办公文具":
                ihttpView.getStationery();
                break;
            case "数码周边":
                ihttpView.getNumRound();
                break;
            case "游戏专区":
                ihttpView.getGames();
                break;

        }
    }

    @Override
    public void onClassGood(ClassifGoodBean classifGoodBean) {
        tyRigClaifyAdapter.updataData(classifGoodBean.getResult().get(0).getChild());
        tyRigrecomAdapter.updataData(classifGoodBean.getResult().get(0).getHot_product_list());
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
}
