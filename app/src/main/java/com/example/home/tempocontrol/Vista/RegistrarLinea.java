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
import com.example.home.tempocontrol.Datos.D_Linea;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistrarLinea extends AppCompatActivity implements View.OnClickListener {

    private EditText txtLinea;
    private Button btnRegistrar;
    private Button btnSalir;
    private GridView dgvLineas;
    DBHandler dbHandler;
    SQLiteDatabase localDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_linea);
        txtLinea = (EditText) findViewById(R.id.txtDescripcionLinea);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarLinea);
        btnSalir = (Button) findViewById(R.id.btnVuelvedesdeLinea);
        dgvLineas = (GridView) findViewById(R.id.dgvLinea);

        dbHandler = new DBHandler(RegistrarLinea.this);
        localDB = dbHandler.getWritableDatabase();
        llenarGrid();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linea = txtLinea.getText().toString();
                if(!linea.equals(""))
                {
                    localDB.execSQL(D_Linea._Insert(linea));
                    vToast("Linea Insertada!");
                    llenarGrid();
                    txtLinea.setText("");
                }
                else
                {
                    vToast("Ingrese dato!");
                }
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
        localDB = dbHandler.getWritableDatabase();
        Cursor registro = localDB.rawQuery(D_Linea._Select(), null);
        if(registro.moveToFirst())
        {
            do {
                lista.add(registro.getString(0));
                lista.add(registro.getString(1));
            }while (registro.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvLineas.setAdapter(adapter);
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
