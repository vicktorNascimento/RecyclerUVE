package com.example.recycleruve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapterHorizon extends RecyclerView.Adapter<RecyclerAdapterHorizon.CancionesViewHolder>{
   //Atributo
    private ArrayList<Pelicula> peliculas ;
    private FragmentManager fragmentManager;
    private Context context;

    //Para este constructor se le pasa la lista de peliculas y no el hasmap entero
    public RecyclerAdapterHorizon( ArrayList<Pelicula> peliculas, FragmentManager fragmentManager,Context context ) {
        this.context=context;
        this.peliculas = peliculas;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerAdapterHorizon.CancionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutParaListItem = R.layout.recycler_vertica_row;
        //creo el inflater
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        boolean attach = false;

        View view =inflater.inflate(layoutParaListItem,parent,attach);

        //creo el Manejador de la vista
        CancionesViewHolder viewHolder = new CancionesViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHorizon.CancionesViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override //pillo el tamaño de la lista
    public int getItemCount() {
        return peliculas.size();
    }

    public class CancionesViewHolder extends RecyclerView.ViewHolder {
        TextView listaItemTitulo;
        ImageView imagenCheck;

        protected void fragmentoVideito(int position){

            FragmentTransaction ft=  fragmentManager.beginTransaction();
            //aqui cuando le paso el fragmente de la pelicula le paso la clase Pelicula en especifico.
            Fragment_video fv= new Fragment_video(peliculas.get(position));
            ft.replace(R.id.fragment_container, fv)
                    .addToBackStack(null)
                   .commit();


        }


        public void bind(int listaIndex){
            //aqui cojo el nombre de la pelicula
            listaItemTitulo.setText(peliculas.get(listaIndex).getNombre());
            //aqui uso la libreria glide
            Glide.with(context).load(peliculas.get(listaIndex).getImagen()).into(imagenCheck);

        }
        public CancionesViewHolder(@NonNull View itemView) {
            super(itemView);

            listaItemTitulo = itemView.findViewById(R.id.listaItemTitulo);
            imagenCheck= itemView.findViewById(R.id.imagePeli);




           //definimos el onclik
            imagenCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //en el on click llamamos a nuestra clase fragmento y le pasamos el getAdapterPosition para que ponga en el contenedor que esta
                    //en el activite main. y que muestre el nuevo fragmeto de video es decir que ya no se muestre el recycler si no el fragmento del video.
                    fragmentoVideito(getAdapterPosition());
                }
            });{

            }



        }



    }

}
