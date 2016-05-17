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
import com.sweedelight.ganesh.sweedelight.Activities.Snacks;
import com.sweedelight.ganesh.sweedelight.Activities.Sweet;
import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SnacksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SnacksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class SnacksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Sweet> snacksList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index ;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private SnacksAdapter adapter;

    public SnacksFragment(int index) {
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
    public SnacksFragment newInstance(int index) {
        SnacksFragment fragment = new SnacksFragment(index);
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

        View v = inflater.inflate(R.layout.fragment_snacks, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.snacks_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initializeData();
        //Toast.makeText(getActivity(), "Inside Recyler", Toast.LENGTH_SHORT).show();
        adapter = new SnacksAdapter(snacksList);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sweet snack = snacksList.get(position);
                Intent in = new Intent(getActivity(), IndividualItemActivity.class);
                in.putExtra("name",snack.getName());
                in.putExtra("thumb",snack.getThumb());
                in.putExtra("desc",snack.getDesc());
                in.putExtra("price",snack.getPrice());
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
        if (index == 0)
            snacksList = new ArrayList<>(Snacks.chaat);
        else if (index == 1)
            snacksList = new ArrayList<>(Snacks.snack_time);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class SnacksAdapter extends RecyclerView.Adapter<SnacksAdapter.SnackViewHolder> {


        @Override
        public SnackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_individual_item, parent, false);
            SnackViewHolder mSnackViewHolder = new SnackViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mSnackViewHolder;
        }

        @Override
        public void onBindViewHolder(SnackViewHolder holder, int position) {
            holder.snacksListName.setText(snacksList.get(position).name);
            Picasso.with(getActivity())
                    .load(snacksList.get(position).thumb)
                    .placeholder(R.drawable.s4) // optional
                    .error(R.drawable.s4)         // optional
                    .into(holder.snacksListPhoto);
            holder.price.setText(snacksList.get(position).price+"");

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return snacksList.size();
        }

        List<Sweet> snacksList;

        SnacksAdapter(List<Sweet> snacksList) {

            this.snacksList = snacksList;
        }

        public class SnackViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView snacksListName;
            ImageView snacksListPhoto;
            TextView price;

            SnackViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.card_view_category);
                snacksListName = (TextView) itemView.findViewById(R.id.textview_category_name);
                snacksListPhoto = (ImageView) itemView.findViewById(R.id.imageview_category);
                price=(TextView) itemView.findViewById(R.id.price);

            }
        }

    }
}

