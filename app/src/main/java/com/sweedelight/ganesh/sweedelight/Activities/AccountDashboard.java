package com.sweedelight.ganesh.sweedelight.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.HashMap;

public class AccountDashboard extends AppCompatActivity implements AsyncResponse{

    private Integer[] mImages = {
            R.drawable.account, R.drawable.password,
            R.drawable.address, R.drawable.wish,
            R.drawable.order, R.drawable.transaction,
            R.drawable.reward, R.drawable.newsletter,
            R.drawable.logout};

    private String[] mLabels;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_dashboard);

        // set Toolbar
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // display home button
      // ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get label data
        mLabels = getResources().getStringArray(R.array.account_list_labels);
        settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //initialize list view
        ListView listView = (ListView) findViewById(R.id.account_dashboard_list_view);
        listView.setAdapter(new AccountDashboardListAdapter(this,mImages,mLabels));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch(position)
                {
                    case 0: // Account Details
                            startActivity(new Intent(AccountDashboard.this,AccountDetails.class));
                            break;
                    case 1: // Change password
                            startActivity(new Intent(AccountDashboard.this,ChangePassword.class));
                            break;
                    case 2: // Address Book
                            startActivity(new Intent(AccountDashboard.this,AddressBook.class));
                            break;
                    case 3: // Wish List
                            startActivity(new Intent(AccountDashboard.this,WishList.class));
                            break;
                    case 4: // Order History
                            startActivity(new Intent(AccountDashboard.this,OrderHistory.class));
                            break;
                    case 5: // Transaction History
                            startActivity(new Intent(AccountDashboard.this,TransactionHistory.class));
                            break;
                    case 6: // Rewards Points
                            startActivity(new Intent(AccountDashboard.this,RewardPoints.class));
                            break;
                    case 7: // Newsletter Subscription
                            startActivity(new Intent(AccountDashboard.this,NewsletterSubscription.class));
                            break;
                    case 8: // Log Out
                            HashMap<String,String> params = new HashMap();
                            params.put("rt","a/account/logout");
                            params.put("token",settings.getString("token",""));
                            HTTPTask api_call = new HTTPTask("POST",params,AccountDashboard.this,AccountDashboard.this);
                            api_call.execute();
                            break;
                    default: break;
                }
            }
        });
    }

    public void processFinish(String output) {
        Toast.makeText(this, "You have been logged out!", Toast.LENGTH_SHORT).show();
    }

}
