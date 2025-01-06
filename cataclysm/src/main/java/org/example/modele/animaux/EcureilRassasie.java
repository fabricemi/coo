package org.example.modele.animaux;

import org.example.modele.Arbre;
import org.example.modele.ComposantJeu;
import org.example.modele.personnages.Personnage;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EcureilRassasie extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {

        ecureuil.setEstRefugieBuisson(false);
        ecureuil.setEstRefugieArbre(false);
        if(estDanger(verifierDanger(matrice,ecureuil))){
            fuireDanger(matrice,ecureuil);
            return;
        }

        directionPrioritaire(matrice, ecureuil);
        if (priority_standard) {
            seDeplacerCaseVide(matrice, ecureuil);
            ecureuil.diminuerNbrTour();
        }
        System.out.println("rassaié");

    }
}
