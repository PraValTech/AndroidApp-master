package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {
    private MaterialSearchView searchView;
    private SharedPreferences prefs;
    private String logintoken;
    HashMap<String, String> hashMap = new HashMap<String, String>();
    HTTPTask api_call;
    HashMap<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        params = new HashMap<>();
        params.put("rt", "a/product/category&category_id=0");

        api_call = new HTTPTask("GET", params, this, this);
        api_call.execute();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        logintoken = prefs.getString("logintoken", null);

        if (logintoken == null) {
            navigationView.getMenu().findItem(R.id.nav_myaccount).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        } else {
            navigationView.getMenu().findItem(R.id.nav_myaccount).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
        }
        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        SliderLayout mDemoSlider = (SliderLayout) findViewById(R.id.slider);

//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("TEXT1", R.drawable.s2);
        file_maps.put("TEXT2", R.drawable.s3);
        file_maps.put("TEXT3", R.drawable.s4);
        file_maps.put("TEXT4", R.drawable.s5);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            // .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        //  mDemoSlider.addOnPageChangeListener(this);
        // ListView l = (ListView)findViewById(R.id.transformers);
        //l.setAdapter(new TransformerAdapter(this));
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
//                Toast.makeText(MainActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
//        final CardView cardView = (CardView) findViewById(R.id.offers1_card_view);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View v) {
//                v.getContext().startActivity(new Intent(v.getContext(), Sweets.class));
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void openCategories(View view) {
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

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

        if (id == R.id.action_legal) {
            Intent in = new Intent(getBaseContext(), Legal.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_categories) {
            Intent intent = new Intent(MainActivity.this, Categories.class);
            startActivity(intent);

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(MainActivity.this, ShoppingCart.class);
            startActivity(intent);

        } else if (id == R.id.nav_specials) {
            Intent intent = new Intent(MainActivity.this, Offers.class);
            startActivity(intent);

        } else if (id == R.id.nav_myaccount) {
            Intent intent = new Intent(MainActivity.this, AccountDashboard.class);
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            prefs.edit().putString("logintoken", null).apply();

        } else if (id == R.id.about_us) {
            Intent intent = new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);
        } else if (id == R.id.review) {
            Intent intent = new Intent(MainActivity.this, Review.class);
            startActivity(intent);
        } else if (id == R.id.action_legal) {
            Intent intent = new Intent(MainActivity.this, Legal.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void processFinish(String output) {
        StringBuilder stringBuilder = new StringBuilder(output);
        JSONObject result = null;
        try {
            result = new JSONObject(stringBuilder.toString());
            JSONArray categories = result.getJSONArray("subcategories");
            JSONObject curr_category;
            // LinearLayout layout= (LinearLayout)findViewById(R.id.categories_layout);

            for (int i = 0; i < categories.length(); i++) {

                curr_category = categories.getJSONObject(i);
                hashMap.put(curr_category.getString("name"), curr_category.getString("thumb"));
                // System.out.println((hashMap.getKey()+" "+hashMap.getValue());
            }
            for (Map.Entry m : hashMap.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }

            LinearLayout layout_sweets = (LinearLayout) findViewById(R.id.categories_sweets);
            TextView textView1 = (TextView) layout_sweets.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Sweets")) {
                textView1.setText("Sweets");
                ImageView imageView = (ImageView) layout_sweets.findViewById(R.id.categories_image);

                setImage(imageView, hashMap.get("Sweets"));
            }
            layout_sweets.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Sweets.class));
                }
            });
            LinearLayout layout_namkeens = (LinearLayout) findViewById(R.id.categories_namkeens);
            TextView textView2 = (TextView) layout_namkeens.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Namkeens")) {
                textView2.setText("Namkeens");
                ImageView imageView = (ImageView) layout_namkeens.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Namkeens"));
            }
            layout_namkeens.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Savouries.class));
                }
            });
            LinearLayout layout_cakes = (LinearLayout) findViewById(R.id.categories_cakes);
            TextView textView3 = (TextView) layout_cakes.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Cakes &amp; Bakery")) {
                textView3.setText("Cakes");
                ImageView imageView = (ImageView) layout_cakes.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Cakes &amp; Bakery"));
            }
            layout_cakes.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Cakes.class));
                }
            });
            LinearLayout layout_snacks = (LinearLayout) findViewById(R.id.categories_snacks);
            TextView textView4 = (TextView) layout_snacks.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Evening Snacks")) {
                textView4.setText("Snacks");
                ImageView imageView = (ImageView) layout_snacks.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Evening Snacks"));
            }
            layout_snacks.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Snacks.class));
                }
            });

            LinearLayout layout_finedine = (LinearLayout) findViewById(R.id.categories_finedine);
            TextView textView5 = (TextView) layout_finedine.findViewById(R.id.categories_text);
            if (hashMap.containsKey("FineDine")) {
                textView5.setText("FineDine");
                ImageView imageView = (ImageView) layout_finedine.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("FineDine"));
            }
            layout_finedine.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Finedines.class));
                }
            });
            LinearLayout layout_dryfruits = (LinearLayout) findViewById(R.id.categories_dryfruits);
            TextView textView6 = (TextView) layout_dryfruits.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Dry Fruits")) {
                textView6.setText("Dry Fruits");
                ImageView imageView = (ImageView) layout_dryfruits.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Dry Fruits"));
            }
            layout_dryfruits.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Dryfruits.class));
                }
            });
            LinearLayout layout_dairy = (LinearLayout) findViewById(R.id.categories_dairy);
            TextView textView7 = (TextView) layout_dairy.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Dairy Products")) {
                textView7.setText("Dairy Products");
                ImageView imageView = (ImageView) layout_dairy.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Dairy Products"));
            }
            layout_dairy.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), DiaryProducts.class));
                }
            });
            LinearLayout layout_others = (LinearLayout) findViewById(R.id.categories_others);
            TextView textView8 = (TextView) layout_others.findViewById(R.id.categories_text);
            if (hashMap.containsKey("Others")) {
                textView8.setText("Others");
                ImageView imageView = (ImageView) layout_others.findViewById(R.id.categories_image);
                setImage(imageView, hashMap.get("Others"));
            }
            layout_others.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Others.class));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImage(ImageView imageView, String thumb){
        Picasso.with(this)
                .load(thumb)
                .placeholder(R.drawable.s4) // optional
                .error(R.drawable.s4)         // optional
                .into(imageView);

    }




}