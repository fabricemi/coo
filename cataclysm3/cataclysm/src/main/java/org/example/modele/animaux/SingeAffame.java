package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;
import org.example.modele.personnages.Personnage;

import java.util.List;
import java.util.Map;

public class SingeAffame extends EtatSinge{


    @Override
    public void deplacer( Singe singe,Map<Integer, List<ComposantJeu>> matrice) {
        directionPrioritaire(matrice, singe);

        if (priority_banane){
            manger(matrice, sens_assoc_banane, singe);
            singe.diminuerNbrTr();
            singe.peutDevenirAmi();
            return;
        }

        if (priority_champignon){
            manger(matrice, sens_assoc_champignon, singe);
            singe.diminuerNbrTr();
            singe.peutDevenirAmi();
            return;
        }

        if (priority_standard){
            seDeplacerCaseVide(matrice, singe);
        }

    }
}
