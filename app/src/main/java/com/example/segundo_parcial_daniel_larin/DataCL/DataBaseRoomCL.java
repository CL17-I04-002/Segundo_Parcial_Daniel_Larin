package com.example.segundo_parcial_daniel_larin.DataCL;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.segundo_parcial_daniel_larin.daoCL.ContactoDaoCL;
import com.example.segundo_parcial_daniel_larin.modelCL.Contacto_CL;

@Database(entities = {Contacto_CL.class}, version = 1)
public abstract class DataBaseRoomCL extends RoomDatabase {
    public abstract ContactoDaoCL contactoDaoCL();
}
