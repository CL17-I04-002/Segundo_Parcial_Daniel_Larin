package com.example.segundo_parcial_daniel_larin.adapterCL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segundo_parcial_daniel_larin.NuevoContactoCL;
import com.example.segundo_parcial_daniel_larin.R;
import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

import java.util.List;

public class ContactoAdapterCL extends RecyclerView.Adapter<ContactoAdapterCL.ViewHolder> {

    ///Se manejaran los atributos de la clase que tiene más jerarquía
    private List<Contacto_CL> lst_contacto;
    private Context context;
    private ContactoDaoCL dao;



    ///Manipulación de la vista xml
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_id_contacto;
        TextView txt_nombre_contacto;
        TextView txt_numero_contacto;
        TextView txt_nombre_propietario;

        Button btn_modificar_contacto;
        Button btn_eliminar_contacto;

        Context context;

        public ViewHolder(View view, Context context){
            super(view);
            txt_id_contacto=(TextView) view.findViewById(R.id.txt_id_contacto);
            txt_nombre_contacto=(TextView) view.findViewById(R.id.txt_nombre_contato);
            txt_numero_contacto=(TextView) view.findViewById(R.id.txt_numero_contato);
            txt_nombre_propietario=(TextView) view.findViewById(R.id.txt_nombre_propietario);

            btn_modificar_contacto= view.findViewById(R.id.btn_modificar_contacto);
            btn_eliminar_contacto= view.findViewById(R.id.btn_eliminar_contacto);
            this.context=context;
        }


    }
    public ContactoAdapterCL(List<Contacto_CL> lst_contacto, Context context, ContactoDaoCL dao){
        this.context=context;
        this.lst_contacto=lst_contacto;
        this.dao=dao;
    }




    ///Configuraciones
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto_cl, parent, false);
        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    ///ELIMINAR
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ///El objeto t almacena un valor de la posición de la lista contacto
        Contacto_CL contacto_cl=lst_contacto.get(position);
        ///Por medio del objeto holder se accede a las propiedades de la clase ContactoAdapter
        holder.txt_id_contacto.setText(""+contacto_cl.getId());
        holder.txt_nombre_contacto.setText(contacto_cl.getNombre());
        holder.txt_numero_contacto.setText(contacto_cl.getNumero());
        holder.txt_nombre_propietario.setText(contacto_cl.getPropietario());

        holder.btn_eliminar_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.delete(contacto_cl);

                ///Se notifica que el objeto fue eliminado, es una actualización
                ///del activity de forma asíncrona
                lst_contacto=dao.getAll();
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(),"ELiminado",Toast.LENGTH_LONG).show();
            }
        });
        holder.btn_modificar_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NuevoContactoCL.class);
                ///estado = 1 = modificar
                intent.putExtra("estado",1);
                intent.putExtra("contacto",contacto_cl);
                intent.putExtra("numero",contacto_cl);
                intent.putExtra("propietario",contacto_cl);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        ///Es necesario retornar el tamaño del recycler
        return lst_contacto.size();
    }
}