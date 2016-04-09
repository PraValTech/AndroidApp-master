package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.Arrays;
import java.util.List;

public class Location extends AppCompatActivity {
    private static int flag1 = 0, flag2 = 0, flag3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        int city = android.R.layout.simple_dropdown_item_1line;
        int area = android.R.layout.simple_dropdown_item_1line;
        int store = android.R.layout.simple_dropdown_item_1line;

        String[] city_array = getResources().getStringArray(R.array.City);
        String[] area_array = getResources().getStringArray(R.array.Area);
        String[] store_array = getResources().getStringArray(R.array.Store);

        List<String> city_list = Arrays.asList(city_array);
        List<String> area_list = Arrays.asList(area_array);
        List<String> store_list = Arrays.asList(store_array);

        ArrayAdapter<String> adapter_city = new ArrayAdapter(this, city, city_list);
        ArrayAdapter<String> adapter_area = new ArrayAdapter(this, area, area_list);
        ArrayAdapter<String> adapter_store = new ArrayAdapter(this, store, store_list);

        final AutoCompleteTextView autocompleteView_city =
                (AutoCompleteTextView) findViewById(R.id.City);
        final AutoCompleteTextView autocompleteView_area =
                (AutoCompleteTextView) findViewById(R.id.Area);
        final AutoCompleteTextView autocompleteView_store =
                (AutoCompleteTextView) findViewById(R.id.Store);

        autocompleteView_area.setAdapter(adapter_area);
        autocompleteView_city.setAdapter(adapter_city);
        autocompleteView_store.setAdapter(adapter_store);
        autocompleteView_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_area.showDropDown();
                System.out.print("entered into area");
                if (autocompleteView_area.getListSelection() > 0) {
                    flag1 = 1;
                    //System.out.println(flag + "flag at area");
                } else
                    flag1 = 0;

            }
        });
        autocompleteView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_city.showDropDown();
                System.out.print("entered into city");
                if (autocompleteView_area.getListSelection() >= 0)
                    flag2 = 1;
                else
                    flag2 = 0;
            }
        });
        autocompleteView_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autocompleteView_store.showDropDown();
                System.out.print("entered into store");
                if (autocompleteView_area.getListSelection() > 0)
                    flag3 = 1;
                else
                    flag3 = 0;
            }
        });
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

        autocompleteView_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (autocompleteView_area.getListSelection() > 0) {

                }
                // Toast.makeText(getApplicationContext(),(CharSequence)arg0.getItemAtPosition(arg2), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void callMainActivity(View view) {
        //if(flag1==1 && flag2==1 && flag3==1){
        Intent in = new Intent(getBaseContext(), MainActivity.class);
        startActivity(in);
//        }
//
//        else
//            Toast.makeText(getBaseContext(),"Please select all fields properly",Toast.LENGTH_LONG);
//    }
    }

}




