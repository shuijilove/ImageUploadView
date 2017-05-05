package com.moral.imageuploadview.lib;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by haijiang on 2017/5/4.
 */

public class ImageAdapter extends BaseAdapter {
    private int mMaxNum = 9;
    private Context context;
    private ArrayList<ImageModel> imageList;
    private ImageLoaderInterface imageLoader;
    private ImageClickListener imageClickListener;

    private int iconHeight;

    private int delPicRes;

    private int addPicRes;

    private boolean isShowDel;

    public int getmMaxNum() {
        return mMaxNum;
    }

    public void setmMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
    }

    public ImageLoaderInterface getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ImageClickListener getImageClickListener() {
        return imageClickListener;
    }

    public void setImageClickListener(ImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public int getIconHeight() {
        return this.iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public int getDelPicRes() {
        return delPicRes;
    }

    public void setDelPicRes(int delPicRes) {
        this.delPicRes = delPicRes;
    }

    public int getAddPicRes() {
        return addPicRes;
    }

    public void setAddPicRes(int addPicRes) {
        this.addPicRes = addPicRes;
    }

    public boolean isShowDel() {
        return isShowDel;
    }

    public void setShowDel(boolean showDel) {
        this.isShowDel = showDel;
    }

    public ImageAdapter(Context context, ArrayList<ImageModel> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size()>=mMaxNum?mMaxNum:imageList.size()+1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        FrameLayout frameLayout = null;
        ViewHoler holer = null;
        if(frameLayout == null){
            frameLayout = new FrameLayout(context);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            holer = new ViewHoler();
            holer.iv_pic = new ImageView(context);
            holer.iv_del = new ImageView(context);
            frameLayout.addView( holer.iv_pic);
            frameLayout.addView( holer.iv_del);
            frameLayout.setTag(holer);
        }else{
            holer = (ViewHoler) frameLayout.getTag();
        }
        FrameLayout.LayoutParams pic_params = new FrameLayout.LayoutParams(iconHeight,
                iconHeight);
        pic_params.setMargins(10, 10, 10, 10);
        holer.iv_pic.setLayoutParams(pic_params);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.TOP | Gravity.END;
        holer.iv_del.setPadding(5, 5, 5, 5);
        holer.iv_del.setLayoutParams(layoutParams);

        holer.iv_del.setImageResource(delPicRes);
        if(i==imageList.size()){
            if(imageLoader!=null){
                imageLoader.displayImage(context,addPicRes,holer.iv_pic);
            }
        }else{
            if(imageLoader!=null){
                Log.d("haijiang",imageList.get(i).getImg());
                imageLoader.displayImage(context,imageList.get(i).getImg(),holer.iv_pic);
            }
        }
        if(isShowDel){
            if(i==imageList.size()){
                holer.iv_del.setVisibility(View.GONE);
            }else{
                holer.iv_del.setVisibility(View.VISIBLE);
            }
        }else{
            holer.iv_del.setVisibility(View.GONE);
        }
        holer.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageClickListener!=null){
                    if(imageList.size()<mMaxNum&&i==imageList.size()){
                        imageClickListener.addImageClickListener();
                    }else{
                        imageClickListener.showImageClickListener(imageList,i);
                    }

                }
            }
        });
        holer.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageClickListener!=null){
                    imageClickListener.delImageClickListener(i);
                }
            }
        });
        return frameLayout;
    }
    public static class ViewHoler{
        private ImageView iv_pic;
        private ImageView iv_del;
    }
}
