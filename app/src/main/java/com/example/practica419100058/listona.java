package com.example.practica419100058;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import adaptadores.adaptador;

public class listona extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView reciclun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listona);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reciclun =findViewById(R.id.listota);
        adaptador av= new adaptador();
        av.context=this;

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        reciclun.setLayoutManager(llm);
        reciclun.setAdapter(av);

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
}