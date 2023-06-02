package main.domain.classes;

import main.domain.classes.enumerations.dificultats;

public class RecordLong extends Record{
    private Long temps_record;//en segons

    /**
     * Creadora del record de temps
     * @param nom_record nom del record
     * @param modalitat (facil, normal, dificil)
     */
    public RecordLong(String nom_record, String modalitat) {
        super(nom_record, modalitat);    
        temps_record = (Long)(dificultats.from_string_to_dif(modalitat).get_temps_max());
    }
    
    /**
     * Consultora del temps que té el record
     * @return el valor en segons del record actual
     */
    public Long get_valor() {
        return this.temps_record;
    }

    /**
     * Funcio per saber si el record de temps s'ha batut o no
     * @param valor segons de la partida que preten batre el record
     * @param nom_user nom de l'usuari amb una partida de temps = segons_partida
     * @return
     */
    public boolean actualitza(Object valor, String nom_user) {
        Long segons_partida = (Long)valor;
        
        if(segons_partida < temps_record) {
            super.nom_usuari = nom_user;
            this.temps_record = segons_partida;
            

            return true;
        }
        return false;
    }

}
