package com.example.recycleruve;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    EditText name;
    EditText email;
    EditText password;
    Button btn;
    FirebaseAuth mAuth;
    String ftPerfil;
    FirebaseFirestore mFirestor;
    private ImageButton imgBtn1;
    private ImageButton imgBtn2;
    private ImageButton imgBtn3;
    private ImageButton imgBtn4;
    private ImageButton imgBtn5;
    private ImageButton imgBtn6;
    private  EditText repeatpass;
    private EditText fecha_nacimiento;
    private ArrayList<ImagenPerfil> imgPerfil = new ArrayList<>();
    private ArrayList<ImageButton> botones = new ArrayList<>();

    public RegisterFragment(){

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        name=v.findViewById(R.id.editTextTextPersonName);
        email=v.findViewById(R.id.editTextTextEmailAddress);
        password=v.findViewById(R.id.editTextTextPassword);

        botones.add(imgBtn1=v.findViewById(R.id.imageButton2));
        botones.add(imgBtn2=v.findViewById(R.id.imageButton));
        botones.add(imgBtn3=v.findViewById(R.id.imageButton3));
        botones.add(imgBtn4=v.findViewById(R.id.imageButton6));
        botones.add(imgBtn5=v.findViewById(R.id.imageButton5));
        botones.add(imgBtn6=v.findViewById(R.id.imageButton4));
        eleccion(imgBtn1);
        eleccion(imgBtn2);
        eleccion(imgBtn3);
        eleccion(imgBtn4);
        eleccion(imgBtn5);
        eleccion(imgBtn6);

        repeatpass= v.findViewById(R.id.editTextTextPasswordConfirm);
        fecha_nacimiento= v.findViewById(R.id.editTextDate);

        btn=v.findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        mFirestor=FirebaseFirestore.getInstance();
        FirebaseFirestore mFire = FirebaseFirestore.getInstance();

        mFire.collection("FotosParaPerfil").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        int i=0;
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            ImagenPerfil p = documentSnapshot.toObject(ImagenPerfil.class);
                            imgPerfil.add(p);
                            System.out.println(p.getImagen());
                            //esto lo hago por si no se llegara a seleccionar ninguna imagen y sea la predeterminada
                            ftPerfil=imgPerfil.get(0).getImagen();
                            Glide.with(getContext()).load(p.getImagen()).into(botones.get(i));
                            i++;
                        }

                    }


                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error getting documents.", e);
                    }
                });

        //se que esto no lo deveria de repetir esto hacer pero ya son la 1 y aun no he terminado
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn1.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn2.setBackgroundColor(2);
                imgBtn3.setBackgroundColor(2);
                imgBtn4.setBackgroundColor(2);
                imgBtn5.setBackgroundColor(2);
                imgBtn6.setBackgroundColor(2);

                ftPerfil=imgPerfil.get(0).getImagen();
                System.out.println("0");
            }
        });

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn2.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn1.setBackgroundColor(2);
                imgBtn3.setBackgroundColor(2);
                imgBtn4.setBackgroundColor(2);
                imgBtn5.setBackgroundColor(2);
                imgBtn6.setBackgroundColor(2);
                ftPerfil=imgPerfil.get(1).getImagen();
                System.out.println("1");
            }
        });

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn3.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn2.setBackgroundColor(2);
                imgBtn1.setBackgroundColor(2);
                imgBtn4.setBackgroundColor(2);
                imgBtn5.setBackgroundColor(2);
                imgBtn6.setBackgroundColor(2);
                ftPerfil=imgPerfil.get(2).getImagen();
                System.out.println("2");
            }
        });

        imgBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn4.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn2.setBackgroundColor(2);
                imgBtn3.setBackgroundColor(2);
                imgBtn1.setBackgroundColor(2);
                imgBtn5.setBackgroundColor(2);
                imgBtn6.setBackgroundColor(2);
                ftPerfil=imgPerfil.get(3).getImagen();
                System.out.println("3");
            }
        });

        imgBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn5.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn2.setBackgroundColor(2);
                imgBtn3.setBackgroundColor(2);
                imgBtn4.setBackgroundColor(2);
                imgBtn1.setBackgroundColor(2);
                imgBtn6.setBackgroundColor(2);
                ftPerfil=imgPerfil.get(4).getImagen();
                System.out.println("4");
            }
        });

        imgBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn6.setBackgroundColor(Color.rgb(255, 255, 0));
                imgBtn2.setBackgroundColor(2);
                imgBtn3.setBackgroundColor(2);
                imgBtn4.setBackgroundColor(2);
                imgBtn5.setBackgroundColor(2);
                imgBtn1.setBackgroundColor(2);
                ftPerfil=imgPerfil.get(5).getImagen();
                System.out.println("5");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        return v;
    }

    private void eleccion(ImageButton imgBbtn){
        imgBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBbtn.getDrawingCache();
                System.out.println();
                imgBbtn.setBackgroundColor(15566);
            }
        });
    }


    public void register(){
        String nombre = name.getText().toString();
        String correo= email.getText().toString();
        String contraseña =password.getText().toString();
        String contReapeat =repeatpass.getText().toString();
        String fechaNacimiento=fecha_nacimiento.getText().toString();
        String fotoPerfil= ftPerfil;


        if(!nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty()){
            if (isEmailValid(correo)){
                if(contraseña.equals(contReapeat)) {
                    if (password.length() > 5) {
                        createUser(nombre, correo, contraseña,fechaNacimiento,fechaNacimiento);
                    } else {
                        Toast.makeText(getContext(), "tu aja aja es muuy pequeña ;)", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "la contraseña debe ser igual en los dos campos", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getContext(),"Ese correo esta mal bb", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(),"No puedes dejar el campo nombre, email, y contrasela vacio", Toast.LENGTH_LONG).show();
        }
    }

    private void createUser(String nombre, String correo, String password, String fechaNacimiento, String fotoperfil) {
        mAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String id = mAuth.getCurrentUser().getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("name",nombre);
                    map.put("email",correo);
                    map.put("password", password);
                    map.put("fechaNacimiento", fechaNacimiento);
                    map.put("fotoPerfil", fotoperfil);
                    mFirestor.collection("Users").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                //Intent i = new Intent(RegisterFragment.this,LoginActivity.class);
                                FragmentManager fragmentManager = getParentFragmentManager();

                                    // Verificar si hay fragmentos en la pila de retroceso
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    // Eliminar la transacción más reciente de la pila de retroceso y regresar al fragmento anterior
                                    fragmentManager.popBackStack();
                                    Toast.makeText(getContext(),"El usuario se registro correctamente", Toast.LENGTH_LONG).show();
                                } else {
                                    // No hay más fragmentos en la pila de retroceso
                                    // En este caso, puedes cerrar la actividad o salir de la aplicación
                                    // utilizando el método finish() o System.exit(0)
                                    System.out.println("se mamo");
                                }
                            }else{
                                Toast.makeText(getContext(),"Esto se fue a la puta", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
    }

    //de ajajas
    public boolean isEmailValid(String correo){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
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