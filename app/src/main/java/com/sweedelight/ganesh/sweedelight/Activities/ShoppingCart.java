package com.sweedelight.ganesh.sweedelight.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {
    private List<Cart> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.shopping_cart_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager= new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initializeData();
        CartAdapter adapter= new CartAdapter(items);
        mRecyclerView.setAdapter(adapter);

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
        items = new ArrayList<>();
        items.add(new Cart("Kaju Katli", "IBJC", "₹500","₹2500"));
        items.add(new Cart("Jalebi", "IBJC", "₹500","₹2500"));
        items.add(new Cart("Peda", "IBJC", "₹500","₹2500"));


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
        /*private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.getContext().startActivity(new Intent(v.getContext(),Sweets.class));

                v.getContext().startActivity(new Intent(v.getContext(),Savouries.class));

            }
        };*/


        @Override
        public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_shopping_cart, parent, false);
            CartViewHolder mCartViewHolder = new CartViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mCartViewHolder;
        }

        @Override
        public void onBindViewHolder(CartViewHolder holder, int position) {
            holder.itemsName.setText(items.get(position).name);
            holder.itemsProductCode.setText(items.get(position).product_code);
            holder.itemsUnitPrice.setText(items.get(position).unit_price);
            //holder.itemsQuantity.toString(items.get(position).quantity);
            holder.itemsTotal.setText(items.get(position).total);

        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
        List<Cart> items;

        CartAdapter(List<Cart> items){
            this.items = items;
        }

        public class CartViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView itemsName;
            TextView itemsProductCode;
            TextView itemsUnitPrice;
            me.himanshusoni.quantityview.QuantityView quantityView;
            TextView itemsTotal;
            Integer itemsQuantity;

            CartViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view_shopping_cart);
                itemsName = (TextView)itemView.findViewById(R.id.shopping_cart_name);
                itemsProductCode=(TextView)itemView.findViewById(R.id.shopping_cart_product_code);
                itemsUnitPrice=(TextView)itemView.findViewById(R.id.shopping_cart_unit_price);
                //  quantityView=(me.himanshusoni.quantityview.QuantityView)itemView.findViewById(R.id.shopping_cart_quantity);
                // itemsQuantity=Integer.parseInt(quantityView.toString());
                itemsTotal=(TextView)itemView.findViewById(R.id.shopping_cart_total);

            }
        }

    }



}

class Cart {
    String name;
    String product_code;
    String unit_price;
    //Integer quantity;
    String total;
    Cart(String name, String product_code,String unit_price,String total) {
        this.name = name;
        this.product_code = product_code;
        this.unit_price = unit_price;
        //this.quantity=quantity;
        this.total=total;
    }
}


