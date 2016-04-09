package com.sweedelight.ganesh.sweedelight.Activities;


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

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

public class Dryfruits extends AppCompatActivity {
    private List<Dryfruit> dryfruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryfruits);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.dryfruits_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initializeData();
        DryfruitsAdapter adapter= new DryfruitsAdapter(dryfruits);
        mRecyclerView.setAdapter(adapter);
       /* mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Dryfruit dryfruit = dryfruits.get(position);
                if(position==0) {
                    Intent in = new Intent(Dryfruits.this, IndividualItemActivity.class);
                    startActivity(in);
                }else if(position==1){
                    Intent in = new Intent(Dryfruits.this, IndividualItemActivity.class);
                    startActivity(in);

                }
                Toast.makeText(getApplicationContext(), cake.name + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
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
        dryfruits = new ArrayList<>();
        dryfruits.add(new Dryfruit("Honeycomb Pastry Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Blueberry Cream Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Mango cream cake ", R.drawable.metro));
        dryfruits.add(new Dryfruit("Litchi Cream Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Honey Almond Cream Cake ", R.drawable.metro));
        dryfruits.add(new Dryfruit("Green Apple Cream Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Honeycomb Pastry Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Blueberry Cream Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Mango cream cake ", R.drawable.metro));
        dryfruits.add(new Dryfruit("Litchi Cream Cake", R.drawable.metro));
        dryfruits.add(new Dryfruit("Honey Almond Cream Cake ", R.drawable.metro));
        dryfruits.add(new Dryfruit("Green Apple Cream Cake", R.drawable.metro));
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
            DryfruitViewHolder mDryfruitViewHolder = new DryfruitViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mDryfruitViewHolder;
        }

        @Override
        public void onBindViewHolder(DryfruitViewHolder holder, int position) {
            holder.dryfruitsName.setText(dryfruits.get(position).name);
            holder.dryfruitsPhoto.setImageResource(dryfruits.get(position).photoId);

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return dryfruits.size();
        }
        List<Dryfruit> dryfruits;

        DryfruitsAdapter(List<Dryfruit> dryfruits){
            this.dryfruits = dryfruits;
        }

        public class DryfruitViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView dryfruitsName;
            ImageView dryfruitsPhoto;

            DryfruitViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_category);
                dryfruitsName = (TextView)itemView.findViewById(R.id.textview_category_name);
                dryfruitsPhoto = (ImageView)itemView.findViewById(R.id.imageview_category);
            }
        }

    }



}

class Dryfruit {
    String name;
    int photoId;

    Dryfruit(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}
