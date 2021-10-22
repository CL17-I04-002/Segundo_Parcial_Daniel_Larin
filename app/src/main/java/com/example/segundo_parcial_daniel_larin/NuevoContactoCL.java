package com.example.segundo_parcial_daniel_larin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoCL;
import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoImpRoomCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

public class NuevoContactoCL extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView txtContacto;
    private Button btnGuardarContacto;

    private EditText ed_nombre;
    private EditText ed_numero;
    private EditText ed_propietario;


    private ContactoDaoCL dao;

    private Contacto_CL contacto;

    private int estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto_cl);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        estado=0;
        Intent intent =new Intent(this,MainCLActivity.class);
        dao=new ContactoDaoImpRoomCL(getApplicationContext());

        //instancias a los componentes graficos
        this.ed_nombre=(EditText)findViewById(R.id.edt_nombre);
        this.ed_numero=(EditText)findViewById(R.id.edt_numero);
        this.ed_propietario=(EditText)findViewById(R.id.edt_nombre_property);
       /// this.txtContacto=(TextView) findViewById(R.id.txt_nuevo_contacto);
        this.btnGuardarContacto=(Button) findViewById(R.id.btnGuardarContacto);

        sharedPreferences = getSharedPreferences(MainCLActivity.FILE_PROPIETARIO, MODE_PRIVATE);
        ed_propietario.setText(sharedPreferences.getString("nombre", ""));

        cargar();



        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estado==0) {
                        guardar();
                }
                ///estado = 1
                else {
                        actualizar();

                    }

                reset();
            ///Ejecutamos el redireccionamiento del activity
            startActivity(intent);

            }
        });
    }

    public boolean validarCamposVacios(){
        boolean valor=false;
        if(!(ed_nombre.getText().toString().isEmpty()) && (ed_numero.getText().toString().isEmpty()) && (ed_propietario.getText().toString().isEmpty())){
            valor=true;
        }
        return valor;
    }
    public void  guardar(){
            contacto = new Contacto_CL();
            //contacto.setId(0);
            contacto.setNombre(ed_nombre.getText().toString());
            contacto.setNumero(ed_numero.getText().toString());
            contacto.setPropietario(ed_propietario.getText().toString());
            dao.save(contacto);
        }

    public void actualizar(){
        contacto.setNombre(ed_nombre.getText().toString());
        contacto.setNumero(ed_numero.getText().toString());
        contacto.setPropietario(ed_propietario.getText().toString());
        dao.update(contacto);
    }


    public void  reset(){
        ed_nombre.setText("");
        ed_numero.setText("");
        ///ed_propietario.setText("");
        btnGuardarContacto.setText("Guardar");
    }
    ///Este método para saber si el estado es modificar  1 o 0 (default) por medio de getIntExtra
    public void cargar(){
        Intent intent;
        try {

            intent=getIntent();
            contacto=(Contacto_CL) intent.getSerializableExtra("contacto");
            ed_nombre.setText(contacto.getNombre());
            contacto = (Contacto_CL) intent.getSerializableExtra("numero");
            ed_numero.setText(contacto.getNumero());
            contacto = (Contacto_CL) intent.getSerializableExtra("propietario");
            ed_propietario.setText(contacto.getPropietario());
            estado=intent.getIntExtra("estado",0);


            btnGuardarContacto.setText("Actualizar");

            ///Si hay una excepción, se captura acá
        }catch (Exception e){
            estado=0;

        }
    }
}