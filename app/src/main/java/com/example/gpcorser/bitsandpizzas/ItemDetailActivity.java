package com.example.gpcorser.bitsandpizzas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Spinner;

public class ItemDetailActivity extends Activity {

    private ShareActionProvider shareActionProvider;
    public static final String COONVERSION_TYPE = "convertTypeNo";
    private int convertTypeNo = 0;

/*     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);



       // display number of button from 0 to 2

        TextView textView = (TextView) findViewById(R.id.item_text);
        textView.setText(String.valueOf(pizzaNo));


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // enable UP button
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //get the type number for the type of conversion :
        // - 0 : weight
        // - 1 : volume
        // - 2 : cup
        convertTypeNo = (Integer) getIntent().getExtras().get(COONVERSION_TYPE);
        ArrayAdapter<CharSequence> adapter;



        // An adapter to convert the String[] into something that can go in the Spinner
        switch (convertTypeNo)
        {
            case 0 : adapter = ArrayAdapter.createFromResource(this, R.array.weightUnits, android.R.layout.simple_spinner_item); break;
            case 1 : adapter = ArrayAdapter.createFromResource(this, R.array.volumeUnits, android.R.layout.simple_spinner_item); break;
            default: adapter = ArrayAdapter.createFromResource(this, R.array.cupUnits, android.R.layout.simple_spinner_item);
        }


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        Spinner toSpinner = (Spinner) findViewById(R.id.spinner_to);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    public void convert(View view) {
        Spinner fromSpinner, toSpinner;
        EditText fromEditText, toEditText;

        fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        toSpinner = (Spinner) findViewById(R.id.spinner_to);
        fromEditText = (EditText) findViewById(R.id.editText_from);
        toEditText = (EditText) findViewById(R.id.editText_to);

        // Get the string from the Spinners and number from the EditText
        String fromString = (String) fromSpinner.getSelectedItem();
        String toString = (String) toSpinner.getSelectedItem();
        double input = Double.valueOf(fromEditText.getText().toString());

        // Convert the strings to something in our Unit enu,
        ConversionTools.Unit fromUnit = ConversionTools.Unit.fromString(fromString);
        ConversionTools.Unit toUnit = ConversionTools.Unit.fromString(toString);

        // Create a converter object and convert!
        ConversionTools converter = new ConversionTools(fromUnit, toUnit);
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(result));
    }
}
