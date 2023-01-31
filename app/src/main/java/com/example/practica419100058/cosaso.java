package com.example.practica419100058;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class cosaso extends AppCompatActivity {
    Toolbar toolbar;
    SharedPreferences archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosaso);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
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

    public void click_añadir(View view) {
        Intent añade = new Intent(this,anade.class);
        startActivity(añade);
    }

    public void click_revisar(View view) {
        Intent revisa = new Intent(this,listona.class);
        startActivity(revisa);
    }
    public void click_editar(View view) {
        Intent edita = new Intent(this,editona.class);
        startActivity(edita);
    }
    public void click_salir(View view){
        if (archivo.contains("Id_usuario")) {
            SharedPreferences.Editor editor = archivo.edit();
            editor.remove("Id_usuario");
            editor.commit();
            Intent ses = new Intent(this, MainActivity.class);
            startActivity(ses);
            finish();
        }
        Toast.makeText(this,"Ha cerrado su sesion",Toast.LENGTH_SHORT).show();
    }
}