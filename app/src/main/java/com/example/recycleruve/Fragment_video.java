package com.example.recycleruve;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recycleruve.db.BaseDatosHelper;
import com.example.recycleruve.db.EstructuraBBDD;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Fragment_video extends Fragment {
    //atributos
    private VideoView videoView;
    private YouTubePlayerView youTubePlayerView;
    private Pelicula pelicula;
    private TextView textViewTitulo;
    private BaseDatosHelper dbHelper;
    private float timer=0;
    private YouTubePlayerTracker tracker;

    public Fragment_video(){

    }

    //recibe por parametro el objeto pelicula
    public Fragment_video(Pelicula pelicula){
        this.pelicula=pelicula;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //creamos el view para trabajar.
       View v= inflater.inflate(R.layout.fragment_video, container, false);
         dbHelper= new BaseDatosHelper(getContext());

        textViewTitulo=(TextView) v.findViewById(R.id.textViewTitulo);
        // seteo el texto del titulo de la pelicula con el nombre de la pelicula,
        textViewTitulo.setText(pelicula.getNombre());



        //he usado una libreria externa para poder mostrar el contenedor de yt en mi fragmento.
        youTubePlayerView = (YouTubePlayerView) v.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
               //aqui le paso al loadVideo el enlace de la pelicula que cojo del objeto pelicula.
                //y como se inicia automaticamente pues ya estaria.
                youTubePlayer.loadVideo(pelicula.getUrl(), setTime());
                tracker = new YouTubePlayerTracker();
                youTubePlayer.addListener(tracker);
                System.out.println("---------------------------------------");

                System.out.println(setTime());

            }
        });
        /*
                tracker.getState();
                tracker.getCurrentSecond();
                tracker.getVideoDuration();
                tracker.getVideoId();
         */



        return v;
    }

    //te dice el tiempo que queda
    public float setTime(){
        String clave[]={pelicula.getNombre().toString()};
        if (users(dbHelper,clave)){

        }
        return timer;
    }

    @Override
    public void onPause() {
        System.out.println(tracker.getCurrentSecond());
        duracion();
        System.out.println(timer);
        super.onPause();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.commit();
    }

    @Override
    public void onDestroy() {
        //System.out.println(tracker.getCurrentSecond());
        //duracion();
        super.onDestroy();



    }

    //cuando se destruye se guarda por donde va.
    public void duracion(){

        if(tracker.getState()== PlayerConstants.PlayerState.ENDED){

        }
        // Obtiene la duración total del video en milisegundos
        double millse=15.000;
        String clave[]={pelicula.getNombre().toString()};
        System.out.println(tracker.getVideoDuration());
        System.out.println((tracker.getVideoDuration()-millse)+">"+tracker.getCurrentSecond());
        if ((tracker.getVideoDuration()-millse)>tracker.getCurrentSecond()){
            //si la peli esta en la base de datos.
            if (users(dbHelper,clave)){

                String peli[]={pelicula.getNombre().toString()};
                //aqui carga el cursor
                Cursor cursor = dbHelper.getReadableDatabase().query(
                        EstructuraBBDD.TABLA_DATOS_PERSONALES,
                        EstructuraBBDD.projection,
                        EstructuraBBDD.COLUMN_NAME+ " = ?",
                        peli,
                        null,
                        null,
                        EstructuraBBDD.sortOrder
                );

                if (cursor.moveToFirst()) {
                        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.COLUMN_NAME));
                        //String pass = cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.COLUMN_NAME));
                        ContentValues values = new ContentValues();

                            values.put(EstructuraBBDD.COLUMN_PASS,tracker.getCurrentSecond()+"");
                            String arg[]= new String[1];
                            arg[0]=nombre;

                            String selection = EstructuraBBDD.COLUMN_NAME+" LIKE ?";

                            int count = dbHelper.getReadableDatabase().update(
                                    EstructuraBBDD.TABLA_DATOS_PERSONALES,
                                    values,
                                    selection,
                                    arg);


                        //users.add(nombre);
                    Toast.makeText(getContext(), "se cambio el time del video", Toast.LENGTH_LONG).show();
                }


            }else{
                //si no esta en la base de datos y la peli no se vio entero se insertara en la base de datos.
                if ((tracker.getVideoDuration()-millse)>tracker.getCurrentSecond()) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    //Creamos mapa de valores con los nombres de las tablas
                    ContentValues values = new ContentValues();

                    values.put(EstructuraBBDD.COLUMN_NAME, pelicula.getNombre().toString());
                    values.put(EstructuraBBDD.COLUMN_PASS, tracker.getCurrentSecond() + "");

                    //INSERTAMOS
                    long newRowId = db.insert(EstructuraBBDD.TABLA_DATOS_PERSONALES, null, values);
                    Toast.makeText(getContext(), "se añadio el time del video", Toast.LENGTH_LONG).show();
                }//si no, no se guardara por que entiendo que se vio completa.
            }

            //por si hay movidas con el fin del video
        }else if(tracker.getState()== PlayerConstants.PlayerState.ENDED){
            if (users(dbHelper,clave)){

                String peli[]={pelicula.getNombre().toString()};
                //aqui carga el cursor
                Cursor cursor = dbHelper.getReadableDatabase().query(
                        EstructuraBBDD.TABLA_DATOS_PERSONALES,
                        EstructuraBBDD.projection,
                        EstructuraBBDD.COLUMN_NAME+ " = ?",
                        peli,
                        null,
                        null,
                        EstructuraBBDD.sortOrder
                );

                if (cursor.moveToFirst()) {
                    String nombre = cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.COLUMN_NAME));
                    //String pass = cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.COLUMN_NAME));
                    ContentValues values = new ContentValues();

                    values.put(EstructuraBBDD.COLUMN_PASS,"0");
                    String arg[]= new String[1];
                    arg[0]=nombre;

                    String selection = EstructuraBBDD.COLUMN_NAME+" LIKE ?";

                    int count = dbHelper.getReadableDatabase().update(
                            EstructuraBBDD.TABLA_DATOS_PERSONALES,
                            values,
                            selection,
                            arg);


                    //users.add(nombre);
                    Toast.makeText(getContext(), "se cambio el time del video", Toast.LENGTH_LONG).show();
                }


            }
        }

    }


    //esta es la consulta que hago para ver si el usuario y passs ya existen.
    public boolean users(BaseDatosHelper dbHelper, String clave[]){
        String claves[] = clave;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //esto es para buscar.
        //creo estos datos de columnas y seleccion where
        Cursor cursor = dbHelper.getReadableDatabase().query(
                EstructuraBBDD.TABLA_DATOS_PERSONALES,
                EstructuraBBDD.projection,
                EstructuraBBDD.COLUMN_NAME+ " = ?",
                claves,
                null,
                null,
                EstructuraBBDD.sortOrder
        );

        if (cursor.moveToFirst()==true){
            this.timer = Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(EstructuraBBDD.COLUMN_PASS)));
            System.out.println(timer);
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
        //String nombres = cursor.getString(0);
        //cursor.getString(2);

    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(getContext());


    }



}
