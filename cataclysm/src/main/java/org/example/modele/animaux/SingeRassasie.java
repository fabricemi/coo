package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class SingeRassasie extends EtatSinge{

    @Override
    public void deplacer(Singe singe,Map<Integer, List<ComposantJeu>> matrice) {
        directionPrioritaire(matrice, singe);

        if(priority_standard){
            seDeplacerCaseVide(matrice, singe);
            singe.diminuerNbrTr();
        }
    }
}
