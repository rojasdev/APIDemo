package com.demo.apidemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchDataAsyncTask extends AsyncTask<String, Void, List<TableModel>> {
    private final GridViewAdapter adapter;

    public FetchDataAsyncTask(GridViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<TableModel> doInBackground(String... params) {
        String urlString = params[0];
        List<TableModel> itemList = new ArrayList<>();

        Log.d("Running","Retrievingggg");

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int tableId = jsonObject.getInt("id");
                String tableName = jsonObject.getString("label");
                itemList.add(new TableModel(tableId, tableName));
            }

            reader.close();
            connection.disconnect();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    @Override
    protected void onPostExecute(List<TableModel> itemList) {
        adapter.setData(itemList);
        adapter.notifyDataSetChanged();
    }
}

