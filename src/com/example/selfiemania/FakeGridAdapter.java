package com.example.selfiemania;

import android.media.Image;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;



/**
 * Created by dao on 10/8/14.
 */
public class FakeGridAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    //This app fakes a gridview by creating a listview with
    //3 framelayouts in each row. This is so that the first row can be the first image, but larger
    ArrayList<HashMap<String, String>> arraylist;
    ArrayList<ArrayList<HashMap<String, String>>> data;
    HashMap<String, String> resultp;

    public FakeGridAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.arraylist = arraylist;
        resultp = arraylist.get(0);
        //This creates the first image
        data = new ArrayList<ArrayList<HashMap<String, String>>>();
        //This creates the data for each row, each of which is represented by
        //a single arraylist of 3 HashMap<String, String>'s.
        for(int i = 1; i < arraylist.size(); i +=3){
            ArrayList<HashMap<String, String>> alhm = new ArrayList<HashMap<String, String>>();
            alhm.add(arraylist.get(i));
            if((i+1)< arraylist.size())
                alhm.add(arraylist.get(i+1));
            if((i+2)<arraylist.size())
                alhm.add(arraylist.get(i+2));
            data.add(alhm);
        }

    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public Object getItem(int id){
        return arraylist.get(id);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        inflater = LayoutInflater.from(context);

        if(position == 0)
            return getTopView(position, convertView, parent);
        return getGridView(position, convertView, parent);
    }

    private View getTopView(final int position, View convertView, ViewGroup parent){

        View topView = inflater.inflate(R.layout.header, parent, false);
        ImageView iv = (ImageView)topView.findViewById(R.id.img);
        Picasso.with(context).load(resultp.get("standard_resolution")).into(iv);
        iv.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent i = new Intent(context, ViewImageActivity.class);
                i.putExtra("standard_resolution", resultp.get("standard_resolution"));
                context.startActivity(i);
            }
        });
        return topView;

    }

    private View getGridView(final int position, View convertView, ViewGroup parent){
        View thisView = inflater.inflate(R.layout.grid_row, parent, false);

        int startindex = position -1;
        ImageView img1, img2, img3;

        img1 = (ImageView)thisView.findViewById(R.id.img1);
        img2 = (ImageView)thisView.findViewById(R.id.img2);
        img3 = (ImageView)thisView.findViewById(R.id.img3);

        ArrayList<HashMap<String, String>> alhm = data.get(startindex);

        final HashMap<String, String> image1 = alhm.get(0);

        Picasso.with(context).load(image1.get("thumbnail")).into(img1);
        img1.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent i = new Intent(context, ViewImageActivity.class);
                i.putExtra("standard_resolution", image1.get("standard_resolution"));
                context.startActivity(i);
            }
        });

        if(alhm.size()>=2){
            final HashMap<String, String> image2 = alhm.get(1);
            Picasso.with(context).load(image2.get("thumbnail")).into(img2);
            img2.setOnClickListener(new OnClickListener() {
                public void onClick(View v)
                {
                    Intent i = new Intent(context, ViewImageActivity.class);
                    i.putExtra("standard_resolution", image2.get("standard_resolution"));
                    context.startActivity(i);
                }
            });

        }

        if(alhm.size()==3){
            final HashMap<String, String> image3 = alhm.get(2);
            Picasso.with(context).load(image3.get("thumbnail")).into(img3);
            img3.setOnClickListener(new OnClickListener() {
                public void onClick(View v)
                {
                    Intent i = new Intent(context, ViewImageActivity.class);
                    i.putExtra("standard_resolution", image3.get("standard_resolution"));
                    context.startActivity(i);
                }
            });

        }

        return thisView;
    }

}
