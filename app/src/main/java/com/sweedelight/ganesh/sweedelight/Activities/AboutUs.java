package com.sweedelight.ganesh.sweedelight.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.sweedelight.ganesh.sweedelight.R;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // set content to the WebView
        // used WebView for 'justified' alignment
        WebView webView = (WebView) findViewById(R.id.company_info);
        webView.loadData(getString(R.string.about_us_content), "text/html", "utf-8");
        webView.setBackgroundColor(0x00000000);         //set background to transparent
    }


}
