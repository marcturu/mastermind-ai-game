package test;


import main.domain.classes.User_persona;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;

import java.util.Vector;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Classe de testeig de User_persona.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser_persona {

    /**
     *Objecte de la prova: Test de la constructora User_persona (amb password)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_persona() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password123");
        assertEquals("Mateix id", up.get_id(), 1);
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixa puntuacioF", up.get_puntuacioF(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioN", up.get_puntuacioN(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioD", up.get_puntuacioD(), 0.0, 0.5);
        assertEquals("Mateix password", up.validate_password("password123"), true);
        assertEquals("Mateixa puntuacioPvsP", up.get_puntuaciopvp(), 0.0, 0.5);
        assertEquals("Mateix streakF", up.get_streakF(), 0);
        assertEquals("Mateix streakN", up.get_streakN(), 0);
        assertEquals("Mateix streakD", up.get_streakD(), 0);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixes partides guanyades", up.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", up.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_acabades(), 0);

    }

    /**
     *Objecte de la prova: Test de la constructora User_persona (sense password)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_persona2() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", up.get_id(), 1);
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixa puntuacioF", up.get_puntuacioF(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioN", up.get_puntuacioN(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioD", up.get_puntuacioD(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioPvsP", up.get_puntuaciopvp(), 0.0, 0.5);
        assertEquals("Mateix streakF", up.get_streakF(), 0);
        assertEquals("Mateix streakN", up.get_streakN(), 0);
        assertEquals("Mateix streakD", up.get_streakD(), 0);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixes partides guanyades", up.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", up.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_acabades(), 0);
    }

    /**
     *Objecte de la prova: Test de la funció get_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el password introduït és el que correspon.
     */
    @Test
    public void test_get_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password_a_validar");
        assertEquals("Mateix password", up.get_password(), "password_a_validar");
    }

    /**
     *Objecte de la prova: Test de la funció get_streakF
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el streakF inicialitzat amb la creadora és el que correspon.
     */
    @Test
    public void test_get_streakF() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, up.get_streakF(), 1.0);
    }

    /**
     *Objecte de la prova: Test de la funció get_streakN
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el streakN inicialitzat amb la creadora és el que correspon.
     */
    @Test
    public void test_get_streakN() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, up.get_streakN(), 1.0);
    }

    /**
     *Objecte de la prova: Test de la funció get_streakD
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el streakD inicialitzat amb la creadora és el que correspon.
     */
    @Test
    public void test_get_streakD() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, up.get_streakD(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_rondes_totals() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(up.get_rondes_totals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_partides_totals() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(up.get_partides_totals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_guanyades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor partides_guanyades és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     *            Després, creem una nova partida que es guanya i mirem com l'atribut partides_guanyades ara és 1. Després fem el mateix però perdent, per veure com l'usuari u1 seguiex tenint només 1 partida guanyada.
     */
    @Test
    public void test_get_partides_guanyades() throws Exception{
        User_persona u1 = new User_persona(3, "JUAN", Type_user.user_persona) ;
        assertEquals(u1.get_partides_guanyades(), 0);

        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //dificultats dif = new dificultats(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova);
        u1.set_partida_acabada(partida_prova, true, dificultats.FACIL.get_dificultat());
        assertEquals("L'usuari 1 té 1 partida guanyada", u1.get_partides_guanyades(), 1);
        Partida partida_prova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova2);
        u1.set_partida_acabada(partida_prova2, false, dificultats.FACIL.get_dificultat());
        assertEquals("L'usuari 1 té 1 partida guanyada", u1.get_partides_guanyades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_num_partides_acabades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides acabades correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_num_partides_acabades() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals("Mateixes partides acabades", up.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_num_partides_actuals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides actuals (no acabades) correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_num_partides_actuals() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals("Mateixes partides no acabades", up.get_num_partides_actuals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_is_partides_acabades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous Users. Fem que "juguin 3 partides". Comprovem que els ids de les partides "jugades" són iguals als introduïts manualment.
     */
    @Test
    public void test_get_ids_partides_acabades() {
        User_persona u1 = new User_persona(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova1, true, dificultats.FACIL.get_dificultat());
        Partida partida_nova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova2, true, dificultats.FACIL.get_dificultat());
        Partida partida_nova3 = new Partida(3, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova3, true, dificultats.FACIL.get_dificultat());
        // List<Integer> list = new ArrayList<>(); list.add(1); list.add(2); list.add(3);
        List<Integer> intList = Arrays.asList(1, 2, 3);
        assertEquals("Mateixos ids partides acabades", u1.get_ids_partides_acabades(), intList);
    }

    /**
     * Objecte de la prova: Test de la funció get_ids_partides_actives.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un dos nous usuaris i afegim partides creades. Comprovem que els ids de les partides afegides són iguals als introduïts manualment.
     */
    @Test
    public void test_get_ids_partides_actives() {
        User_persona u1 = new User_persona(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova1);
        Partida partida_nova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova2);
        Partida partida_nova3 = new Partida(3, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova3);
        //List<Integer> list = new ArrayList<>(); list.add(1); list.add(2); list.add(3);
        List<Integer> intList = Arrays.asList(1, 2, 3);
        assertEquals("Mateixos ids partides no acabades", u1.get_ids_partides_actives(), intList);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuaciopvp.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User_persona
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacio és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuaciopvp() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, up.get_puntuaciopvp(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_estadistiques.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que les estadístiques d'aquest usuari es corresponen amb les creades manualment pel test (tots els valors a 0, ja que encara no ha jugat aquest nou Usuari).
     */
    @Test
    public void test_get_estadistiques() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        //Vector<Double> stats_prova = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        Vector<Double> stats_prova = new Vector<Double>();
        for(int i = 0; i < 10; ++i) {
            stats_prova.add(0.0);
        }
        assertEquals("Mateixes estadístiques", stats_prova, up.get_estadistiques());
    }

    /**
     *Objecte de la prova: Test de la funció validate_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el password introduït és el que correspon.
     */
    @Test
    public void test_validate_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password_a_validar");
        assertEquals("Password a validar correcte", up.validate_password("password_a_validar"), true);
    }

    /**
     *Objecte de la prova: Test de la funció set_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou User amb els paràmetres indicats. Després, creem un password i comprovem que el valor afegit en aquest set_password ha estat afegit correctement.
     */
    @Test
    public void test_set_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.set_password("password_afegit");
        assertEquals("Mateix password afegit", up.validate_password("password_afegit"), true);
    }

    /**
     * Objecte de la prova: Test de la funció set_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, es crida al setter del nom i se li assigna un nou. Es comprovem que efectivament el contingut del valor del nom nou és el mateix que l’introduït (nom).
     */
    @Test
    public void test_set_nom() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.set_nom("Jordi");
        assertEquals("Mateix nom canviat", up.get_nom(), "Jordi");
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_rondes_totals() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.incrementar_rondes_totals();
        assertEquals("Mateixes rondes totals després d'incrementar-les", up.get_rondes_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_partides_totals() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.incrementar_partides_totals();
        assertEquals("Mateixes partides totals després d'incrementar-les", up.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_partides_guanyades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_guanyades és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_partides_guanyades() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.incrementar_partides_guanyades();
        assertEquals("Mateixes partides guanyades després d'incrementar-les", up.get_partides_guanyades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_streak.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor streak és l'esperat (1) després d'augmentar-lo en 1a unitat en totes les seves respectives dificultats.
     */
    @Test
    public void test_incrementar_streak() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.incrementar_streak("facil");
        assertEquals("Mateix streak després d'incrementar-lo", up.get_streakF(), 1);
        up.incrementar_streak("normal");
        assertEquals("Mateix streak després d'incrementar-lo", up.get_streakN(), 1);
        up.incrementar_streak("dificil");
        assertEquals("Mateix streak després d'incrementar-lo", up.get_streakD(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció reiniciar_streak.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor streak streakF (en aquest cas) és l'esperat (0) després d'augmentar-lo en dues unitats i reiniciar-lo, posteriorment.
     */
    @Test
    public void test_reiniciar_streak() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.incrementar_streak("facil");
        assertEquals("Mateix streak després d'incrementar-lo", up.get_streakF(), 1);
        up.incrementar_streak("facil");
        assertEquals("Mateix streak després d'incrementar-lo", up.get_streakF(), 2);
        up.reiniciar_streak("facil");
        assertEquals("Mateix streak després de reiniciar-lo", up.get_streakF(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció afegir_partida_nova.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades i, per tant, noves, per després comprovar que efectivament hi ha 1 partida en la llista de partides no acabades i totals.
     */
    @Test
    public void test_afegir_partida_nova(){
        User_persona u1 = new User_persona(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        assertEquals(u1.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció afegeix_partida_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides acabades per després comprovar que, efectivament, hi ha 1 partida en la llista de partides acabades.
     */
    @Test
    public void test_afegeix_partida_acabada(){
        User_persona u1 = new User_persona(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        assertEquals(u1.get_num_partides_acabades(), 0);
        u1.afegeix_partida_acabada(partida_nova);
        assertEquals(u1.get_num_partides_acabades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció elimina_partida_no_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades (noves) per després siumlar la seva finalització eliminant-la d'aquesta mateixa llista amb la funció a testejar per comprovar, finalment, com aquesta llista de partides actuals està buida.
     */
    @Test
    public void test_elimina_partida_no_acabada(){
        User_persona u1 = new User_persona(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        assertEquals(u1.get_num_partides_acabades(), 0);
        u1.elimina_partida_no_acabada(partida_nova);
        u1.afegeix_partida_acabada(partida_nova);
        assertEquals(u1.get_num_partides_actuals(), 0);
        assertEquals(u1.get_num_partides_acabades(), 1);
    }

    /**
     *Objecte de la prova: Test de la funció set_puntuacio_pvp
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou User amb els paràmetres indicats. Després, comprovem que les puntuacions de les partides acabades síon les que corresponen.
     */
    @Test
    public void test_set_puntuacio_pvp() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.set_puntuacio_pvp(50);
        assertEquals(up.get_puntuaciopvp(), 50.0, 0.5);
        //Si és negativa, és 0:
        User_persona up2 = new User_persona(2, "Ferran", Type_user.user_persona);
        up2.set_puntuacio_pvp(-50);
        assertEquals(up2.get_puntuaciopvp(), 0.0, 0.5);
    }


    /**
     *Objecte de la prova: Test de la funció set_partida_acabada
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dous nous nou Usuaris persona amb els paràmetres indicats. Després, fem que juguin una partida, la guanyi el usuari1 i comprovem que tot s'actualitza bé després d'acabar la partida i fer el set_partida_acabada(...)
     */
    /*
    @Test
    public void test_set_partida_acabada() {
        User_persona up = new User_persona(3, "JUAN", Type_user.user_persona);
        assertEquals(up.get_partides_guanyades(), 0);
        User_persona up2 = new User_persona(1, "FERRI", Type_user.user_persona) ;
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, up, up2, dificultats.FACIL, true);
        up.afegir_partida_nova(partida_prova);
        assertEquals(up.get_num_partides_actuals(), 1);
        up.set_partida_acabada(partida_prova, true, "PvsP");
        assertEquals(up.get_num_partides_acabades(), 1);
        assertEquals(up.get_num_partides_actuals(), 0);
    } */

}