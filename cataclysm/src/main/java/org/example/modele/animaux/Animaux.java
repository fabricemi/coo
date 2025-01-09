package org.example.modele.animaux;

import org.example.modele.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Animaux extends ComposantJeu{
    protected Position position;


    public void initPosition(int x, int y) {
        this.position = new Position(x,y);
    }

    /**
     * initialise la position de l'animal lors de la génération d ela zone de jeu
     * @param x l'abscisse
     * @param y l'ordonné
     */
    public void setPosition(int x, int y){
        this.position.x=x;
        this.position.y=y;
    }

    public Position getPosition() {
        return position;
    }


    /**
     * donne à l'aminal la couleur qu'il faut en fonction de son état
     */
    public abstract void setApparence();

    /**
     * deplace l'animal
     * @param matrice la zone de jeu
     */
    public abstract Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice);

    public Integer[] caseAmodifier(String sens) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
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


    /**
     *connaitre les elemnt squ'il y a autour de l'animal
     * @param matrice la carte du jeu
     * @return Map<String, ComposantJeu> contenant les elemnts autour de l'animal associé à l a direction
     * @throws IndexOutOfBoundsException si le l'abscisse x deborde
     * @throws NullPointerException si l'ordonné y deborde
     */
    public Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice)
    {
        Map<String, ComposantJeu> eltsAutours = new TreeMap<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

     /*   if (sensValide(x , y+1,matrice)) {
            eltsAutours.put("D", matrice.get(x).get(y + 1));
        }
        if (sensValide(x , y-1,matrice)) {
            eltsAutours.put("G", matrice.get(x).get(y - 1));
        }
        if (sensValide(x - 1, y,matrice)) {
            eltsAutours.put("H", matrice.get(x - 1).get(y));
        }
        if (sensValide(x + 1, y,matrice)) {
            eltsAutours.put("B", matrice.get(x + 1).get(y));
        }*/
        return Utils.elementAutours(matrice,x,y);
    }

    public Map<String, ComposantJeu> elementAutoursADeuxCases(Map<Integer, List<ComposantJeu>> matrice)
    {
        Map<String, ComposantJeu> eltsAutours = new TreeMap<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

     /*   if (sensValide(x , y+1,matrice)) {
            eltsAutours.put("D", matrice.get(x).get(y + 1));
        }
        if (sensValide(x , y-1,matrice)) {
            eltsAutours.put("G", matrice.get(x).get(y - 1));
        }
        if (sensValide(x - 1, y,matrice)) {
            eltsAutours.put("H", matrice.get(x - 1).get(y));
        }
        if (sensValide(x + 1, y,matrice)) {
            eltsAutours.put("B", matrice.get(x + 1).get(y));
        }*/
        return Utils.elementAutoursADeuxCases(matrice,x,y);
    }
    public List<Position> posADeuxCases(Map<Integer, List<ComposantJeu>> matrice)
    {

        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        return Utils.possiblePositionsADeuxCases(matrice, x, y);
    }
    /**
     * trouve la direction prioriataire pour un animal
     * @param matrice la zone de jeu
     */
    //public abstract void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice);
/*
    public boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }
*/

    /**
     * appliquer le deplacement d'n animal une fois qu'il à detecter la directiion prioritaire
     * @param matrice la zone jeu
     * @param sens le sens
     * @return True si le deplacement est réalisable
     */
    protected boolean appliquerDeplacement(Map<Integer, List<ComposantJeu>> matrice, String sens ){
        try {
            int col =caseAmodifier(sens)[0];
            int row =caseAmodifier(sens)[1];


            ZoneVide vide=new ZoneVide();
            vide.initPosition(this.getPosition().getX(), this.getPosition().getY());
            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), vide);

            matrice.get(col).remove(row);
            matrice.get(col).add(row, this);
            this.setPosition(col,row);

            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    protected boolean seDeplacerViaPostion(Map<Integer, List<ComposantJeu>> matrice, Position new_pos){
        try {
            ZoneVide vide=new ZoneVide();
            vide.initPosition(this.getPosition().getX(), this.getPosition().getY());


            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), vide);


            matrice.get(new_pos.getX()).remove(new_pos.getY());
            matrice.get(new_pos.getX()).add(new_pos.getY(), this);
            this.setPosition(new_pos.getX(),new_pos.getY());

            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }


    //public abstract boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice);

    //public abstract void manger(Map<Integer, List<ComposantJeu>> matrice, String sens);
    //public abstract void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice);


}
