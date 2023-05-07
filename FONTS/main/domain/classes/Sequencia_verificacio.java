package main.domain.classes;

import main.domain.classes.enumerations.colors;
import main.domain.classes.types.Pair;

public class Sequencia_verificacio extends Sequencia {

    public Sequencia_verificacio(){
        super();
    }

    /**
     * Funció que serveix per modificar el array de la Sequencia de Verificacio, fa comprobacions de que sigui correcta i en cas contrari salta una Excepcio
     * @param colors[] array Sequencia de verificacio que volem assignar
     * @param colors[] solucio Sequencia de Solucio per a poder fer les comprobacions
     * @param colors[] intentada Sequencia Intendada per poder fer comprobacions
     */
    public void set_array_verificacio(colors[] verificacio, colors[] solucio, colors[]  intentada) throws Exception{
        if (array.length != 4) throw new Exception("El tamany de la sequencia no es 4");
        Pair<Integer, Integer> pair_correcte = get_verificacio(solucio,intentada);
        Pair<Integer, Integer> pair_entrat = get_negres_blanques(verificacio);
        if ((pair_correcte.first() != pair_entrat.first()) || (pair_correcte.second() != pair_entrat.second())) throw new Exception("Sequencia de Verficacio incorrecte");
        else{
            this.array = verificacio;
        }
    }

    /**
     * Funcio que retorna la verificacio d'una sequencia
     * @param solucio
     * @return espigues blanques i negres que ha fet la sequencia
     */
    public Pair<Integer,Integer> get_verificacio(colors[] arr_solucio, colors[] array) {
        Integer blanques = 0, negres = 0;
        Pair<Integer,Integer> result = new Pair<>(blanques, negres);

        for(int i = 0; i < 4; ++i) {
            if(arr_solucio[i] == array[i]) ++negres;
            else {
                boolean done = false;
                for(int j = 0; j < 4 && !done; ++j) {
                    if(array[i] == arr_solucio[j]) {
                        ++blanques;
                        done = true;
                    }
                }
            }
        }
        result.set_first(blanques);
        result.set_second(negres);
        return result;
    }

    private Pair<Integer, Integer> get_negres_blanques(colors[] verificacio_entrada) {
        int blanques = 0, negres = 0;
        for(int i = 0; i < 4; ++i) {
            if(verificacio_entrada[i] == colors.NEGRE) ++negres;
            else if(verificacio_entrada[i] == colors.BLANC) ++blanques;
        }

        return new Pair<>(blanques, negres);
    }
}