package org.example.modele.animaux;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class EcureilEffraye extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {
        ecureuil.diminuerNbrTouEffraye();
        System.out.println("deplecement effrayé");
        System.out.println("nbr tr reste"+ ecureuil.getNbrTourEffraye());
        System.out.println(ecureuil.getPosition());
        if(ecureuil.getNbrTourEffraye()<=0){
            ecureuil.setEstEffraye(false);
            ecureuil.setEstRefugieArbre(false);
            ecureuil.setNbrTourEffraye(3);
        }

    }
}
