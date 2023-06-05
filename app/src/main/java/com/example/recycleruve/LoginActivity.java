package com.example.recycleruve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {
    public LoginActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        loginLogin login = new loginLogin(this,fragmentTransaction);
        fragmentTransaction.add(R.id.conteinerLogin, login);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView myTextView = (TextView) findViewById(R.id.textViewDD);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar la lógica que deseas ejecutar cuando se hace clic en el TextView
                System.out.println("hola bb");

                RegisterFragment registerFragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.conteinerLogin,registerFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitNow();
            }
        });
        //Aqui llamo al fragmento que esta en el contenedor Framelayout que contiene el Recyclerview y el searchView  y le paso el hashmap y el contexto

        //aqui seteo el fragmento ya iniciado en el contenedor fragmento
       // getSupportFragmentManager().beginTransaction().replace(R.id.conteinerLogin, login).commit();


    }
    protected void fragmentoVideito(int position){
        //Aqui llamo al fragmento que esta en el contenedor Framelayout que contiene el Recyclerview y el searchView  y le paso el hashmap y el contexto
        //loginLogin login = new loginLogin(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.conteinerLogin, login).commit();


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(this);


    }

}