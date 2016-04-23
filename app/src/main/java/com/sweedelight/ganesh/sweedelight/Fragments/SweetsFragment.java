package com.sweedelight.ganesh.sweedelight.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.squareup.picasso.Picasso;
import com.sweedelight.ganesh.sweedelight.Activities.AsyncResponse;
import com.sweedelight.ganesh.sweedelight.Activities.Categories;
import com.sweedelight.ganesh.sweedelight.Activities.HTTPTask;
import com.sweedelight.ganesh.sweedelight.Activities.IndividualItemActivity;
import com.sweedelight.ganesh.sweedelight.Activities.RecyclerTouchListener;
import com.sweedelight.ganesh.sweedelight.Activities.Sweets;
import com.sweedelight.ganesh.sweedelight.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
public class SweetsFragment extends Fragment implements AsyncResponse {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static List<Barfi> sweetsList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index ;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private SweetsAdapter adapter;
    HTTPTask api_call;
    HashMap<String, String> params;
    int page=0;
    int current_page=0;
    String result;

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
        args.putInt("index", index);


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
        params = new HashMap<>();
        params.put("rt", "a/product/filter");
        params.put("category_id", "74");
        params.put("page", "2");


        api_call = new HTTPTask("GET", params, this, getContext());
        api_call.execute();

//        params.put("page","2");
//        api_call = new HTTPTask("GET", params, this, getContext());
//        api_call.execute();



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

      // result= Sweets.getResult();
       // initializeData(result);
        //Toast.makeText(getActivity(), "Inside Recyler", Toast.LENGTH_SHORT).show();
//        adapter = new SweetsAdapter(sweetsList);
//        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Barfi barfi = sweetsList.get(position);
                Intent in = new Intent(getActivity(), IndividualItemActivity.class);
                in.putExtra("name",barfi.getName());
                in.putExtra("thumb",barfi.getThumb());
                in.putExtra("desc",barfi.getDesc());
                in.putExtra("price",barfi.getPrice());
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

    @Override
    public void processFinish(String output) {
        JSONObject result = null;
        boolean isFirst=false;
            try {
            result = new JSONObject(output);
            page= Integer.parseInt(result.getString("total"));
            current_page= result.getInt("page");
            JSONArray items= result.getJSONArray("rows");
            JSONObject curr_item;
            JSONObject curr_sweet;

                if(current_page==1)
                {
                    isFirst=true;
                }
            int l= items.length();
            Log.v("length", l+"");
            for (int i=0; i<l; i++) {
                curr_item = items.getJSONObject(i);
                curr_sweet = curr_item.getJSONObject("cell");
                Log.v("in for loop", curr_sweet.toString());
                sweetsList.add(new Barfi(curr_sweet.getString("name"),
                        curr_sweet.getString("thumb"),
                        curr_sweet.getString("description"),
                        curr_sweet.getInt("price")
                ));
            }

                for(int k=0; k<sweetsList.size(); k++)
                {
                    Log.v("item",sweetsList.get(k)+"");
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {

//            if(page>current_page)
//            {
//                params.put("page", (current_page+1)+"");
//                api_call = new HTTPTask("GET",params,this,getContext());
//                api_call.execute();
//
//            }
//            else {
         //       if(isFirst)
                adapter = new SweetsAdapter(sweetsList);
           //     else
             //   adapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(adapter);
            //}
        }
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
           Picasso.with(getActivity())
                    .load(sweetsList.get(position).thumb)
                    .placeholder(R.drawable.s4) // optional
                    .error(R.drawable.s4)         // optional
                    .into(holder.sweetsListPhoto);

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
    String thumb;
    String desc;
    float price;

    Barfi(String name, String thumb, String desc, float price) {
        this.name = name;
        this.thumb = thumb;
        this.desc = desc;
        this.price= price;
    }

    public String getName()
    {
        return name;
    }
    public String getThumb(){
        return thumb;
    }
    public String getDesc(){return desc;}
    public String getPrice(){return price+"" ;}
}

