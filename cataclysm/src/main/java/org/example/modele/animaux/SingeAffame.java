package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;
import org.example.modele.ZoneVide;
import org.example.modele.aliments.ChampignonH;
import org.example.modele.aliments.ChampignonVenimeux;
import org.example.modele.personnages.Personnage;

import java.util.List;
import java.util.Map;

public class SingeAffame extends EtatSinge{


    @Override
    public void deplacer( Singe singe,Map<Integer, List<ComposantJeu>> matrice) {
        directionPrioritaire(matrice, singe);
        if(estDanger(verifierDanger(matrice, singe))){
            System.out.println(singe.getPosition()+" est entouré de: "+verifierDanger(matrice, singe));
            if(singe.estEntoureCocotier(matrice)){
                singe.setInCocotier(true);
                System.out.println("un");

            } else if(singe.estEntoureRocher(matrice)){
                singe.setInRocher(true);
                System.out.println("deux");
            }
            else {
                setSensDanger(verifierDanger(matrice, singe));
                courir(singe,sens_oppos_danger,matrice);
                System.out.println("sens inverse");
            }
            return;
        }
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
        singe.diminuerNbrTr();

    }

    @Override
    public String toString() {
        return Colors.BLACK.getCode() + "S" + Colors.RESET.getCode();
    }
}
