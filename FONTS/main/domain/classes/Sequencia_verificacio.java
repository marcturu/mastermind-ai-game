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
     * Funcio per calcular els pins negres donada una solucio i un intent
     * @param arr_solucio array de colors que conte la solucio
     * @param array_provat array de colors que conte la sequencia que volem verificar
     * @return pins negres que ha fet la sequencia
     */
    private static int get_pins_negres(colors[] arr_solucio, colors[] array_provat) {
        int negres = 0;
        for(int i = 0; i < 4; ++i) {
            if (arr_solucio[i] == array_provat[i]) ++negres;
        }
        return negres;
    }

    /**
     * Funcio per calcular els pins blanques donada una solucio i un intent
     * @param arr_solucio array de colors que conte la solucio
     * @param array_provat array de colors que conte la sequencia que volem verificar
     * @return pins blanques que ha fet la sequencia
     */
    private static int get_pins_blanques(colors[] arr_solucio, colors[] array_provat) {
        int blanques = 0;
        int contador_repeticions = 1;

        for(int i = 0; i < 4; ++i) {
            boolean comprova_repeticions = false;
            
            //mirem que no haguem comprovat ja aquest color
            for(int j = 0; j < i; ++j) {
                if(array_provat[i] == array_provat[j]) {
                    comprova_repeticions = true;
                }
            }
            if(comprova_repeticions) continue;

            contador_repeticions = 1;

            for(int j = i+1; j < 4; ++j) {
                if(array_provat[i] == array_provat[j]) {
                    ++contador_repeticions;
                }
            }

            for(int j = 0; j < 4; ++j) {
                if(contador_repeticions == 0) break;

                if(i != j && array_provat[i] == arr_solucio[j]) {
                    ++blanques;
                    --contador_repeticions;
                }

                if(i == j && array_provat[i] == arr_solucio[j]) {
                    --contador_repeticions;
                }
            }
        }
        return blanques;
    }

    /**
     * Funcio que retorna la verificacio d'una sequencia
     * @param arr_solucio array de colors que conte la solucio
     * @param array array de colors que conte la sequencia que volem verificar
     * @return espigues blanques i negres que ha fet la sequencia
     */
    public static Pair<Integer,Integer> get_verificacio(colors[] arr_solucio, colors[] array) {
        Integer negres = get_pins_negres(arr_solucio, array);
        Integer blanques = get_pins_blanques(arr_solucio, array);
        
        return new Pair<>(blanques, negres);
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