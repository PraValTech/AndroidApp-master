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
import android.widget.Toast;

import com.sweedelight.ganesh.sweedelight.Activities.Categories;
import com.sweedelight.ganesh.sweedelight.Activities.IndividualItemActivity;
import com.sweedelight.ganesh.sweedelight.Activities.RecyclerTouchListener;
import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SweetsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SweetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class SweetsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Barfi> sweetsList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index ;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private SweetsAdapter adapter;

    public SweetsFragment(int index) {
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
    public SweetsFragment newInstance(int index) {
        SweetsFragment fragment = new SweetsFragment(index);
        Bundle args = new Bundle();
        this.index=index;
        Log.d("hi",""+index);
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

        View v = inflater.inflate(R.layout.fragment_sweets, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.sweets_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initializeData();
        Toast.makeText(getActivity(), "Inside Recyler", Toast.LENGTH_SHORT).show();
        adapter = new SweetsAdapter(sweetsList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Barfi barfi = sweetsList.get(position);
                Intent in = new Intent(getActivity(), IndividualItemActivity.class);
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
        sweetsList= new ArrayList<>();
        Log.d("hell",""+index);
        if(index == 0 ) {
            sweetsList.add(new Barfi("Kalakhand", R.drawable.s4));
            sweetsList.add(new Barfi("Bikaneri Square", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Barfi", R.drawable.s4));
            sweetsList.add(new Barfi("Rose Square", R.drawable.s4));
            sweetsList.add(new Barfi("Pinni", R.drawable.s4));
            sweetsList.add(new Barfi("Moti Bhog", R.drawable.s4));
        } else if(index==1){
            sweetsList.add(new Barfi("Rajbhog", R.drawable.s4));
            sweetsList.add(new Barfi("Rasgulla", R.drawable.s4));
            sweetsList.add(new Barfi("Jalebi", R.drawable.s4));
            sweetsList.add(new Barfi("Malai Sandwich", R.drawable.s4));
            sweetsList.add(new Barfi("Jahangiri", R.drawable.s4));
            sweetsList.add(new Barfi("Chandrakala", R.drawable.s4));
            sweetsList.add(new Barfi("Sweet bondi", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Malai Chamcham", R.drawable.s4));
            sweetsList.add(new Barfi("Matka Rasgulla", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Doodh Chamcham", R.drawable.s4));
            sweetsList.add(new Barfi("Chamcham", R.drawable.s4));
            sweetsList.add(new Barfi("Kheer Kadam", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Basundi", R.drawable.s4));
        } else if(index==2){
            sweetsList.add(new Barfi("Dryfruit Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Motichur Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Motichur Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Gond Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Boondi Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Coconut Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Pista Big Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Laddoo", R.drawable.s4));

        } else if(index==3){
            sweetsList.add(new Barfi("Chota Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Dharwad Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Choco Chips Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Mathura Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Mango Delight", R.drawable.s4));
            sweetsList.add(new Barfi("Honey Butterscotch", R.drawable.s4));
        } else if(index==4){
            sweetsList.add(new Barfi("Kaju Takus", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju SuryaMukhi", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Strawberry", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju TIlak", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Tokri", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Paan", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Katli", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Roll", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Sandwich", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Kalash", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Ball", R.drawable.s4));
        } else if(index==5){
            sweetsList.add(new Barfi("Kaju Choco Roll", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Biscuits", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Basket", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Ball", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Dil", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Gujiya", R.drawable.s4));
            sweetsList.add(new Barfi("DryFruits", R.drawable.s4));
            sweetsList.add(new Barfi("Kaju Sangam Barfi", R.drawable.s4));
        } else if(index==6){
            sweetsList.add(new Barfi("Malai Sandwich", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Doodh Chamcham", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Chena Payas", R.drawable.s4));
            sweetsList.add(new Barfi("Moti Paak", R.drawable.s4));
            sweetsList.add(new Barfi("Strawberry Petha", R.drawable.s4));
            sweetsList.add(new Barfi("Matka Rasgulla", R.drawable.s4));
            sweetsList.add(new Barfi("Malai Puri", R.drawable.s4));
            sweetsList.add(new Barfi("Kheer Kadam", R.drawable.s4));
        } else if(index==6){
            sweetsList.add(new Barfi("Malai Sandwich", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Doodh Chamcham", R.drawable.s4));
            sweetsList.add(new Barfi("Spl Chena Payas", R.drawable.s4));
            sweetsList.add(new Barfi("Moti Paak", R.drawable.s4));
            sweetsList.add(new Barfi("Strawberry Petha", R.drawable.s4));
            sweetsList.add(new Barfi("Matka Rasgulla", R.drawable.s4));
            sweetsList.add(new Barfi("Malai Puri", R.drawable.s4));
            sweetsList.add(new Barfi("Kheer Kadam", R.drawable.s4));
        }
        else if(index==7){
            sweetsList.add(new Barfi("Soan Papdi", R.drawable.s4));
            sweetsList.add(new Barfi("Motichur Laddoo", R.drawable.s4));
            sweetsList.add(new Barfi("Chocolate Soan Papdi", R.drawable.s4));
            sweetsList.add(new Barfi("Malai Ghevar ", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Ghevar", R.drawable.s4));
            sweetsList.add(new Barfi("Gujiya", R.drawable.s4));
            sweetsList.add(new Barfi("Moong Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Bajusahi", R.drawable.s4));
        }
        else if(index==8){
            sweetsList.add(new Barfi("Badam Gini", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Fancy Roll", R.drawable.s4));
            sweetsList.add(new Barfi("Milk Cake", R.drawable.s4));
            sweetsList.add(new Barfi("White Kalakand", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Chota Peda", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Roll", R.drawable.s4));
            sweetsList.add(new Barfi("Kesar Peda", R.drawable.s4));
        }
        else if(index==9){
            sweetsList.add(new Barfi("Moong Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Halwa",R.drawable.s4));
            sweetsList.add(new Barfi("Soan Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Bombay Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Dryfruit Halwa", R.drawable.s4));

        }else {
            sweetsList.add(new Barfi("Moong Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Badam Halwa",R.drawable.s4));
            sweetsList.add(new Barfi("Soan Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Bombay Halwa", R.drawable.s4));
            sweetsList.add(new Barfi("Dryfruit Halwa", R.drawable.s4));


        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class SweetsAdapter extends RecyclerView.Adapter<SweetsAdapter.BarfiViewHolder> {


        @Override
        public BarfiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
            BarfiViewHolder mBarfiViewHolder = new BarfiViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mBarfiViewHolder;
        }

        @Override
        public void onBindViewHolder(BarfiViewHolder holder, int position) {
            holder.sweetsListName.setText(sweetsList.get(position).name);
            holder.sweetsListPhoto.setImageResource(sweetsList.get(position).photoId);

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return sweetsList.size();
        }

        List<Barfi> sweetsList;

        SweetsAdapter(List<Barfi> sweetsList) {

            this.sweetsList = sweetsList;
        }

        public class BarfiViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView sweetsListName;
            ImageView sweetsListPhoto;

            BarfiViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.card_view_category);
                sweetsListName = (TextView) itemView.findViewById(R.id.textview_category_name);
                sweetsListPhoto = (ImageView) itemView.findViewById(R.id.imageview_category);
            }
        }

    }
}

class Barfi {
    String name;
    int photoId;

    Barfi(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}

