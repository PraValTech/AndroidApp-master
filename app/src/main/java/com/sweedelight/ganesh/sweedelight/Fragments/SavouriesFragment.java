package com.sweedelight.ganesh.sweedelight.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.sweedelight.ganesh.sweedelight.Activities.IndividualItemActivity;
import com.sweedelight.ganesh.sweedelight.Activities.RecyclerTouchListener;
import com.sweedelight.ganesh.sweedelight.Activities.Savouries;
import com.sweedelight.ganesh.sweedelight.Activities.Sweet;
import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SavouriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SavouriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class SavouriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Sweet> savouriesList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index ;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private SavouriesAdapter adapter;

    public SavouriesFragment(int index) {
        // Required empty public constructor
        this.index=index;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment SweetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public SavouriesFragment newInstance(int index) {
        SavouriesFragment fragment = new SavouriesFragment(index);
        Bundle args = new Bundle();
        this.index=index;
        Log.d("hi", "" + index);
        args.putInt("index",index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // / Inflate the layout for this fragment\

        View v = inflater.inflate(R.layout.fragment_savouries, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.savouries_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initializeData();

        adapter = new SavouriesAdapter(savouriesList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet barfi = savouriesList.get(position);
                Intent in = new Intent(getActivity(), IndividualItemActivity.class);
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
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet savoury = savouriesList.get(position);
                Intent in = new Intent(getActivity(), IndividualItemActivity.class);
                in.putExtra("name",savoury.getName());
                in.putExtra("thumb",savoury.getThumb());
                in.putExtra("desc",savoury.getDesc());
                in.putExtra("price",savoury.getPrice());
                startActivity(in);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) throws RuntimeException{
        super.onAttach(context);
     /*   if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void initializeData() {

        if(index == 0 ) {
           savouriesList= new ArrayList<>(Savouries.mixture);


        } else if(index==1) {
            savouriesList= new ArrayList<>(Savouries.bhujia);

        } else if(index==2){
            savouriesList= new ArrayList<>(Savouries.chips);

        }else if(index==3){
            savouriesList= new ArrayList<>(Savouries.namkeen);
        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class SavouriesAdapter extends RecyclerView.Adapter<SavouriesAdapter.SavouryViewHolder> {


        @Override
        public SavouryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_individual_item, parent, false);
            SavouryViewHolder mSavouryViewHolder = new SavouryViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mSavouryViewHolder;
        }

        @Override
        public void onBindViewHolder(SavouryViewHolder holder, int position) {
            holder.savouriesListName.setText(savouriesList.get(position).name);
            Picasso.with(getActivity())
                    .load(savouriesList.get(position).thumb)
                    .placeholder(R.drawable.s4) // optional
                    .error(R.drawable.s4)         // optional
                    .into(holder.savouriesListPhoto);
            holder.price.setText(savouriesList.get(position).price+"");

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return savouriesList.size();
        }

        List<Sweet> savouriesList;

        SavouriesAdapter(List<Sweet> savouriesList) {

            this.savouriesList = savouriesList;
        }

        public class SavouryViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView savouriesListName;
            ImageView savouriesListPhoto;
            TextView price;

            SavouryViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.card_view_category);
                savouriesListName = (TextView) itemView.findViewById(R.id.textview_category_name);
                savouriesListPhoto = (ImageView) itemView.findViewById(R.id.imageview_category);
                price= (TextView)itemView.findViewById(R.id.price);
            }
        }

    }
}


