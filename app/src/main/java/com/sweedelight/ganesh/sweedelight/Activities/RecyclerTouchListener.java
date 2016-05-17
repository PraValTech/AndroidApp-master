package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.sweedelight.ganesh.sweedelight.Fragments.FinedinesFragment;
import com.sweedelight.ganesh.sweedelight.Fragments.SavouriesFragment;
import com.sweedelight.ganesh.sweedelight.Fragments.SnacksFragment;
import com.sweedelight.ganesh.sweedelight.Fragments.SweetsFragment;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private Categories.ClickListener CategoriesclickListener;
    private SweetsFragment.ClickListener SweetsclickListener;
    private SavouriesFragment.ClickListener SavouriesclickListener;
    private  Cakes.ClickListener CakesclickListener;
    private SnacksFragment.ClickListener SnacksclickListener;
    private FinedinesFragment.ClickListener FinedinesclickListener;
    private Dryfruits.ClickListener DryfruitsclickListener;
    private DiaryProducts.ClickListener DiaryProductsclickListener;
    private Others.ClickListener OthersclickListener;
    private GestureDetector gestureDetector;

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

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SavouriesFragment.ClickListener SavouriesclickListener) {
        this.SavouriesclickListener = SavouriesclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && SavouriesclickListener != null) {
                    SavouriesclickListener.onLongClick(child, recyclerView.getChildPosition(child));
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
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SnacksFragment.ClickListener SnacksclickListener) {
        this.SnacksclickListener = SnacksclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && SnacksclickListener != null) {
                    SnacksclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FinedinesFragment.ClickListener FinedinesclickListener) {
        this.FinedinesclickListener = FinedinesclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && FinedinesclickListener != null) {
                    FinedinesclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Dryfruits.ClickListener DryfruitsclickListener) {
        this.DryfruitsclickListener = DryfruitsclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && DryfruitsclickListener != null) {
                    DryfruitsclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final DiaryProducts.ClickListener DiaryproductsclickListener) {
        this.DiaryProductsclickListener = DiaryproductsclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && DiaryProductsclickListener != null) {
                    DiaryProductsclickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Others.ClickListener OthersclickListener) {
        this.OthersclickListener = OthersclickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && OthersclickListener != null) {
                    OthersclickListener.onLongClick(child, recyclerView.getChildPosition(child));
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

        if (child != null && SavouriesclickListener != null && gestureDetector.onTouchEvent(e)) {
            SavouriesclickListener.onClick(child, rv.getChildPosition(child));
        }

        if (child != null && SnacksclickListener != null && gestureDetector.onTouchEvent(e)) {
            SnacksclickListener.onClick(child, rv.getChildPosition(child));
        }
        if (child != null && FinedinesclickListener != null && gestureDetector.onTouchEvent(e)) {
            FinedinesclickListener.onClick(child, rv.getChildPosition(child));
        }
        if (child != null && DryfruitsclickListener != null && gestureDetector.onTouchEvent(e)) {
            DryfruitsclickListener.onClick(child, rv.getChildPosition(child));
        }

        if (child != null && DiaryProductsclickListener != null && gestureDetector.onTouchEvent(e)) {
            DiaryProductsclickListener.onClick(child, rv.getChildPosition(child));
        }

        if (child != null && CakesclickListener != null && gestureDetector.onTouchEvent(e)) {
            CakesclickListener.onClick(child, rv.getChildPosition(child));
        }

        if (child != null && OthersclickListener != null && gestureDetector.onTouchEvent(e)) {
            OthersclickListener.onClick(child, rv.getChildPosition(child));
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
