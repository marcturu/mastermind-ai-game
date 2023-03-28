package main.domain.classes.algorismes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Genetic_algorithm implements Maquina {

    
    // Definir la longitud de la secuencia y los posibles colores
    private static final int LONGITUD_SECUENCIA = 4;
    private static final String[] COLORES_POSIBLES = {"rojo", "verde", "azul", "amarillo", "blanco", "negro"};

    // Definir el tamaño de la población y el número máximo de generaciones
    private static final int TAMANO_POBLACION = 50;
    private static final int NUM_GENERACIONES_MAX = 100;

    // Definir las probabilidades de cruce y mutación
    private static final double PROBABILIDAD_CRUCE = 0.6;
    private static final double PROBABILIDAD_MUTACION = 0.1;

    // Función para evaluar la aptitud de una secuencia candidata
    private static int evaluarAptitud(String[] solucion, String[] secuencia) {
        int aptitud = 0;
        int numBlancos = 0;
        int numNegros = 0;
        List<String> solucionRestante = new ArrayList<>(Arrays.asList(solucion));
        for (int i = 0; i < secuencia.length; i++) {
            if (secuencia[i].equals(solucion[i])) {
                numNegros++;
                solucionRestante.remove(secuencia[i]);
            } else if (solucionRestante.contains(secuencia[i])) {
                numBlancos++;
                solucionRestante.remove(secuencia[i]);
            }
        }
        aptitud = numNegros * 10 + numBlancos;
        return aptitud;
    }

    // Función para seleccionar los mejores individuos mediante el método de torneo
    private static List<String[]> seleccionarTorneo(String[][] poblacion, int tamanoTorneo, String[] solucion) {
        List<String[]> seleccionados = new ArrayList<>();
        for (int i = 0; i < poblacion.length; i++) {
            int[] torneo = new int[tamanoTorneo];
            for (int j = 0; j < tamanoTorneo; j++) {
                torneo[j] = new Random().nextInt(poblacion.length);
            }
            int ganadorTorneo = torneo[0];
            for (int j = 1; j < tamanoTorneo; j++) {
                int candidato = torneo[j];
                if (evaluarAptitud(poblacion[candidato], solucion) >
                        evaluarAptitud(poblacion[ganadorTorneo], solucion)) {
                    ganadorTorneo = candidato;
                }
            }
            seleccionados.add(poblacion[ganadorTorneo]);
        }
        return seleccionados;
    }

    // Función para generar una nueva población mediante el cruce y la mutación
    private static String[][] generarNuevaPoblacion(List<String[]> seleccionados) {
        String[][] nuevaPoblacion = new String[TAMANO_POBLACION][LONGITUD_SECUENCIA];
        for (int i = 0; i < TAMANO_POBLACION; i += 2) {
            String[] padre1 = seleccionados.get(new Random().nextInt(seleccionados.size()));
            String[] padre2 = seleccionados.get(new Random().nextInt(seleccionados.size()));
            if (Math.random() < PROBABILIDAD_CRUCE) {
                // Cruzar los padres para generar dos hijos
                int puntoCruce = new Random().nextInt(LONGITUD_SECUENCIA - 1) + 1;
                String[] hijo1 = Arrays.copyOfRange(padre1, 0, puntoCruce);
                String[] hijo2 = Arrays.copyOfRange(padre2, puntoCruce, LONGITUD_SECUENCIA);
                System.arraycopy(padre1, puntoCruce, hijo2, puntoCruce, LONGITUD_SECUENCIA - puntoCruce);
                System.arraycopy(padre2, 0, hijo1, 0, puntoCruce);
                nuevaPoblacion[i] = hijo1;
                nuevaPoblacion[i + 1] = hijo2;
            } else {
                // Copiar los padres sin cruzarlos
                nuevaPoblacion[i] = padre1;
                nuevaPoblacion[i + 1] = padre2;
            }
        }
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            if (Math.random() < PROBABILIDAD_MUTACION) {
                // Mutar la secuencia
                int indiceMutacion = new Random().nextInt(LONGITUD_SECUENCIA);
                String nuevoColor = COLORES_POSIBLES[new Random().nextInt(COLORES_POSIBLES.length)];
                nuevaPoblacion[i][indiceMutacion] = nuevoColor;
            }
        }
        return nuevaPoblacion;
    }

    // Función para resolver el juego de Mastermind mediante un algoritmo genético
    public static List<String[]> resolver(String[] solucion) {
        // Generar la población inicial de forma aleatoria
        String[][] poblacion = new String[TAMANO_POBLACION][LONGITUD_SECUENCIA];
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            for (int j = 0; j < LONGITUD_SECUENCIA; j++) {
                poblacion[i][j] = COLORES_POSIBLES[new Random().nextInt(COLORES_POSIBLES.length)];
            }
        }

        // Iterar hasta encontrar la solución o alcanzar el número máximo de generaciones
        List<String[]> intentos = new ArrayList<>();
        int numGeneraciones = 0;
        while (numGeneraciones < NUM_GENERACIONES_MAX) {
            // Evaluar la aptitud de la población actual
            int[] aptitudes = new int[TAMANO_POBLACION];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                aptitudes[i] = evaluarAptitud(solucion, poblacion[i]);
                if (aptitudes[i] == LONGITUD_SECUENCIA * 10) {
                    // Se ha encontrado la solución
                    intentos.add(poblacion[i]);
                    return intentos;
                }
            }
            // Seleccionar los mejores individuos
            List<String[]> seleccionados = seleccionarTorneo(poblacion, TAMANO_POBLACION / 2, solucion);

            // Generar una nueva población
            poblacion = generarNuevaPoblacion(seleccionados);

            // Añadir los intentos a la lista
            for (String[] secuencia : seleccionados) {
                intentos.add(secuencia);
            }
            numGeneraciones++;
        }

        // No se ha encontrado la solución
        return intentos;
    }

    public static void main(String[] args) {
        String[] solucion = {"rojo", "azul", "verde", "amarillo"};
        List<String[]> intentos = resolver(solucion);
        System.out.println("Intentos:");
        for (String[] intento : intentos) {
            System.out.println(Arrays.toString(intento));
        }
    }
}




