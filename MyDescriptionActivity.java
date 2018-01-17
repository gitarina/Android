package com.example.kirpicheva.hellojson;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by kirpicheva on 04.07.16.
 */
public class MyDescriptionActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        String desc = getIntent().getStringExtra("DESCRIPTION");
        TextView infoTextView = (TextView) findViewById(R.id.desc_label);
        infoTextView.setText(desc);
    }
}
