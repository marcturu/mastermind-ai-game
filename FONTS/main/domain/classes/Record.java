package main.domain.classes;

import java.util.*;

public class Record{
    private String nom_record;
    private String nom_usuari;
    private int punts;

    public Record (String nom_record) {
        this.nom_record = nom_record;
        this.nom_usuari = null;
        this.punts = -1;
    }

    //GETTERS
    public String get_nom_record() {
        return this.nom_record;
    }
    public String get_nom_usuari() {
        return this.nom_usuari;
    }
    public int get_punts() {
        return this.punts;
    }

    //FUNCIONAL
    public boolean check_if_record(int punts, String nom_usuari) throws IllegalArgumentException{
        if(punts < 0) {
            throw new IllegalArgumentException("Invalid value for \'punts\', it should be positive");
        }
        if((punts > this.punts) || (this.punts == (-1))) {
            this.nom_usuari = nom_usuari;
            this.punts = punts;
            return true;
        }
        return false;
    }
};