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
import com.example.home.tempocontrol.Datos.D_Mesa;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistrarMesa extends AppCompatActivity implements View.OnClickListener {

    private EditText txtMesa;
    private Button btnRegistrar;
    private Button btnVolver;
    private GridView dgvMesa;
    DBHandler handler;
    SQLiteDatabase localDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mesa);
        txtMesa = (EditText) findViewById(R.id.txtDescripcionMesa);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarMesa);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdeMesa);
        dgvMesa = (GridView) findViewById(R.id.dgvMesas);
        handler = new DBHandler(RegistrarMesa.this);
        localDB = handler.getWritableDatabase();
        llenarGrid();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesa = txtMesa.getText().toString();
                if(!mesa.equals(""))
                {
                    localDB.execSQL(D_Mesa._Insert(mesa));
                    localDB.close();
                    llenarGrid();
                    txtMesa.setText("");
                    vToast("Mesa insertada!");
                }else
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
        ArrayList<String> lista =  new ArrayList<>();
        localDB = handler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Mesa._Select(),null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvMesa.setAdapter(adapter);
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
