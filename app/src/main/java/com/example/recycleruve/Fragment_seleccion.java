package com.example.recycleruve;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Fragment_seleccion extends Fragment {
    //atributos
    private Context context;
    private HashMap<String, ArrayList<Pelicula> > mapaPeliculas = new HashMap<String, ArrayList<Pelicula> >();
    private SearchView searchView;
    private RecyclerAdapter myAdapter;

    public Fragment_seleccion(){

    }

    //esete es el constructor que le paso el hashMap con las listas y el contexto para mis movidas.
    public Fragment_seleccion(Context context, HashMap<String, ArrayList<Pelicula> > mapaPeliculas){
         this.context=context;
        this.mapaPeliculas=mapaPeliculas;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //aqui creo el view para poder trabajar con las cosas
        View v = inflater.inflate(R.layout.fragment_selelccion, container, false);

        //este searchView es para buscar en las listas
        searchView= (SearchView)v.findViewById(R.id.searchBusqueda);
        searchView.clearFocus();
        //aqui le pongo un onQuerry para cuando se le introduce data
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                //en caso de que se le pase data que llame a mi metodo de filtrado.
                filterList(s);
                return true;
            }
        });

        //aqui capturo el recyclerView.
        RecyclerView myRv = (RecyclerView) v.findViewById(R.id.rvCanciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        // y seteo el adaptador de la clase de RecyclerAdapter que cree yo.
        myAdapter = new RecyclerAdapter(mapaPeliculas ,context,this.getParentFragmentManager());
        myRv.setAdapter(myAdapter);



        return v;
    }

    //esete es mi metodo de filtrado que funciona con los hasmap.
    private void filterList(String text) {
        //me creo un nuevo hasmap filtrado
        HashMap<String, ArrayList<Pelicula> > mapafiltrado = new HashMap<String, ArrayList<Pelicula> >();
        Set<String> keys = mapaPeliculas.keySet();
        //para cada key de mi hasmap original buscara en cada lista que contiene en esa key
        for (String key : keys) {
            //para cada pelicula dentro de cada lista cojera su nombre.
            for (Pelicula p : mapaPeliculas.get(key) ) {
                //en caso de que su nombre contenga en texto que le pasemos por parametros
                if(p.getNombre().toLowerCase().contains(text.toLowerCase())){
                    //lo cojera y lo añadira a nuestro nuevo hasmap pero primero comprobamos si esa key ya existe
                    if(mapafiltrado.containsKey(key)){
                        //en caso de que exista lo metemos en esa lista que contiene esa key
                        mapafiltrado.get(key).add(p);
                    }else{
                        //en caso de que en la nueva lista esa key no exista creara una nueva lista con esa categoria
                        mapafiltrado.put(key,new ArrayList<Pelicula>());
                        // y le añade a dentro de su lista que tiene esa categoria su pelicula correspondiente
                        mapafiltrado.get(key).add(p);
                    }
                }

            }

        }
        //una vez terminado el proceso veremos si el hasmap esta vacio o no. En caso de que si mostrara un mensaje de que no se pudo encontrar.
        if (mapafiltrado.size()==0) {
            Toast.makeText(getContext(),"No could found it", Toast.LENGTH_LONG).show();

        }else{
            //en caso de que se encontro se seteara el nuevo hasmap en el Recycler adpter que yo cree.
            this.myAdapter.setFilterlist(mapafiltrado);
        }
    }



}
