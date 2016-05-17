package com.sweedelight.ganesh.sweedelight.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sweedelight.ganesh.sweedelight.R;

public class EditAddressBook extends AppCompatActivity {

    Intent receivedIntent;
    TextInputEditText firstName, lastName, companyName, address1, address2, zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address_book);
        // set Toolbar
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        receivedIntent = getIntent();

        // entry into activity after clicking edit address
        if (receivedIntent.getStringExtra("Button").equals("Edit") )
        {
            firstName = (TextInputEditText) findViewById(R.id.input_first_name);
            String string = receivedIntent.getStringExtra("first_name");
            firstName.setText(string);

            lastName = (TextInputEditText) findViewById(R.id.input_last_name);
            string = receivedIntent.getStringExtra("last_name");
            lastName.setText(string);

            companyName = (TextInputEditText) findViewById(R.id.input_company_name);
            string = receivedIntent.getStringExtra("company_name");
            companyName.setText(string);

            address1 = (TextInputEditText) findViewById(R.id.input_address_1);
            string = receivedIntent.getStringExtra("address_1");
            address1.setText(string);

            address2 = (TextInputEditText) findViewById(R.id.input_address_2);
            string = receivedIntent.getStringExtra("address_2");
            address2.setText(string);

            Spinner spinner = (Spinner) findViewById(R.id.input_area);
            string = receivedIntent.getStringExtra("area_name");
            spinner.setSelection(getIndex(spinner, string));

            spinner = (Spinner) findViewById(R.id.input_city);
            string = receivedIntent.getStringExtra("city_name");
            spinner.setSelection(getIndex(spinner, string));

            spinner = (Spinner) findViewById(R.id.input_state);
            string = receivedIntent.getStringExtra("state_name");
            spinner.setSelection(getIndex(spinner, string));

            zipCode = (TextInputEditText) findViewById(R.id.input_zip_code);
            string = receivedIntent.getStringExtra("zip_code");
            zipCode.setText(string);
        }
        else
        {
            Button button = (Button)findViewById(R.id.update_button);
            button.setText("Add Address");
        }

    }

    public void onClickButton(View v){


        if(receivedIntent.getStringExtra("Button").equals("Add"))
        {
            ;// what to do if new address is added
        }
        else if(receivedIntent.getStringExtra("Button").equals("Edit"))
        {
            ;// what to do if existing address is being edited
        }
    }

    // private method for getting index
    private int getIndex(Spinner spinner, String string) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof TextInputEditText) {
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
