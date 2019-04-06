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
import com.example.home.tempocontrol.Datos.D_Motivo;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistrarMotivo extends AppCompatActivity implements View.OnClickListener{

    private EditText txtMotivo;
    private Button btnRegistrar;
    private Button btnVolver;
    private GridView dgvMotivos;
    DBHandler dbHandler;
    SQLiteDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_motivo);
        txtMotivo = (EditText) findViewById(R.id.txtDescripcionMotivo);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarMotivo);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdeMotivo);
        dgvMotivos = (GridView) findViewById(R.id.dgvMotivo);

        dbHandler = new DBHandler(RegistrarMotivo.this);
        localDB = dbHandler.getWritableDatabase();
        llegarGrid();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String motivo = txtMotivo.getText().toString();
                if(!motivo.equals(""))
                {
                    localDB.execSQL(D_Motivo.insertar(motivo));
                    vToast("Motivo insertado!");
                    llegarGrid();
                    txtMotivo.setText("");
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

    private void llegarGrid()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = dbHandler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_Motivo._Select(),null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter;
        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvMotivos.setAdapter(adapter);
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
