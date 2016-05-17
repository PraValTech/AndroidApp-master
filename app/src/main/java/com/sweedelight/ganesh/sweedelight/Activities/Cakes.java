package com.sweedelight.ganesh.sweedelight.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cakes extends AppCompatActivity implements AsyncResponse2{
    private List<Sweet> cakes;
    HTTPTask2 api_call;
    HashMap<String, String> params;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
         mRecyclerView = (RecyclerView)findViewById(R.id.cakes_recycler_view);
       mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
       // initializeData();

        params = new HashMap<>();
        params.put("rt", "a/product/filter");
        params.put("category_id", "128");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet cake = cakes.get(position);

                    Intent in = new Intent(Cakes.this, IndividualItemActivity.class);
                    in.putExtra("name",cake.getName());
                    in.putExtra("thumb",cake.getThumb());
                    in.putExtra("desc",cake.getDesc());
                    in.putExtra("price",cake.getPrice());
                    startActivity(in);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
//        final CardView cardView = (CardView) findViewById(R.id.card_view_category);
        //      cardView.setOnClickListener(new View.OnClickListener() {
        //        @Override

        //      public void onClick(View v) {
        //        v.getContext().startActivity(new Intent(v.getContext(), Sweets.class));
        //  }
        //});


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
     @Override
    public void processFinish(String cat_id, String output) {
        JSONObject result = null;
        try {
            result = new JSONObject(output);
            JSONArray items = result.getJSONArray("rows");
            JSONObject curr_item;
            JSONObject curr_sweet;
            cakes= new ArrayList<>();

            if (cat_id == "128") {
                Log.v("cakes", "cakes");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    cakes.add(new Sweet(curr_sweet.getString("name"),
                            curr_sweet.getString("thumb"),
                            curr_sweet.getString("description"),
                            curr_sweet.getInt("price")
                    ));
                }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        finally {
            CakesAdapter adapter= new CakesAdapter(cakes);
            mRecyclerView.setAdapter(adapter);

        }


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class CakesAdapter extends RecyclerView.Adapter<CakesAdapter.CakeViewHolder>{
        /*private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.getContext().startActivity(new Intent(v.getContext(),Sweets.class));

                v.getContext().startActivity(new Intent(v.getContext(),Savouries.class));

            }
        };*/


        @Override
        public CakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_individual_item, parent, false);
            CakeViewHolder mCakeViewHolder = new CakeViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mCakeViewHolder;
        }

        @Override
        public void onBindViewHolder(CakeViewHolder holder, int position) {
            holder.cakesName.setText(cakes.get(position).name);
            Picasso.with(getApplicationContext())
                    .load(cakes.get(position).thumb)
                    .placeholder(R.drawable.s4) // optional
                    .error(R.drawable.s4)         // optional
                    .into(holder.cakesPhoto);
            holder.price.setText(cakes.get(position).price+"");

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return cakes.size();
        }
        List<Sweet> cakes;

        CakesAdapter(List<Sweet> cakes){
            this.cakes = cakes;
        }

        public class CakeViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView cakesName;
            ImageView cakesPhoto;
            TextView price;


            CakeViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_category);
                cakesName = (TextView)itemView.findViewById(R.id.textview_category_name);
                cakesPhoto = (ImageView)itemView.findViewById(R.id.imageview_category);
                price= (TextView)itemView.findViewById(R.id.price);
            }
        }

    }



}


