package com.example.gpcorser.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class SearchRecipeFragment extends Fragment implements View.OnClickListener {

    private Spinner fromSearchSpinner;
    private EditText fromRecipeEditText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_search_recipe,container,false);

        // An adapter to convert the String[] into something that can go in the Spinner
        ArrayAdapter<CharSequence> myAdapter =  ArrayAdapter.createFromResource(getActivity(), R.array.website, android.R.layout.simple_spinner_item);



        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner searchSpinner = (Spinner) myView.findViewById(R.id.spinner_search_engine);

        searchSpinner.setAdapter(myAdapter);


        fromSearchSpinner = (Spinner) myView.findViewById(R.id.spinner_search_engine);
        fromRecipeEditText = (EditText) myView.findViewById(R.id.editText_recipe);
        Button searchButton = (Button) myView.findViewById(R.id.button_search_recipe);
        searchButton.setOnClickListener(this);

        return myView;
    }

    public void onClick(View myView){
        if (myView.getId()==R.id.button_search_recipe)
        {
            searchRecipe();
        }
    }

    public void searchRecipe()
    {
        Spinner fromSearchSpinner;
        EditText fromRecipeEditText;

        fromSearchSpinner = (Spinner) getView().findViewById(R.id.spinner_search_engine);
        fromRecipeEditText = (EditText) getView().findViewById(R.id.editText_recipe);

        // Get the string from the Spinners and number from the EditText
        String searchString = (String) fromSearchSpinner.getSelectedItem();
        String recipeString = fromRecipeEditText.getText().toString();

        Intent myIntent = new Intent(getActivity(), WebWieverActivity.class);
        myIntent.putExtra("SEARCH_SPINNER", searchString);
        myIntent.putExtra("RECIPE_TEXT", recipeString);
        getActivity().startActivity(myIntent);
    }

}
