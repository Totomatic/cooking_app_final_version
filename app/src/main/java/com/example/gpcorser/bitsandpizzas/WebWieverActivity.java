package com.example.gpcorser.bitsandpizzas;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebWieverActivity extends Activity {
    public static String SEARCH_SPINNER;
    public static String RECIPE_TEXT;
    private WebView myWebView;
    private String urlToLoad = "http://";
    private String searchWebsite;
    private String  recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_wiever);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();

        Bundle myBundle = myIntent.getExtras();

        searchWebsite = (String) myBundle.
                get("SEARCH_SPINNER");
        recipeName = (String) myBundle.get("RECIPE_TEXT");

        //setting up the web browser
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebWieverActivity.MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //launch the search on the desired website
        if (searchWebsite.equals("marmiton.org")) {
            urlToLoad = urlToLoad + "m.marmiton.org/recettes/recherche.aspx?aqt=" + recipeName;
        }
        else if (searchWebsite.equals("recipe.com")){
            urlToLoad = urlToLoad + "www.recipe.com/search/?searchType=recipe&searchTerm=" + recipeName;
        }
        else if(searchWebsite.equals("allrecipes.com"))
            urlToLoad = urlToLoad + "www.allrecipes.com/search/results/?wt=" + recipeName;
        else{
            urlToLoad = "http:/google.com";
        }


        //load the result page
        myWebView.loadUrl(urlToLoad);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().contains(searchWebsite)) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

}
