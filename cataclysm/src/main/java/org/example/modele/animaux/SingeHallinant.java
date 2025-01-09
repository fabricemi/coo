package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class SingeHallinant extends EtatSinge{

    @Override
    public void deplacer(Singe singe, Map<Integer, List<ComposantJeu>> matrice) {
        singe.diminuerNbrTr();
        directionPrioritaire(matrice, singe);
        if(priority_standard){
            seDeplacerCaseVide(matrice, singe);
        }
    }

    @Override
    public String toString() {
        return Colors.YELLOW.getCode() + "S" + Colors.RESET.getCode();
    }
}
