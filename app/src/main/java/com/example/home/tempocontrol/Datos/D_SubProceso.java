package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_SubProceso {
    private static final String TABLA = "SubProceso";

    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";

    private static final String COLUMN_Id = "SubProceso_ID";
    private static final String COLUMN_Descripcion  = "SubProceso_Descripcion";

    public static final String T_SubProceso = "create table  " + TABLA
            + "(" + COLUMN_Id + " " + INT_TYPE + " primary key autoincrement,"
            + COLUMN_Descripcion + " " + STRING_TYPE + " not null)";

    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;

    public static String _Insert(String subProceso)
    {
        String insert = "INSERT INTO " + TABLA + " VALUES(null, '" + subProceso + "');";
        return insert;
    }

    public static String _Update(int id, String subProceso)
    {
        String update = "UPDATE " + TABLA
                + " SET " + COLUMN_Descripcion + " = '" + subProceso + "' WHERE "
                + COLUMN_Id + " = " + id + ";";
        return update;
    }

    public static String _Delete(int id)
    {
        return "DELETE FROM " + TABLA + " WHERE " + COLUMN_Id + " = " + id + ";";
    }

    public static String _Select()
    {
        String select = "SELECT " + COLUMN_Id + " , " + COLUMN_Descripcion + " FROM " + TABLA;
        return select;
    }
}
