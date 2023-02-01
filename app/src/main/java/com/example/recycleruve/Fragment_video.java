package com.example.recycleruve;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Fragment_video extends Fragment {
    //atributos
    private VideoView videoView;
    private YouTubePlayerView youTubePlayerView;
    private Pelicula pelicula;
    private TextView textViewTitulo;

    //recibe por parametro el objeto pelicula
    public Fragment_video(Pelicula pelicula){
        this.pelicula=pelicula;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //creamos el view para trabajar.
       View v= inflater.inflate(R.layout.fragment_video, container, false);

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
                youTubePlayer.loadVideo(pelicula.getUrl(), 0);
            }
        });
        return v;
    }



}
