package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class EcureilEffraye extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {
        ecureuil.diminuerNbrTouEffraye();
        System.out.println("effrayé : il reste "+ecureuil.getNbrTourEffraye());


        if (ecureuil.estAffameApres5tour()) {
            ecureuil.resetNbrTour();
            ecureuil.setEstRassasie(false);
        }
        //System.out.println("deplecement effrayé à la position "+ecureuil.getPosition());
        //System.out.println("nbr tr reste"+ ecureuil.getNbrTourEffraye());

        if(ecureuil.getNbrTourEffraye()<=0){
            ecureuil.setEstEffraye(false);
            ecureuil.setEstRefugieArbre(false);
            ecureuil.setEstRefugieBuisson(false);
            ecureuil.setNbrTourEffraye(3);
            if(ecureuil.isEstJankie()){
               ecureuil.setEtatEcureil(new EcureilJankie());
            } else if (ecureuil.isEstRassasie()) {
                ecureuil.setEtatEcureil(new EcureilRassasie());
            }

        }



    }

    @Override
    public String toString() {
        return Colors.WHITE.getCode() + "E" + Colors.RESET.getCode();
    }
}
