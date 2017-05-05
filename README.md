# ImageUploadView
自定义图片上传控件
使用方法
```
  <com.moral.imageuploadview.lib.ImageUploadView
        android:id="@+id/iv_load"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    </com.moral.imageuploadview.lib.ImageUploadView>
```

```
iv_load.setImageClickListener(new ImageClickListener() {
            @Override
            public void addImageClickListener() {
                //选择相册，加入图片
            }

            @Override
            public void showImageClickListener(ArrayList<ImageModel> list, int position) {
                //查看大图
            }

            @Override
            public void delImageClickListener(int position) {
               //删除图片

            }
        }).setImageLoaderInterface(new ImageLoaderUtil())//图片加载类
          .setShowDel(true)//是否显示删除按钮
          .setOneLineShowNum(3)//每行几张图
          .setmPicSize(ImageUploadView.dp2px(this, 110));//每张图大小
    }
```
![](https://github.com/shuijilove/ImageUploadView/blob/master/sreenshot/Screenshot_2017-05-05-11-21-25.png)  
