package com.example.kirpicheva.hellojson;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Выводим список исполнителей, полученный из json.
 */
public class MyListActivity extends Activity{

        String[] arrayArtistName;
        String[] arrayArtistDescription;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
// Выполнение асинхронной задачи.
        new ParseTask().execute();

        }
// Первый экран, первое действие с первым элементом списка.

        private class ParseTask extends AsyncTask<Void, Void, String> {

                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                // Cтрока, в которую будет записываться json, полученный с сервера.
                String jsonResult = "";


                @Override
                protected String doInBackground(Void... params) {

                        // Забираем json с сервера с помощью GET-запроса. Записываем в строку.
                        try

                        {
                                URL url = new URL("http://download.cdn.yandex.net/mobilization-2016/artists.json");

                                urlConnection = (HttpURLConnection) url.openConnection();
                                urlConnection.setRequestMethod("GET");
                                urlConnection.connect();


                                InputStream inputStream = urlConnection.getInputStream();
                                StringBuilder builder = new StringBuilder();

                                reader = new BufferedReader(new InputStreamReader(inputStream));

                                String line;
                                while ((line = reader.readLine()) != null) {
                                        builder.append(line);
                                }
                                jsonResult = builder.toString();
                                Log.i("GSON", "Первый результат: " + jsonResult);

                        } catch (
                                Exception e
                                )

                        {
                                Log.i("GSON", "Перехвачено исключение: " + jsonResult);
                        }

                        return jsonResult;
                }

                @Override
                protected void onPostExecute(String json) {

                        super.onPostExecute(json);

                        // Используем GSON-объект для того, чтобы распарсить полученную строку с json.

                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        Artist[] artists = gson.fromJson(json, Artist[].class);
                        Log.i("GSON", "Имя: " + artists[1].name);

                        // Массивы строк, для передачи в arrаy-адаптер.

                        arrayArtistName = new String[artists.length];
                        arrayArtistDescription = new String[artists.length];

                        // Присваиваем один массив другому.
                        for (int i = 0; i < artists.length; i++) {
                                arrayArtistName[i]  = artists[i].name;
                                arrayArtistDescription[i]  = artists[i].description;

                        }

                        Log.i("GSON", "Описание: " + artists[1].description);


                        // Cоздаем arrаy-адаптер.
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyListActivity.this, android.R.layout.simple_list_item_1, arrayArtistName);
                        final ListView listView = (ListView) findViewById(R.id.artist);
                        // Присваиваем адаптер списку.
                        listView.setAdapter(adapter);

// Обработка события — выбрали пункт списка.

                        listView.setOnItemClickListener(new OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> arg0, View arg1,
                                                        int pos, long id) {


                                        Intent secondScreen = new Intent(MyListActivity.this, MyDescriptionActivity.class);

                                        secondScreen.putExtra("DESCRIPTION", arrayArtistDescription[pos]);

                                        startActivity(secondScreen);
                                }
                        });
                }
        }

        }