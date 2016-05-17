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

public class Dryfruits extends AppCompatActivity implements AsyncResponse2 {
    private List<Sweet> dryfruits;
    HTTPTask2 api_call;
    HashMap<String, String> params;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryfruits);
       mRecyclerView = (RecyclerView)findViewById(R.id.dryfruits_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        params = new HashMap<>();
        params.put("rt", "a/product/filter");
        params.put("category_id", "83");


        api_call = new HTTPTask2("GET", params, this, this);
        api_call.execute();

//        initializeData();
//        DryfruitsAdapter adapter= new DryfruitsAdapter(dryfruits);
//        mRecyclerView.setAdapter(adapter);
       mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet barfi = dryfruits.get(position);
                Intent in = new Intent(getApplicationContext(), IndividualItemActivity.class);
                in.putExtra("name", barfi.getName());
                in.putExtra("thumb", barfi.getThumb());
                in.putExtra("desc", barfi.getDesc());
                in.putExtra("price", barfi.getPrice());
                startActivity(in);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet  dryfruit = dryfruits.get(position);
                Intent in = new Intent(Dryfruits.this, IndividualItemActivity.class);
                in.putExtra("name",dryfruit.getName());
                in.putExtra("thumb",dryfruit.getThumb());
                in.putExtra("desc",dryfruit.getDesc());
                in.putExtra("price",dryfruit.getPrice());
                startActivity(in);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        final CardView cardView = (CardView) findViewById(R.id.card_view_category);
//              cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//
//              public void onClick(View v) {
//                v.getContext().startActivity(new Intent(v.getContext(), Sweets.class));
//          }
//        });
//
//
//         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void processFinish(String cat_id, String output) {

        JSONObject result = null;
        try {
            result = new JSONObject(output);
            JSONArray items = result.getJSONArray("rows");
            JSONObject curr_item;
            JSONObject curr_sweet;
            dryfruits= new ArrayList<>();

            if (cat_id == "83") {
                Log.v("dryfruits", "dryfruits");

                for (int i = 0; i < items.length(); i++) {
                    curr_item = items.getJSONObject(i);
                    curr_sweet = curr_item.getJSONObject("cell");
                    Log.v("in for loop", curr_sweet.toString());
                    dryfruits.add(new Sweet(curr_sweet.getString("name"),
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
            DryfruitsAdapter adapter= new DryfruitsAdapter(dryfruits);
            mRecyclerView.setAdapter(adapter);
        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class DryfruitsAdapter extends RecyclerView.Adapter<DryfruitsAdapter.DryfruitViewHolder>{
        /*private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.getContext().startActivity(new Intent(v.getContext(),Sweets.class));

                v.getContext().startActivity(new Intent(v.getContext(),Savouries.class));

            }
        };*/


        @Override
        public DryfruitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_individual_item, parent, false);
            DryfruitViewHolder mDryfruitViewHolder = new DryfruitViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mDryfruitViewHolder;
        }

        @Override
        public void onBindViewHolder(DryfruitViewHolder holder, int position) {
            holder.dryfruitsName.setText(dryfruits.get(position).name);
            Picasso.with(getApplicationContext())
                            .load(dryfruits.get(position).thumb)
                            .placeholder(R.drawable.s4) // optional
                            .error(R.drawable.s4)         // optional
                            .into(holder.dryfruitsPhoto);
            holder.price.setText(dryfruits.get(position).price+"");

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return dryfruits.size();
        }
        List<Sweet> dryfruits;

        DryfruitsAdapter(List<Sweet> dryfruits){
            this.dryfruits = dryfruits;
        }

        public class DryfruitViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView dryfruitsName;
            ImageView dryfruitsPhoto;
            TextView price;

            DryfruitViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_category);
                dryfruitsName = (TextView)itemView.findViewById(R.id.textview_category_name);
                dryfruitsPhoto = (ImageView)itemView.findViewById(R.id.imageview_category);
                price= (TextView)itemView.findViewById(R.id.price);
            }
        }

    }



}

