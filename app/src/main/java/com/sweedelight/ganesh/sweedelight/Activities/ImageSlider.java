package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sweedelight.ganesh.sweedelight.R;
import com.viewpagerindicator.CirclePageIndicator;


public class ImageSlider extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private static int NUM_PAGES;
    private static int currentPage=0;
    CirclePageIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        viewPager=(ViewPager)findViewById(R.id.pager);
        pagerAdapter= new slidingimage(this);
        viewPager.setAdapter(pagerAdapter);


        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        //mIndicator.setCurrentItem(pagerAdapter.getCount() - 1);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =4;

        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//                    //currentPage = 0;
//                    Intent in = new Intent(getBaseContext(), Location.class);
//                    startActivity(in);
//                }
//                viewPager.setCurrentItem(currentPage++, true);
//            }
//        };
//      Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2000, 2000);
//
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
//                if (currentPage == NUM_PAGES)
                if(position==3)
                {
                    {
                        //currentPage = 0;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // TODO: Your application init goes here.
                                Intent in = new Intent(getBaseContext(), Location.class);
                                startActivity(in);
                            }
                        }, 2000);

                    }
                }
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });




    }

    public void onSkip(View view) {

        Intent in = new Intent(getBaseContext(), Location.class);
        startActivity(in);

    }
}