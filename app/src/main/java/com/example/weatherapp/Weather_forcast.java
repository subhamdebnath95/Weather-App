package com.example.weatherapp;

import   androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather_forcast extends AppCompatActivity {

    private EditText e1;
    private Button bt;
    private TextView t1 , t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        e1 = (EditText)findViewById(R.id.et);
        bt = (Button)findViewById(R.id.gw);
        t1 = (TextView)findViewById(R.id.tv);
        t2 = (TextView)findViewById(R.id.tv1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apikey = "09a390427bcf1293a4192438f7326e19";
                String city = e1.getText().toString();
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=09a390427bcf1293a4192438f7326e19";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("main");
                            String temperature = object.getString("temp");
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object1 = array.getJSONObject(0);
                            Double temp = Double.parseDouble(temperature)-273.15;
                            String description = object1.getString("description");
                            t1.setText(temp.toString().substring(0,5) + "°C");
                            t2.setText(description);
                        } catch (JSONException e) {
                           Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Weather_forcast.this,error.toString(),Toast.LENGTH_SHORT).show();;

                    }
                });
                queue.add(request);
            }
        });

    }
}