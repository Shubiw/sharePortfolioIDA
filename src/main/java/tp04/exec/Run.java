/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.exec;

import java.util.ArrayList;
import tp04.metier.Action;
import tp04.metier.ActionComposee;
import tp04.metier.ActionSimple;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;

public class Run {
    
    public static void afficherBourse(ArrayList<Action> bourse, Jour j) {
        System.out.println("Cours de la bourse pour le " + j.getNoJour() + "/" + j.getAnnee());
        for (Action element : bourse) {
            if (element.valeur(j) != 0.0f) {
                System.out.println(element.getLibelle() + " : " + element.valeur(j));
            }
        }
    }

    public static void main(String[] args) {
        
        ArrayList<Action> bourse = new ArrayList<>();
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);
        
        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        bourse.add(bnp);
        axa = new ActionSimple("AXA");
        bourse.add(axa);
        bqAss = new ActionComposee("Banque-Assurance");
        bourse.add(bqAss);
        
        
        
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);
        // affichage des cours - comme 1 action simple et 1 action composee
        System.out.println("Action simple *bnp* à j1 : " + bnp.valeur(j1));
        System.out.println("Action *Banque-Assurance* à j2 : " + bqAss.valeur(j2));

        Portefeuille p;
        p = new Portefeuille();
        p.acheter(axa, 10);
        System.out.println("Portefeuille : " + p);
        p.acheter(bnp, 20);
        System.out.println("Portefeuille : " + p);
        p.acheter(bqAss, 5);
        System.out.println("Portefeuille : " + p);
        p.acheter(bqAss, 15);
        System.out.println("Portefeuille : " + p);
        System.out.println("Portefeuille à j1 : " + p.valeur(j1));
        p.vendre(axa, 5);
        System.out.println("Portefeuille : " + p);
        p.vendre(axa, 5);
        System.out.println("Portefeuille : " + p);
        p.vendre(axa, 5);
        System.out.println("Portefeuille : " + p);
        p.vendre(bnp, 50);
        System.out.println("Portefeuille : " + p);
        
        afficherBourse(bourse, j1);
 
    }

}
