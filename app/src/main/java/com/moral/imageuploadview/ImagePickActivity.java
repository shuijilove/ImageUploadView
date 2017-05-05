package com.moral.imageuploadview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.moral.imageuploadview.lib.ImageClickListener;
import com.moral.imageuploadview.lib.ImageModel;
import com.moral.imageuploadview.lib.ImageUploadView;

import java.util.ArrayList;

public class ImagePickActivity extends AppCompatActivity implements ImageClickListener {
    private ImageUploadView iv_load;
    private ArrayList<ImageModel> list = new ArrayList<>();
    private static final int REQUEST_CODE = 1024;
    private ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initImagePick();

        iv_load = (ImageUploadView) findViewById(R.id.iv_load);
        iv_load.setImageClickListener(this).setImageLoaderInterface(new ImageLoaderUtil()).setShowDel(true).setOneLineShowNum(3).setmPicSize(ImageUploadView.dp2px(this, 110));
    }

    @Override
    public void addImageClickListener() {
        imagePicker.setSelectLimit(9-iv_load.getImageList().size());    //选中数量限制
        Intent intent = new Intent(ImagePickActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    public void showImageClickListener(ArrayList<ImageModel> list, int position) {
        Toast.makeText(ImagePickActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delImageClickListener(int position) {
        iv_load.delImage(position);
    }

    private void initImagePick() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setMultiMode(true);
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (requestCode == REQUEST_CODE&&data!=null) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    list.clear();
                    for (ImageItem item : images){
                        list.add(new ImageModel(item.path));
                    }
                    iv_load.addImage(list);
                }
            }
        }
    }
}
