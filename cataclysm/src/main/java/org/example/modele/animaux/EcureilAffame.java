package org.example.modele.animaux;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class EcureilAffame extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {
        directionPrioritaire(matrice, ecureuil);

        if (priority_gland){
            manger(matrice, sens_assoc_gland,ecureuil);
            ecureuil.diminuerNbrTour();
            return;
        }
         if (priority_champignon) {
            manger(matrice, sens_assoc_champignon,ecureuil);
            ecureuil.diminuerNbrTour();
            return;
         }

         if (priority_standard) {
            seDeplacerCaseVide(matrice, ecureuil);
             ecureuil.diminuerNbrTour();
        }

        System.out.println("affamé");
    }



}
