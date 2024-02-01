/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
