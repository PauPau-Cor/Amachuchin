package com.example.practica419100058;

import static global.info.chicharron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import global.info;

public class informacion extends AppCompatActivity {
    TextView numbrin, paterin, materin, telefoin, numin, callin, metodono, totin, producin, cantin,procesin;
    Toolbar toolbar;
    ImageButton telegono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        numbrin =findViewById(R.id.nombresote);
        paterin =findViewById(R.id.apellidote1);
        materin =findViewById(R.id.apellidote2);
        telefoin =findViewById(R.id.telefonsote);
        numin =findViewById(R.id.numcasota);
        callin =findViewById(R.id.callesota);
        metodono =findViewById(R.id.metodote);
        totin =findViewById(R.id.totalota);
        producin =findViewById(R.id.productote);
        cantin =findViewById(R.id.cantidota);
        procesin =findViewById(R.id.textote);
        telegono =findViewById(R.id.llama);

        telegono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclic_telegono();
            }
        });

        int posicion;
        posicion=getIntent().getIntExtra("pos",-1);
        numbrin.setText(info.lista.get(posicion).getNombre());
        paterin.setText(info.lista.get(posicion).getApellidop());
        materin.setText(info.lista.get(posicion).getApellidom());
        telefoin.setText(info.lista.get(posicion).getTelefono());
        numin.setText(info.lista.get(posicion).getNumero());
        callin.setText(info.lista.get(posicion).getCalle());
        totin.setText(info.lista.get(posicion).getPrecio());
        metodono.setText(info.lista.get(posicion).getMetodo());
        producin.setText(info.lista.get(posicion).getManzana());
        cantin.setText(info.lista.get(posicion).getCantidad());
        procesin.setText(info.lista.get(posicion).getProceso());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void onclic_telegono() {
        Intent telegono1=new Intent(Intent.ACTION_CALL);
                telegono1.setData(Uri.parse("tel: "+telefoin.getText().toString()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},10);
                    return;
                }
                startActivity(telegono1);
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

    public void click_borra(View view) {
        if(chicharron==1){
            int i;
            String nomin;
            nomin = numbrin.getText().toString();
            i=getIntent().getIntExtra("pos",-1);
            String url = "http://192.168.1.68/amachuchin/delete.php?nom="+ nomin;
            JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("yo",error.getMessage());
                }
            });
            RequestQueue lanzarPeticion= Volley.newRequestQueue(this);
            lanzarPeticion.add(pet);
            info.lista.remove(i);
            Intent seva = new Intent(this,cosaso.class);
            startActivity(seva);
            Toast.makeText(this,"El pedido ha sido eliminado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No tiene los permisos necesarios para esto",Toast.LENGTH_SHORT).show();
        }

    }
}