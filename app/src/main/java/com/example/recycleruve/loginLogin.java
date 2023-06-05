package com.example.recycleruve;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class loginLogin extends Fragment {
    //attributes
    private Context context;
    private FragmentTransaction fragmentTransaction;
    private EditText username;
    private EditText password;
    private static String ftPerfil;
    private Button btn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    public loginLogin(){

    }

    public loginLogin(Context context, FragmentTransaction fragmentTransaction){
        this.context=context;
        this.fragmentTransaction=fragmentTransaction;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login_login, container, false);
         username = v.findViewById(R.id.username);
         password= v.findViewById(R.id.password);
         btn=v.findViewById(R.id.login);
        mAuth= FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();

        //loginLogin login = new loginLogin(this.context);
        //aqui seteo el fragmento ya iniciado en el contenedor fragmento
        //getSupportFragmentManager().beginTransaction().replace(R.id.conteinerLogin, login).commit();


        TextView myTextView = (TextView) v.findViewById(R.id.checkedTextView);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Aquí puedes agregar la lógica que deseas ejecutar cuando se hace clic en el TextView
                System.out.println("hola bb");
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction ts = getParentFragmentManager().beginTransaction();
                ts.replace(R.id.conteinerLogin,registerFragment);
                ts.addToBackStack(null);
                //.show(registerFragment);
                ts.commit();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return v;
    }

    public void login(){
        String usernamelogin = username.getText().toString();
        String passwordlogin = password.getText().toString();
        if(usernamelogin.trim().isEmpty()||passwordlogin.trim().isEmpty()){
            Toast.makeText(getContext(), "Los campos estan vacios", Toast.LENGTH_LONG).show();
        }else {
            mAuth.signInWithEmailAndPassword(usernamelogin, passwordlogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Obtener el usuario que ha iniciado sesión
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userID = user.getUid();
                        System.out.println(userID);
                        sd(userID);
                        // Cerrar la actividad de inicio de sesión
                        getActivity().finish();
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "No seas mamon", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }



    }


    public void sd(String userID){
        //este es mi intento de consseguir la foto del perfil del usuario para cambiarlo.
        FirebaseFirestore ref = FirebaseFirestore.getInstance();
        ref.collection(userID).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Usuario p = documentSnapshot.toObject(Usuario.class);
                            System.out.println(p.getFotoPerfil());
                            System.out.println(p.getFotoPerfil());
                            System.out.println(p.getFotoPerfil());
                            p.getFotoPerfil();

                        }

                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error getting documents.", e);
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences datos= PreferenceManager.getDefaultSharedPreferences(getContext());


    }

}