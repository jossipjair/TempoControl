package com.example.home.tempocontrol.Datos;

/**
 * Created by jossip on 12/01/2018.
 */

// =)

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {


    public static final String DB_NAME = "dbMapeoPersonal.db";
    public static final int DB_VERSION = 1;

    public DBHandler(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación de tablas en el DBHELPER
        sqLiteDatabase.execSQL(D_Lado.T_LADO);
        sqLiteDatabase.execSQL(D_Proceso.T_Proceso);
        sqLiteDatabase.execSQL(D_SubProceso.T_SubProceso);
        sqLiteDatabase.execSQL(D_Linea.T_Linea);
        sqLiteDatabase.execSQL(D_Motivo.T_MOTIVO);
        sqLiteDatabase.execSQL(D_Mesa.T_MESAS);
        sqLiteDatabase.execSQL(D_Persona.T_PERSONA);
        sqLiteDatabase.execSQL(D_DetalleUbicacion.T_DETALLE_UBICACION_SR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(D_Lado.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_Proceso.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_SubProceso.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_Linea.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_Motivo.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_Mesa.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_Persona.EXISTS_TABLE);
        sqLiteDatabase.execSQL(D_DetalleUbicacion.EXISTS_TABLE);
        onCreate(sqLiteDatabase);
    }
}
