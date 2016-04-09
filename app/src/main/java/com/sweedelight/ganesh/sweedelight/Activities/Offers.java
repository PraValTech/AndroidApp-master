package com.sweedelight.ganesh.sweedelight.Activities;


import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.sweedelight.ganesh.sweedelight.R;

public class Offers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView t1 = (TextView) findViewById(R.id.offer1_text);
        t1.setText(" 1st Order Free :\n" +

                "1.  Applicable only for Registered Customers\n" +
                "2. 100% value of your 1st Order will be credited to SweeDelight Wallet account\n" +
                "3.  Maximum discount of Rs. 500/-\n" +
                "4.  No Coupon code required. Account will get credited on Order completion\n" +
                "5.  SweeDelight wallet amount can be used for future purchases from our portal\n" +
                "6.  Cashback amount will reflect under Rewards points in SweeDelight account\n");

        TextView t2 = (TextView) findViewById(R.id.offer2_text);
        t2.setText(" Valentine Day Offer :  \n" +

                " 1.  Applicable until February end\n" +
                " 2.  Flat 15% Off on any Cake\n" +
                " 3.  Coupon Code : SWEEVDAY\n" +
                " 4.  Coupon Code can be used at Guest Checkout for 1st time user and 2nd order onwards for Registered Customers\n" +
                " 5.  Coupon applicable only on cakes\n" +
                " 6.  Delivery charge of Rs. 50/- applicable on cakes");

        TextView t3 = (TextView) findViewById(R.id.offer3_text);
        t3.setText(" Referral Program:  \n" +
                " 1.  Applicable only for Registered Customers\n" +
                " 2.  Friend's details are required in Referral link post login\n" +
                " 3. 100 Rs/- credit for every successful referral to referrer\n" +
                " 4.  Referral amount gets credited on your friend's first purchase completion\n" +
                " 5.  Friend's first order is also applicable for 1st Order free offer");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }



}
