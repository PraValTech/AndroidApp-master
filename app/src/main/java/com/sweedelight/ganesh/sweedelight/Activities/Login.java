package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONObject;

import java.util.HashMap;





public class Login extends AppCompatActivity implements AsyncResponse{



    TextInputEditText username;
    TextInputEditText password;
    String username_string;
    String password_string;
    HashMap<String,String> params;
    JSONObject jsonobject;
    HTTPTask api_call;


    SharedPreferences settings;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (TextInputEditText)findViewById(R.id.input_login_name);
        password = (TextInputEditText)findViewById(R.id.input_password);


        settings = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        editor = settings.edit();

    }

    public void onClickLogin(View v)
    {
        username_string=username.getText().toString();
        password_string=password.getText().toString();
        params = new HashMap<>();
        params.put("rt","a/account/login");
        params.put("email",username_string);
        params.put("password",password_string);

        api_call = new HTTPTask("POST",params,this,this);
        api_call.execute();
    }


    public void onClickRegister(View v)
    {
        startActivity(new Intent(this,Register.class));
    }

    @Override
    public void processFinish(String response) {
        if(response!=null)
        {
            // add code to store token in shared preferences
            try{
                jsonobject = new JSONObject(response);
                Log.i("Response", response);
                String status = jsonobject.getString("status");

                // check if login was successful
                if(status.equals("1")) {
                    String token = jsonobject.getString("token");
                    editor.putString("login_name", username_string);
                    editor.putString("token", token);
                    editor.commit();
                    Log.i("Token", settings.getString("token", ""));
                    Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Error! Invalid username/password!", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                Log.e("ERROR", e.getMessage(), e);
                Toast.makeText(this, "There was an error! Try again!", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "There was an error! Try again!", Toast.LENGTH_SHORT).show();
        }

    }


}

