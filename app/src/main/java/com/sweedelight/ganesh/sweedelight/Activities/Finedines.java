package com.sweedelight.ganesh.sweedelight.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sweedelight.ganesh.sweedelight.Fragments.FinedinesFragment;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Finedines extends AppCompatActivity  implements AsyncResponse2{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    HTTPTask2 api_call;
    HashMap<String, String> params;
    public static List<Sweet> atithi = new ArrayList<>();
    public static List<Sweet> chinese = new ArrayList<>();
    public static List<Sweet> indian_breads = new ArrayList<>();
    public static List<Sweet> north_spl = new ArrayList<>();
    public static List<Sweet> sizzler = new ArrayList<>();
    public static List<Sweet> south = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        params = new HashMap<>();
        params.put("rt", "a/product/filter");
        params.put("category_id", "125");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "122");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "123");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "127");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "124");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "126");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snacks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(String cat_id, String output) {


        JSONObject result = null;
        try {
            result = new JSONObject(output);
            JSONArray items = result.getJSONArray("rows");
            JSONObject curr_item;
            JSONObject curr_sweet;


            if (cat_id == "125") {
                Log.v("atithi", "athiti");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    atithi.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "122") {
                Log.v("chinese", "chinese");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    chinese.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "123") {
                Log.v("indian breads", "indian breads  ");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    indian_breads.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "127") {
                Log.v("north", "north");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    north_spl.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }


            if (cat_id == "124") {
                Log.v("sizzler", "sizzler");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    sizzler.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }
            if (cat_id == "126") {
                Log.v("south", "south");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    south.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_snacks, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            FinedinesFragment finedinesFragment = new FinedinesFragment(position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            List<Sweet> sweetList = new ArrayList<>();
            if (position == 0)
                sweetList = atithi;
            if (position == 1)
                sweetList = chinese;
            if (position == 2)
                sweetList = indian_breads;
            if (position == 3)
                sweetList = north_spl;
            if (position == 4)
                sweetList = sizzler;
            if (position == 5)
                sweetList = south;



            fragment= finedinesFragment.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Athithi Menu";
                case 1:
                    return "Chinese ";
                case 2:
                    return "Indian Breads ";
                case 3:
                    return "North Special ";
                case 4:
                    return "Sizzler & Tikka ";
                case 5:
                    return "South Special";








            }
            return null;
        }
    }
}
