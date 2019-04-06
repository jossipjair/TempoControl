package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */


public class D_DetalleUbicacion {
    private static final String TABLA = "Detalle_Ubicaciones";

    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";

    private static final String COLUMN_ID = "IdDetalle";
    private static final String COLUMN_PERSONA_ID = "Persona_ID";
    private static final String COLUMN_LINEA_ID = "Linea_ID";
    private static final String COLUMN_MESA_ID = "Mesa_ID";
    private static final String COLUMN_MOTIVO_ID = "Motivo_ID";
    private static final String COLUMN_PROCESO_ID = "Proceso_ID";
    private static final String COLUMN_SUBPROCESO_ID = "SubProceso_ID";
    private static final String COLUMN_LADO_ID = "Lado_ID";
    private static final String COLUMN_FHORA_INICIO = "Fecha_Hora_Inicio";
    private static final String COLUMN_FHORA_FIN = "Fecha_Hora_Fin";
    private static final String COLUMN_USUARIO  = "Usuario";

    private static final String COLUMN_DNI = "Dni";
    private static final String COLUMN_LINEA = "Nomb_Linea";
    private static final String COLUMN_MESA = "Nomb_Mesa";
    private static final String COLUMN_MOTIVO = "Nomb_Motivo";
    private static final String COLUMN_PROCESO = "Nomb_Proceso";
    private static final String COLUMN_SUBPROCESO = "Nomb_SubProceso";
    private static final String COLUMN_LADO = "Nomb_Lado";
    private static final String COLUMN_FECHA = "FechaActual";


    public static String _SelectUbicaciones(String fecha)
    {
        String select = "SELECT Dni, Nomb_Proceso, Nomb_SubProceso, Nomb_Linea, Nomb_Lado, Nomb_Mesa, Nomb_Motivo, FechaActual FROM " + TABLA
                + " WHERE FechaActual like '%" + fecha + "%'";
        return select;
    }

    //SIN USAR
    public static final String T_DETALLE_UBICACION = "create table "
            + TABLA + "(" + COLUMN_ID + " " + INT_TYPE + " primary key autoincrement, "
            + COLUMN_PERSONA_ID +  " " + STRING_TYPE + " FOREIGN KEY (" + COLUMN_PERSONA_ID + ") REFERENCES Persona(" + COLUMN_PERSONA_ID  + ") not null,"
            + COLUMN_LINEA_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_LINEA_ID +  ") REFERENCES Linea(" + COLUMN_LINEA_ID  + ") not null,"
            + COLUMN_MESA_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_MESA_ID + ") REFERENCES Mesa(" + COLUMN_MESA_ID+ ") not null,"
            + COLUMN_MOTIVO_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_MOTIVO_ID + ") REFERENCES Motivo(" + COLUMN_MOTIVO_ID + ") not null,"
            + COLUMN_PROCESO_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_PROCESO_ID + ") REFERENCES  Proceso(" + COLUMN_PROCESO_ID  + ")not null,"
            + COLUMN_SUBPROCESO_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_SUBPROCESO_ID + ") REFERENCES SubProceso(" + COLUMN_SUBPROCESO_ID +") not null,"
            + COLUMN_LADO_ID + " " + STRING_TYPE + " FOREIGN KEY(" + COLUMN_LADO_ID  + ") REFERENCES Lado (" + COLUMN_LADO_ID + ")not null,"
            + COLUMN_FHORA_INICIO + " " + STRING_TYPE + " not null,"
            + COLUMN_FHORA_FIN + " " + STRING_TYPE + " null,"
            + COLUMN_USUARIO + " " + STRING_TYPE + " null);";

    //

    public static final String T_DETALLE_UBICACION_SR = "create table "
            + TABLA + "("
            + COLUMN_ID + " " + INT_TYPE + " primary key autoincrement, "
            + COLUMN_DNI + " " + STRING_TYPE + " not null, "
            + COLUMN_LINEA + " " + STRING_TYPE + " not null, "
            + COLUMN_MESA + " " + STRING_TYPE + " not null, "
            + COLUMN_MOTIVO + " " + STRING_TYPE + " not null, "
            + COLUMN_PROCESO + " " + STRING_TYPE + " not null, "
            + COLUMN_SUBPROCESO + " " + STRING_TYPE + " not null, "
            + COLUMN_LADO + " " + STRING_TYPE + " not null,"
            + COLUMN_FECHA + " " + STRING_TYPE + " not null);";

    public static String _InsertarUbicaciones(String dni, String linea, String mesa, String motivo, String proceso, String subProceso, String lado, String fecha)
    {
        String insert = "INSERT INTO " + TABLA
                + " VALUES (null, '"
                + dni + "','"
                + linea + "','"
                + mesa + "','"
                + motivo + "','"
                + proceso + "','"
                + subProceso +"','"
                + lado + "','"
                + fecha +"');";
        return insert;
    }

    public static final String EXISTS_TABLE = "drop table if exists " + TABLA;


    public static String _Update(int idDetalle, int idPersona, int idLinea, int idMesa, int idMotivo, int idProceso, int idSubProceso, int idLado, String fechaHoraInicio, String fechaHoraFin, String usuario)
    {
        String update = "UPDATE " + TABLA
                + " SET "
                + COLUMN_PERSONA_ID + "='"	+ idPersona + "',"
                + COLUMN_LINEA_ID + "='" + idLinea + "',"
                + COLUMN_MESA_ID + "='" + idMesa + "',"
                + COLUMN_MOTIVO_ID + "='" + idMotivo + "',"
                + COLUMN_PROCESO_ID + "='" + idProceso + "',"
                + COLUMN_SUBPROCESO_ID + "='" + idSubProceso + "',"
                + COLUMN_LADO_ID + "='" + idLado + "',"
                + COLUMN_FHORA_INICIO + "='" + fechaHoraInicio + "',"
                + COLUMN_FHORA_FIN + "='" + fechaHoraFin + "',"
                + COLUMN_USUARIO + "='" + usuario + "' WHERE "
                + COLUMN_ID + "= " + idDetalle +  ";";
        return update;
    }

    public static String _Delete(int idDetalle)
    {
        String delete = "DELETE FROM " + TABLA + " WHERE " + COLUMN_ID + " = " + idDetalle + ";";
        return delete;
    }






    //Sin utilizar
    /*
    public static String _SelectReferenciado(String fecha)
    {
        String select = "SELECT du.IdDetalle AS ID, p.Persona_DNI AS DNI, p.Persona_Nombres AS NOMBRES, p.Persona_Apellidos AS APELLIDOS, "
                + " pro.Proceso_Descripcion AS Proceso, subp.SubProceso_Descripcion AS SubProceso, "
                + " lin.Linea_ID AS Linea, lad.Lado_Descripcion AS Lado, me.Mesa_ID AS Mesa, du.Fecha_Hora_Inicio AS Hora "
                + " FROM Detalle_Ubicacion as du, Persona as p, Linea as li, Mesa as me, Motivo as mot, Proceso as pro, "
                + " SubProceso as subp, Linea as lin, Lado as lad "
                + " WHERE Detalle_Ubicacion.Persona_ID = Persona.Persona_ID "
                + " AND Detalle_Ubicacion.Linea_ID = Linea.Linea_ID"
                + " AND Detalle_Ubicacion.Mesa_ID = Mesa.Mesa_ID "
                + " AND Detalle_Ubicacion.Motivo_ID = Motivo.Motivo_ID "
                + " AND Detalle_Ubicacion.Proceso_ID = Proceso.Proceso_ID "
                + " AND Detalle_Ubicacion.SubProceso_ID = SubProceso.SubProceso_ID "
                + " AND Detalle_Ubicacion.Lado_ID = Lado.Lado_ID "
                + " AND Fecha_Hora_Inicio = " + fecha + ";";
        return select;
    }*/
    //


}
