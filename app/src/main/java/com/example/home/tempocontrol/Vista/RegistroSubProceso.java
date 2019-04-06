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
import com.example.home.tempocontrol.Datos.D_SubProceso;
import com.example.home.tempocontrol.R;

import java.util.ArrayList;

public class RegistroSubProceso extends AppCompatActivity implements View.OnClickListener {
    private EditText txtSubProceso;
    private Button btnRegistrar;
    private Button btnVolver;
    private GridView dgvSubProceso;

    DBHandler handler;
    SQLiteDatabase localDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sub_proceso);
        txtSubProceso = (EditText) findViewById(R.id.txtDescripcionSubProceso);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarSubProcesos);
        btnVolver = (Button) findViewById(R.id.btnVuelvedesdeSubProceso);
        dgvSubProceso = (GridView) findViewById(R.id.dgvProceso);

        handler = new DBHandler(RegistroSubProceso.this);
        localDB = handler.getWritableDatabase();
        llenarGrid();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subProceso = txtSubProceso.getText().toString();
                if(!subProceso.equals(""))
                {
                    localDB.execSQL(D_SubProceso._Insert(subProceso));
                    vToast("SubProceso Insertado!");
                    txtSubProceso.setText("");
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

    private void llenarGrid()
    {
        ArrayList<String> lista = new ArrayList<>();
        localDB = handler.getWritableDatabase();
        Cursor registros = localDB.rawQuery(D_SubProceso._Select(), null);
        if(registros.moveToFirst())
        {
            do {
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        dgvSubProceso.setAdapter(adapter);
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
