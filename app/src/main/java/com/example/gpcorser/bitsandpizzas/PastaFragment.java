package com.example.gpcorser.bitsandpizzas;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastaFragment extends Fragment implements View.OnClickListener {

    private EditText minuteTimer;

    public PastaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_pasta,container,false);

        minuteTimer = (EditText) myView.findViewById(R.id.editText_recipe);
        Button searchButton = (Button) myView.findViewById(R.id.button_search_recipe);
        searchButton.setOnClickListener(this);

        return myView;
    }

    public void onClick(View myView)
    {
        switch (myView.getId()){
            case R.id.button_start_timer :
                onStartTimer();
                break;
            case R.id.button_stop_timer :
                onStopTimer();
                break;
        }

    }

    public void onStartTimer(){

        CountDownTimer myCountdownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                minuteTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish(){

            }
        };
    }

    public void onStopTimer(){
        myCount;

    }

}
