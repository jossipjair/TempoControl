package com.example.home.tempocontrol.Vista;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.home.tempocontrol.Datos.DBHandler;
import com.example.home.tempocontrol.Datos.D_DetalleUbicacion;
import com.example.home.tempocontrol.Datos.D_Lado;
import com.example.home.tempocontrol.Datos.D_Linea;
import com.example.home.tempocontrol.Datos.D_Mesa;
import com.example.home.tempocontrol.Datos.D_Motivo;
import com.example.home.tempocontrol.Datos.D_Persona;
import com.example.home.tempocontrol.Datos.D_Proceso;
import com.example.home.tempocontrol.Datos.D_SubProceso;
import com.example.home.tempocontrol.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class RegistraUbicaciones extends AppCompatActivity implements View.OnClickListener {

    private EditText txtDni;
    private EditText txtNombresApellidos;
    private Spinner spProceso;
    private Spinner spSubProceso;
    private Spinner spLinea;
    private Spinner spLado;
    private Spinner spMesa;
    private Spinner spMotivos;
    private EditText txtFechaHora;
    private Button btnRegistroUbicacion;
    private Button btnVolver;
    DBHandler dbHandler;
    SQLiteDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_ubicaciones);

        txtDni = (EditText) findViewById(R.id.txtDni);
        txtNombresApellidos = (EditText) findViewById(R.id.txtNombresApellidos);
        spProceso = (Spinner) findViewById(R.id.spProceso);
        spSubProceso = (Spinner) findViewById(R.id.spSubProceso);
        spLinea = (Spinner) findViewById(R.id.spLinea);
        spLado = (Spinner) findViewById(R.id.spLado);
        spMesa = (Spinner) findViewById(R.id.spMesa);
        spMotivos = (Spinner) findViewById(R.id.spMotivo);
        txtFechaHora = (EditText) findViewById(R.id.txtFechaHora);
        btnRegistroUbicacion = (Button) findViewById(R.id.btnRegistrarUbicacion);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdeUbicacion);

        dbHandler = new DBHandler(RegistraUbicaciones.this);
        localDB = dbHandler.getWritableDatabase();
        llenarspProceso();
        llenarspSubProceso();
        llenarspLinea();
        llenarspLado();
        llenarspMesa();
        llenarspMotivos();
        mostrarFechaHoraActual();

        btnRegistroUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = txtDni.getText().toString();
                String proceso = spProceso.getSelectedItem().toString();
                String subProceso = spSubProceso.getSelectedItem().toString();
                String linea = spLinea.getSelectedItem().toString();
                String lado = spLado.getSelectedItem().toString();
                String mesa = spMesa.getSelectedItem().toString();
                String motivo = spMotivos.getSelectedItem().toString();
                String fecha = txtFechaHora.getText().toString();
                if(!dni.equals("") && !fecha.equals(""))
                {
                    localDB.execSQL(D_DetalleUbicacion._InsertarUbicaciones(dni,linea,mesa,motivo,proceso,subProceso,lado,fecha));
                    vToast("Registro salvado!");
                    txtDni.setText("");
                    txtNombresApellidos.setText("");
                    mostrarFechaHoraActual();
                }
                else
                {
                    vToast("Ingrese datos!");
                }

            }
        });

        txtDni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    buscarPersona();
                }catch (Exception ex)
                {

                }
            }
        });

    }

    private void vToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void buscarPersona()
    {
        int dni = Integer.parseInt(txtDni.getText().toString());
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor persona = localDB.rawQuery(D_Persona._SelectPersona(dni), null);
        if(persona.moveToFirst())
        {
            do {
                lista.add(persona.getString(0));
            }while (persona.moveToNext());
        }
        txtNombresApellidos.setText(lista.get(0).toString());
    }

    public void llenarspMotivos()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Motivo._Select(), null);

        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spMotivos.setAdapter(adapter);
    }


    public void llenarspProceso()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Proceso._Select(), null);

        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spProceso.setAdapter(adapter);
    }

    public void llenarspSubProceso()
    {
        ArrayList<String> lista =  new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_SubProceso._Select(),null);

        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        spSubProceso.setAdapter(adapter);
    }

    public void llenarspLinea()
    {
        ArrayList<String> lista =  new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Linea._Select(),null);

        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spLinea.setAdapter(adapter);
    }

    public void llenarspLado()
    {
        ArrayList<String> lista =  new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Lado._Select(),null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spLado.setAdapter(adapter);
    }

    public void llenarspMesa()
    {
        ArrayList<String> lista =  new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Mesa._Select(),null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spMesa.setAdapter(adapter);
    }

    public void mostrarFechaHoraActual()
    {
        String fechaHora = "";
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        fechaHora = dia + "/" + mes + "/" + anio + " " + hora + ":" + minuto + ":" + segundo;
        txtFechaHora.setText(fechaHora);
    }

    public void volverMenu(View v)
    {
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {

    }
}
