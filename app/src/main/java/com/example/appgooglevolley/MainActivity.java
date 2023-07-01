package com.example.appgooglevolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btconsulta (View view){
        //Captura
        EditText usuario =(EditText)findViewById(R.id.editUsuario);
        EditText password =(EditText)findViewById(R.id.editPassword);
        String user, pass;
        user=usuario.getText().toString();
        pass=password.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/login";
        JSONObject params = new JSONObject();
        try {
            params.put("correo", user);
            params.put("clave", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Extraer el token de la respuesta
                            String token = response.getString("access_token");

                            // Manejar la respuesta exitosa
                            // Aquí puedes verificar la respuesta del servidor y realizar las acciones correspondientes

                            Bundle envio =new Bundle();
                            envio.putString("Token", token);
                            Intent intent = new Intent(MainActivity.this, ActSecuLsta.class);
                            intent.putExtras(envio);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error
                        // Aquí puedes mostrar un mensaje de error al usuario o realizar otras acciones
                        TextView mensaje = (TextView)findViewById(R.id.txtAviso);
                        mensaje.setText("Error de Credenciales");

                    }
                });
        queue.add(request);

    }
    public void btlimpiar (View view){
        EditText usuario =(EditText)findViewById(R.id.editUsuario);
        EditText password =(EditText)findViewById(R.id.editPassword);
        usuario.setText("");
        password.setText("");

    }

}