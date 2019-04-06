package com.example.home.tempocontrol.Vista;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.home.tempocontrol.Datos.DBHandler;
import com.example.home.tempocontrol.Datos.D_Lado;
import com.example.home.tempocontrol.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Registralado extends AppCompatActivity implements View.OnClickListener {

    private EditText txtLadoDescripcion;
    private Button btnInsertar;
    private Button btnVolver;
    private GridView dgvLados;

    DBHandler dbHand;
    SQLiteDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lado);

        txtLadoDescripcion = (EditText) findViewById(R.id.txtDescripcionLado);
        btnInsertar = (Button) findViewById(R.id.btnRegistraLado);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdeLado);
        dgvLados = (GridView) findViewById(R.id.dgvLado);

        dbHand = new DBHandler(Registralado.this);
        localDB = dbHand.getWritableDatabase();
        llenar_lstv();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ladoDescripcion = txtLadoDescripcion.getText().toString();
                if(!ladoDescripcion.equals("")) {
                    localDB.execSQL(D_Lado.insertar(ladoDescripcion));
                    vToast("Registro Insertado!");
                    txtLadoDescripcion.setText("");
                    llenar_lstv();
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

    private void llenar_lstv()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHand.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Lado._Select(), null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvLados.setAdapter(adapter);
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
