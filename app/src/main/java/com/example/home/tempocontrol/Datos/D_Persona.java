package com.example.home.tempocontrol.Datos;

import com.example.home.tempocontrol.Vista.RegistraUbicaciones;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_Persona {
    private static final String TABLA = "Persona";
    private static final String INT_TYPE = "integer";
    private static final String STRING_TYPE = "text";

    private static final String COLUMN_ID = "Persona_ID";
    private static final String COLUMN_DNI = "Persona_DNI";
    private static final String COLUMN_NOMBRES = "Persona_Nombres";
    private static final String COLUMN_APELLIDOS = "Persona_Apellidos";

    public static final String T_PERSONA = "create table " + TABLA
            + "(" + COLUMN_ID + " " + INT_TYPE + " primary key autoincrement, "
            + COLUMN_DNI + " " + STRING_TYPE + " not null, "
            + COLUMN_NOMBRES + " " + STRING_TYPE + " not null, "
            + COLUMN_APELLIDOS + " " + STRING_TYPE + " not null);";

    public static  final String EXISTS_TABLE = "drop table if exists " + TABLA;

    public static String _Insert(String dni, String nombres, String apellidos)
    {
        String insert =  "INSERT INTO " + TABLA + " values(null, '" + dni + "', '" + nombres +  "', '" + apellidos + "');";
        return insert;
    }

    public static String _Update(int id, String dni, String nombres, String apellidos)
    {
        String update = "UPDATE " + TABLA
                + "SET " + COLUMN_DNI + "= '" + dni
                + "', " + COLUMN_NOMBRES + "= '" + nombres
                + "', " + COLUMN_APELLIDOS + "='" + apellidos
                + "' WHERE " + COLUMN_ID + "=" + id + ";";
        return  update;
    }

    public static String _Delete(int id)
    {
        return "DELETE FROM " + TABLA + " WHERE " + COLUMN_ID + " = " + id;
    }

    public static String _Select()
    {
        String select = "SELECT " + COLUMN_ID + ", " + COLUMN_DNI + ", " + COLUMN_NOMBRES + ", " + COLUMN_APELLIDOS + " FROM " + TABLA;
        return select;
    }

    public static String _SelectPersona(int dni)
    {
        String select = "SELECT Persona_Nombres || ' '|| Persona_Apellidos  FROM " + TABLA
                 + " WHERE Persona_DNI = " + dni;
        return select;
    }

    public static String[][] _CargarDatosPersonas()
    {
        String datos[][] = new String[647][3];
        datos[0][0]="00000000";	datos[0][1]="xxxxx";	datos[0][2]="xxxxx";
        datos[1][0]="11111111";	datos[1][1]="yyyyy";	datos[1][2]="yyyy";

        return datos;
    }

}
