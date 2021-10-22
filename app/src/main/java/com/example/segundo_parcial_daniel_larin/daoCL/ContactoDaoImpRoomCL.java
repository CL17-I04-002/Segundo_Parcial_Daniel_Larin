package com.example.segundo_parcial_daniel_larin.daoCL;

import android.content.Context;

import androidx.room.Room;

import com.example.segundo_parcial_daniel_larin.DataCL.DataBaseRoomCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

import java.util.List;

public class ContactoDaoImpRoomCL implements ContactoDaoCL{

    private DataBaseRoomCL db;

    private ContactoDaoCL dao;

    public ContactoDaoImpRoomCL(Context context){
        db = Room.databaseBuilder(context,DataBaseRoomCL.class, "db").allowMainThreadQueries().build();
        dao=db.contactoDaoCL();
    }

    @Override
    public List<Contacto_CL> getAll() {
        return dao.getAll();
    }

    @Override
    public Contacto_CL get(int id) {
        return dao.get(id);
    }

    @Override
    public void save(Contacto_CL entity) {
        dao.save(entity);
    }

    @Override
    public void delete(Contacto_CL entity) {
        dao.delete(entity);
    }

    @Override
    public void update(Contacto_CL entity) {
        dao.update(entity);
    }
}
