package com.bw.shoppcar.adapter;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.ShoppCarBean;
import com.bw.shoppcar.CacheManager;
import com.bw.shoppcar.R;
import com.bw.shoppcar.presenter.ShopcarPresenterImpl;


public class ShopcarAdapter extends BaseRVAdapter<ShoppCarBean> {
    private boolean isEditMode;//是否是编辑模式

    private ShopcarPresenterImpl shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        notifyDataSetChanged();//刷新列表
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_car;
    }

    @Override
    protected void convert(final ShoppCarBean itemData, BaseViewHolder baseViewHolder, final int position) {
        ImageView productImg = baseViewHolder.getView(R.id.productImage);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + itemData.getUrl()).into(productImg);
        TextView productNameTv = baseViewHolder.getView(R.id.productName);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = baseViewHolder.getView(R.id.productPrice);
        productPriceTv.setText((String) itemData.getProductPrice());
        TextView productNumTv = baseViewHolder.getView(R.id.productCount);
        productNumTv.setText(itemData.getProductNum());
        final CheckBox produectSelectCheckBox = baseViewHolder.getView(R.id.productSelect);

        if (!isEditMode) {//正常模式
            produectSelectCheckBox.setChecked(itemData.isProductSelected());
            initProductSelectCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            initAddDecClickListener(baseViewHolder, itemData, position);
        } else {//编辑模式
            initEditModeCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)) {
                produectSelectCheckBox.setChecked(true);
            } else {
                produectSelectCheckBox.setChecked(false);
            }
        }


    }

    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShoppCarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()) {
                    //当前在编辑模式下，选择了该商品，需要把该商品添加到删除队列中
                    CacheManager.getInstance().addDeleteShopcarBean(itemData, position);
                } else {
                    //在编辑模式下，该商品由已选择变为未选择，那么需要把它从删除队列中删除
                    CacheManager.getInstance().deleteOneShopcarBean(itemData, position);
                }
            }
        });
    }

    private void initAddDecClickListener(BaseViewHolder baseViewHolder, final ShoppCarBean itemData, final int position) {
        baseViewHolder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                int newNum = oldNum + 1;
                shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(), position, String.valueOf(newNum));
            }
        });

        baseViewHolder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(), position, String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShoppCarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newSelected;
                if (produectSelectCheckBox.isChecked()) {
                    newSelected = false;
                } else {
                    newSelected = true;
                }
                shopcarPresenter.updateProductSelected(itemData.getProductId(), newSelected, itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(), position);
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
