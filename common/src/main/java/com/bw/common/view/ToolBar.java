package com.bw.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.common.R;


public class ToolBar extends RelativeLayout {

    private ImageView toolBarLeftImg, toolbarRightImg;//控件对象
    private TextView toolbarTitleTv, toolbarRightTv;
    private boolean isShowLeft = true;//左边布局是否显示
    private boolean isShowTitle = true;//标题是否显示
    private boolean isShowRight = true;//右边布局是否显示
    private boolean isRightOnlyText = false;//右边布局是否仅仅是文字
    private int rightImgId;//右边图片Id
    private int leftImgId;//左边图片Id
    private String rightText;//右边文本内容
    private String titleText;//标题文本内容
    private int rightTextColor;//右边字体颜色
    private float rightTextSize;//右边字体大小
    private IToolBarClickListner iToolBarClickListner;

    public ToolBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void setToolBarClickListner(IToolBarClickListner iToolBarClickListner) {
        this.iToolBarClickListner = iToolBarClickListner;
    }

    /**
     * 上下文
     * <p>
     * 对应配置控件属性的 attribute文件
     *
     * @param context
     * @param attributeSet
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        initToolBarAttrs(context, attributeSet);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toolbar_layout, this);//第二个参数一定要是this

        toolBarLeftImg = findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = findViewById(R.id.toolbarRightTv);
        //使用属性值来控制toolbar里控件的显示
        if (!isShowLeft) showNotLeft();
        if (!isShowTitle) showNotTitle();
        if (rightImgId != 0) setToolBarRightImg(rightImgId);
        if (rightText != null) setToolbarRightTv(rightText);
        if (rightTextColor != 0) setRightTextColor(rightTextColor);
        if (rightTextSize != 0) setRightTvTextSize((int) rightTextSize);
        if (titleText != null) setToolBarTitle(titleText);
        if (!isShowRight) showNotRight();
        if (leftImgId != 0) setToolBarLeftImg(leftImgId);
        if (isRightOnlyText) showOnlyRightTv(rightText);

        initListener();


    }

    private void initListener() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null) {
                    iToolBarClickListner.onLeftClick();
                }
            }
        });

        toolbarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null) {
                    iToolBarClickListner.onRightClick();
                }
            }
        });

        toolbarRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null) {
                    iToolBarClickListner.onRightClick();
                }
            }
        });
    }


    //初始化Toolbar在布局中定义的属性值
    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        //获取Toolbar在布局中定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        isShowLeft = typedArray.getBoolean(R.styleable.ToolBar_left_show, true);
        isShowTitle = typedArray.getBoolean(R.styleable.ToolBar_title_show, true);
        rightText = typedArray.getString(R.styleable.ToolBar_right_text);
        rightText = typedArray.getString(R.styleable.ToolBar_right_text);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_right_src, 0);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_left_src, 0);
        rightTextColor = typedArray.getColor(R.styleable.ToolBar_right_text_color, Color.BLACK);
        rightTextSize = typedArray.getInt(R.styleable.ToolBar_right_text_size, 20);
        titleText = typedArray.getString(R.styleable.ToolBar_title_text);
        isShowRight = typedArray.getBoolean(R.styleable.ToolBar_right_show, true);
        isRightOnlyText = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_text, false);


    }

    //修改toolbar的显示的主题
    public void setToolBarTitle(String title) {
        toolbarTitleTv.setText(title);
    }

    //修改左侧显示图片
    public void setToolBarLeftImg(int imgId) {
        toolBarLeftImg.setImageResource(imgId);
    }

    //修改右侧显示图片
    public void setToolBarRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
    }

    //修改右侧文本
    public void setToolbarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    //不显示右侧的内容
    public void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    //只显示右侧的图片
    public void showOnlyRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightTv.setVisibility(GONE);
    }

    //只显示右侧的文本
    public void showOnlyRightTv(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    //左侧图片不显示
    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }

    //不显示title
    public void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    //修改右侧字体颜色
    public void setRightTextColor(int color) {
        toolbarRightTv.setTextColor(color);
    }

    //配置右侧字体大小
    public void setRightTvTextSize(int size) {
        toolbarRightTv.setTextSize(size);
    }


    //封装ToolBar的控件的点击事件
    public interface IToolBarClickListner {
        void onLeftClick();//ToolBar的左侧控件被点击时调用

        void onRightClick();//ToolBar的右侧控件被点击
    }
}
