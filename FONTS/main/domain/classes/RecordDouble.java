package main.domain.classes;

public class RecordDouble extends Record{
    private Double punts_record;
    
    /**
     * Creadora del record de punts
     * @param nom_record nom del record
     * @param modalitat (facil, normal, dificil, pvp).
     */
    public RecordDouble(String nom_record, String modalitat) {
        super(nom_record, modalitat);
        punts_record = 0.0;
    }

    /**
     * getter dels punts del record
     * @return els punts amb els que es te el record
     */
    public Double get_valor() {
        return punts_record;
    }

    /**
     * Funcio per comprobar si es bat el record actual o no
     * @param valorre punts amb els que es preten batre el record
     * @param nom_user nom de l'usuari que preten batre el record
     * @return si s'ha batut el record i actualitzat el record o no
     */
    public boolean actualitza(Object valor, String nom_user) {
        Double new_punts = (Double)valor;
        if((new_punts > punts_record) ) {
            super.nom_usuari = nom_user;
            punts_record = new_punts;
            return true;
        }
        return false;
    }

}
