package com.sweedelight.ganesh.sweedelight.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.sweedelight.ganesh.sweedelight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FinedinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FinedinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class FinedinesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Finedine> finedineList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index ;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private FinedinesAdapter adapter;

    public FinedinesFragment(int index) {
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
    public FinedinesFragment newInstance(int index) {
        FinedinesFragment fragment = new FinedinesFragment(index);
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

        View v = inflater.inflate(R.layout.fragment_finedines, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.finedines_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        initializeData();
        Toast.makeText(getActivity(), "Inside Recyler", Toast.LENGTH_SHORT).show();
        adapter = new FinedinesAdapter(finedineList);
        mRecyclerView.setAdapter(adapter);
       /* mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Barfi barfi = sweetsList.get(position);
                Intent in = new Intent(getActivity(), Categories.class);
                startActivity(in);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/
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
        finedineList= new ArrayList<>();
        Log.d("hell", "" + index);
        if(index == 0 ) {
            finedineList.add(new Finedine("Choliya Shikampur", R.drawable.s4));
            finedineList.add(new Finedine("Cherry Cooler", R.drawable.s4));
            finedineList.add(new Finedine("Cheese Kurkuri", R.drawable.s4));
            finedineList.add(new Finedine("Chatpate Sakarkand ke kebab", R.drawable.s4));
            finedineList.add(new Finedine("Choliya Shikampur", R.drawable.s4));
            finedineList.add(new Finedine("Cherry Cooler", R.drawable.s4));
            finedineList.add(new Finedine("Cheese Kurkuri", R.drawable.s4));
            finedineList.add(new Finedine("Chatpate Sakarkand ke kebab", R.drawable.s4));

        } else if(index==1) {
            finedineList.add(new Finedine("Veg Fried Rice", R.drawable.s4));
            finedineList.add(new Finedine("Veg Sichuan Fried Rice", R.drawable.s4));
            finedineList.add(new Finedine("Schezwan Triple Fried Rice ", R.drawable.s4));
            finedineList.add(new Finedine("Veg Fried Rice", R.drawable.s4));
            finedineList.add(new Finedine("Veg Sichuan Fried Rice", R.drawable.s4));
            finedineList.add(new Finedine("Schezwan Triple Fried Rice ", R.drawable.s4));
        } else if(index==2) {
            finedineList.add(new Finedine("Stuffed Paratha", R.drawable.s4));
            finedineList.add(new Finedine("Puri Bhaji", R.drawable.s4));
            finedineList.add(new Finedine("Phulka ", R.drawable.s4));
            finedineList.add(new Finedine("Methi Paratha", R.drawable.s4));
            finedineList.add(new Finedine("Wheat Paratha", R.drawable.s4));
            finedineList.add(new Finedine("Chilly paratha ", R.drawable.s4));

        } else if(index==3) {
            finedineList.add(new Finedine("Curd Rice", R.drawable.s4));
            finedineList.add(new Finedine("Mixed Veg Curry", R.drawable.s4));
            finedineList.add(new Finedine("Green Salad ", R.drawable.s4));
            finedineList.add(new Finedine("Curd Rice", R.drawable.s4));
            finedineList.add(new Finedine("Mixed Veg Curry", R.drawable.s4));
            finedineList.add(new Finedine("Green Salad ", R.drawable.s4));

        } else if(index==4) {
            finedineList.add(new Finedine("Paneer Tikka", R.drawable.s4));
            finedineList.add(new Finedine("Paneer Haryali", R.drawable.s4));
            finedineList.add(new Finedine("Paneer Sizzler ", R.drawable.s4));
            finedineList.add(new Finedine("Paneer Tikka", R.drawable.s4));
            finedineList.add(new Finedine("Paneer Haryali", R.drawable.s4));
            finedineList.add(new Finedine("Paneer Sizzler ", R.drawable.s4));

        } else if(index==5) {
            finedineList.add(new Finedine("Idli Vada", R.drawable.s4));
            finedineList.add(new Finedine("Vada Sambhar", R.drawable.s4));
            finedineList.add(new Finedine("Dosa ", R.drawable.s4));
            finedineList.add(new Finedine("Idli Vada", R.drawable.s4));
            finedineList.add(new Finedine("Vada Sambhar", R.drawable.s4));
            finedineList.add(new Finedine("Dosa ", R.drawable.s4));

        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    class FinedinesAdapter extends RecyclerView.Adapter<FinedinesAdapter.FinedineViewHolder> {


        @Override
        public FinedineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_category, parent, false);
            FinedineViewHolder mFinedineViewHolder = new FinedineViewHolder(v);
            //v.setOnClickListener(mOnClickListener);
            return mFinedineViewHolder;
        }

        @Override
        public void onBindViewHolder(FinedineViewHolder holder, int position) {
            holder.finedineListName.setText(finedineList.get(position).name);
            holder.finedineListPhoto.setImageResource(finedineList.get(position).photoId);

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return finedineList.size();
        }

        List<Finedine> finedineList;

        FinedinesAdapter(List<Finedine> finedineList) {

            this.finedineList = finedineList;
        }

        public class FinedineViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView finedineListName;
            ImageView finedineListPhoto;

            FinedineViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.card_view_category);
                finedineListName = (TextView) itemView.findViewById(R.id.textview_category_name);
                finedineListPhoto = (ImageView) itemView.findViewById(R.id.imageview_category);
            }
        }

    }
}

class Finedine {
    String name;
    int photoId;

    Finedine(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}

