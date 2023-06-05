package com.example.recycleruve.db;

public class EstructuraBBDD {
    //definimos la tabla

    public static final String TABLA_DATOS_PERSONALES = "datosPersonales";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_PASS = "password";



    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + EstructuraBBDD.TABLA_DATOS_PERSONALES + " (" +
            EstructuraBBDD.COLUMN_ID + " INTEGER PRIMARY KEY," +
            EstructuraBBDD.COLUMN_NAME + " TEXT," +
            EstructuraBBDD.COLUMN_PASS + " TEXT)";

    // Define que columns de la database de la query
    public static final String[] projection = {
            EstructuraBBDD.COLUMN_NAME,
            EstructuraBBDD.COLUMN_PASS
    };

    // Ordenar resultados
    public static final String sortOrder =
            EstructuraBBDD.COLUMN_PASS + " DESC";

    // Filtra resultados WHERE "title" = 'My Title'
    //modifique esta vayna para mi condicion de consulta sea de nombre y de pass con los datos que yo le pase en el metodo que cree en el main.
    public static final String selection = EstructuraBBDD.COLUMN_NAME+ " = ? AND "+ EstructuraBBDD.COLUMN_PASS +"= ?";
    public static final String[] selectionArgs = { "Nombre" };

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBBDD.TABLA_DATOS_PERSONALES;





}