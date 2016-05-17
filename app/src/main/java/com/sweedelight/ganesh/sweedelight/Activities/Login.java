package com.sweedelight.ganesh.sweedelight.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
                    settings.edit().putString("logintoken","valid").apply();
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

