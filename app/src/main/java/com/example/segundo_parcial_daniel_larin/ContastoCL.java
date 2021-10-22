package com.example.segundo_parcial_daniel_larin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.segundo_parcial_daniel_larin.adapterCL.ContactoAdapterCL;
import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoCL;
import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoImpRoomCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContastoCL extends AppCompatActivity {

    private FloatingActionButton btnNuevoContacto;
    private RecyclerView rwContacto;

    List<Contacto_CL> lstContacto;

    ContactoDaoCL dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contasto_cl);

        ///Inicializando la interfaz en este caso el orígen es base de datos
        dao=new ContactoDaoImpRoomCL(getApplicationContext());

        Intent intent = new Intent(this, NuevoContactoCL.class);
        //instancias a componentes graficos
        this.btnNuevoContacto=(FloatingActionButton) findViewById(R.id.btnNuevo_contacto);
        this.rwContacto=(RecyclerView) findViewById(R.id.rw_contactos);


                //Evento del botón flotante
        this.btnNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///redirecciona al activity NuevoTareaActivity
                startActivity(intent);
            }
        });

        cargarDatos();
        ContactoAdapterCL adapterCL = new ContactoAdapterCL(this.lstContacto, getApplicationContext(), dao);
        rwContacto.setLayoutManager(new LinearLayoutManager(this));
        rwContacto.setAdapter(adapterCL);
        ///rwContacto.notify();

    }
    void cargarDatos(){
        lstContacto= new ArrayList<Contacto_CL>();
        lstContacto=dao.getAll();
    }
}