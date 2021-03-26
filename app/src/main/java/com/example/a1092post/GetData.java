package com.example.a1092post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GetData extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data3);
        API api = new API(this);
        textView = findViewById(R.id.showData);
        GetDataInterface getDataInterface = new GetDataInterface() {
            @Override
            public void waitGetAllRestaurantDone(ArrayList<HashMap<String, String>> arrayList) {
                textView.setText(arrayList.toString());
            }
        };
        api.getAllRestaurant(getDataInterface);
    }
}