package com.example.recycleruve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CancionesViewHolder>{
    //Atributos
    public Context context;
    public ArrayList<Pelicula> peliculas ;
    public FragmentManager fragmentManager;
    private HashMap<String, ArrayList<Pelicula> > mapaPeliculas;
    public RecyclerAdapter(){

    }
    //constructor. Para este se recibe el hasmap pero para el siguiente Recycler se recibe su lista.
    public RecyclerAdapter(HashMap<String, ArrayList<Pelicula> > mapaPeliculas, Context context, FragmentManager fragmentManager) {

        this.context=context;
        this.fragmentManager=fragmentManager;
        this.mapaPeliculas=mapaPeliculas;
    }




    @NonNull
    @Override
    //Método invocado desde el constructor para crear y pintar el contenido del Recycler,
    //que invoca alconstructor de nuestro ViewHolder.
    public CancionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutParaListItem = R.layout.recycler_xxxx_row;
        //creo el inflater
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        boolean attach = false;

        View view =inflater.inflate(layoutParaListItem,parent,attach);

        RecyclerAdapterHorizon recyclerAdapterHorizon= new RecyclerAdapterHorizon(peliculas,fragmentManager,context);

        //creo el Manejador de la vista
        CancionesViewHolder viewHolder = new CancionesViewHolder(view);

        return new CancionesViewHolder(view);
    }



    @Override
    //Método para poblar todos los items del recycler,
    //debe invocar al método bind de nuestro Recycler
    public void onBindViewHolder(@NonNull CancionesViewHolder holder, int position) {
       holder.bind(position);
    }

    @Override
    //Método que retorna los elementos del Recycl el numero de elementos solo se cambia aqui la lista y ya
    public int getItemCount() {
        return mapaPeliculas.size();
    }

    //este es Setter del hasmap que lo uso si me pongo a usar la barra de busqueda
    public void setFilterlist(HashMap<String, ArrayList<Pelicula> > mapaFiltrado){
        this.mapaPeliculas=mapaFiltrado;
        notifyDataSetChanged();
    }

    public class CancionesViewHolder extends RecyclerView.ViewHolder {
        TextView tvListaCancionesItem;
        ImageView imagenCheck;
        RecyclerView rvchild;
        RecyclerAdapterHorizon rverticlal;




        public void bind(int listaIndex){
            //pillo el keyset que contiene las categorias de las pelis y las paso a un array con cada uno
            //y selecciono cada uno segun el index
            Set<String> keys=mapaPeliculas.keySet();
            tvListaCancionesItem.setText((String) keys.toArray()[listaIndex]);


            //para titulo de la categoria es decir categoria carga cada RecyclerView con su lista correspondiente.
            RecyclerAdapterHorizon myAdapter = new RecyclerAdapterHorizon(mapaPeliculas.get((String) keys.toArray()[listaIndex]),fragmentManager,context);
            rvchild.setAdapter(myAdapter);

        }


        public CancionesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListaCancionesItem = itemView.findViewById(R.id.listaItemCancion);
            rvchild= itemView.findViewById(R.id.rvPeliculas);
            //aqui pongo el recycler view con sus decoration y en horizontal
            rvchild.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

            rvchild.setLayoutManager(linearLayoutManager);


        }





    }




}
