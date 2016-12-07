package com.example.gpcorser.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ItemMaterialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView converterRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza_material, container, false);

        String[] converterNames = new String[Item.converterType.length];
        for (int i = 0; i < converterNames.length; i++) {
            converterNames[i] = Item.converterType[i].getName();
        }

        int[] converterImages = new int[Item.converterType.length];
        for (int i = 0; i < converterImages.length; i++) {
            converterImages[i] = Item.converterType[i].getImageResourceId();
        }

        CaptionedImageAdapter adapter = new CaptionedImageAdapter(converterNames, converterImages);
        converterRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getActivity());
        converterRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
                intent.putExtra(ItemDetailActivity.COONVERSION_TYPE, position);
                getActivity().startActivity(intent);
            }

        });


        return converterRecycler;
    }


}
