package com.moral.imageuploadview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.moral.imageuploadview.lib.ImageClickListener;
import com.moral.imageuploadview.lib.ImageModel;
import com.moral.imageuploadview.lib.ImageUploadView;

import java.util.ArrayList;

/**
 * https://github.com/Bilibili/boxing
 */
public class BoxActivity extends AppCompatActivity implements ImageClickListener{
    private ImageUploadView iv_load;
    private ArrayList<ImageModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_load = (ImageUploadView) findViewById(R.id.iv_load);
        iv_load.setImageClickListener(this).setImageLoaderInterface(new ImageLoaderUtil()).setShowDel(true).setOneLineShowNum(3).setmPicSize(ImageUploadView.dp2px(this,110));
        list.add(new ImageModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493961951271&di=724dd8f887b4308f26bcd9ce4afc2b43&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fjryh_170321%2Fdesk_001.jpg"));
        list.add(new ImageModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493961951269&di=591d2edf4fde2d907e5e344b01f55061&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20151030%2F1024x768_eb49eefac46bef1.jpg"));
        list.add(new ImageModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493961951264&di=bb667944b212ecd2ffa36f6cb96c48d2&imgtype=0&src=http%3A%2F%2Fuserimage7.360doc.com%2F16%2F0123%2F23%2F6622010_201601232355360953727142.jpg"));
        list.add(new ImageModel("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1493951840&di=668a11e125095968b9252a3846b41423&src=http://pic1.win4000.com/pic/b/f2/b1921309969.jpg"));
        iv_load.addImage(list);
    }

    @Override
    public void addImageClickListener() {
        iv_load.addImage(new ImageModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493961951269&di=591d2edf4fde2d907e5e344b01f55061&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20151030%2F1024x768_eb49eefac46bef1.jpg"));
    }

    @Override
    public void showImageClickListener(ArrayList<ImageModel> list, int position) {
        Toast.makeText(BoxActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delImageClickListener(int position) {
        iv_load.delImage(position);
    }
}
