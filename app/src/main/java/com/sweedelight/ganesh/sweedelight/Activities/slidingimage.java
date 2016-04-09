package com.sweedelight.ganesh.sweedelight.Activities;

/**
 * Created by Ankita on 10-02-2016.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
/**
 * Created by Ankita on 09-02-2016.
 */
public class slidingimage extends PagerAdapter {

    private int [] images={R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};
    private LayoutInflater layoutInflater;
    private Context context;

    public slidingimage(Context context){
        this.context= context;

        //  layoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view= layoutInflater.inflate(R.layout.slidingimage, container, false);
        ImageView imageView= (ImageView)item_view.findViewById(R.id.introImageView);
        imageView.setImageResource(images[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout)object);
    }
}
