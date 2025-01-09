package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.ChampignonVenimeux;
import org.example.modele.aliments.Gland;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class Ecureil extends Animaux implements Observateur {

    private static int globalIdCounter = 0;
    private final int id;

    {
        id = ++globalIdCounter;
    }


    private boolean estRassasie;


    private int nbrTour;

    public int getId() {
        return id;
    }

    Personnage personnage;
    public boolean estAmi;
    public boolean estRefugieArbre;
    public boolean estRefugieBuisson;

    public boolean estEffraye;
    public boolean estJankie;

    private int nbrTourEffraye;
    private String rep;
    EtatEcureil etatEcureil;

    public Ecureil() {
        estJankie=false;
        estEffraye=false;
        estAmi = false;
        estRefugieArbre=false;
        estRefugieBuisson=false;


        estRassasie = true;
        nbrTour = 5;
        nbrTourEffraye=3;
        rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
        setEtatEcureil(new EcureilRassasie());
    }

    public void setEtatEcureil(EtatEcureil etatEcureil) {
        this.etatEcureil = etatEcureil;
    }

    @Override
    public String toString() {
        return rep;
    }


    public boolean isEstRefugieArbre() {
        return estRefugieArbre;
    }

    public void setEstRefugieArbre(boolean estRefugieArbre) {
        this.estRefugieArbre = estRefugieArbre;
    }

    public boolean isEstRefugieBuisson() {
        return estRefugieBuisson;
    }

    public static int getGlobalIdCounter() {
        return globalIdCounter;
    }

    public static void setGlobalIdCounter(int globalIdCounter) {
        Ecureil.globalIdCounter = globalIdCounter;
    }

    public boolean isEstEffraye() {
        return estEffraye;
    }

    public void setEstEffraye(boolean estEffraye) {
        this.estEffraye = estEffraye;
    }

    public boolean isEstJankie() {
        return estJankie;
    }

    public void setEstJankie(boolean estJankie) {
        this.estJankie = estJankie;
    }

    public void setEstRefugieBuisson(boolean estRefugieBuisson) {
        this.estRefugieBuisson = estRefugieBuisson;
    }

    public boolean isEstRassasie() {
        return estRassasie;
    }

    @Override
    public void setApparence() {

        this.mettreAjouter();

        if (estEffraye){
            rep = Colors.WHITE.getCode() + "E" + Colors.RESET.getCode();
            setEtatEcureil(new EcureilEffraye());
            return;
        }
        if(estJankie){
            rep = Colors.RED.getCode() + "E" + Colors.RESET.getCode();
            setEtatEcureil(new EcureilJankie());
            return;
        }


        if (estAmi) {
            rep = Colors.PURPLE.getCode() + "E" + Colors.RESET.getCode();
        } else if (estAffameApres5tour()) {
            rep = Colors.BLACK.getCode() + "E" + Colors.RESET.getCode();
           // setEtatEcureil(new EcureilAffame());
        } else if (estRassasie) {
            setEtatEcureil(new EcureilRassasie());
            rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
        }

        if (estRefugieArbre){
            rep = Colors.GREEN.getCode() + "E" + Colors.RESET.getCode();
        }
        if (estRefugieBuisson){
            rep = Colors.YELLOW.getCode() + "E" + Colors.RESET.getCode();
        }
        System.out.println(rep+"pos= "+this.getPosition().toString()+" est effrayé" + " est ami: " + estAmi
                + " a fait 5 tours : " + estAffameApres5tour()
                + " est rassasié : " + estRassasie + " nbr tour associé: " + nbrTour);
        System.out.println(rep+" pos= "+this.getPosition().toString()+" est effrayé " + estEffraye);

        if (estAffameApres5tour()) {
            resetNbrTour();
            estRassasie = false;
            estJankie=false;
            setEtatEcureil(new EcureilAffame());
        }



    }

    public List<ComposantJeu> estEntoureBouA(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);

        Set<Map.Entry<String, ComposantJeu>> tests = this.elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();

        List<ComposantJeu> arbres=new ArrayList<>();
        List<ComposantJeu> buissons=new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Arbre) {
                arbres.add(composantJeu);
            }
            if(composantJeu instanceof  Buisson){
                buissons.add(composantJeu);
            }
        }

        if (!arbres.isEmpty()){
            return  arbres;
        }
        if (!buissons.isEmpty()){
            return  buissons;
        }
        return new ArrayList<>();
    }

    public List<ComposantJeu> estEntoureBuisson(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);

        Set<Map.Entry<String, ComposantJeu>> tests = this.elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        List<ComposantJeu> buissons=new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            ComposantJeu composantJeu = paire.getValue();
            if(composantJeu instanceof  Buisson){
                buissons.add(composantJeu);
            }
        }
        if (!buissons.isEmpty()){
            return  buissons;
        }
        return new ArrayList<>();
    }

    public boolean isEstAmi() {
        return estAmi;
    }

    public void resetNbrTour() {
        nbrTour = 5;
    }

    public void setNbrTourEffraye(int nbrTourEffraye) {
        this.nbrTourEffraye = nbrTourEffraye;
    }

    public void  diminuerNbrTour(){
        nbrTour--;
   }

    public int getNbrTourEffraye() {
        return nbrTourEffraye;
    }

    public void diminuerNbrTouEffraye(){
        nbrTourEffraye--;
    }

    @Override
    public Map<Integer, List<ComposantJeu>>  seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
   /*     if(estEffraye){
            setEtatEcureil(new EcureilEffraye());
            etatEcureil.deplacer(this,matrice);
            return;
        }
        if(isEstJankie()){
            setEtatEcureil(new EcureilJankie());
            etatEcureil.deplacer(this,matrice);
            return;
        }

        if (estRassasie){
            setEtatEcureil(new EcureilRassasie());
        }
        else {
            setEtatEcureil(new EcureilAffame());
        }*/
         if(estRefugieBuisson || estRefugieArbre){
             setEstRefugieArbre(false);
             setEstRefugieBuisson(false);
         }
        etatEcureil.deplacer(this,matrice);
        return matrice;
    }

    public boolean estAffameApres5tour() {
        return nbrTour <= 0;
    }



    @Override
    public void mettreAjouter() {
        personnage = Personnage.getInstance();
        estAmi = personnage.getAmis().contains(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ecureil ecureil = (Ecureil) o;
        return id == ecureil.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setEstRassasie(boolean estRassasie) {
        this.estRassasie = estRassasie;
    }

    public void setNbrTour(int nbrTour) {
        this.nbrTour = nbrTour;
    }



}
