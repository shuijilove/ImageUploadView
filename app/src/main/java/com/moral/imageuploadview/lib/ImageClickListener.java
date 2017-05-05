package com.moral.imageuploadview.lib;

import java.util.ArrayList;

/**
 * 点击监听类
 * 选择图片、查看、删除
 * Created by haijiang on 2017/5/4.
 */

public interface ImageClickListener  {
    void addImageClickListener();

    void showImageClickListener(ArrayList<ImageModel> list, int position);

    void delImageClickListener(int position);
}
