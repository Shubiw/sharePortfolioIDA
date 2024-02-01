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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {
    
    Map<Action, LignePortefeuille> mapLignes;
    private ArrayList<String> historiqueAchat = new ArrayList<String>();
    private ArrayList<String> historiqueVente = new ArrayList<String>();
    
    /**
    * Récupère l'historique des transactions de vente du portefeuille.
    *
    * @return Une liste (ArrayList) contenant les transactions de vente du portefeuille.
    */
    public ArrayList getHistoriqueVente() {
            return historiqueVente;
        }
   
    
    /**
    * Récupère l'historique des transactions d'achat du portefeuille.
    *
    * @return Une liste (ArrayList) contenant les transactions d'achat du portefeuille.
    */
    public ArrayList getHistoriqueAchat() {
            return historiqueAchat;
        }
    
    


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
    * Enregistre une transaction de vente dans l'historique du portefeuille.
    *
    * @param a L'action vendue.
    * @param q La quantité d'actions vendues.
    *
    * La méthode crée une chaîne de description de la vente, comprenant le libellé de l'action
    * et la quantité vendue, puis l'ajoute à l'historique des ventes du portefeuille.
    */
    public void setHistoriqueVente(Action a, int q){
            String element ="Action vendue: " + a.getLibelle()+  " d'une quantité de: "+ String.valueOf(q)  ;
            this.historiqueVente.add(element);
    }
    /**
    * Enregistre une transaction d'achat dans l'historique du portefeuille.
    *
    * @param a L'action achetée.
    * @param q La quantité d'actions achetées.
    *
    * La méthode crée une chaîne de description de l'achat, comprenant le libellé de l'action
    * et la quantité achetée, puis l'ajoute à l'historique des achats du portefeuille.
    */
    public void setHistoriqueAchat(Action a, int q){
            String element ="Action achetée: " + a.getLibelle()+  " d'une quantité de: "+ String.valueOf(q);
            this.historiqueAchat.add(element);
    }
     /* Calcule et affiche le pourcentage de la valeur totale du portefeuille 
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
            setHistoriqueAchat(a, q);
            
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
            setHistoriqueAchat(a, q);
        }
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
                setHistoriqueVente(a, q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                setHistoriqueVente(a, q);
                this.mapLignes.remove(a);
            }
            else{
                System.out.println("Il n'est pas possible de vendre plus d'action qu'on en possède");
            }
        }      
        else{
            System.out.println("Vous n'avez pas les ressources pour vendre");
        }
    }
    
    /**
     * Cette méthode retourne le cours d'une action pour un jour donné
     * @param a Action du portefeuille
     * @param j Jour donné
     * @return un float qui est la valeur quotidienne d'une action pour un jour donné
     */
    public Float getCoursDuJour(Action a, Jour j) {
        return this.mapLignes.get(a).getAction().valeur(j); //Récupération de cours de l'action pour le jour donné
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
    /**
     * Cette méthode affiche le libellé d'une action, son cours pour un jour donné, ainsi que le pourcentage de l'action dans le portefeuille.
     * @param j Jour donné
     * @return affichage qui est un String 
     */
    public String coursPourcentageJour(Jour j) {
        /*Initialisation*/
        float totalPortefeuille = this.valeur(j); //Valeur totale des actions du portefeuille pour un jour donné
        float totalAction; //Valeur totale d'une ligne dans un portefeuille
        String affichage = ""; //Affichage de tous les cours d'une journée pour un portefeuille
        
        /*Traitement*/
        for (LignePortefeuille lp : this.mapLignes.values()) {
            totalAction = lp.getQte() * lp.getAction().valeur(j); //Calcul de la valeur de totale de chaque action dans un portefeuille
            affichage = affichage + lp.getAction().getLibelle() 
                    + " : \nCours du portefeuille = " + totalAction 
                    + "\nPourcentage de l'action = " + String.format("%.2f", totalAction / totalPortefeuille * 100)+"%"; // = "Libelle de l'action" + "Cours du portefeuille" + "Pourcentage de l'action"
            affichage = affichage + "\n\n"; //Saut de 2 lignes
        } 
        
        return affichage;
    }
}