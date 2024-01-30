/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {
    
    Map<Action, LignePortefeuille> mapLignes;
    
    
    private class LignePortefeuille {
        
        private Action action;
        
        private int qte;
        
        public int getQte() {
            return qte;
        }
        
        public void setQte(int qte) {
            this.qte = qte;
        }
        
        public Action getAction() {
            return this.action;
        }
        
        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        public String toString() {
            return Integer.toString(qte);
        }
    }
    
    public Portefeuille() {
        this.mapLignes = new HashMap();
    }
    /**
     * Calcule et affiche le pourcentage de la valeur totale du portefeuille 
     * pour chaque action contenue dans le portefeuille.
     * 
     * @param jour Le jour pour lequel calculer les pourcentages.
     */
    public Map<Action, String> calculerPourcentagesParAction(Jour jour) {
    Map<Action, String> pourcentages = new HashMap<>();
    float valeurTotale = this.valeur(jour);

    for (Map.Entry<Action, LignePortefeuille> entry : mapLignes.entrySet()) {
        Action action = entry.getKey();
        LignePortefeuille ligne = entry.getValue();
        float valeurAction = action.valeur(jour) * ligne.getQte();
        float pourcentage = (valeurAction / valeurTotale) * 100;
        pourcentages.put(action, String.format("%.2f", pourcentage) + "%");
    }

    return pourcentages;
    
}

public void afficherPourcentagesParAction(Jour jour) {
    
    System.out.println("Répartition du portefeuille en pourcentage pour le jour " + jour.getNoJour() + "/" + jour.getAnnee() + " :");
    Map<Action, String> pourcentages = calculerPourcentagesParAction(jour);
    pourcentages.forEach((action, pct) -> System.out.println(action + ": " + pct));
}

    
    
    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
        }        
    }
    
    public String toString() {
        return this.mapLignes.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
}
