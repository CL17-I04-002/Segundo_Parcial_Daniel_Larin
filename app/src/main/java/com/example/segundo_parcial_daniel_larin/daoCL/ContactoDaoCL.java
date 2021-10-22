package com.example.segundo_parcial_daniel_larin.daoCL;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

import java.util.List;

@Dao
public interface ContactoDaoCL {
    ///Es equivalente al SELECT de BDD
    @Query("select * from contacto")
    public List<Contacto_CL> getAll();

    ///Es equivalente a SELECT * FROM WHERE...
    @Query("select * from contacto where id = :id")
    public Contacto_CL get(int id);

    ////Es equivalente a INSERT INTO...
    @Insert
    public void save(Contacto_CL entity);

    ///Es equivalente a DELETE * FROM....
    @Delete
    public void delete(Contacto_CL entity);

    ///Es equivalente a UPDATE SET....
    @Update
    public void update(Contacto_CL entity);
}
