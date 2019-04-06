package com.example.home.tempocontrol.Vista;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.home.tempocontrol.Datos.DBHandler;
import com.example.home.tempocontrol.Datos.D_DetalleUbicacion;
import com.example.home.tempocontrol.Datos.D_Persona;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MostrarUbicaciones extends AppCompatActivity {

    private GridView dgvMostrarUbicaciones;
    private EditText txtFechaReporte;
    private Button btnGeneraReporte;
    DBHandler dbHandler;
    SQLiteDatabase localDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_mostrar_ubicaciones);
        dgvMostrarUbicaciones = (GridView) findViewById(R.id.dgvListaUbicaciones);
        txtFechaReporte = (EditText) findViewById(R.id.txtFechaReporte);
        btnGeneraReporte = (Button) findViewById(R.id.btnReporte);
        mostrarFechaActual();
        dbHandler = new DBHandler(MostrarUbicaciones.this);
        localDB = dbHandler.getWritableDatabase();
        btnGeneraReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarGrid();
            }
        });
    }

    private void llenarGrid()
    {
        String fecha = txtFechaReporte.getText().toString();
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_DetalleUbicacion._SelectUbicaciones(fecha), null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
                lista.add(registros.getString(2));
                lista.add(registros.getString(3));
                lista.add(registros.getString(4));
                lista.add(registros.getString(5));
                lista.add(registros.getString(6));
                lista.add(registros.getString(7));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvMostrarUbicaciones.setAdapter(adapter);
    }

    public void mostrarFechaActual()
    {
        String fechaHora = "";
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        fechaHora = dia + "/" + mes + "/" + anio;
        txtFechaReporte.setText(fechaHora);
    }




}
