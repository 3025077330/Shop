package com.bw.shopping.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bw.common.ShoppingConstant;
import com.bw.framwork.base.BaseRVAdapter;
import com.bw.net.bean.HomeBean;
import com.bw.shopping.R;
import com.bw.shopping.product.view.ProductDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends BaseRVAdapter<Object> {
    /**
     * 各种不同布局的类型值 ，总共有六种
     */
    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int HOT_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int SECKILL_TYPE = 5;


    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                return R.layout.banner_item;
            case CHANNEL_TYPE:
                return R.layout.channel_item;
            case ACT_TYPE:
                return R.layout.act_item;
            case HOT_TYPE:
                return R.layout.hot_item;
            case RECOMMEND_TYPE:
                return R.layout.recommend_item;
            case SECKILL_TYPE:
                return R.layout.seckill_item;

            default:
                return R.layout.banner_item;

        }

    }

    @Override
    protected void convert(Object itemData, BaseViewHolder baseViewHolder, int position) {
        switch (position) {
            case 0:
                displayBanner(itemData, baseViewHolder);
                break;
            case 1:
                displayChannel(itemData, baseViewHolder);
                break;
            case 2:
                displayAct(itemData, baseViewHolder);
                break;
            case 3:
                displayHot(itemData, baseViewHolder);
                break;
            case 4:
                displayRecommend(itemData, baseViewHolder);
                break;
            case 5:
                displaySeckill(itemData, baseViewHolder);
                break;
            default:
                displayBanner(itemData, baseViewHolder);
        }
    }

    private void displayChannel(Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
        RecyclerView recyView = baseViewHolder.getView(R.id.recy_view);
        recyView.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 5));
        final ChannelAdapter channelAdapter = new ChannelAdapter();
        recyView.setAdapter(channelAdapter);
        channelAdapter.updataData(channelInfoBeans);
    }


    private void displayAct(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>) itemData;
        ViewPager viewPager = baseViewHolder.getView(R.id.view_pager);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true, new ScaleInOutTransformer());
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return actInfoBeans.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView view = new ImageView(baseViewHolder.itemView.getContext());
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                //绑定数据
                Glide.with(baseViewHolder.itemView.getContext())
                        .load(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + actInfoBeans.get(position).getIcon_url())
                        .into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

    }


    private void displayBanner(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
        Banner banner = baseViewHolder.getView(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        });
        List<String> imageUrls = new ArrayList<>();
        for (HomeBean.ResultBean.BannerInfoBean item : bannerInfoBeans) {
            imageUrls.add(ShoppingConstant.BASE_RESOURCE_IMAGE_URL + item.getImage());
        }
        banner.setImages(imageUrls);
        banner.start();
    }


    private void displayHot(Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>) itemData;
        RecyclerView recyView = baseViewHolder.getView(R.id.recy_view);
        recyView.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 2));
        final HotAdapter hotAdapter = new HotAdapter();
        recyView.setAdapter(hotAdapter);
        hotAdapter.updataData(hotInfoBeans);
        hotAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("productId", hotAdapter.getItemData(position).getProduct_id());
                intent.putExtra("productName", hotAdapter.getItemData(position).getName());
                intent.putExtra("productPrice", hotAdapter.getItemData(position).getCover_price());
                intent.putExtra("url", hotAdapter.getItemData(position).getFigure());
                intent.setClass((Activity) (baseViewHolder.itemView.getContext()), ProductDetailActivity.class);
                ((Activity) (baseViewHolder.itemView.getContext())).startActivity(intent);
            }
        });

    }


    private void displayRecommend(Object itemData, final BaseViewHolder viewHolder) {
        List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBean = (List<HomeBean.ResultBean.RecommendInfoBean>) itemData;
        RecyclerView recyView = viewHolder.getView(R.id.recy_view);
        final RecommedAdapter recommedAdapter = new RecommedAdapter();
        recyView.setLayoutManager(new GridLayoutManager(viewHolder.itemView.getContext(), 3));
        recyView.setAdapter(recommedAdapter);
        recommedAdapter.updataData(recommendInfoBean);
        recommedAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("productId", recommedAdapter.getItemData(position).getProduct_id());
                intent.putExtra("productName", recommedAdapter.getItemData(position).getName());
                intent.putExtra("productPrice", recommedAdapter.getItemData(position).getCover_price());
                intent.putExtra("url", recommedAdapter.getItemData(position).getFigure());
                intent.setClass((Activity) (viewHolder.itemView.getContext()), ProductDetailActivity.class);
                ((Activity) (viewHolder.itemView.getContext())).startActivity(intent);
            }
        });


    }


    private void displaySeckill(Object itemData, final BaseViewHolder viewHolder) {
        HomeBean.ResultBean.SeckillInfoBean seckillInfoBean = (HomeBean.ResultBean.SeckillInfoBean) itemData;
        RecyclerView rvSeckill = viewHolder.getView(R.id.rv_seckill);
        rvSeckill.setLayoutManager(new GridLayoutManager(viewHolder.itemView.getContext(), 6));
        final SeckillAdapter seckillAdapter = new SeckillAdapter();
        rvSeckill.setAdapter(seckillAdapter);
        seckillAdapter.updataData(seckillInfoBean.getList());
        seckillAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra("productId", seckillAdapter.getItemData(position).getProduct_id());
                intent.putExtra("productName", seckillAdapter.getItemData(position).getName());
                intent.putExtra("productPrice", seckillAdapter.getItemData(position).getCover_price());
                intent.putExtra("url", seckillAdapter.getItemData(position).getFigure());
                intent.setClass((Activity) (viewHolder.itemView.getContext()), ProductDetailActivity.class);
                ((Activity) (viewHolder.itemView.getContext())).startActivity(intent);
            }
        });

    }


    @Override
    protected int getViewType(int postion) {
        switch (postion) {
            case 0:
                return BANNER_TYPE;
            case 1:
                return CHANNEL_TYPE;
            case 2:
                return ACT_TYPE;
            case 3:
                return HOT_TYPE;
            case 4:
                return RECOMMEND_TYPE;
            case 5:
                return SECKILL_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}



