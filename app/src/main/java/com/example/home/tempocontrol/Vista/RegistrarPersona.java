package com.example.home.tempocontrol.Vista;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.home.tempocontrol.Datos.DBHandler;
import com.example.home.tempocontrol.Datos.D_Persona;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistrarPersona extends AppCompatActivity implements View.OnClickListener{

    private EditText txtDni;
    private EditText txtNombres;
    private EditText txtApellidos;
    private Button btnRegistro;
    private Button btnVolver;
    private Button btnCargarPersonas;
    private GridView dgvPersona;
    DBHandler dbHandler;
    SQLiteDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_persona);
        txtDni = (EditText) findViewById(R.id.txtDni);
        txtNombres = (EditText) findViewById(R.id.txtNombres);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        btnRegistro = (Button) findViewById(R.id.btnRegistraPersona);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdePersona);
        dgvPersona = (GridView) findViewById(R.id.dgvPersonaLista);
        btnCargarPersonas = (Button) findViewById(R.id.btnCargarDatos);
        dbHandler =  new DBHandler(RegistrarPersona.this);
        localDB = dbHandler.getWritableDatabase();

        llenarGrid();
         btnRegistro.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String dni = txtDni.getText().toString();
                 String nombre = txtNombres.getText().toString();
                 String apellidos = txtApellidos.getText().toString();
                 if(!dni.equals("") && !nombre.equals("") && !apellidos.equals(""))
                 {
                     localDB.execSQL(D_Persona._Insert(dni, nombre, apellidos));
                     txtDni.setText("");
                     txtNombres.setText("");
                     txtApellidos.setText("");
                     llenarGrid();
                     vToast("Persona Insertado!");
                 }
                 else
                 {
                     vToast("Ingrese datos!");
                 }
             }
         });

        btnCargarPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datos[][] = new String[646][3];
                datos = D_Persona._CargarDatosPersonas();
                String dni = "";
                String nombre = "";
                String apellidos = "";
                for (int i = 0; i <= 646; i++)
                {
                    dni = datos[i][0].toString();
                    nombre = datos[i][1].toString();
                    apellidos = datos[i][2].toString();
                    localDB.execSQL(D_Persona._Insert(dni, nombre, apellidos));

                }
                llenarGrid();
            }
        });

    }


    private void vToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void llenarGrid()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB =  dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Persona._Select(), null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
                lista.add(registros.getString(2));
                lista.add(registros.getString(3));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvPersona.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

    }

    public void volverMenu(View v)
    {
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
    }
}
