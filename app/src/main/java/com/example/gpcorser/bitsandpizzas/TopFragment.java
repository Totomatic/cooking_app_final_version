package com.example.gpcorser.bitsandpizzas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RelativeLayout layout =(RelativeLayout)inflater.inflate(R.layout.fragment_top,  container, false);
        RecyclerView pizzaRecycler = (RecyclerView)layout.findViewById(R.id.pizza_recycler);
        String[] pizzaNames = new String[3];
        for (int i = 0; i < 3; i++) {
            pizzaNames[i] = Item.converterType[i].getName();
        }
        int[] pizzaImages = new int[3];
        for (int i = 0; i < 3; i++) {
            pizzaImages[i] = Item.converterType[i].getImageResourceId();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);
        CaptionedImageAdapter adapter = new CaptionedImageAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        adapter.setListener(new CaptionedImageAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
                intent.putExtra(ItemDetailActivity.COONVERSION_TYPE, position);
                getActivity().startActivity(intent);
            }
        });
        return layout;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_top, container, false);
    }

}
