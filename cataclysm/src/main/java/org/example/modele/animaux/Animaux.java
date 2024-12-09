package org.example.modele.animaux;

import org.example.modele.ComposantJeu;
import org.example.modele.Observateur;
import org.example.modele.Position;
import org.example.modele.ZoneVide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Animaux extends ComposantJeu{
    protected Position position;
    public void initPosition(int x, int y) {
        this.position = new Position(x,y);
    }

    public void setPosition(int x, int y){
        this.position.x=x;
        this.position.y=y;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void setEtat();

    public abstract void seDeplacer(Map<Integer, List<ComposantJeu>> matrice);

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


    public Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice)
    {
        Map<String, ComposantJeu> eltsAutours = new TreeMap<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if (sensValide(x , y+1,matrice)) {
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
        }
        return eltsAutours;
    }
    public abstract void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice);
    public boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }

    protected boolean appliquerDeplacement(Map<Integer, List<ComposantJeu>> matrice, String sens ){
        try {
            int col =caseAmodifier(sens)[0];
            int row =caseAmodifier(sens)[1];

            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), new ZoneVide());

            matrice.get(col).remove(row);
            matrice.get(col).add(row, this);
            this.setPosition(col,row);

            return true;
        }
        catch (IndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    public abstract boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice);
}
