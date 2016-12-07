package com.example.gpcorser.bitsandpizzas;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastaFragment extends Fragment implements View.OnClickListener{

        private Button buttonStartTime, buttonStopTime;
        private EditText edtTimerValue;
        private TextView textViewShowTime; // will show the time
        private CountDownTimer countDownTimer; // built in android class
        // CountDownTimer
        private long totalTimeCountInMilliseconds=0; // total count down time in milliseconds


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_pasta, container, false);

        buttonStartTime = (Button) myView.findViewById(R.id.btnStartTime);
        buttonStopTime = (Button)  myView.findViewById(R.id.btnStopTime);
        textViewShowTime = (TextView)  myView.findViewById(R.id.tvTimeCount);
        edtTimerValue = (EditText)  myView.findViewById(R.id.edtTimerValue);

        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);

        return myView;
    }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnStartTime) {
                setTimer();
                buttonStopTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.GONE);
                edtTimerValue.setVisibility(View.GONE);
                edtTimerValue.setText("");
                startTimer();

            } else if (v.getId() == R.id.btnStopTime) {
                countDownTimer.cancel();
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.GONE);
                edtTimerValue.setVisibility(View.VISIBLE);
                textViewShowTime.setText("");
            }
        }

        private void setTimer() {
            int time = Integer.parseInt(edtTimerValue.getText().toString());

            totalTimeCountInMilliseconds = 60 * time * 1000;

        }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;


                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format

            }

            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished
                textViewShowTime.setText("Cooked");
                textViewShowTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.GONE);
                edtTimerValue.setVisibility(View.VISIBLE);

                Intent myIntent = new Intent(getActivity(),CookNotificationService.class);
                myIntent.putExtra(CookNotificationService.EXTRA_MESSAGE,"cooked");
                getActivity().startService(myIntent);

            }

        }.start();

    }
}




