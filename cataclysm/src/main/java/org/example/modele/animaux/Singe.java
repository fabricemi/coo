package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class Singe extends Animaux implements Observateur {
    //Map<String, ComposantJeu> eltsAutours = new TreeMap<>();

    String priorite;
    private boolean priority_banane = false;
    private boolean priority_champignon = false;
    private String sens_assoc_banane;
    private String sens_assoc_champignon;
    private boolean priority_standard = false;

    private int nbr_consecutif_approx_pers=0;
    private String sens_assoc_standard;


    Personnage personnage;
    private boolean estRassasie;

    private boolean estAmi;
    private String rep;

    public List<String> sensAleatoire = new ArrayList<>();
    private int nbrTour;

    private EtatSinge etatSinge;

    public Singe() {
        estAmi = false;
        estRassasie = true;
        nbrTour = 3;
        rep = Colors.BLUE.getCode() + "S" + Colors.RESET.getCode();
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

    @Override
    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice) {
        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            String sens = paire.getKey();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Banane) {
                priority_banane = true;
                sens_assoc_banane = sens;
            }
            if (composantJeu instanceof Champignon) {
                priority_champignon = true;
                sens_assoc_champignon = sens;
            }
            if (composantJeu instanceof ZoneVide) {
                priority_standard = true;
                sensAleatoire.add(sens);
            }

        }

    }


    public boolean estAffameApres3tour() {
        return nbrTour <= 0;
    }

    public void resetNbrTour() {
        nbrTour = 3;
    }
    @Override
    public void setEtat() {
        this.mettreAjouter();
        if (estAmi) {
            etatSinge=new SingeEstAmi();
        } else if (estAffameApres3tour()) {
            etatSinge=new SingeAffame();
        } else if (estRassasie) {
            etatSinge=new SingeRassasie();
        }
        rep=etatSinge.gererEtat();
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

    @Override
    public void manger(Map<Integer, List<ComposantJeu>> matrice, String sens){
        appliquerDeplacement(matrice, sens);
        aMangerApproximite(matrice);
        estRassasie = true;
        nbrTour = 4;
    }

    @Override
    public void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice){
        if(!sensAleatoire.isEmpty()){
            String seens = sensAleatoire.get(new Random().nextInt(sensAleatoire.size()));
            appliquerDeplacement(matrice, seens);
            this.sensAleatoire.clear();
        }
    }
    @Override
    public void seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);
        this.directionPrioritaire(matrice);
        System.out.println(rep + " est ami: " + estAmi
                + " a fait 3 tours : " + estAffameApres3tour() + " est rassasié : "
                + estRassasie + " nbr tour associé: " + nbrTour);
        String priorite = (priority_banane && !estRassasie) ? "banane" :
                (priority_champignon && !estRassasie) ? "champignon" :
                        priority_standard ? "standard" : "none";

        switch (priorite) {
            case "banane":
                manger(matrice,sens_assoc_banane);
                break;
            case "champignon":
                manger(matrice,sens_assoc_champignon);
                break;
            case "standard":
                seDeplacerCaseVide(matrice);
                break;
            default:
                break;
        }
        nbrTour--;

        if(nbr_consecutif_approx_pers==2){
            personnage.attacher(this);
            nbr_consecutif_approx_pers=0;
        }


        priority_standard = false;
        priority_champignon = false;
        priority_banane=false;
        sens_assoc_banane=null;
        sens_assoc_champignon = null;
        sens_assoc_standard = null;
    }

    @Override
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
