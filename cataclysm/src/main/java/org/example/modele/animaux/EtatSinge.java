package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.ChampignonH;
import org.example.modele.personnages.Personnage;

import java.util.*;

public abstract class EtatSinge {
    protected boolean priority_banane = false;
    protected boolean priority_champignon = false;
    protected String sens_assoc_banane;
    protected String sens_assoc_champignon;
    protected boolean priority_standard = false;
    protected boolean cocotier=false;
    protected String sens_oppos_danger;
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

    private boolean seDeplacerPourManger(Map<Integer, List<ComposantJeu>> matrice, String sens,Singe singe){
        try {
            int col =singe.caseAmodifier(sens)[0];
            int row =singe.caseAmodifier(sens)[1];


            ZoneVide vide=new ZoneVide();
            vide.initPosition(singe.getPosition().getX(), singe.getPosition().getY());
            matrice.get(singe.getPosition().getX()).remove(singe.getPosition().getY());
            matrice.get(singe.getPosition().getX()).add(singe.getPosition().getY(), vide);

            if(matrice.get(col).get(row) instanceof ChampignonH){
                singe.setEtatSinge(new SingeHallinant());
            }
            else {
                singe.setEtatSinge(new SingeRassasie());
            }

            matrice.get(col).remove(row);
            matrice.get(col).add(row, singe);
            singe.setPosition(col,row);
            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    public void manger(Map<Integer, List<ComposantJeu>> matrice, String sens, Singe singe){
        //singe.appliquerDeplacement(matrice, sens);
        seDeplacerPourManger(matrice,sens,singe);
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


    private static boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }

    public void directionsFuirDanger(Map<Integer, List<ComposantJeu>> matrice, Singe singe) {
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
    public void sensDanger(Map<Integer, List<ComposantJeu>> matrice, Singe singe) {
        Map<String, Boolean> dirDangers=new TreeMap<>();
        dirDangers.put("D", false);
        dirDangers.put("G", false);
        dirDangers.put("H", false);
        dirDangers.put("B", false);

        int x = singe.getPosition().getX();
        int y = singe.getPosition().getY();


        for (int i = 1; i < 4; i++) {
            if (sensValide(x, y + i, matrice)) {
                ComposantJeu composantJeu = matrice.get(x).get(y + i);
                if (composantJeu instanceof Scorpion || composantJeu instanceof Serpent) {
                    dirDangers.put("D", true);
                }
            }
            if (sensValide(x, y - i, matrice)) {
                ComposantJeu composantJeu1 = matrice.get(x).get(y - i);
                if (composantJeu1 instanceof Scorpion || composantJeu1 instanceof Serpent) {
                    dirDangers.put("G", true);
                }
            }
            if (sensValide(x - i, y, matrice)) {
                ComposantJeu composantJeu2 = matrice.get(x - i).get(y);
                if (composantJeu2 instanceof Serpent || composantJeu2 instanceof Scorpion) {
                    dirDangers.put("H", true);
                }
            }
            if (sensValide(x + i, y, matrice)) {
                ComposantJeu composantJeu3 = matrice.get(x + i).get(y);
                if (composantJeu3 instanceof Scorpion || composantJeu3 instanceof Serpent) {
                    dirDangers.put("B", true);
                }
            }
        }
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

    public  Map<String, Boolean> verifierDanger(Map<Integer,
            List<ComposantJeu>> matrice, Singe ecureil)
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
                if(composantJeu instanceof Scorpion || composantJeu instanceof Serpent){
                    dirDangers.put("D", true);
                }
            }
            if ( sensValide(x,y-i,matrice)) {
                ComposantJeu composantJeu1=matrice.get(x).get(y - i);
                if(composantJeu1 instanceof Scorpion || composantJeu1 instanceof Serpent){
                    dirDangers.put("G", true);
                }
            }
            if (sensValide(x-i,y,matrice)) {
                ComposantJeu composantJeu2=matrice.get(x - i).get(y);
                if(composantJeu2 instanceof Scorpion || composantJeu2 instanceof Serpent){
                    dirDangers.put("H", true);
                }
            }
            if (sensValide(x+i,y,matrice)) {
                ComposantJeu composantJeu3=matrice.get(x + i).get(y);
                if(composantJeu3 instanceof Scorpion || composantJeu3 instanceof Serpent){
                    dirDangers.put("B", true);
                }
            }
        }




        return dirDangers;
    }

    public boolean faire_courir(int x, int y,  Map<Integer,
            List<ComposantJeu>> matrice, Singe singe){
        if(sensValide(x,y,matrice) ){
            if(matrice.get(x).get(y) instanceof ZoneVide){
                ZoneVide zoneVide=(ZoneVide) matrice.get(x).get(y);
                int n_x=zoneVide.getPosition().getX();
                int n_y=zoneVide.getPosition().getY();

                ZoneVide vide=new ZoneVide();
                vide.initPosition(x,y);
                matrice.get(singe.getPosition().getX()).remove(singe.getPosition().getY());
                matrice.get(singe.getPosition().getX()).add(singe.getPosition().getY(),vide);

                matrice.get(n_x).remove(n_y);
                matrice.get(n_x).add(n_y, singe);
                singe.setPosition(n_x, n_y);
                return true;
            }
        }

        return false;
    }
    public boolean estDanger(Map<String, Boolean> dangers){
        return dangers.get("D") || dangers.get("H") || dangers.get("G") || dangers.get("B");
    }

    public void courir(Singe singe, String sens,Map<Integer,
            List<ComposantJeu>> matrice){
        int x=singe.getPosition().getX();
        int y=singe.getPosition().getY();

        if(sens.equals("D")){
            for (int i = y+1; i <matrice.get(x).size(); i++) {
                if(faire_courir(x,i,matrice, singe)){
                    break;
                }
            }
        }
        else if (sens.equals("G")){
            if(y!=0){
                for (int i = y-1; i >=0; i--) {
                    if(faire_courir(x,i,matrice, singe)){
                        break;
                    }
                }
            }

        } else if (sens.equalsIgnoreCase("B")) {
            for (int i = x+1; i <matrice.size(); i++) {
                if(faire_courir(i,y,matrice, singe)){
                    break;
                }
            }
        }
        else {
            if(x!=0){
                for (int i = x-1; i >=0; i--) {
                    if(faire_courir(i,y,matrice, singe)){
                        break;
                    }
                }
            }
        }
    }
}
