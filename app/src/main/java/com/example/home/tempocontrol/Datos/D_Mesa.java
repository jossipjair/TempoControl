package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Mesa {
    private static final String TABLA = "Mesa";
    private static final String INT_TYPE = "integer";
    private static final String STRING_TYPE = "text";

    private static final String COLUMN_Id = "Mesa_ID";
    private static final String COLUMN_Descripcion = "Mesa_Descripcion";

    public static final String T_MESAS = "create table " + TABLA + "(" + COLUMN_Id + " " +
            INT_TYPE + " primary key autoincrement," +
            COLUMN_Descripcion + " " + STRING_TYPE + ");";

    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;

    public static String _Insert(String mesa)
    {
        String insert = "INSERT INTO " + TABLA + " values(null, '" + mesa + "');";
        return insert;
    }

    public static String  _Update(int id, String mesa)
    {
        String update = "UPDATE " + TABLA
                + " SET " + COLUMN_Descripcion + " = '" + mesa + "' WHERE "
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
