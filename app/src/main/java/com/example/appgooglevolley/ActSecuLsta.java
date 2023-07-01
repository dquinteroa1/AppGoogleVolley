package com.example.appgooglevolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActSecuLsta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_secu_lsta);

        //TextView sms = (TextView)findViewById(R.id.txtMessen);
        //Bundle envio=this.getIntent().getExtras();
        //sms.setText("Token"+envio.getString("Token"));

    }
    public void btBusqueda (View view){
        Bundle envio=this.getIntent().getExtras();
        TextView sms = (TextView)findViewById(R.id.txtMessen);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/clientes/search";
        JSONObject params = new JSONObject();
        try {
            params.put("fuente", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        //ArrayList<String> lstnombre = new ArrayList<String> ();
                        //JSONArray JSONlista = null;
                        //try {
                          //  JSONlista = new JSONArray(response);
                            //for(int i=0; i< JSONlista.length();i++){
                              //  JSONObject name=  JSONlista.getJSONObject(i);
                                //lstnombre.add(name.getString("nombre").toString());
                            //}
                            //sms.setText(lstnombre.toString());
                        // } catch (JSONException e) {
                         //   e.printStackTrace();
                       // }

                        sms.setText("Respuesta"+"\n"+ response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        sms.setText("Error");

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Agregar el encabezado de autenticación "Bearer Token"
                headers.put("Authorization", "Bearer"+ " " + envio.getString("Token"));
                return headers;
            }
        };

        queue.add(request);



    }

    public void btlimpiar (View view){
        TextView sms = (TextView)findViewById(R.id.txtMessen);
        sms.setText("");

    }


}