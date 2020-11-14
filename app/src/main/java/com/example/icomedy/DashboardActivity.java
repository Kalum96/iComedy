package com.example.icomedy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class DashboardActivity extends AppCompatActivity {


    RequestQueue queue;
    String url ="https://official-joke-api.appspot.com/random_joke";
    TextView txtjokes ,txtid,txtsetup,txtType,txtpunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        queue = Volley.newRequestQueue(this);
        txtjokes=findViewById(R.id.txtJokes);
        txtid=findViewById(R.id.txtID);
        txtType=findViewById(R.id.txtType);
        txtsetup=findViewById(R.id.txtSetup);
        txtpunch=findViewById(R.id.txtPunchLine);



    }

    public void getJokes(View view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
                    public void onResponse(JSONObject response) {
                int ID=0;
                String type,setup,punch;
                try {
                    ID=response.getInt("id");
                    type=response.getString("type");
                    setup=response.getString("setup");
                    punch=response.getString("punchline");
                    Joke joke=new Joke(ID,type,setup,punch);

                    txtid.setText(joke.getID()+"");
                    txtid.setVisibility(View.VISIBLE);
                    txtType.setText(joke.getType()+"");
                    txtType.setVisibility(View.VISIBLE);
                    txtsetup.setText(joke.getSetup()+"");
                    txtsetup.setVisibility(View.VISIBLE);
                    txtpunch.setText(joke.getPunchLine()+"");
                    txtpunch.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txtjokes.setText("Response : "+ID);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err=error.toString();
                        txtjokes.setText("Cannot Get Data : "+ error.toString());

                    }
                });
        queue.add(jsonObjectRequest);

    }
}