
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
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;
        import com.sweedelight.ganesh.sweedelight.R;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Vector;

        public class IndividualItemActivity extends AppCompatActivity {
            private TabLayout tabLayout;
            private ViewPager viewPager;
            private TextView name;
            private TextView description;
            private TextView price;
            private ImageView thumbnail;
            private String description_panel_item_name="";
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name= (TextView)findViewById(R.id.itemname);
        description=(TextView)findViewById(R.id.item_sub_title);
        price=(TextView)findViewById(R.id.itemprice);
        thumbnail=(ImageView)findViewById(R.id.categories_image);
        int weights= android.R.layout.simple_dropdown_item_1line;
        String[] weight_array= getResources().getStringArray(R.array.weight);
        List<String> weight_list = Arrays.asList(weight_array);
        ArrayAdapter<String> adapter_weight = new ArrayAdapter(this, weights, weight_list);

        Intent in=getIntent();
        name.setText(in.getStringExtra("name"));
        description_panel_item_name= in.getStringExtra("name");
        description.setText(in.getStringExtra("desc"));
        price.setText("Rs. " + in.getStringExtra("price"));
        Picasso.with(this)
                .load(in.getStringExtra("thumb"))
                .placeholder(R.drawable.s4) // optional
                .error(R.drawable.s4)         // optional
                .into(thumbnail);

        final AutoCompleteTextView autocompleteView_weight =
                (AutoCompleteTextView) findViewById(R.id.weight);
        autocompleteView_weight.setAdapter(adapter_weight);
        autocompleteView_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_weight.showDropDown();

            }
        });

        Description description= new Description(in.getStringExtra("name"));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
     //   mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.containerindiv);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabsindiv);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

            private void setupViewPager(ViewPager viewPager) {
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(new Description(description_panel_item_name), "Description");
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