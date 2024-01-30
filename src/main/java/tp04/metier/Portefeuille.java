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

    public ArrayList getHistVente() {
            return historiqueVente;
        }
        
    public void setHistVente(ArrayList hist) {
            this.historiqueVente = hist;
        }
    
    public ArrayList getHistAchat() {
            return historiqueAchat;
        }
        
    public void setHistAchat(ArrayList hist) {
            this.historiqueAchat = hist;
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

    public void setHistoriqueVente(Action a){
            String element ="Action vendue: " + a.getLibelle()+  " d'une quantité de: "+ String.valueOf(this.mapLignes.get(a).getQte())  ;
            this.historiqueVente.add(element);
    }
    
    public void setHistoriqueAchat(Action a){
            String element ="Action achetée: " + a.getLibelle()+  " d'une quantité de: "+ String.valueOf(this.mapLignes.get(a).getQte())  ;
            this.historiqueAchat.add(element);
    }
    
    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
            setHistoriqueAchat(a);
            
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
            setHistoriqueAchat(a);
        }
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
                setHistoriqueVente(a);
            } else if (this.mapLignes.get(a).getQte() == q) {
                setHistoriqueVente(a);
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
