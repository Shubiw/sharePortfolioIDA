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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

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
    
     /**
     * Test of acheter method, of class Portefeuille.
     */
     
    @Test
    public void testVendre() {
        Portefeuille instance = new Portefeuille();
        ActionSimple action = new ActionSimple("NomAction");
        int quantiteAVendre = 15;
        int quantiteAAcheter = 20;
        int quantiteAVendre2 = 5;
        String attenduAchat1 ="Action achetée: " + "NomAction"+  " d'une quantité de: "+ "20"  ;
        String attenduVendre1 ="Action vendue: " + "NomAction"+  " d'une quantité de: "+ "15"  ;
        String attenduVendre2 ="Action vendue: " + "NomAction"+  " d'une quantité de: "+ "5"  ;



        instance.acheter(action, quantiteAAcheter); // Achetez 10 actions
        instance.getHistoriqueAchat().contains("20");
        assertEquals(attenduAchat1, instance.getHistoriqueAchat().get(0));

        // Utilisez la méthode vendre pour tester la vente de quantité supérieure au stock
        instance.vendre(action, quantiteAVendre);
        assertEquals(attenduVendre1, instance.getHistoriqueVente().get(0));
        // Assurez-vous que l'action a été retirée du portefeuille après la tentative de vente
        instance.vendre(action, quantiteAVendre2);
        assertEquals(attenduVendre2, instance.getHistoriqueVente().get(1));
        
        assertFalse(instance.mapLignes.containsKey(action));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
 * Exécute une transaction simple, impliquant la création d'une instance de portefeuille,
 * l'achat et la vente d'une quantité spécifiée d'une action, et la vérification de l'historique des transactions.
 *
 * Cette méthode effectue les étapes suivantes :
 * 1. Crée une nouvelle instance de portefeuille en utilisant la classe Portefeuille.
 * 2. Crée une action simple sur une action nommée "NomAction" en utilisant la classe ActionSimple.
 * 3. Définit les quantités d'achat (quantiteAAcheter) et de vente (quantiteAVendre) de l'action.
 * 4. Exécute l'achat de l'action dans le portefeuille avec la quantité spécifiée.
 * 5. Exécute la vente de l'action du portefeuille avec la quantité spécifiée.
 * 6. Vérifie l'historique des transactions en vérifiant les événements d'achat et de vente enregistrés.
 *
 * Les chaînes attendues pour les événements d'achat et de vente sont générées et comparées
 * avec les événements réellement enregistrés dans l'historique du portefeuille.
 *
 */
    @Test
    public void transaction() {
        Portefeuille instance = new Portefeuille();
        ActionSimple a = new ActionSimple("NomAction");
        int quantiteAVendre = 10;
        int quantiteAAcheter = 10;
        
        String attenduAchat ="Action achetée: " + "NomAction"+  " d'une quantité de: "+ "10"  ;
        String attenduVente ="Action vendue: " + "NomAction"+  " d'une quantité de: "+ "10"  ;
        instance.acheter(a, quantiteAAcheter);
        instance.vendre(a, quantiteAVendre);
        assertEquals(attenduAchat, instance.getHistoriqueAchat().get(0));
        assertEquals(attenduVente, instance.getHistoriqueVente().get(0));
            
        
        
        
    }
    
    @Test
    public void testCalculerPourcentagesParAction() throws ParseException {
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
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // Locale avec virgule comme séparateur décimal
        float totalPercentage = 0f;
        for (String pctStr : pourcentages.values()) {
            // Remplacez les pourcentages par des points avant de parser
            totalPercentage += format.parse(pctStr.replace("%", "")).floatValue();
        }

        // Vérification que le total des pourcentages est 100%
        assertEquals(100.0f, totalPercentage, 0.01f, "La somme des pourcentages n'est pas égale à 100");

        // Vérification que chaque action a le pourcentage correct
        // La valeur totale du portefeuille est 10*200 + 20*100 = 4000
        // La contribution d'AXA est de 2000, ce qui devrait être 50% du portefeuille
        // La contribution de BNP est également de 2000, ce qui devrait être 50% du portefeuille
        assertEquals("50,00%", pourcentages.get(axa), "Le pourcentage d'AXA est incorrect");
        assertEquals("50,00%", pourcentages.get(bnp), "Le pourcentage de BNP est incorrect");

    }
    
}