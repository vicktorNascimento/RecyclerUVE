package com.example.recycleruve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    //Atributos
    private HashMap<String, ArrayList<Pelicula> > mapaPeliculas = new HashMap<String, ArrayList<Pelicula> >();

    //para esta tarea decidi cambiar las imagenes no por memoria si no por internet y en el video igual debido a que tener que llenar dos recycler view hacia demasiado pesado
    //y creo que asi seria mucho mas ligero. las librerias que implementado son las de glide y las de YoutubeView.


    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().hide();
        //para versiones recientes esto es mejor bb.
        getSupportActionBar().hide();

        //esto es para el splash para que quede rexulon
        Intent i = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(i);


        //aqui cargo las pelis y las colas con sus nombres si estan repetidas o no y las añado.
        addPeli("Acction",new Pelicula("Cobra kai", "https://es.web.img2.acsta.net/pictures/21/01/04/12/12/0170975.jpg", "CfOuYm8EnBA", "Action"));
        addPeli("Acction", new Pelicula("The umbrella acedemy", "https://es.web.img3.acsta.net/r_1280_720/pictures/20/06/30/09/27/0936610.jpg", "vhSoMI1DPrA", "Action"));
        addPeli("Acction",new Pelicula("Vikings", "https://www.ecartelera.com/carteles-series/100/127/006_p.jpg", "8Co0o5V8WA4", "Action"));
        addPeli("Acction",new Pelicula("The wicher", "https://cdn.grupoelcorteingles.es/SGFM/dctm/MEDIA03/202102/08/00183110707896____1__640x640.jpg", "66UlOXnyQmo", "Action"));

        addPeli("Comedy",new Pelicula("Brokling 99", "https://es.web.img3.acsta.net/pictures/18/12/18/11/59/0638993.jpg", "sEOuJ4z5aTc", "Comedy"));
        addPeli("Comedy",new Pelicula("How i meet your mother", "https://mx.web.img3.acsta.net/r_1280_720/medias/nmedia/18/74/38/63/20215058.jpg", "cjJLEYMzpjc", "Comedy"));
        addPeli("Comedy",new Pelicula("Big bang theory","https://www.ecartelera.com/carteles-series/000/22/012_p.jpg", "https://www.youtube.com/watch?v=mRo7uaDW7jc", "Comedy"));

        addPeli("Anime",new Pelicula("Shingeki no kiojin", "https://i.pinimg.com/736x/fd/c1/e2/fdc1e2cda971f808ea1c918291385a5c.jpg" , "FRn6xXXF-7s","Anime" ));
        addPeli("Anime",new Pelicula("Haikyuu", "https://ih1.redbubble.net/image.1008486797.3847/flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "V8KTuiJeBH8", "Anime"));
        addPeli("Anime",new Pelicula("Jujutsy kaisen", "https://ramenparados.com/wp-content/uploads/2022/09/Jujutsu-Kaisen-2-1-724x1024.jpg", "aPBUUJbrAWo", "Anime"));
        addPeli("Anime",new Pelicula("Kimetsu no jayba", "https://es.web.img3.acsta.net/pictures/19/09/18/13/46/1341879.jpg", "stiZAeIqxlk", "Anime"));

        addPeli("Romance",new Pelicula("Ha nacido una estrella", "data:image/jpeg;base64,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", "v_OQJHtKqbA", "Romance"));
        addPeli("Romance",new Pelicula("El amor es como el cha", "https://es.web.img2.acsta.net/r_1280_720/pictures/21/08/17/14/46/5897398.jpg", "hQi9zEaCMYI", "Romance"));
        addPeli("Romance",new Pelicula("Sex Education", "https://es.web.img2.acsta.net/pictures/20/01/08/09/31/0501142.jpg", "WSXMGu_9PK8", "Romance"));
        addPeli("Romance",new Pelicula("Propuesta laboral", "https://es.web.img3.acsta.net/pictures/22/02/14/17/12/4268762.jpg", "mh4R-WXRhQo", "Romance"));

        //Aqui llamo al fragmento que esta en el contenedor Framelayout que contiene el Recyclerview y el searchView  y le paso el hashmap y el contexto
        Fragment_seleccion fragment_seleccion= new Fragment_seleccion(this, mapaPeliculas);
        //aqui seteo el fragmento ya iniciado en el contenedor fragmento
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment_seleccion).commit();


    }


    //esta clase es interesante por que funciona con un hashmap que la key es la categoria y dentro del valor del hash tiene listas de peliculas.
    //Por que asi? por que en el caso de que ese categoria de peliculas ya existe la meto en su lista de peliculas en caso de que no se crea esa lista que contendra las peliculas
    //de esa categoria que es la key.
    public void addPeli(String tipe, Pelicula pelicula){
        //hasmap
        //si el tipo de la peli existe la añade a la lista de ese tipo que ya existe
        if(mapaPeliculas.containsKey(tipe)){
            mapaPeliculas.get(tipe).add(pelicula);

        }else{
            //en caso de que no la crea la lista de ese tipo y añade la pelicula de ese tipo ahi.
            mapaPeliculas.put(tipe,new ArrayList<Pelicula>());
            mapaPeliculas.get(tipe).add(pelicula);

        }
    }


}