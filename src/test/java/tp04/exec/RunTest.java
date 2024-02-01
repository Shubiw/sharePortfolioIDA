/*
 * Copyright 2024 mehdi.
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
package tp04.exec;

import java.util.ArrayList;
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

/**
 *
 * @author mehdi
 */
public class RunTest {
    
    public RunTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
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
     * Test of afficherBourse method, of class Run.
     */
    @Test
    public void testAfficherBourse() {
        System.out.println("afficherBourse");
        ArrayList<Action> bourse = new ArrayList<>();
        ActionSimple bnp, axa, gog, rio;
        ActionComposee bqAss, comp1, comp2;
        
        Jour j1, j2, j3, j4, j5;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);
        j3 = new Jour(2014, 3);
        j4 = new Jour(2015, 4);
        j5 = new Jour(2018, 5);
        
        
        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        bourse.add(bnp);
        axa = new ActionSimple("AXA");
        bourse.add(axa);
        gog = new ActionSimple("Google");
        bourse.add(gog);
        rio = new ActionSimple("Riot");
        bourse.add(rio);
        
        bqAss = new ActionComposee("Banque-Assurance");
        bourse.add(bqAss);
        comp1 = new ActionComposee("Compo1");
        bourse.add(comp1);
        comp2 = new ActionComposee("Compo2");
        bourse.add(comp2);
        
        
        
        
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        
        comp1.enrgComposition(rio, 0.5f);
        comp1.enrgComposition(axa, 0.6f);
        
        
        comp2.enrgComposition(gog, 0.8f);
        comp2.enrgComposition(rio, 0.0f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j3, 400);
        
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j3, 200);
        
        rio.enrgCours(j5, 505);
        
        gog.enrgCours(j3, 50);
        
        boolean results = false;

        results = Run.afficherBourse(bourse, j3);
        // TODO review the generated test code and remove the default call to fail.
        
        assertEquals(results, true);
    }

    /**
     * Test of main method, of class Run.
     */
  
    
}