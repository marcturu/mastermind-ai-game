package main.domain.classes;

public class RecordInteger extends Record{
    private Integer streak_record;

    /**
     * Creadora de records de "ratxes"
     * @param nom_record nom del record
     * @param modalitat (facil, normal, dificil, pvp).
     */
    public RecordInteger(String nom_record, String modalitat) {
        super(nom_record, modalitat);
        streak_record = 0;
    }

    /**
     * Consultora del valor de la ratxa més alta
     * @return la ratxa mes llarga de la modalitat
     */
    public Integer get_valor() {
        return this.streak_record;
    }

    /**
     * Funcio per comprobar si es bat el record o no
     * @param valor ratxa que preten batre el record
     * @param nom_user nom de l'usuari amb streak la modalitat "new_streak"
     * @return si s'ha batut el record + actualitzar valors o no
     */
    public boolean actualitza(Object valor, String nom_user) {
        Integer new_streak = (Integer)valor;
        if(new_streak > streak_record) {
            super.nom_usuari = nom_user;
            this.streak_record = new_streak;
            return true;
        }
        return false;
    }
    
}
