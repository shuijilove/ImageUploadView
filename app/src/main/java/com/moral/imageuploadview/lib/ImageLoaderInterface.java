package com.moral.imageuploadview.lib;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * 图片加载接口
 * Created by haijiang on 2017/5/4.
 */

public interface ImageLoaderInterface{
    void displayImage(Context context, String path, ImageView imageView);
    void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView);
}
