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
