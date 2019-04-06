package com.example.home.tempocontrol.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Lado {

    private static final String TABLA = "Lado";

    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";
    private static final String COLUMN_Id = "Lado_ID";
    private static final String COLUMN_Descripcion = "Lado_Descripcion";

    //Creación de tabla
    public static final String T_LADO = "create table  " + TABLA
            + "(" + COLUMN_Id + " " + INT_TYPE + " primary key autoincrement,"
            + COLUMN_Descripcion + " " + STRING_TYPE + " not null)";

    //Eliminar Tabla si existe en la base de Datos
    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;

    public static String insertar(String lado)
    {
        String insertarLocal = "INSERT INTO " + TABLA + " VALUES (null, '" + lado + "');";
        return  insertarLocal;
    }

    public static String update(Integer id, String lado)
    {
        String actualiza =  "UPDATE "
                            + TABLA + " SET "
                            + COLUMN_Descripcion + " = '"
                            + lado + "' WHERE "
                            + COLUMN_Id + " = " + id + ";";
        return actualiza;
    }

    public static String _Select()
    {
        return "SELECT " + COLUMN_Id + " , " +  COLUMN_Descripcion + " FROM " + TABLA;
    }

    public static String delete(Integer id)
    {
        String elimina = "DELETE FROM " + TABLA + " WHERE " + COLUMN_Id + " = " + id + ";";
        return elimina;
    }



}
