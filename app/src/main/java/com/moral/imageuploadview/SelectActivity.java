package com.moral.imageuploadview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * https://github.com/jeasonlzy/ImagePicker
 * Created by haijiang on 2017/5/5.
 */

public class SelectActivity extends AppCompatActivity {
    private Button bt_one, bt_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_select_layout);
        bt_one = (Button) findViewById(R.id.bt_one);
        bt_two = (Button) findViewById(R.id.bt_two);
        bt_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,ImagePickActivity.class);
                startActivity(intent);
            }
        });
        bt_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,BoxActivity.class);
                startActivity(intent);
            }
        });
    }
}
