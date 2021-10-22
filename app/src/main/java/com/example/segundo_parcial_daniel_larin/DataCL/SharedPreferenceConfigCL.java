package com.example.segundo_parcial_daniel_larin.DataCL;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfigCL {
    private SharedPreferences preferences;

    public SharedPreferenceConfigCL(Context context){
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }
    public SharedPreferences getPreferences(){
        return preferences;
    }
    public void delete(){
        preferences.edit().clear();
    }
}
