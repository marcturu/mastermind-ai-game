package test;

import main.domain.classes.User;
import main.domain.classes.User_persona;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;

import java.util.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Classe de testeig de User.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser {

    /**
     *Objecte de la prova: Test de la constructora User
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetre “id”, "nom" i "tipus_user" i comprovem que els valors de id, nom i tipus_user siguin els mateixos, igual que els no introduïts.
     */
    @Test
    public void test_constructora_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", u.get_id(), 1);
        assertEquals("Mateix nom", u.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", u.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixes rondes totals", u.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", u.get_partides_totals(), 0);
        assertEquals("Mateixa puntuació facil", 0.0, u.get_puntuacioF(), 1.0);
        assertEquals("Mateixa puntuació normal", 0.0, u.get_puntuacioN(), 1.0);
        assertEquals("Mateixa puntuació dificil", 0.0, u.get_puntuacioD(), 1.0);       
        assertEquals("Mateixes partides guanyades", u.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", u.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", u.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_id.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor id és el mateix que l’introduït.
     */
    @Test
    public void test_get_id() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", u.get_id(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del nom és el mateix que l’introduït.
     */
    @Test
    public void test_get_nom() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix nom", u.get_nom(), "Marc");
    }

    /**
     * Objecte de la prova: Test de la funció get_tipus_user.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres corresponents, comprovem que el contingut del valor del user_persona és el mateix que l’introduït.
     */
    @Test
    public void test_get_tipus_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix tipus d'usuari", u.get_tipus_user(), Type_user.user_persona);
    }

    /**
     * Objecte de la prova: Test de la funció get_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_rondes_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_rondes_totals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_partides_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_partides_totals(), 0);
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
        User u1 = new User(3, "JUAN", Type_user.user_persona) ;
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
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
     * Objecte de la prova: Test de la funció get_partides_acabades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides acabades correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_num_partides_acabades() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateixes partides acabades", u.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_actuals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides actuals (no acabades) correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_partides_actuals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateixes partides no acabades", u.get_num_partides_actuals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioF.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioF és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioF() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioF(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioN.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioN és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioN() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioN(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioD.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioD és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioD() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioD(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_is_partides_acabades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous Users. Fem que "juguin 3 partides". Comprovem que els ids de les partides "jugades" són iguals als introduïts manualment.
     */
    @Test
    public void test_get_ids_partides_acabades() {
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova1, true, dificultats.FACIL.get_dificultat());
        Partida partida_nova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova2, true, dificultats.FACIL.get_dificultat());
        Partida partida_nova3 = new Partida(3, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova3, true, dificultats.FACIL.get_dificultat());
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        assertEquals("Mateixos ids partides acabades", u1.get_ids_partides_acabades(), list);
    }

    /**
     * Objecte de la prova: Test de la funció get_ids_partides_actives.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un dos nous usuaris i afegim partides creades. Comprovem que els ids de les partides afegides són iguals als introduïts manualment.
     */
    @Test
    public void test_get_ids_partides_actives() {
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova1);
        Partida partida_nova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova2);
        Partida partida_nova3 = new Partida(3, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova3);
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        assertEquals("Mateixos ids partides no acabades", u1.get_ids_partides_actives(), list);
    }

    /**
     * Objecte de la prova: Test de la funció get_partida_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un dos nous usuaris i els fem "jugar" i "acabar" una partida. Comprovem que la funció get_partida_acabada ens retorna la partida acabada corresponent al seu id
     */
    @Test
    public void test_get_partida_acabada() {
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.set_partida_acabada(partida_nova, true, dificultats.FACIL.get_dificultat());
        try{
            assertEquals("Mateixa partida acabada", u1.get_partida_acabada(1), partida_nova);
        }catch (Exception ex){

        }
    }

    /**
     * Objecte de la prova: Test de la funció set_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, es crida al setter del nom i se li assigna un nou. Es comprovem que efectivament el contingut del valor del nom nou és el mateix que l’introduït (nom).
     */
    @Test
    public void test_set_nom() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.set_nom("Jordi");
        assertEquals("Mateix nom canviat", u.get_nom(), "Jordi");
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_rondes_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.incrementar_rondes_totals();
        assertEquals("Mateixes rondes totals després d'incrementar-les", u.get_rondes_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_partides_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.incrementar_partides_totals();
        assertEquals("Mateixes partides totals després d'incrementar-les", u.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció set_partida_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després de fer una petita comprovació de que la partida s'afageix bé a la llista de partides no acabades, acabem la partida amb victòria de l'usuari u1 i comprovem com s'ha eliminat la partida de la llista de partides no acabades i s'ha afegit a la llista d'acabades, al mateix temps que es comprova que l'usuari u1 l'ha guanyat.
     */
    @Test
    public void test_set_partida_acabada() throws Exception{
        User u1 = new User(3, "JUAN", Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona) ;
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        u1.set_partida_acabada(partida_prova, true, dificultats.FACIL.get_dificultat());
        assertEquals(u1.get_num_partides_acabades(), 1);
        assertEquals(u1.get_num_partides_actuals(), 0);
        assertEquals(u1.get_partides_guanyades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció set_puntuacio.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor de les puntuacioncs és l'esperat després de realitzar els canvis fets al test.
     */
    @Test
    public void test_set_puntuacio() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.set_puntuacio(50, 5, 10, "facil");
        assertEquals(u.get_puntuacioF(), 200.0, 0.5);
        u.set_puntuacio(50, 5, 10, "normal");
        assertEquals(u.get_puntuacioN(), 200.0, 0.5);
        u.set_puntuacio(50, 5, 10, "dificil");
        assertEquals(u.get_puntuacioD(), 200.0, 0.5);
        //No pot ser negatiu:
        User u2 = new User(2, "Ferran", Type_user.user_persona);
        u2.set_puntuacio(50, 1, 11, "dificil");
        assertEquals(u2.get_puntuacioD(), 0.0, 0.5);
    }

    /**
     * Objecte de la prova: Test de la funció afegir_partida_nova.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades i, per tant, noves, per després comprovar que efectivament hi ha 1 partida en la llista de partides no acabades i totals.
     */
    @Test
    public void test_afegir_partida_nova() throws Exception{
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        assertEquals(u1.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció actualitza_partida_actual.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades i, per tant, noves, per després comprovar que efectivament hi ha 1 partida en la llista de partides no acabades i totals.
     */
    @Test
    public void test_actualitza_partida_actual() throws Exception{
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova1);
        u1.actualitza_partida_actual(1, partida_nova1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals("partida_nova3 segueix sent la mateixa però actualitzada", u1.get_ids_partides_actives(), list);
    }

}