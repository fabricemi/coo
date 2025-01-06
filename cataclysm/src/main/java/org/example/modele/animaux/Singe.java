package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class Singe extends Animaux implements Observateur {

    private int nbr_consecutif_approx_pers=0;



    Personnage personnage;
    private boolean estRassasie;

    private boolean estAmi;
    private String rep;

    private int nbrTour;

    private EtatSinge etatSinge;

    public Singe() {
        estAmi = false;
        estRassasie = true;
        nbrTour = 3;
        rep = Colors.BLUE.getCode() + "S" + Colors.RESET.getCode();
    }

    public void setEtatSinge(EtatSinge etatSinge) {
        this.etatSinge = etatSinge;
    }

    @Override
    public String toString() {
        return rep;
    }

    @Override
    public void mettreAjouter() {
        personnage = Personnage.getInstance();
        estAmi = personnage.getAmis().contains(this);
    }





    public boolean estAffameApres3tour() {
        return nbrTour <= 0;
    }

    public void resetNbrTour() {
        nbrTour = 3;
    }
    @Override
    public void setApparence() {
        this.mettreAjouter();
        if (estAmi) {

            rep=Colors.PURPLE.getCode() + "S" + Colors.RESET.getCode();
        } else if (estAffameApres3tour()) {
            rep=Colors.BLACK.getCode() + "S" + Colors.RESET.getCode();
        } else if (estRassasie) {
            rep=Colors.BLUE.getCode() + "S" + Colors.RESET.getCode();
        }
       // System.out.println(rep + " est ami: " + estAmi
           //     + " a fait 3 tours : " + estAffameApres3tour() + " est rassasié : " + estRassasie + " nbr tour associé: " + nbrTour);

        if (estAffameApres3tour()) {
            resetNbrTour();
            estRassasie = false;
        }
    }

    public void setEstAmi(boolean estAmi) {
        this.estAmi = estAmi;
    }


    public void setEstRassasie(boolean estRassasie) {
        this.estRassasie = estRassasie;
    }



    public  void diminuerNbrTr(){
        nbrTour--;
    }
    @Override
    public Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {

        if(estRassasie){
            setEtatSinge(new SingeRassasie());
        }
        else {
            setEtatSinge(new SingeAffame());
        }

        etatSinge.deplacer(this, matrice);
        return matrice;
    }


    public void peutDevenirAmi(){
        if(nbr_consecutif_approx_pers==2){
            personnage.attacher(this);
            nbr_consecutif_approx_pers=0;
        }
    }

    public boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice){
        this.elementAutours(matrice);
        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Personnage) {
                nbr_consecutif_approx_pers++;
                return true;
            }
        }
        return false;
    }

}
