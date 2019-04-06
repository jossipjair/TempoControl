package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Motivo {

    private static final String TABLA = "Motivo";
    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";
    private static final String COLUMN_Id = "Motivo_ID";
    private static final String COLUMN_Descripcion = "Motivo_Descripcion";

    public static final String T_MOTIVO = "create table  " + TABLA
            + "(" + COLUMN_Id + " " + INT_TYPE + " primary key autoincrement,"
            + COLUMN_Descripcion + " " + STRING_TYPE + " not null)";

    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;


    public static String insertar(String motivo)
    {
        String insertarLocal = "INSERT INTO " + TABLA + " VALUES (null, '" + motivo + "');";
        return  insertarLocal;
    }

    public static String update(Integer id, String motivo)
    {
        String actualiza =  "UPDATE "
                + TABLA + " SET "
                + COLUMN_Descripcion + " = '"
                + motivo + "' WHERE "
                + COLUMN_Id + " = " + id + ";";
        return actualiza;
    }

    public static String _Select()
    {
        return "SELECT " + COLUMN_Id + " , " +  COLUMN_Descripcion + " FROM " + TABLA;
    }

    public static String _Delete(Integer id)
    {
        String elimina = "DELETE FROM " + TABLA + " WHERE " + COLUMN_Id + " = " + id + ";";
        return elimina;
    }

}
