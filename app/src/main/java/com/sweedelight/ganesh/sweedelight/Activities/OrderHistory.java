package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderHistory extends AppCompatActivity implements AsyncResponse{


    private List<List<String>> data = new ArrayList<List<String>>();
    HashMap<String, String> params;
    String token;
    ListView listView;
    TextView total_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        // set Toolbar
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialize list view
        listView = (ListView)findViewById(R.id.order_history_list_view);
        //retrieve login_name and token from shared preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        token = settings.getString("token", "");

        // make the api call
        params = new HashMap<>();
        params.put("rt", "a/account/history");
        params.put("token", token);
        HTTPTask api_call = new HTTPTask("POST", params, this, this);
        api_call.execute();

        total_orders = (TextView)findViewById(R.id.total_orders);

    }

    @Override
    public void processFinish(String output) {
        if (output == null) {
            Toast.makeText(this, "There was an error! Try again!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject root = new JSONObject(output);
                Log.i("Received JSON: ",output);
                JSONArray orders = root.getJSONArray("orders");
                total_orders.setText(root.getString("total_orders"));
                int i;
                for(i=0;i<orders.length();i++)
                {
                    JSONObject temp = orders.getJSONObject(i);
                    List<String> element = new ArrayList<>();
                    element.add("#"+temp.getString("order_id"));
                    element.add(temp.getString("date_added"));
                    element.add(temp.getString("products"));
                    element.add(temp.getString("name"));
                    element.add(temp.getString("total"));
                    element.add(temp.getString("status"));
                    data.add(element);
                }
                listView.setAdapter(new OrderHistoryListAdapter(this,data));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
