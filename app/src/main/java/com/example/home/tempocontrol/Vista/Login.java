package com.example.home.tempocontrol.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.tempocontrol.R;

public class Login extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPasword);
        btnInsertar = (Button) findViewById(R.id.btnIngresar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicioSesion();
            }
        });

    }

    private void inicioSesion()
    {
        String usuario = txtUsuario.getText().toString();
        String pass = txtPassword.getText().toString();
        if(usuario.equals("")|| pass.equals(""))
        {
            Toast.makeText(this, "Ingrese Usuario y/o Contraseña", Toast.LENGTH_SHORT).show();
        }
        else if(usuario.equals("JESCALAYA")&& pass.equals("jossip123"))
        {
            Intent intent = new Intent(this, Principal.class);
            intent.putExtra("Usuario", usuario);
            startActivity(intent);

        }
        else if(usuario.equals("CONTROL") && pass.equals("1234"))
        {
            Intent intent = new Intent(this, Principal.class);
            intent.putExtra("Usuario", usuario);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Error de inicio de sesión", Toast.LENGTH_SHORT).show();
        }
    }

}
