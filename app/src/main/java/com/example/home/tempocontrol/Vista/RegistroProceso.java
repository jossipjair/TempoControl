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
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.tempocontrol.Datos.DBHandler;
import com.example.home.tempocontrol.Datos.D_Proceso;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistroProceso extends AppCompatActivity implements View.OnClickListener{

    private EditText txtDescripcion;
    private Button btnInsertar;
    private Button btnVolver;
    private GridView dgvProceso;

    DBHandler dbHand;
    SQLiteDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_proceso);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcionProceso);
        btnInsertar = (Button) findViewById(R.id.btnRegistrarProceso);
        btnVolver = (Button) findViewById(R.id.btnVolver);
        dgvProceso = (GridView) findViewById(R.id.dgvProceso);

        dbHand = new DBHandler(RegistroProceso.this);
        localDB = dbHand.getWritableDatabase();
        llenarGrid();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = txtDescripcion.getText().toString();
                if(!descripcion.equals(""))
                {
                    localDB.execSQL(D_Proceso._Insert(descripcion));
                    vToast("Proceso insertado!");
                    txtDescripcion.setText("");
                    llenarGrid();
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
    @Override
    public void onClick(View view) {

    }

    private void llenarGrid()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHand.getWritableDatabase();
        Cursor registro = localDB.rawQuery(D_Proceso._Select(), null);
        if(registro.moveToFirst())
        {
            do {
                lista.add(registro.getString(0));
                lista.add(registro.getString(1));
            }while (registro.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvProceso.setAdapter(adapter);
    }

    public void volverMenu(View v)
    {
        Intent intent = new Intent(this,Principal.class);
        startActivity(intent);
    }
}
