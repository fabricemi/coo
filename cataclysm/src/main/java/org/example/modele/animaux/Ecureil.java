package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.Gland;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class Ecureil extends Animaux implements Observateur {

    private static int globalIdCounter = 0;
    private final int id;

    {
        id = ++globalIdCounter;
    }

    String priorite;
    public boolean priority_gland = false;
    public String sens_assoc_gland;
    public boolean priority_champignon = false;
    public String sens_assoc_champignon;
    public boolean priority_standard = false;
    public boolean peut_devenir_ami = false;
    public boolean est_entoure_vegetaux = false;
    public String sens_assoc_standard;

    private boolean estRassasie;

    public List<String> sensAleatoire = new ArrayList<>();
    private int nbrTour;

    public int getId() {
        return id;
    }

    Personnage personnage;
    public boolean estAmi;
    private String rep;

    public Ecureil() {
        estAmi = false;
        estRassasie = true;
        nbrTour = 5;
        rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
    }

    @Override
    public String toString() {
        return rep;

    }

    @Override
    public void setEtat() {
        this.mettreAjouter();
        if (estAmi) {
            rep = Colors.PURPLE.getCode() + "E" + Colors.RESET.getCode();
        } else if (estAffameApres5tour()) {
            rep = Colors.BLACK.getCode() + "E" + Colors.RESET.getCode();
        } else if (estRassasie) {
            rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
        }
        System.out.println(rep + " est ami: " + estAmi
                + " a fait 5 tours : " + estAffameApres5tour() + " est rassasié : " + estRassasie + " nbr tour associé: " + nbrTour);

        if (estAffameApres5tour()) {
            resetNbrTour();
            estRassasie = false;
        }

    }

    @Override
    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice) {
        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            String sens = paire.getKey();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Gland) {
                priority_gland = true;
                sens_assoc_gland = sens;
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
        if (!(priority_gland || priority_champignon || priority_standard)) {
            est_entoure_vegetaux = true;
        }
    }


    public void resetNbrTour() {
        nbrTour = 5;
    }

    @Override
    public boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);
        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice).entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Personnage) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);
        this.directionPrioritaire(matrice);

        String priorite = (priority_gland && !estRassasie) ? "gland" :
                (priority_champignon && !estRassasie) ? "champignon" :
                        priority_standard ? "standard" : "none";

        switch (priorite) {
            case "gland":
                appliquerDeplacement(matrice, sens_assoc_gland);
                estRassasie = true;
                nbrTour = 5;
                if (aMangerApproximite(matrice)) {
                    personnage.attacher(this);
                }
                break;
            case "champignon":
                appliquerDeplacement(matrice, sens_assoc_champignon);
                estRassasie = true;
                nbrTour = 5;
                if (aMangerApproximite(matrice)) {
                    personnage.attacher(this);
                }
                break;
            case "standard":
                if (!sensAleatoire.isEmpty()) {
                    String seens = sensAleatoire.get(new Random().nextInt(sensAleatoire.size()));
                    appliquerDeplacement(matrice, seens);
                    this.sensAleatoire.clear();
                }
                break;
            default:
                break;
        }
        nbrTour--;
        priority_standard = false;
        priority_gland = false;
        priority_champignon = false;
        sens_assoc_gland = null;
        sens_assoc_champignon = null;
        sens_assoc_standard = null;

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


}
