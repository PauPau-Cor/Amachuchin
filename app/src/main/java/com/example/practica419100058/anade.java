package com.example.practica419100058;

import static global.info.chicharron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import global.info;
import POJOS.residentes;

public class anade extends AppCompatActivity {
    Toolbar toolbar;
    EditText nombrinini, paternini, maternini, callesina, numerinini, manzanana, telefonini, cantidini, metodini, precini;
    Button edit;
    Spinner spiniruni;
    String procesini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anade);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombrinini =findViewById(R.id.nombresin);
        paternini =findViewById(R.id.apellidin1);
        maternini =findViewById(R.id.apellidin2);
        callesina =findViewById(R.id.calle);
        numerinini =findViewById(R.id.numcasa);
        manzanana =findViewById(R.id.manzana);
        cantidini =findViewById(R.id.canti);
        telefonini=findViewById(R.id.telefonsin);
        edit=findViewById(R.id.añade);
        metodini=findViewById(R.id.metodun);
        spiniruni=findViewById(R.id.spinirillo);
        precini=findViewById(R.id.precio);

        ArrayAdapter<CharSequence> adaptiriru=ArrayAdapter.createFromResource(this,R.array.Proceso, android.R.layout.simple_spinner_item);
        spiniruni.setAdapter(adaptiriru);
        spiniruni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posichon, long abedul) {
                procesini= parent.getItemAtPosition(posichon).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd();
            }
        });

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

    private void onAdd() {
        String numbrin,patirn,matirn,callin,numin,manzin, telefin, cantin, precin, metodin;
        numbrin= nombrinini.getText().toString();
        patirn= paternini.getText().toString();
        matirn= maternini.getText().toString();
        telefin= telefonini.getText().toString();
        callin= callesina.getText().toString();
        numin= numerinini.getText().toString();
        manzin= manzanana.getText().toString();
        cantin= cantidini.getText().toString();
        precin= precini.getText().toString();
        metodin= metodini.getText().toString();

        if(!numbrin.isEmpty()&&!patirn.isEmpty()&&!matirn.isEmpty()&&!telefin.isEmpty()&&!callin.isEmpty()&&!numin.isEmpty()&&!manzin.isEmpty()&&!cantin.isEmpty()&&!precin.isEmpty()&&!metodin.isEmpty()){
            String url = "http://192.168.1.68/amachuchin/insertar.php?nom="+ numbrin+"&apep="+ patirn+"&apem="+ matirn+"&tel="+ telefin+"&num="+ numin+"&cal="+ callin+"&tol="+ precin+"&pro= "+ manzin+"&can="+ cantin+"&met="+ metodin+"&proc="+ procesini;
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

            residentes person = new residentes();
            person.setNombre(nombrinini.getText().toString());
            person.setApellidop(paternini.getText().toString());
            person.setApellidom(maternini.getText().toString());
            person.setTelefono(telefonini.getText().toString());
            person.setCalle(callesina.getText().toString());
            person.setNumero(numerinini.getText().toString());
            person.setMetodo(metodini.getText().toString());
            person.setCantidad(cantidini.getText().toString());
            person.setProceso(procesini);
            person.setManzana(manzanana.getText().toString());
            person.setPrecio(precini.getText().toString());
            info.lista.add(person);
            Toast.makeText(this,"El pedido ha sido registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Por favor rellene todos los campos",Toast.LENGTH_SHORT).show();
        }
    }



    public void click_eliminar(View view) {
        nombrinini.setText("");
        paternini.setText("");
        maternini.setText("");
        telefonini.setText("");
        callesina.setText("");
        numerinini.setText("");
        manzanana.setText("");
        cantidini.setText("");
        manzanana.setText("");
        precini.setText("");
        metodini.setText("");
    }
}