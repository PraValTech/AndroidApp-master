package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
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

import com.sweedelight.ganesh.sweedelight.Fragments.SavouriesFragment;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Savouries extends AppCompatActivity implements AsyncResponse2{

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
    public static List<Sweet> mixture = new ArrayList<>();
    public static List<Sweet> bhujia = new ArrayList<>();
    public static List<Sweet> chips = new ArrayList<>();
    public static List<Sweet> namkeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savouries);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        params.put("category_id", "92");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "91");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "102");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "108");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_savouries, menu);
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


            if (cat_id == "92") {
                Log.v("mixtures", "mixtures");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    mixture.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "91") {
                Log.v("bhujia", "bhujia");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    bhujia.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "102") {
                Log.v("chips", "chips");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    chips.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }

            if (cat_id == "108") {
                Log.v("namkeens", "namkeens");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    namkeen.add(new Sweet(curr_sweet.getString("name"),
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
            View rootView = inflater.inflate(R.layout.fragment_savouries, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
            SavouriesFragment savouriesFragment = new SavouriesFragment(position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            List<Sweet> sweetList = new ArrayList<>();
            if (position == 0)
                sweetList = mixture;
            if (position == 1)
                sweetList = bhujia;
            if (position == 2)
                sweetList = chips;
            if (position == 3)
                sweetList = namkeen;

            fragment= savouriesFragment.newInstance(position);
            return fragment;
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mixture";
                case 1:
                    return "Bhujia";
                case 2:
                    return "Chips";
                case 3:
                    return "Namkeen";
            }
            return null;
        }
    }
}
