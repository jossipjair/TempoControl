package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Linea {
    private static final String TABLA = "Linea";
    private static final String INT_TYPE = "integer";
    private static final String STRING_TYPE = "text";

    private static final String COLUMN_Id = "Linea_ID";
    private static final String COLUMN_Descripcion = "Linea_Descripcion";

    public static final String T_Linea = "create table " + TABLA + "(" + COLUMN_Id + " " +
                                        INT_TYPE + " primary key autoincrement," +
                                        COLUMN_Descripcion + " " + STRING_TYPE + ");";
    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;

    public static String _Insert(String linea)
    {
        String insert = "INSERT INTO " + TABLA + " values(null, '" + linea + "');";
        return insert;
    }

    public static String  _Update(int id, String linea)
    {
        String update = "UPDATE " + TABLA
                        + " SET " + COLUMN_Descripcion + " = '" + linea + "' WHERE "
                        + COLUMN_Id + " = " + id + ";";

        return update;
    }

    public static String _Delete(int id)
    {
        return "DELETE FROM " + TABLA + " WHERE " + COLUMN_Id + " = " + id + ";";
    }

    public  static String _Select()
    {
        return "SELECT " + COLUMN_Id + ", " + COLUMN_Descripcion + " FROM " + TABLA;
    }
}
