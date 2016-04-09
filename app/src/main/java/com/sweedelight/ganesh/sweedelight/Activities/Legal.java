package com.sweedelight.ganesh.sweedelight.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.sweedelight.ganesh.sweedelight.Adapters.ExpandableListAdapter;
import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Legal extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal2);
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        String privacy= "Privacy Policy";
        String privacy_content= "SweeDelight respects your privacy. This Privacy Policy provides succinctly the manner your data is collected and used by SweeDelight on the Site. As a visitor to the Site/ Customer you are advised to please read the Privacy Policy carefully. By accessing the services provided by the Site you agree to the collection and use of your data by SweeDelight in the manner provided in this Privacy Policy.\n" +
                "\n" +
                "As part of the registration process on the Site, SweeDelight may collect the following personally identifiable information about you: Name including first and last name, email address, mobile phone number and contact details, Postal code, Demographic profile (like your age, gender, occupation, education, address etc.) and information about the pages on the site you visit/access, the links you click on the site, the number of times you access the page and any such browsing information.\n" +
                "\n" +
                "SweeDelight will collect personally identifiable information about you only as part of a voluntary registration process, on-line survey or any combination thereof. The Site may contain links to other Web sites. SweeDelight is not responsible for the privacy practices of such Web sites which it does not own, manage or control. The Site and third-party vendors, including Google, use first-party cookies (such as the Google Analytics cookie) and third-party cookies (such as the DoubleClick cookie) together to inform, optimize, and serve ads based on someone's past visits to the Site. \n" +
                "\n" +
                "SweeDelight will use your personal information to provide personalized features to you on the Site and to provide for promotional offers to you through the Site and other channels. SweeDelight will also provide this information to its business associates and partners to get in touch with you when necessary to provide the services requested by you. SweeDelight will use this information to preserve transaction history as governed by existing law or policy. SweeDelight may also use contact information internally to direct its efforts for product improvement, to contact you as a survey respondent, to notify you if you win any contest; and to send you promotional materials from its contest sponsors or advertisers. SweeDelight will also use this information to serve various promotional and advertising materials to you via display advertisements through the Google Ad network on third party websites. You can opt out of Google Analytics for Display Advertising and customize Google Display network ads using the Ads Preferences Manager. Information about Customers on an aggregate (exlcuding any information that may identify you specifically) covering Customer transaction data and Customer demographic and location data may be provided to partners of SweeDelight for the purpose of creating additional features on the website, creating appropriate merchandising or creating new products and services and conducting marketing research and statistical analysis of customer behaviour and transactions.\n" +
                "\n" +
                "SweeDelight will not use your financial information for any purpose other than to complete a transaction with you. SweeDelight does not rent, sell or share your personal information and will not disclose any of your personally identifiable information to third parties. In cases where it has your permission to provide products or services you've requested and such information is necessary to provide these products or services the information may be shared with SweeDelight’s business associates and partners. SweeDelight may, however, share consumer information on an aggregate with its partners or thrird parties where it deems necessary. In addition SweeDelight may use this information for promotional offers, to help investigate, prevent or take action regarding unlawful and illegal activities, suspected fraud, potential threat to the safety or security of any person, violations of the Site’s terms of use or to defend against legal claims; special circumstances such as compliance with subpoenas, court orders, requests/order from legal authorities or law enforcement agencies requiring such disclosure.\n" +
                "\n" +
                "To correct or update any information you have provided, the Site allows you to do it online. In the event of loss of access details you can send an e-mail to: info@sweedelight.com\n" +
                "\n" +
                "\n" +
                "Policy updates SweeDelight reserves the right to change or update this policy at any time. Such changes shall be effective immediately upon posting to the Site.";
        String shipping= "Shipping Policy";
        String shipping_content= "Once you are done selecting your products and have made a purchase on SweeDelight, we commit to deliver your order at the earliest possible time subject to availability. Your order will be delivered to you in anytime between 30 Min to 3 Hrs. However, the above conditions do not apply during adverse climatic and social conditions. We also might need additional time to fulfill bulk orders. You can get all your bulk orders processed with one days advance notice.";
        String refund= "Refund Policy";
        String refund_content="We have a \"no questions asked return and refund policy\" which entitles all our members to return the product at the time of delivery if due to some reason they are not satisfied with the quality or freshness of the product. We will take the returned product back with us and issue a credit note for the value of the return products which will be credited to your account on the Site. This can be used to pay your subsequent shopping bills.";
        listDataHeader.add(privacy);
        listDataHeader.add(shipping);
        listDataHeader.add(refund);

        // Adding child data
        List<String> privacy1 = new ArrayList<String>();
        privacy1.add(privacy_content);
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");

        List<String> refund1= new ArrayList<String>();
        refund1.add(refund_content);
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");

        List<String> shipping1 = new ArrayList<String>();
        shipping1.add(shipping_content);
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), privacy1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), refund1);
        listDataChild.put(listDataHeader.get(2), shipping1);
    }
}
