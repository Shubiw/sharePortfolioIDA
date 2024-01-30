/*
 * Copyright 2024 Tylan.
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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tylan
 */
public class PortefeuilleTest {
    
    public PortefeuilleTest() {
    }

    /**
     * Test de la méthode getCoursDuJour 
     * @throws Throwable 
     */
    @Test
    public void testGetCoursDuJour() throws Throwable {
        
        //Instanciation des jours
        Jour j1 = new Jour(2014, 1); 
        Jour j2 = new Jour(2014, 2);
        
        //Instanciation de l'action
        ActionSimple bnp = new ActionSimple("BNP");
            // Enregistrement des Cours pour l'action
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        //Instanciation du porteufeuille
        Portefeuille p = new Portefeuille();
        //Achat de l'action
        p.acheter(bnp, 20);
        
        // Création du resultat attendu
        Float result = p.getCoursDuJour(bnp, j1);
        // Test de la méthode
        assertEquals(100f ,result, 0.0);
    }
    
    /**
     * Fonction de test de la méthode coursPourcentageJour
     * @throws Throwable 
     */
    @Test
    public void testCoursPourcentageJour() throws Throwable {      
        //Instanciation des jours
        Jour j1 = new Jour(2014, 1); 
        Jour j2 = new Jour(2014, 2);
        
        //Instanciation de l'action
        ActionSimple bnp = new ActionSimple("BNP");
        // Enregistrement des Cours pour l'action
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        //Instanciation du porteufeuille
        Portefeuille p = new Portefeuille();
        //Achat de l'action
        p.acheter(bnp, 20);
        
        // Création du resultat attendu
        String result = p.coursPourcentageJour(j1);
        // Test de la méthode
        String test = "BNP : \n" + "Cours du portefeuille = 2000.0\n" + "Pourcentage de l'action = 100,00%"+"\n\n";
        assertEquals(test ,result, "0");
    }
    
}
