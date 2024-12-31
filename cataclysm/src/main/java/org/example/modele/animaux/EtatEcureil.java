package org.example.modele.animaux;

import org.example.modele.ComposantJeu;
import org.example.modele.Utils;
import org.example.modele.ZoneVide;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.Gland;
import org.example.modele.personnages.Personnage;

import java.util.*;

public abstract class EtatEcureil  {
    protected boolean priority_gland = false;
    protected String sens_assoc_gland;
    protected boolean priority_champignon = false;
    protected String sens_assoc_champignon;
    protected boolean priority_standard = false;

    public List<String> sensAleatoire = new ArrayList<>();

   public abstract void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice);


    public boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureuil) {
        this.elementAutours(matrice, ecureuil);
        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice, ecureuil).entrySet();
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
    public void manger(Map<Integer, List<ComposantJeu>> matrice, String sens,Ecureil ecureuil){
        appliquerDeplacement(matrice, sens,ecureuil);
        ecureuil.setNbrTour(5);
        ecureuil.setEstRassasie(true);
        if (aMangerApproximite(matrice, ecureuil)) {
            Personnage.getInstance().attacher(ecureuil);
        }
    }
    protected boolean appliquerDeplacement(Map<Integer, List<ComposantJeu>> matrice, String sens , Ecureil ecureuil){
        try {
            int col =ecureuil.caseAmodifier(sens)[0];
            int row =ecureuil.caseAmodifier(sens)[1];
            matrice.get(ecureuil.getPosition().getX()).remove(ecureuil.getPosition().getY());
            matrice.get(ecureuil.getPosition().getX()).add(ecureuil.getPosition().getY(), new ZoneVide());

            matrice.get(col).remove(row);
            matrice.get(col).add(row, ecureuil);
            ecureuil.setPosition(col,row);

            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }
    public void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureil){
        if (!sensAleatoire.isEmpty()) {
            String seens = sensAleatoire.get(new Random().nextInt(sensAleatoire.size()));
            appliquerDeplacement(matrice, seens, ecureil);
            this.sensAleatoire.clear();
        }
    }

    public Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice,Ecureil ecureuil)
    {
        int x = ecureuil.getPosition().getX();
        int y = ecureuil.getPosition().getY();
        return Utils.elementAutours(matrice,x,y);
    }
    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureuil) {

        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice, ecureuil).entrySet();
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

    }
}
