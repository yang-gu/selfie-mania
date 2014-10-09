package com.example.selfiemania;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ViewImageActivity extends Activity {

    ImageView iv;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_image);
        iv = (ImageView)findViewById(R.id.img);
        Intent i = getIntent();
        Picasso.with(getBaseContext()).load(i.getStringExtra("standard_resolution")).into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                finish();
            }
        });


    }

}
