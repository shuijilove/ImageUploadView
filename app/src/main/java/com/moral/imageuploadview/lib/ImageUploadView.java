package com.moral.imageuploadview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.moral.imageuploadview.R;

import java.util.ArrayList;

/**
 * Created by haijiang on 2017/5/4.
 */

public class ImageUploadView extends LinearLayout {
    private Context context;
    private GridView gridView;
    /**
     * 图片加载接口
     */
    private ImageLoaderInterface imageLoaderInterface;
    /**
     * 点击事件接口
     */
    private ImageClickListener imageClickListener;

    private ImageAdapter adapter;

    private ArrayList<ImageModel> imageList;
    /**
     * 默认单个大小
     */
    private static final int PIC_SIZE = 80;
    /**
     * 默认单行显示数量
     */
    private static final int ONE_LINE_SHOW_NUM = 4;
    /**
     * 默认单个大小
     */
    private static final int MAX_NUM = 9;
    /**
     * 单个item大小
     */
    private int mPicSize;
    /**
     * 添加图片
     */
    private int mAddLabel;
    /**
     * 删除图片
     */
    private int mDelLabel;
    /**
     * 是否显示删除
     */
    private boolean isShowDel;
    /**
     * 单行显示数量，默认4
     */
    private int oneLineShowNum;
    /**
     * 最大数量
     */
    private int maxNum;

    public ImageLoaderInterface getImageLoaderInterface() {
        return imageLoaderInterface;
    }

    public ImageUploadView setImageLoaderInterface(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoaderInterface = imageLoaderInterface;
        adapter.setImageLoader(this.imageLoaderInterface);
        return this;
    }

    public ImageClickListener getImageClickListener() {
        return imageClickListener;
    }

    public ImageUploadView setImageClickListener(ImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
        adapter.setImageClickListener(this.imageClickListener);
        return this;
    }

    public int getmPicSize() {
        return mPicSize;
    }

    public ImageUploadView setmPicSize(int mPicSize) {
        this.mPicSize = mPicSize;
        adapter.setIconHeight(this.mPicSize);
        return this;
    }

    public int getmAddLabel() {
        return mAddLabel;
    }

    public ImageUploadView setmAddLabel(int mAddLabel) {
        this.mAddLabel = mAddLabel;
        adapter.setAddPicRes(this.mAddLabel);
        return  this;
    }

    public int getmDelLabel() {
        return mDelLabel;
    }

    public ImageUploadView setmDelLabel(int mDelLabel) {
        this.mDelLabel = mDelLabel;
        adapter.setDelPicRes(this.mDelLabel);
        return this;
    }

    public boolean isShowDel() {
        return isShowDel;
    }

    public ImageUploadView setShowDel(boolean showDel) {
        this.isShowDel = showDel;
        adapter.setShowDel(this.isShowDel);
        return this;
    }

    public int getOneLineShowNum() {
        return oneLineShowNum;
    }

    public ImageUploadView setOneLineShowNum(int oneLineShowNum) {
        this.oneLineShowNum = oneLineShowNum;
        gridView.setNumColumns(oneLineShowNum);
        return this;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public ImageUploadView setMaxNum(int maxNum) {
        this.maxNum = maxNum;
        adapter.setmMaxNum(this.maxNum);
        return this;
    }

    public ImageUploadView(Context context) {
        this(context, null);
    }

    public ImageUploadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageUploadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageUploadView);
        mPicSize = typedArray.getDimensionPixelSize(R.styleable.ImageUploadView_pic_size, dp2px(context, PIC_SIZE));
        isShowDel = typedArray.getBoolean(R.styleable.ImageUploadView_is_show_del, true);
        mAddLabel = typedArray.getResourceId(R.styleable.ImageUploadView_add_res, R.mipmap.image_add);
        mDelLabel = typedArray.getResourceId(R.styleable.ImageUploadView_del_res, R.mipmap.image_del);
        oneLineShowNum = typedArray.getInt(R.styleable.ImageUploadView_one_line_show_num, ONE_LINE_SHOW_NUM);
        maxNum = typedArray.getInt(R.styleable.ImageUploadView_max_num, MAX_NUM);
        typedArray.recycle();
        imageList = new ArrayList<>();
        gridView = new GridView(context);
        gridView.setNumColumns(oneLineShowNum);
        adapter = new ImageAdapter(context,imageList);
        adapter.setIconHeight(mPicSize);
        adapter.setAddPicRes(mAddLabel);
        adapter.setDelPicRes(mDelLabel);
        gridView.setAdapter(adapter);
        addView(gridView);
    }

    /**
     * 加载数据
     */
    public void addImage(ImageModel model) {
        if (model == null) {
            return;
        }
        imageList.add(model);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 加载数据
     */
    public void addImage(ArrayList<ImageModel> modelList) {
        if (modelList == null) {
            return;
        }
        imageList.addAll(modelList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * 清除数据
     */
    public void clearImage(){
        imageList.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取图片数量
     * @return
     */
    public ArrayList<ImageModel> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImageModel> imageList) {
        this.imageList = imageList;
    }

    /**
     * 刷新控件
     */
    public void notifyImage(){
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void delImage(int position){
        imageList.remove(position);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
