import java.utils;
import main.domain.classes;

public static class Record() {
    private String nom_record;
    private String nom_usuari;
    private int punts;

    public Record (String nom_record) {
        this.nom_record = nom_record;
        this.nom_usuari = null;
        this.punts = null;
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
    public boolean check_if_record(int punts, String nom_usuari) {
        if((punts > this.punts) or (this.punts == null)) {
            this.nom_usuari = nom_usuari;
            this.punts = punts;
            Fullreturn true;
        }
        return false;
    }
};