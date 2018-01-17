package com.example.kirpicheva.hellojson;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
// Открывает краткое описание приложения.
    public void onClick(View view1) {

        // Создаем интент.
        Intent aboutScreen = new Intent(MainActivity.this, AboutActivity.class);
        // Ключ ARTIST для названия группы.
        aboutScreen.putExtra("ARTIST", "Ундервуд");
        // Ключ DESCRIPTION для описания.
        aboutScreen.putExtra("DESCRIPTION", "группа из Симферополя");
        // Стартуем активити.
        startActivity(aboutScreen);
    }
// Открывает список исполнителей.
    public void onList(View view2) {

        Intent listScreen = new Intent(MainActivity.this, MyListActivity.class);
        startActivity(listScreen);
    }






    }


