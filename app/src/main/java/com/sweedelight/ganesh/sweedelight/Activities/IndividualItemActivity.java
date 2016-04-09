
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
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.TextView;

        import com.sweedelight.ganesh.sweedelight.R;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Vector;

        public class IndividualItemActivity extends AppCompatActivity {
            private TabLayout tabLayout;
            private ViewPager viewPager;

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int weights= android.R.layout.simple_dropdown_item_1line;
        String[] weight_array= getResources().getStringArray(R.array.weight);
        List<String> weight_list = Arrays.asList(weight_array);
        ArrayAdapter<String> adapter_weight = new ArrayAdapter(this, weights, weight_list);
        final AutoCompleteTextView autocompleteView_weight =
                (AutoCompleteTextView) findViewById(R.id.weight);
        autocompleteView_weight.setAdapter(adapter_weight);
        autocompleteView_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_weight.showDropDown();


            }
        });

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
     //   mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.containerindiv);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsindiv);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

            private void setupViewPager(ViewPager viewPager) {
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(new Description(), "Description");
                adapter.addFragment(new FragReview(), "Review");
                viewPager.setAdapter(adapter);
            }

            class ViewPagerAdapter extends FragmentPagerAdapter {
                private final List<Fragment> mFragmentList = new ArrayList<>();
                private final List<String> mFragmentTitleList = new ArrayList<>();

                public ViewPagerAdapter(FragmentManager manager) {
                    super(manager);
                }

                @Override
                public Fragment getItem(int position) {
                    return mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mFragmentList.size();
                }

                public void addFragment(Fragment fragment, String title) {
                    mFragmentList.add(fragment);
                    mFragmentTitleList.add(title);
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return mFragmentTitleList.get(position);
                }
            }

}