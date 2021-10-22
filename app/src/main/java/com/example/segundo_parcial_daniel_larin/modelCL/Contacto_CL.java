package com.example.segundo_parcial_daniel_larin.modelCL;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contacto")
public class Contacto_CL implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;

    ///@ColumnInfo(name = "nombre")
    String nombre;

    ///@ColumnInfo(name = "numero")
    String numero;

    //////@ColumnInfo(name = "propietario")
    String propietario;

    public Contacto_CL() {
    }

    public Contacto_CL(int id, String nombre, String numero, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
