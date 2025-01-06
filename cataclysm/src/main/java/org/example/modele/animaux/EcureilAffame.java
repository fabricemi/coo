package org.example.modele.animaux;

import org.example.modele.Arbre;
import org.example.modele.ComposantJeu;
import org.example.modele.Observateur;
import org.example.modele.ZoneVide;
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
        ecureuil.setNbrTour(5);
        ecureuil.setEstRassasie(true);
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

            if(matrice.get(col).get(row) instanceof ChampignonVenimeux){
                ecureuil.setEstJankie(true);
            }

            matrice.get(col).remove(row);
            matrice.get(col).add(row, ecureuil);
            ecureuil.setPosition(col,row);
            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }




}
