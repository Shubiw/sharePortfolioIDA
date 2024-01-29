/*
 * Copyright 2024 mro72.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tp04.metier.Action;
import tp04.metier.ActionComposee;
import tp04.metier.ActionSimple;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;

/**
 *
 * @author mro72
 */
public class PortefeuilleTest {
    
    public PortefeuilleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        // affichage des cours - comme 1 action simple et 1 action
        System.out.println("Action simple *bnp* à j1 : " + bnp.valeur(j1));
        System.out.println("Action *Banque-Assurance* à j2 : " + bqAss.valeur(j2));
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of acheter method, of class Portefeuille.
     */
     
    @Test
    public void testVendre() {
        Portefeuille instance = new Portefeuille();
        ActionSimple action = new ActionSimple("NomAction");
        int quantiteAVendre = 15;

        instance.acheter(action, 10); // Achetez 10 actions
        

        // Utilisez la méthode vendre pour tester la vente de quantité supérieure au stock
        assertThrows(IllegalArgumentException.class, () -> {
            instance.vendre(action, quantiteAVendre);
        });

        // Assurez-vous que l'action a été retirée du portefeuille après la tentative de vente
        assertFalse(instance.mapLignes.containsKey(action));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Portefeuille.
     */


    /**
     * Test of valeur method, of class Portefeuille.
     */

    
}
