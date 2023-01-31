package com.example.practica419100058;

import static global.info.chicharron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText usuarito, lilpass;
    SharedPreferences archivo;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarito =findViewById(R.id.usuarin);
        lilpass =findViewById(R.id.contrase);
        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        if(archivo.contains("usuario")){
            Intent pasiluco = new Intent(this,cosaso.class);
            startActivity(pasiluco);
            finish();
        }
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Ayuda:
                Intent ayudain = new Intent(this, Ayuda.class);
                startActivity(ayudain);
                return true;
            case R.id.Creditos:
                Intent creditin = new Intent(this, Credito.class);
                startActivity(creditin);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void click_logun(View view) {
        String url="http://192.168.1.68/amachuchin/ingreso.php?usr=";
        url=url+usuarito.getText().toString();
        url=url+"&pass=";
        url=url+lilpass.getText().toString();
        JsonObjectRequest pet=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("usr") != -1) {
                        if (response.getInt("usr") == 1) {
                            Intent init = new Intent(MainActivity.this, cosaso.class);
                            chicharron=1;
                            SharedPreferences.Editor editor = archivo.edit();
                            editor.putInt("Id_usuario", response.getInt("usr"));
                            editor.putBoolean("valido",true);
                            editor.commit();
                            startActivity(init);
                            finish();
                        }if (response.getInt("usr") == 2) {
                            Intent ini = new Intent(MainActivity.this, menurepa.class);
                            chicharron=2;
                            SharedPreferences.Editor editor = archivo.edit();
                            editor.putInt("Id_usuario", response.getInt("usr"));
                            editor.putBoolean("valido",true);
                            editor.commit();
                            startActivity(ini);
                            finish();
                        }
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        usuarito.setText("");
                        lilpass.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("yo",error.getMessage());
            }
        });
        RequestQueue lanzarPeticion= Volley.newRequestQueue(this);
        lanzarPeticion.add(pet);
    }
}

