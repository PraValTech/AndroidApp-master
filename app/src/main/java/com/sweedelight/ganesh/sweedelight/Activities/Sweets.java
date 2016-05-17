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
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.Fragments.SweetsFragment;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sweets extends AppCompatActivity implements AsyncResponse2 {

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
    private static String result;
    HTTPTask2 api_call;
    HashMap<String, String> params;
    public static List<Sweet> barfiArrayList = new ArrayList<>();
    public static List<Sweet> rasbhare = new ArrayList<>();
    public static List<Sweet> ladoos = new ArrayList<>();
    public static List<Sweet> peda = new ArrayList<>();
    public static List<Sweet> kaju = new ArrayList<>();
    public static List<Sweet> dryfruits = new ArrayList<>();
    public static List<Sweet> bengali = new ArrayList<>();
    public static List<Sweet> ghee = new ArrayList<>();
    public static List<Sweet> khowa = new ArrayList<>();
    public static List<Sweet> halwa = new ArrayList<>();
    public static List<Sweet> others = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweets);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        params.put("category_id", "74");
        params.put("page", "1");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "78");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "71");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "94");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "109");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "110");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "111");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "115");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "116");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "113");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();


        params.clear();
        params.put("rt", "a/product/filter");
        params.put("category_id", "112");
        params.put("page", "1");
        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sweets, menu);
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


            if (cat_id == "74") {
                Log.v("barfi", "barfi");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    barfiArrayList.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }
            if (cat_id == "78") {
                Log.v("ladoos", "ladoos");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    ladoos.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "71") {
                Log.v("rasbhare", "rasbhare");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    rasbhare.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "94") {
                Log.v("pedas", "pedas");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    peda.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "109") {
                Log.v("kaju", "kaju");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    kaju.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "110") {
                Log.v("dryfruits", "dryfruits");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    dryfruits.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }

            if (cat_id == "111") {
                Log.v("begali", "bengali");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    bengali.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "115") {
                Log.v("ghee", "ghee");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    ghee.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "116") {
                Log.v("khowa", "khowa");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    khowa.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }

            if (cat_id == "113") {
                Log.v("halwa", "halwa");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    halwa.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }
            if (cat_id == "112") {
                Log.v("others", "others");
                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    others.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("reached", "reached");
        //Toast.makeText("hello", );
    }

    public static String getResult() {
        return result;
    }

    /**
     * A placehold0er fragment containing a simple view.
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
            View rootView = inflater.inflate(R.layout.fragment_sweets, container, false);
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
            SweetsFragment sweetsFragment = new SweetsFragment(position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            List<Sweet> sweetList = new ArrayList<>();
            if (position == 0)
                sweetList = barfiArrayList;
            if (position == 1)
                sweetList = rasbhare;
            if (position == 2)
                sweetList = ladoos;
            if (position == 3)
                sweetList = peda;
            if (position == 4)
                sweetList = kaju;
            if (position == 5)
                sweetList = dryfruits;
            if (position == 6)
                sweetList = bengali;
            if (position == 7)
                sweetList = ghee;
            if (position == 8)
                sweetList = khowa;
            if (position == 9)
                sweetList = halwa;


            fragment = sweetsFragment.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Barfi";
                case 1:
                    return "Rasbhare";
                case 2:
                    return "Laddoos";
                case 3:
                    return "Peda";
                case 4:
                    return "Kaju";
                case 5:
                    return "Dryfruits";
                case 6:
                    return "Bengali";
                case 7:
                    return "Ghee";
                case 8:
                    return "Special Khowa";
                case 9:
                    return "Halwa";

            }
            return null;
        }
    }
}

