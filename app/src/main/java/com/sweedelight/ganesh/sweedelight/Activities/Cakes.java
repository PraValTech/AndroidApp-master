package com.sweedelight.ganesh.sweedelight.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

public class Cakes extends AppCompatActivity {
    private List<Cake> cakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.cakes_recycler_view);
       mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initializeData();
        CakesAdapter adapter= new CakesAdapter(cakes);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cake cake = cakes.get(position);
                if(position==0) {
                    Intent in = new Intent(Cakes.this, IndividualItemActivity.class);
                    startActivity(in);
                }else if(position==1){
                    Intent in = new Intent(Cakes.this, IndividualItemActivity.class);
                    startActivity(in);

                }
                Toast.makeText(getApplicationContext(), cake.name + " is selected!", Toast.LENGTH_SHORT).show();
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
    private void initializeData() {
        cakes = new ArrayList<>();
        cakes.add(new Cake("Honeycomb Pastry Cake", R.drawable.metro));
        cakes.add(new Cake("Blueberry Cream Cake", R.drawable.metro));
        cakes.add(new Cake("Mango cream cake ", R.drawable.metro));
        cakes.add(new Cake("Litchi Cream Cake", R.drawable.metro));
        cakes.add(new Cake("Honey Almond Cream Cake ", R.drawable.metro));
        cakes.add(new Cake("Green Apple Cream Cake", R.drawable.metro));
        cakes.add(new Cake("Honeycomb Pastry Cake", R.drawable.metro));
        cakes.add(new Cake("Blueberry Cream Cake", R.drawable.metro));
        cakes.add(new Cake("Mango cream cake ", R.drawable.metro));
        cakes.add(new Cake("Litchi Cream Cake", R.drawable.metro));
        cakes.add(new Cake("Honey Almond Cream Cake ", R.drawable.metro));
        cakes.add(new Cake("Green Apple Cream Cake", R.drawable.metro));
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
            CakeViewHolder mCakeViewHolder = new CakeViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mCakeViewHolder;
        }

        @Override
        public void onBindViewHolder(CakeViewHolder holder, int position) {
            holder.cakesName.setText(cakes.get(position).name);
            holder.cakesPhoto.setImageResource(cakes.get(position).photoId);

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return cakes.size();
        }
        List<Cake> cakes;

        CakesAdapter(List<Cake> cakes){
            this.cakes = cakes;
        }

        public class CakeViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView cakesName;
            ImageView cakesPhoto;

            CakeViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_category);
                cakesName = (TextView)itemView.findViewById(R.id.textview_category_name);
                cakesPhoto = (ImageView)itemView.findViewById(R.id.imageview_category);
            }
        }

    }



}

class Cake {
    String name;
    int photoId;

    Cake(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}
