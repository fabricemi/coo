package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class SingeRassasie extends EtatSinge{

    @Override
    public void deplacer(Singe singe,Map<Integer, List<ComposantJeu>> matrice) {

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

        directionPrioritaire(matrice, singe);
        if(priority_standard){
            seDeplacerCaseVide(matrice, singe);
            singe.diminuerNbrTr();
        }
    }

    @Override
    public String toString() {
        return Colors.BLUE.getCode() + "S" + Colors.RESET.getCode();
    }
}
