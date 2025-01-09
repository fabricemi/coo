package org.example.modele.personnages;


import org.example.modele.*;
import org.example.modele.aliments.Aliment;
import org.example.modele.animaux.Ecureil;
import org.example.modele.animaux.Singe;

import java.util.*;

public class Personnage extends ComposantJeu implements Sujet {

    private static Personnage instance;

    private StratDepPersonnage stratDepPersonnage;

    private List<Observateur> amis=new ArrayList<>();

    private PosPersonnage position;
    private List<ComposantJeu> objetRamasser = new ArrayList<>();

    private List<Observateur> aProteger=new ArrayList<>();


    /**
     * obtenir l'instance de la classe personnage
     * @return Personnage, l'unique instance de la classe personnage dans le jeu
     */
    public static Personnage getInstance() {
        if (instance == null) {
            instance = new Personnage();
        }
        return instance;
    }

    public void setStratDepPersonnage(StratDepPersonnage stratDepPersonnage) {
        this.stratDepPersonnage = stratDepPersonnage;
    }

    public void initPosition(int x, int y) {
        this.position = PosPersonnage.getInstance(x, y);
    }

    /**
     * initialise la position du personnage lors de la génération d ela zone de jeu
     * @param x l'abscisse
     * @param y l'ordonné
     */
    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public PosPersonnage getPosition() {
        return position;
    }

    /**
     * applique le deplacemnt du personnage
     * @param matrice la carte du  jeu
     * @param sens la direction
     * @return True si le deplacement est faisable
     */
    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice,String sens) {
        switch (sens) {
            case "D":
                this.setStratDepPersonnage(new DepHdPersonnage());
                break;
            case "G":
                this.setStratDepPersonnage(new DepHgPersonnage());
                break;
            case "H":
                this.setStratDepPersonnage(new DepVhPersonnage());
                break;
            case "B":
                this.setStratDepPersonnage(new DepVbPersonnage());
                break;
            default:
                throw new RuntimeException("sens inconnu");
        }
        boolean bool=stratDepPersonnage.seDeplacer(matrice,this);
        stratDepPersonnage=null;
        return bool;
    }


    public List<ComposantJeu> getObjetRamasser() {
        return objetRamasser;
    }


    public void seBattre() {
        //TODO
    }

    /**
     *permet au personnage de connaitre les elemnt squ'il y a autour de lui
     * @param matrice la carte du jeu
     * @return Map<String, ComposantJeu> contenant les elemnts autour du personnage associé à l a direction
     * @throws IndexOutOfBoundsException si le l'abscisse x deborde
     * @throws NullPointerException si l'ordonné y deborde
     */
    public Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice)
            throws IndexOutOfBoundsException, NullPointerException {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

       /*Map<String, ComposantJeu> eltsAutours = new HashMap<>();
        if (sensValide(x,y+1,matrice)) {
            eltsAutours.put("D", matrice.get(x).get(y + 1));
        }
        if ( sensValide(x,y-1,matrice)) {
            eltsAutours.put("G", matrice.get(x).get(y - 1));
        }
        if (sensValide(x-1,y,matrice)) {
            eltsAutours.put("H", matrice.get(x - 1).get(y));
        }
        if (sensValide(x+1,y,matrice)) {
            eltsAutours.put("B", matrice.get(x + 1).get(y));
        }*/

        return Utils.elementAutours(matrice, x,y);
    }

    /**
     * ajoute un objet à la liste d'objet ramasser
     * @param matrice la carte du jeu
     * @param sens le sens
     * @return true si l'objet situé au sens indiqué peut etre ramassé par l'utilisateur
     */
    public boolean ramasserObjet(Map<Integer, List<ComposantJeu>> matrice, String sens) {
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);
            if(eltsAutours.containsKey(sens)){
                ComposantJeu cps=eltsAutours.get(sens);
                if(cps instanceof Aliment){
                    int col=this.caseAmodifier(sens)[0];
                    int row=this.caseAmodifier(sens)[1];
                    this.objetRamasser.add(cps);
                    matrice.get(col).remove(row);
                    matrice.get(col).add(row,new ZoneVide());
                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }

    public boolean reposerObjet(Map<Integer, List<ComposantJeu>> matrice, String sens) {
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);
            if(eltsAutours.containsKey(sens) && !objetRamasser.isEmpty()){
                ComposantJeu cps=eltsAutours.get(sens);
                ComposantJeu oar= objetRamasser.get(new Random().nextInt(objetRamasser.size()));
                if(cps instanceof ZoneVide){
                    int col=this.caseAmodifier(sens)[0];
                    int row=this.caseAmodifier(sens)[1];
                    matrice.get(col).remove(row);
                    matrice.get(col).add(row,oar);

                    this.objetRamasser.remove(oar);
                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }

    /**
     * retourne les coordonnées de la case à modifier en fonction du sens
     * @param sens le sens
     * @return les coordonnées de la case à modifier
     */
    public Integer[] caseAmodifier(String sens){
        int x=this.getPosition().getX();
        int y=this.getPosition().getY();
        int col;
        int row;
        switch (sens) {
            case "D" -> {
                col = x;
                row = y + 1;
            }
            case "G" -> {
                col = x;
                row = y - 1;
            }
            case "H" -> {
                col = x - 1;
                row = y;
            }
            default -> {
                col = x + 1;
                row = y;
            }
        }

        return new Integer[]{col, row};
    }


/*    public boolean apprivoiser(Map<Integer, List<ComposantJeu>> matrice, String sens) {
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);

            if(eltsAutours.containsKey(sens)){
                ComposantJeu cps=eltsAutours.get(sens);
                if(cps instanceof Ecureil || cps instanceof Singe){
                    this.attacher((Observateur) cps);
                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }*/


    /**
     * supprime un animal choisi aléatoirement de la list ed'amis
     */
    public boolean donnerCoup(){
        if(!this.amis.isEmpty()){
            Observateur animaux= this.amis.get(new Random().nextInt(this.amis.size()));
            detacher(animaux);
            if( aProteger.contains(animaux)){
                aProteger.remove(animaux);
            }
            return true;
        }

        return false;
    }

    public List<Observateur> getAmis() {
        return amis;
    }

    public List<Observateur> getaProteger() {
        return aProteger;
    }

    /**
     * ajoute un animal à la liste d'amis
     * @param o l'animal
     */
    @Override
    public void attacher(Observateur o) {
        if(!this.amis.contains(o)){
            this.amis.add(o);
        }
    }


    /**
     * supprime un animal de la liste d'amis
     * @param o l'animal
     */
    @Override
    public void detacher(Observateur o) {
        this.amis.remove(o);
    }

    public void proteger(Observateur o){
        aProteger.add(o);
    }

}
