package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.sweedelight.ganesh.sweedelight.Fragments.SweetsFragment;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private  Cakes.ClickListener CakesclickListener;
    private SweetsFragment.ClickListener SweetsclickListener;
    private GestureDetector gestureDetector;
    private Categories.ClickListener CategoriesclickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Categories.ClickListener CategoriesclickListener) {
        this.CategoriesclickListener = CategoriesclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && CategoriesclickListener != null) {
                    CategoriesclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    public RecyclerTouchListener(FragmentActivity activity, final RecyclerView mRecyclerView, final SweetsFragment.ClickListener SweetsclickListener) {
        this.SweetsclickListener = SweetsclickListener;
        gestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && SweetsclickListener != null) {
                    SweetsclickListener.onLongClick(child, mRecyclerView.getChildPosition(child));
                }
            }
        });
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Cakes.ClickListener CakesclickListener) {
        this.CakesclickListener = CakesclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && CakesclickListener != null) {
                    CakesclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && CategoriesclickListener != null && gestureDetector.onTouchEvent(e)) {
            CategoriesclickListener.onClick(child, rv.getChildPosition(child));
        }

        if (child != null && SweetsclickListener != null && gestureDetector.onTouchEvent(e)) {
            SweetsclickListener.onClick(child, rv.getChildPosition(child));
        }
        if (child != null && CakesclickListener != null && gestureDetector.onTouchEvent(e)) {
            CakesclickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
