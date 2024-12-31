package org.example.modele.animaux;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class EcureilRassasie extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {
        directionPrioritaire(matrice, ecureuil);
        if (priority_standard) {
            seDeplacerCaseVide(matrice, ecureuil);
            ecureuil.diminuerNbrTour();
        }
        System.out.println("rassaié");

    }
}
