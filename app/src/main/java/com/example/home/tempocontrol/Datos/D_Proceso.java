package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Proceso {

    private static final String TABLA = "Proceso";

    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";

    private static final String COLUMN_ID = "Proceso_ID";
    private static final String COLUMN_Descripcion = "Proceso_Descripcion";

    public static final String T_Proceso = "create table " + TABLA
                        + "(" + COLUMN_ID + " " + INT_TYPE + " primary key autoincrement, "
                        + COLUMN_Descripcion + " " + STRING_TYPE + " not null);";

    public static final String EXISTS_TABLE = "drop table if exists " + TABLA + ";";

    public static String _Insert(String proceso)
    {
        String insert = "INSERT INTO " + TABLA + " VALUES(null, '" + proceso + "');";
        return insert;
    }

    public static String _Update(int id, String proceso)
    {
        String update = "UPDATE " + TABLA
                        + " SET " + COLUMN_Descripcion + " = '"
                        + proceso + "' WHERE " + COLUMN_ID + " = " + id + ";";
        return update;
    }

    public static String _Delete(int id)
    {
        String delete = "DELETE FROM " + TABLA + " WHERE " + COLUMN_ID + " = " + id + ";";
        return delete;
    }

    public static String _Select()
    {
        String select = "SELECT " + COLUMN_ID + " , " + COLUMN_Descripcion + " FROM " + TABLA + ";";
        return select;
    }

}
