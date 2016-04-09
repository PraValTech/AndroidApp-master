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

public class Categories extends AppCompatActivity {
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.categories_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initializeData();
        CategoriesAdapter adapter= new CategoriesAdapter(categories);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Category category = categories.get(position);
                if(position==0) {
                    Intent in = new Intent(Categories.this, Sweets.class);
                    startActivity(in);
                }else if(position==1){
                    Intent in = new Intent(Categories.this, Savouries.class);
                    startActivity(in);

                }
                else if(position==2){
                    Intent in = new Intent(Categories.this, Cakes.class);
                    startActivity(in);

                }
                else if(position==3){
                    Intent in = new Intent(Categories.this, Snacks.class);
                    startActivity(in);
                }
                else if(position==4){
                    Intent in = new Intent(Categories.this, Finedines.class);
                    startActivity(in);

                }
                else if(position==5){
                    Intent in = new Intent(Categories.this, Dryfruits.class);
                    startActivity(in);

                }
                Toast.makeText(getApplicationContext(), category.name + " is selected!", Toast.LENGTH_SHORT).show();
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void initializeData() {
        categories = new ArrayList<>();
        categories.add(new Category("Sweets", R.drawable.metro));
        categories.add(new Category("Savouries", R.drawable.metro));
        categories.add(new Category("Cakes", R.drawable.metro));
        categories.add(new Category("Snacks", R.drawable.metro));
        categories.add(new Category("Finedine", R.drawable.metro));
        categories.add(new Category("Dryfruits", R.drawable.metro));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>{
        /*private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.getContext().startActivity(new Intent(v.getContext(),Sweets.class));

                v.getContext().startActivity(new Intent(v.getContext(),Savouries.class));

            }
        };*/


        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
            CategoryViewHolder mCategoryViewHolder = new CategoryViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mCategoryViewHolder;
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            holder.categoriesName.setText(categories.get(position).name);
            holder.categoriesPhoto.setImageResource(categories.get(position).photoId);

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
        List<Category> categories;

        CategoriesAdapter(List<Category> categories){
            this.categories = categories;
        }

        public class CategoryViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView categoriesName;
            ImageView categoriesPhoto;

            CategoryViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_category);
                categoriesName = (TextView)itemView.findViewById(R.id.textview_category_name);
                categoriesPhoto = (ImageView)itemView.findViewById(R.id.imageview_category);
            }
        }

    }



}

class Category {
    String name;
    int photoId;

    Category(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}


