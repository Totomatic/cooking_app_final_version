package com.example.gpcorser.bitsandpizzas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FirstPage extends Fragment {

    public FirstPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_first_page, container, false);

        ImageView imageView = (ImageView) myView.findViewById(R.id.first_page_picture);
        imageView.setImageResource(R.drawable.beargrill);

        return myView;
    }

}
