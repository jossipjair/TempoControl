package com.example.home.tempocontrol.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.home.tempocontrol.R;

public class Principal extends AppCompatActivity implements View.OnClickListener  {

    private Button btnRegistrarProcesos;
    private Button btnRegistrarSubProceso;
    private Button btnRegistrarLinea;
    private Button btnRegistrarLado;
    private Button btnRegistrarMesa;
    private Button btnRegistrarMotivo;
    private Button btnRegistrarPersona;
    private Button btnRegistroUbicaciones;
    private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnRegistrarProcesos = (Button)findViewById(R.id.btnRegistroProceso);
        btnRegistrarSubProceso = (Button) findViewById(R.id.btnRegistraSubProceso);
        btnRegistrarLinea = (Button) findViewById(R.id.btnRegistrarLinea);
        btnRegistrarLado = (Button) findViewById(R.id.btnRegistrarLado);
        btnRegistrarMesa = (Button) findViewById(R.id.btnRegistrarMesa);
        btnRegistrarMotivo = (Button) findViewById(R.id.btnRegistrarMotivo);
        btnRegistrarPersona = (Button) findViewById(R.id.btnRegistrarPersona);
        btnRegistroUbicaciones = (Button) findViewById(R.id.btnRegistrarUbicaciones);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            String usuario = (String) bundle.get("Usuario");
            if(usuario.equals("CONTROL"))
            {
                btnRegistrarProcesos.setVisibility(View.INVISIBLE);
                btnRegistrarSubProceso.setVisibility(View.INVISIBLE);
                btnRegistrarLinea.setVisibility(View.INVISIBLE);
                btnRegistrarLado.setVisibility(View.INVISIBLE);
                btnRegistrarMesa.setVisibility(View.INVISIBLE);
                btnRegistrarMotivo.setVisibility(View.INVISIBLE);
                btnRegistrarPersona.setVisibility(View.VISIBLE);
                btnRegistroUbicaciones.setVisibility(View.VISIBLE);
                btnSalir.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public void onClick(View view) {

    }

    public void llamaProceso(View v)
    {
        Toast.makeText(this, "REGISTRA PROCESO", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistroProceso.class);
        startActivity(intent);
    }

    public void llamaSubProceso(View v)
    {
        Toast.makeText(this, "Registrar SubProceso", Toast.LENGTH_SHORT).show();
        Intent intent =  new Intent(this, RegistroSubProceso.class);
        startActivity(intent);
    }

    public void llamaLinea(View v)
    {
        Toast.makeText(this, "Registrar Línea", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistrarLinea.class);
        startActivity(intent);
    }

    public void llamarLado(View v)
    {
        Toast.makeText(this, "Registrar Lado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Registralado.class);
        startActivity(intent);
    }

    public void llamarMesa(View v)
    {
        Toast.makeText(this, "Registrar Mesas", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistrarMesa.class);
        startActivity(intent);
    }
    
    public void llamarMotivo(View v)
    {
        Toast.makeText(this, "Registrar Motivo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistrarMotivo.class);
        startActivity(intent);
    }

    public void llamarPersona(View v)
    {
        Toast.makeText(this, "Registrar Persona", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistrarPersona.class);
        startActivity(intent);
    }

    public void llamarUbicaciones(View v)
    {
        Toast.makeText(this,"Registro de Ubicaciones", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegistraUbicaciones.class);
        startActivity(intent);
    }

    public void llamarListaUbicaciones(View v)
    {
        Toast.makeText(this,"Listado de Ubicaciones", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MostrarUbicaciones.class);
        startActivity(intent);
    }

    public void salir(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
