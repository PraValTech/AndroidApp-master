package com.sweedelight.ganesh.sweedelight.Activities;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.List;

/**
 * Created by GANESH on 3/3/2016.
 */


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CategoryViewHolder>{
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(),Sweets.class));
        }
    };


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Hi, this is the change for commit in git
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
        CategoryViewHolder mCategoryViewHolder = new CategoryViewHolder(v);
        v.setOnClickListener(mOnClickListener);
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

    CustomAdapter(List<Category> categories){
        this.categories = categories;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
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


