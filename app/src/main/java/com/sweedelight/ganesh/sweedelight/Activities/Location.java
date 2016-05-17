package com.sweedelight.ganesh.sweedelight.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location extends AppCompatActivity {
    private static int flag=0;
    Spinner autocompleteView_city;
    AutoCompleteTextView autocompleteView_area;
    Spinner autocompleteView_store;
    String [] area_array;
    boolean doubleClick=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        int city = android.R.layout.simple_dropdown_item_1line;
        int area = android.R.layout.simple_dropdown_item_1line;
        int store = android.R.layout.simple_dropdown_item_1line;

        String[] city_array = getResources().getStringArray(R.array.City);
        area_array = getResources().getStringArray(R.array.areas);
        String[] store_array = getResources().getStringArray(R.array.Store);

        List<String> city_list = Arrays.asList(city_array);
        List<String> area_list = Arrays.asList(area_array);
        List<String> store_list = Arrays.asList(store_array);

        ArrayAdapter<String> adapter_city = new ArrayAdapter<>(this, city, city_list);
        ArrayAdapter<String> adapter_area = new ArrayAdapter<>(this, area, area_list);
        ArrayAdapter<String> adapter_store = new ArrayAdapter<>(this, store, store_list);

        autocompleteView_city =
                (Spinner) findViewById(R.id.City);
        autocompleteView_area =
                (AutoCompleteTextView) findViewById(R.id.Area);
        autocompleteView_store =
                (Spinner) findViewById(R.id.Store);

        autocompleteView_area.setAdapter(adapter_area);
        autocompleteView_city.setAdapter(adapter_city);
        autocompleteView_store.setAdapter(adapter_store);



        autocompleteView_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {

                if(autocompleteView_area.isPopupShowing())
                {
                    autocompleteView_area.dismissDropDown();
                }
                else
                {
                   autocompleteView_area.showDropDown();
                }


                /*System.out.print("entered into area");
                if (autocompleteView_area.getListSelection() >= 0) {
                    flag = 1;

                    //System.out.println(flag + "flag at area");
                } else if (autocompleteView_area.getListSelection() == ListView.INVALID_POSITION)
                {
                    flag = 0;

                }

                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg0.getWindowToken(), 0);*/

        }

        });

           /*
        autocompleteView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_city.showDropDown();
                System.out.print("entered into city");
                if (autocompleteView_city.getListSelection() >= 0)
                    flag2 = 1;
                else if (autocompleteView_city.getListSelection() == ListView.INVALID_POSITION)
                    flag2 = 0;
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
            }
        });
        autocompleteView_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_store.showDropDown();
                System.out.print("entered into store");
                if (autocompleteView_store.getListSelection() >= 0)
                    flag3 = 1;
                else if (autocompleteView_store.getListSelection() == ListView.INVALID_POSITION)
                    flag3 = 0;
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg0.getWindowToken(), 0);
            }
        });*/


//        autocompleteView_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//                //... your stuff
//                if(autocompleteView_area.getListSelection()>0) {
//                    flag = 1;
//                    System.out.println(flag + "flag at area");
//                }else
//                    flag=0;
//
//
//            }
//        });

        /*autocompleteView_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (autocompleteView_area.getListSelection() >= 0) {
                    flag = 1;

                    //System.out.println(flag + "flag at area");
                } else if (autocompleteView_area.getListSelection() == ListView.INVALID_POSITION)
                {
                    flag = 0;

                }
                // Toast.makeText(getApplicationContext(),(CharSequence)arg0.getItemAtPosition(arg2), Toast.LENGTH_LONG).show();

            }
        });*/


    }

    public void callMainActivity(View view) {
        String area = autocompleteView_area.getText().toString();
        for(int i=0;i<area_array.length;i++)
        {
            if(area.equals(area_array[i])) {
                flag = 1;
                break;
            }
        }

        if(flag==0)
        {
            Toast.makeText(Location.this, "Please enter a valid area", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent in = new Intent(getBaseContext(), MainActivity.class);
            startActivity(in);
        }
//        }
//
//        else
//            Toast.makeText(getBaseContext(),"Please select all fields properly",Toast.LENGTH_LONG);
//    }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof AutoCompleteTextView) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}




