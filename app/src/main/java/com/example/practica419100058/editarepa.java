package com.example.practica419100058;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import POJOS.residentes;

public class editarepa extends AppCompatActivity {
    Toolbar toolbar;
    Button guardar;
    ImageButton ant, sig;
    TextView name,app,apm,tel,calle,nca,prod,tot, cant, meto;
    Spinner spinirin;
    int posicion;
    String procesidit;
    residentes laPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarepa);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = findViewById(R.id.nombredit);
        app = findViewById(R.id.apellido1dit);
        apm = findViewById(R.id.apellido2dit);
        tel = findViewById(R.id.telefonodit);
        calle = findViewById(R.id.calledit);
        nca = findViewById(R.id.numcasadit);
        tot = findViewById(R.id.preciodit);
        prod = findViewById(R.id.productodit);
        cant = findViewById(R.id.cantidaddit);
        meto = findViewById(R.id.metododit);
        guardar = findViewById(R.id.guardabut);
        spinirin = findViewById(R.id.spinirillodit);
        sig = findViewById(R.id.siguiente);
        ant = findViewById(R.id.anterior);

        ArrayAdapter<CharSequence> adaptin=ArrayAdapter.createFromResource(this,R.array.Proceso, android.R.layout.simple_spinner_item);
        spinirin.setAdapter(adaptin);
        spinirin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posichon, long abedul) {
                procesidit= parent.getItemAtPosition(posichon).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclic_guardar_c();
            }
        });

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclic_sig();
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclic_ant();
            }
        });

        posicion = 0;
        if (info.lista.size()>0) {
            laPersona = info.lista.get(0);
            imprime(laPersona);
        }

    }

    private void onclic_ant() {
        if(info.lista.size()>0){
            if(posicion==0)
                posicion=info.lista.size()-1;
            else
                posicion=posicion-1;
            laPersona=info.lista.get(posicion);
            imprime(laPersona);
        }
    }

    private void onclic_sig() {
        if(info.lista.size()>0) {
            if(posicion==info.lista.size()-1)
                posicion=0;
            else
                posicion=posicion+1;
            laPersona=info.lista.get(posicion);
            imprime(laPersona);
        }
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

    private void onclic_guardar_c() {
        if(info.lista.size()>0){
            String numbrin;
            numbrin= name.getText().toString();
            String url = "http://192.168.1.68/amachuchin/updaterepa.php?nom="+ numbrin+"&proc="+ procesidit;
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
            laPersona.setProceso(procesidit);
            Toast.makeText(this,"El pedido ha sido actualizado",Toast.LENGTH_SHORT).show();
        }

    }

    private void imprime(residentes x){
        name.setText(x.getNombre());
        app.setText(x.getApellidop());
        apm.setText(x.getApellidom());
        tel.setText(x.getTelefono());
        calle.setText(x.getCalle());
        nca.setText(x.getNumero());
        meto.setText(x.getMetodo());
        cant.setText(x.getCantidad());
        prod.setText(x.getManzana());
        tot.setText(x.getPrecio());
    }
}