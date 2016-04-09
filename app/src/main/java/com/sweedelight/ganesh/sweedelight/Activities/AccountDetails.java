package com.sweedelight.ganesh.sweedelight.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AccountDetails extends AppCompatActivity implements AsyncResponse {

    TextView login_name;
    TextInputEditText first_name, last_name, email, mobile, fax;
    String login_name_string, token;
    RadioButton radioButtonYes, radioButtonNo;
    HashMap<String, String> params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Retrieve TextView and EditTexts
        login_name = (TextView) findViewById(R.id.login_name);
        first_name = (TextInputEditText) findViewById(R.id.input_first_name);
        last_name = (TextInputEditText) findViewById(R.id.input_last_name);
        email = (TextInputEditText) findViewById(R.id.input_email);
        mobile = (TextInputEditText) findViewById(R.id.input_mobile);
        fax = (TextInputEditText) findViewById(R.id.input_fax);
        radioButtonYes = (RadioButton) findViewById(R.id.input_subscribe_yes);
        radioButtonNo = (RadioButton) findViewById(R.id.input_subscribe_no);
        radioButtonYes.setChecked(false);
        radioButtonNo.setChecked(false);

        //retrieve login_name and token from shared preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        login_name_string = settings.getString("login_name", "");
        token = settings.getString("token", "");

        // make the api call
        params = new HashMap<>();
        params.put("rt", "a/account/edit");
        params.put("token", token);
        HTTPTask api_call = new HTTPTask("GET", params, this, this);
        api_call.execute();

    }


    // called after values are returned
    @Override
    public void processFinish(String output) {
        if (output == null) {
            Toast.makeText(this, "There was an error! Try again!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject root = new JSONObject(output);
                if (root.has("fields")) {   // the response is successful
                    String status = "1";
                    if(root.has("status"))
                    {
                        status = root.getString("status");
                    }
                    if(status.equals("0"))
                    {
                        String error_firstname = root.getString("error_firstname");
                        String error_lastname = root.getString("error_lastname");
                        String error_email = root.getString("error_email");
                        String error_telephone = root.getString("error_telephone");

                        if(!error_firstname.equals("null"))
                            Toast.makeText(this, error_firstname, Toast.LENGTH_LONG).show();
                        else if(!error_lastname.equals("null"))
                        Toast.makeText(this, error_lastname, Toast.LENGTH_LONG).show();
                        else if(!error_email.equals("null"))
                            Toast.makeText(this, error_email, Toast.LENGTH_LONG).show();
                        else if(!error_telephone.equals("null"))
                            Toast.makeText(this, error_telephone, Toast.LENGTH_LONG).show();

                    }
                    JSONObject fields = root.getJSONObject("fields");
                    JSONObject firstName = fields.getJSONObject("firstname");
                    JSONObject lastName = fields.getJSONObject("lastname");
                    JSONObject emailObject = fields.getJSONObject("email");
                    JSONObject telephone = fields.getJSONObject("telephone");
                    JSONObject faxObject = fields.getJSONObject("fax");
                    JSONObject newsletter = fields.getJSONObject("newsletter");

                    login_name.setText(login_name_string);
                    first_name.setText(firstName.getString("value"));
                    last_name.setText(lastName.getString("value"));
                    email.setText(emailObject.getString("value"));
                    mobile.setText(telephone.getString("value"));
                    fax.setText(faxObject.getString("value"));
                    if (newsletter.getString("value").equals("1"))
                        radioButtonYes.setChecked(true);
                    else
                        radioButtonNo.setChecked(true);
                } else if(root.has("status")){
                    String status = root.getString("status");
                    if(status.equals("1"))
                        Toast.makeText(this, "Account Details have been updated!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this, "There was an error!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Error! Not logged in!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void onClickUpdate(View v) {
        params = new HashMap<>();
        params.put("rt", "a/account/edit");
        params.put("token", token);
        params.put("firstname", first_name.getText().toString());
        params.put("lastname", last_name.getText().toString());
        params.put("email", email.getText().toString());
        params.put("telephone", mobile.getText().toString());
        params.put("fax", fax.getText().toString());
        int newsletterValue;
        if (radioButtonYes.isChecked())
            newsletterValue = 1;
        else
            newsletterValue = 0;
        params.put("newsletter", "" + newsletterValue);
        HTTPTask api_call = new HTTPTask("POST", params, this, this);
        api_call.execute();
    }

}