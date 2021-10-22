package com.example.segundo_parcial_daniel_larin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainCLActivity extends AppCompatActivity {

    private EditText ed_nombre_propietario;
    private EditText ed_numero_propietario;
    private Button btn_registrar;
    public static String FILE_PROPIETARIO="configpropietario";
    private SharedPreferences shared_preferences_propietario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clactivity);

        Intent intent = new Intent(MainCLActivity.this, ContastoCL.class);
        if(validar()){
            //Toast.makeText(MainCLActivity.this, "Ya existe el archivo", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainCLActivity.this, "No existe el archivo", Toast.LENGTH_LONG).show();
        }
        ed_nombre_propietario = (EditText) findViewById(R.id.edt_nombre_propietario);
        ed_numero_propietario = (EditText) findViewById(R.id.edt_numero_propietario);
        btn_registrar = (Button) findViewById(R.id.bt_registrar_propietario);


        btn_registrar.setOnClickListener(view -> {
            registrar_propietario();
        });

    }
    public void registrar_propietario(){
        if(!ed_nombre_propietario.getText().toString().isEmpty() && !ed_numero_propietario.getText().toString().isEmpty()){
            Intent intent = new Intent(MainCLActivity.this, ContastoCL.class);
            shared_preferences_propietario = getSharedPreferences(FILE_PROPIETARIO, MODE_PRIVATE);
            SharedPreferences.Editor edit_config_propietario = shared_preferences_propietario.edit();
            edit_config_propietario.putString("nombre", ed_nombre_propietario.getText().toString());
            edit_config_propietario.putString("numero", ed_numero_propietario.getText().toString());
            edit_config_propietario.commit();
            Toast.makeText(MainCLActivity.this, "Propietario registrado", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainCLActivity.this, "Por favor llene los campos", Toast.LENGTH_LONG).show();
        }
    }
    private boolean validar(){
        boolean  b= false;
        File f = new File("/data/data/com.example.segundo_parcial_daniel_larin/shared_prefs/configpropietario.xml");
        if(f.exists()){
            b = true;
        }
        return b;
    }
}