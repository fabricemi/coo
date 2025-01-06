package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.ChampignonVenimeux;
import org.example.modele.aliments.Gland;
import org.example.modele.personnages.Personnage;

import java.util.*;

public abstract class EtatEcureil  {
    protected boolean priority_gland = false;
    protected String sens_assoc_gland;
    protected boolean priority_champignon = false;
    protected String sens_assoc_champignon;
    protected boolean priority_standard = false;
    protected  String sens_oppos_danger;

    public List<String> sensAleatoire = new ArrayList<>();

   public abstract void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice);


    public boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureuil) {
        ecureuil.elementAutours(matrice);
        Set<Map.Entry<String, ComposantJeu>> tests = ecureuil.elementAutours(matrice).entrySet();
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





    public void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureil){
        if (!sensAleatoire.isEmpty()) {
            String seens = sensAleatoire.get(new Random().nextInt(sensAleatoire.size()));
            //appliquerDeplacement(matrice, seens, ecureil);
            ecureil.appliquerDeplacement(matrice, seens);
            this.sensAleatoire.clear();
        }
    }


    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureuil) {

        Set<Map.Entry<String, ComposantJeu>> tests = ecureuil.elementAutours(matrice).entrySet();

        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            String sens = paire.getKey();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Gland) {
                priority_gland = true;
                sens_assoc_gland = sens;
            }
            if (composantJeu instanceof Champignon || composantJeu instanceof ChampignonVenimeux) {
                priority_champignon = true;
                sens_assoc_champignon = sens;
            }
            if (composantJeu instanceof ZoneVide) {
                priority_standard = true;
                sensAleatoire.add(sens);
            }
        }

    }

    public  Map<String, Boolean> verifierDanger(Map<Integer,
            List<ComposantJeu>> matrice, Ecureil ecureil)
            throws IndexOutOfBoundsException, NullPointerException {

        Map<String, Boolean> dirDangers=new TreeMap<>();
        dirDangers.put("D", false);
        dirDangers.put("G", false);
        dirDangers.put("H", false);
        dirDangers.put("B", false);

        int x=ecureil.getPosition().getX();
        int y=ecureil.getPosition().getY();


        for (int i = 1; i <4 ; i++) {
            if (sensValide(x,y+i,matrice)) {
                ComposantJeu composantJeu=matrice.get(x).get(y + i);
                if(composantJeu instanceof Hibou || composantJeu instanceof Renard){
                    dirDangers.put("D", true);
                }
            }
            if ( sensValide(x,y-i,matrice)) {
                ComposantJeu composantJeu1=matrice.get(x).get(y - i);
                if(composantJeu1 instanceof Hibou || composantJeu1 instanceof Renard){
                    dirDangers.put("G", true);
                }
            }
            if (sensValide(x-i,y,matrice)) {
                ComposantJeu composantJeu2=matrice.get(x - i).get(y);
                if(composantJeu2 instanceof Hibou || composantJeu2 instanceof Renard){
                    dirDangers.put("H", true);
                }
            }
            if (sensValide(x+i,y,matrice)) {
                ComposantJeu composantJeu3=matrice.get(x + i).get(y);
                if(composantJeu3 instanceof Hibou || composantJeu3 instanceof Renard){
                    dirDangers.put("B", true);
                }
            }
        }




        return dirDangers;
    }

    public void setSensDanger(Map<String, Boolean> dirDangers){
        if (dirDangers.get("D")) {
            sens_oppos_danger = "G";
        } else if (dirDangers.get("G")) {
            sens_oppos_danger = "D";
        } else if (dirDangers.get("H")) {
            sens_oppos_danger = "B";
        } else {
            sens_oppos_danger = "H";
        }
    }

    public boolean faire_courir(int x, int y,  Map<Integer,
            List<ComposantJeu>> matrice, Ecureil ecureil){
        if(sensValide(x,y,matrice) ){
            if(matrice.get(x).get(y) instanceof ZoneVide){
                ZoneVide zoneVide=(ZoneVide) matrice.get(x).get(y);
                int n_x=zoneVide.getPosition().getX();
                int n_y=zoneVide.getPosition().getY();

                ZoneVide vide=new ZoneVide();
                vide.initPosition(x,y);
                matrice.get(ecureil.getPosition().getX()).remove(ecureil.getPosition().getY());
                matrice.get(ecureil.getPosition().getX()).add(ecureil.getPosition().getY(),vide);

                matrice.get(n_x).remove(n_y);
                matrice.get(n_x).add(n_y, ecureil);
                ecureil.setPosition(n_x, n_y);
                return true;
            }
        }

        return false;
    }
    public void courir(Ecureil ecureil, String sens,Map<Integer,
            List<ComposantJeu>> matrice){
        int x=ecureil.getPosition().getX();
        int y=ecureil.getPosition().getY();

        if(sens.equals("D")){
            for (int i = y+1; i <matrice.get(x).size(); i++) {
               if(faire_courir(x,i,matrice, ecureil)){
                   break;
               }
            }
        }
        else if (sens.equals("G")){
            if(y!=0){
                for (int i = y-1; i >=0; i--) {
                    if(faire_courir(x,i,matrice, ecureil)){
                        break;
                    }
                }
            }

        } else if (sens.equalsIgnoreCase("B")) {
            for (int i = x+1; i <matrice.size(); i++) {
                if(faire_courir(i,y,matrice, ecureil)){
                    break;
                }
            }
        }
        else {
            if(x!=0){
                for (int i = x-1; i >=0; i--) {
                    if(faire_courir(i,y,matrice, ecureil)){
                        break;
                    }
                }
            }
        }
    }
    public boolean estDanger(Map<String, Boolean> dangers){
        return dangers.get("D") || dangers.get("H") || dangers.get("G") || dangers.get("B");
    }

    private static boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }

    public void fuireDanger(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureuil){
        ecureuil.mettreAjouter();
        if(ecureuil.isEstAmi() && aMangerApproximite(matrice,ecureuil)){
            Personnage.getInstance().proteger(ecureuil);

            ZoneVide vide1 = new ZoneVide();
            vide1.initPosition(ecureuil.getPosition().getX(), ecureuil.getPosition().getY());

            matrice.get(ecureuil.getPosition().getX()).remove(ecureuil);
            matrice.get(ecureuil.getPosition().getX()).add(ecureuil.getPosition().getY(), vide1);

            System.out.println("protégé");

            return;
        }

        if(!ecureuil.estEntoureBouA(matrice).isEmpty()){
            List<ComposantJeu> jeus=ecureuil.estEntoureBouA(matrice);
            Vegetaux v=(Vegetaux) jeus.get(new Random().nextInt(jeus.size()));
            if(v instanceof Arbre){
                ecureuil.setEstRefugieArbre(true);
                ecureuil.setEstRefugieBuisson(false);
            }
            else {
                ecureuil.setEstRefugieBuisson(true);
                ecureuil.setEstRefugieArbre(false);
            }
            System.out.println("perché");
            return;
        }

        setSensDanger(verifierDanger(matrice,ecureuil));
        courir(ecureuil,sens_oppos_danger,matrice);
        ecureuil.diminuerNbrTour();
        System.out.println("sens inverse");
    }
}
