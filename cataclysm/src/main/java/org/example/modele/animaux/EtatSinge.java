package org.example.modele.animaux;

import org.example.modele.ComposantJeu;
import org.example.modele.Utils;
import org.example.modele.ZoneVide;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.personnages.Personnage;

import java.util.*;

public abstract class EtatSinge {
    protected boolean priority_banane = false;
    protected boolean priority_champignon = false;
    protected String sens_assoc_banane;
    protected String sens_assoc_champignon;
    protected boolean priority_standard = false;
    public List<String> sensAleatoire = new ArrayList<>();

    public abstract void deplacer( Singe ecureuil,Map<Integer, List<ComposantJeu>> matrice);




    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice, Singe singe) {
        Set<Map.Entry<String, ComposantJeu>> tests = singe.elementAutours(matrice).entrySet();
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


    public void manger(Map<Integer, List<ComposantJeu>> matrice, String sens, Singe singe){
        singe.appliquerDeplacement(matrice, sens);
        singe.aMangerApproximite(matrice);
        singe.resetNbrTour();
        singe.setEstRassasie(true);
    }


    public void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice, Singe singe){
        if(!sensAleatoire.isEmpty()){
            String seens = sensAleatoire.get(new Random().nextInt(sensAleatoire.size()));
            singe.appliquerDeplacement(matrice, seens);
            this.sensAleatoire.clear();
        }
    }
}
