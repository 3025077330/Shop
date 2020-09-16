package com.bw.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bw.common.R;


/**
 * 自定义BottomBar
 */
public class BottomBar extends LinearLayout {
    private IBottomBarSelectListener iBottomBarSelectListener;
    public static final int HOME_INDEX = 0;
    public static final int TYPE_INDEX = 1;
    public static final int FIND_INDEX = 2;
    public static final int SHOPCAR_INDEX = 3;
    public static final int MINE_INDEX = 4;
    private int selectColor;
    private int unselectColor;


    public BottomBar(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    //数组的长度为4
    public void setTabTitle(String[] tabTitles) {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setText(tabTitles[0]);
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setText(tabTitles[1]);
        RadioButton findButton = findViewById(R.id.find);
        findButton.setText(tabTitles[2]);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setText(tabTitles[3]);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setText(tabTitles[4]);

    }

    //初始化函数
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initBottomBarAttrs(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottombar, this);
        RadioGroup radioGroup = findViewById(R.id.bottomGroup);
        //设置点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home) {
                    selectHome();
                } else if (checkedId == R.id.type) {
                    selectType();
                } else if (checkedId == R.id.find) {
                    selectFind();
                } else if (checkedId == R.id.shopcar) {
                    selectShopcar();
                } else if (checkedId == R.id.mine) {
                    selectMine();
                }
            }
        });
        selectHome();//默认显示home
    }

    public void FindIndex(int postion) {
        switch (postion) {
            case HOME_INDEX:
                selectHome();
                break;
            case TYPE_INDEX:
                selectType();
                break;
            case FIND_INDEX:
                selectFind();
                break;
            case SHOPCAR_INDEX:
                selectShopcar();
                break;
            case MINE_INDEX:
                selectMine();
                break;
            default:
                selectHome();
        }


    }


    private void selectFind() {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setTextColor(unselectColor);
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setTextColor(unselectColor);
        RadioButton findButton = findViewById(R.id.find);
        findButton.setTextColor(selectColor);
        findButton.setChecked(true);//设置为选中状态
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(FIND_INDEX);
        }
    }

    private void selectHome() {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setTextColor(selectColor);
        homeButton.setChecked(true);//设置为选中状态
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setTextColor(unselectColor);
        RadioButton findButton = findViewById(R.id.find);
        findButton.setTextColor(unselectColor);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(HOME_INDEX);
        }
    }

    private void selectType() {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setTextColor(unselectColor);
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setTextColor(selectColor);
        typeButton.setChecked(true);//设置为选中状态
        RadioButton findButton = findViewById(R.id.find);
        findButton.setTextColor(unselectColor);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(TYPE_INDEX);
        }
    }

    private void selectShopcar() {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setTextColor(unselectColor);
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setTextColor(unselectColor);
        RadioButton findButton = findViewById(R.id.find);
        findButton.setTextColor(unselectColor);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setTextColor(selectColor);
        shopcarButton.setChecked(true);//设置为选中状态
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(SHOPCAR_INDEX);
        }
    }

    private void selectMine() {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setTextColor(unselectColor);

        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setTextColor(unselectColor);

        RadioButton findButton = findViewById(R.id.find);
        findButton.setTextColor(unselectColor);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setTextColor(selectColor);
        mineButton.setChecked(true);//设置为选中状态
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(MINE_INDEX);
        }
    }

    private void initBottomBarAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BottomBar);
        selectColor = typedArray.getColor(R.styleable.BottomBar_select_textcolor, Color.RED);
        unselectColor = typedArray.getColor(R.styleable.BottomBar_unselect_textcolor, Color.BLACK);
    }


    public void setBottomBarSelectListener(IBottomBarSelectListener listener) {
        this.iBottomBarSelectListener = listener;
    }


    //定义一个接口，；这个接口，Activity或者Fragment实现这个接口，通过这个接口达到自定义view和Activity或者Fragment之间的通信
    public interface IBottomBarSelectListener {
        void onBottomBarSelected(int selectIndex);

        void onBottomForPagerSelected(int selectindex);
    }


}

