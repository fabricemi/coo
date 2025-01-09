package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.ChampignonVenimeux;
import org.example.modele.personnages.Personnage;

import java.util.List;
import java.util.Map;
import java.util.Random;

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
        if(estDanger(verifierDanger(matrice,ecureuil))){
            fuireDanger(matrice, ecureuil);
            return;
        }
         if (priority_standard) {
            seDeplacerCaseVide(matrice, ecureuil);
            ecureuil.diminuerNbrTour();
        }

        System.out.println("affamé");
    }


    private void manger(Map<Integer, List<ComposantJeu>> matrice, String sens,Ecureil ecureuil){
        //appliquerDeplacement(matrice, sens,ecureuil);
        seDeplacerPourManger(matrice, sens, ecureuil);


        if (aMangerApproximite(matrice, ecureuil)) {
            Personnage.getInstance().attacher(ecureuil);
        }
    }


    private boolean seDeplacerPourManger(Map<Integer, List<ComposantJeu>> matrice, String sens,Ecureil ecureuil){
        try {
            int col =ecureuil.caseAmodifier(sens)[0];
            int row =ecureuil.caseAmodifier(sens)[1];


            ZoneVide vide=new ZoneVide();
            vide.initPosition(ecureuil.getPosition().getX(), ecureuil.getPosition().getY());
            matrice.get(ecureuil.getPosition().getX()).remove(ecureuil.getPosition().getY());
            matrice.get(ecureuil.getPosition().getX()).add(ecureuil.getPosition().getY(), vide);

            //ecureuil.setEstRassasie(true);
            //ecureuil.setEstJankie(false);
            if(matrice.get(col).get(row) instanceof ChampignonVenimeux){
                ecureuil.setEstJankie(true);
                //ecureuil.setEstRassasie(false);
                ecureuil.setEtatEcureil(new EcureilJankie());
            }
            else {
                ecureuil.setEtatEcureil(new EcureilRassasie());
                ecureuil.setEstRassasie(true);
            }

            matrice.get(col).remove(row);
            matrice.get(col).add(row, ecureuil);
            ecureuil.setPosition(col,row);
            ecureuil.setNbrTour(6);
            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }


    @Override
    public String toString() {
        return  Colors.BLACK.getCode() + "E" + Colors.RESET.getCode();
    }
}
