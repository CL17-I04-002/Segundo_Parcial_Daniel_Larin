package com.example.segundo_parcial_daniel_larin.daoCL;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.segundo_parcial_daniel_larin.DataCL.SharedPreferenceConfigCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactoImpSharedPreferencesCL implements ContactoDaoCL{

    private SharedPreferenceConfigCL config;
    private int c;

    public ContactoImpSharedPreferencesCL(Context context){
        config = new SharedPreferenceConfigCL(context);
        c = config.getPreferences().getInt("contador", 0);
    }

    @Override
    public List<Contacto_CL> getAll() {
        List<Contacto_CL> list = new ArrayList<Contacto_CL>();

        for (int i = 1; i <= c; i++) {
            Contacto_CL contacto_cl = new Contacto_CL();
            try{
                Set<String> p = config.getPreferences().getStringSet("propietario"+"numero"+"contacto"+i,new HashSet<>());
                String[] items= p.toArray(new String[p.size()]);
                contacto_cl.setId(Integer.parseInt(items[3]));
                contacto_cl.setNombre(items[2]);
                contacto_cl.setNumero(items[1]);
                contacto_cl.setPropietario(items[0]);
                list.add(contacto_cl);
            }catch (Exception e){
            }
        }
        return list;
    }

    @Override
    public Contacto_CL get(int id) {
        return null;
    }

    @Override
    public void save(Contacto_CL entity) {
        SharedPreferences.Editor editor= config.getPreferences().edit();
        c++;

        entity.setId(c);
        Set<String> contacts = new HashSet<>();
        contacts.add(""+entity.getId());
        contacts.add(entity.getNombre());
        contacts.add(entity.getNumero());
        contacts.add(entity.getPropietario());
        editor.putStringSet("contacto"+c, contacts);
        editor.putStringSet("numero"+c, contacts);
        editor.putStringSet("propietario"+c, contacts);
        editor.putInt("contador", c);
        editor.commit();
    }

    @Override
    public void delete(Contacto_CL entity) {

    }

    @Override
    public void update(Contacto_CL entity) {

    }
}
