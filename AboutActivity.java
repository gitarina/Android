package com.example.kirpicheva.hellojson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Упражняемся в передаче значений из одной активити в другую.
 */
public class AboutActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String artist = getIntent().getStringExtra("ARTIST");
        String  desc = getIntent().getStringExtra("DESCRIPTION");

        TextView infoTextView = (TextView)findViewById(R.id.about_label);

        // TODO: Убрать текст в константы strings.xml.

        infoTextView.setText("Приложение позволяет узнать немного больше о любимых артистах. Например, вы знали, что " + artist + " — это " + desc + "?");
    }
}
