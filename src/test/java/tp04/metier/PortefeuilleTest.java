package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import tp04.metier.ActionSimple;
import tp04.metier.ActionComposee;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PortefeuilleTest {

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
