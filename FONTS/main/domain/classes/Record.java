package main.domain.classes;


//import java.util.*;

public abstract class Record{
    protected String nom_record;
    protected String nom_usuari;
    protected String modalitat_record;

    public Record (String nom_record, String dif) {
        this.nom_record = nom_record;
        this.nom_usuari = null;
        this.modalitat_record = dif;
    }

    //GETTERS

    /**
     * getter del nom del record
     * @return nom del record
     */
    public String get_nom_record() {
        return this.nom_record;
    }

    /**
     * getter del nom de l'usuari
     * @return nom de l'usuari que ha batut el record
     */
    public String get_nom_usuari() {
        return this.nom_usuari;
    }

    public abstract Object get_valor();

    /**
     * getter de la dificultat en String del record
     * @return String amb la dificultat(facil, normal, dificil, pvp)
     */
    public String get_modalitat_record() {
        return modalitat_record;
    }

    /**
     * funció abstracta per comprovar si es bat el record. S'implementa a RecordDouble, RecordInteger, RecordDuration.
     * @param punts punts que s'han fet
     * @param nom_usuari nom de l'usuari que ha fet els punts
     * @return si s'ha batut el record o no.
     */
    public abstract boolean actualitza(Object valor, String nom_usuari);
};