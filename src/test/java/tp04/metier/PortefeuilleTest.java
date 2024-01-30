/*
 * Copyright 2024 Senouci Amine.
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import tp04.metier.ActionSimple;
import tp04.metier.ActionComposee;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;


/**
 *
 * @author Senouci Amine
 */
public class PortefeuilleTest {

    @Test
    public void testCalculerPourcentagesParAction() {
        // Initialisation de l'environnement de test
        Jour j1 = new Jour(2014, 1);
        ActionSimple bnp = new ActionSimple("BNP");
        ActionSimple axa = new ActionSimple("AXA");
        Portefeuille p = new Portefeuille();
        p.acheter(axa, 10);  // 10 unités d'AXA à 200
        p.acheter(bnp, 20);  // 20 unités de BNP à 100
        
        // Enregistrement des valeurs des actions pour le jour j1
        bnp.enrgCours(j1, 100);
        axa.enrgCours(j1, 200);

        // Calcul des pourcentages
        Map<Action, String> pourcentages = p.calculerPourcentagesParAction(j1);
        
        // Vérification de l'exactitude des calculs
        float totalPercentage = 0f;
        for (String pctStr : pourcentages.values()) {
            totalPercentage += Float.parseFloat(pctStr.replace("%", ""));
        }

        // Vérification que le total des pourcentages est 100%
        assertEquals(100.0f, totalPercentage, 0.01f);

        // Vérification que chaque action a le pourcentage correct
        // La valeur totale du portefeuille est 10*200 + 20*100 = 4000
        // La contribution d'AXA est de 2000, ce qui devrait être 50% du portefeuille
        // La contribution de BNP est également de 2000, ce qui devrait être 50% du portefeuille
        assertEquals("50.00%", pourcentages.get(axa));
        assertEquals("50.00%", pourcentages.get(bnp));
        
    }
}