package org.example.modele;

import org.example.modele.animaux.Ecureil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice,int x,int y)
            throws IndexOutOfBoundsException, NullPointerException {

        Map<String, ComposantJeu> eltsAutours = new HashMap<>();
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
        }

        return eltsAutours;
    }
    public static Map<String, ComposantJeu> elementAutoursADeuxCases(Map<Integer, List<ComposantJeu>> matrice,int x,int y)
            throws IndexOutOfBoundsException, NullPointerException {

        Map<String, ComposantJeu> eltsAutours = new HashMap<>();
        if (sensValide(x,y+2,matrice)) {
            eltsAutours.put("D", matrice.get(x).get(y + 2));
        }
        if ( sensValide(x,y-2,matrice)) {
            eltsAutours.put("G", matrice.get(x).get(y - 2));
        }
        if (sensValide(x-2,y,matrice)) {
            eltsAutours.put("H", matrice.get(x - 2).get(y));
        }
        if (sensValide(x+2,y,matrice)) {
            eltsAutours.put("B", matrice.get(x + 2).get(y));
        }

        return eltsAutours;
    }
    public static List<Position> possiblePositionsADeuxCases(Map<Integer, List<ComposantJeu>> matrice,
                                                              int x,int y)
            throws IndexOutOfBoundsException, NullPointerException {

        List<Position> positionList=new ArrayList<>();
        if (sensValide(x,y+2,matrice)) {
            if(matrice.get(x).get(y + 2) instanceof ZoneVide){
                positionList.add(new Position(x, y+2));
            }
        }
        if ( sensValide(x,y-2,matrice)) {
            if(matrice.get(x).get(y - 2) instanceof ZoneVide){
                positionList.add(new Position(x, y-2));
            }
        }
        if (sensValide(x-2,y,matrice)) {
            if(matrice.get(x - 2).get(y) instanceof  ZoneVide){
                positionList.add(new Position(x-2, y));
            }
        }
        if (sensValide(x+2,y,matrice)) {
            if(matrice.get(x + 2).get(y) instanceof ZoneVide){
                positionList.add(new Position(x+2, y));
            }
        }

        return positionList;
    }
    public static List<Position> possiblePositionsAUneCases(Map<Integer, List<ComposantJeu>> matrice,
                                                             int x,int y)
            throws IndexOutOfBoundsException, NullPointerException {

        List<Position> positionList=new ArrayList<>();
        if (sensValide(x,y+1,matrice)) {
            if(matrice.get(x).get(y + 1) instanceof ZoneVide){
                positionList.add(new Position(x, y+1));
            }
        }
        if ( sensValide(x,y-1,matrice)) {
            if(matrice.get(x).get(y - 1) instanceof ZoneVide){
                positionList.add(new Position(x, y-1));
            }
        }
        if (sensValide(x-1,y,matrice)) {
            if(matrice.get(x - 1).get(y) instanceof  ZoneVide){
                positionList.add(new Position(x-1, y));
            }
        }
        if (sensValide(x+1,y,matrice)) {
            if(matrice.get(x + 1).get(y) instanceof ZoneVide){
                positionList.add(new Position(x+1, y));
            }
        }

        return positionList;
    }
    public static boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }

    public static List<Ecureil> ecureilsAutours(Map<Integer, List<ComposantJeu>> matrice,
                                                             int x,int y)
            throws IndexOutOfBoundsException, NullPointerException {

        List<Ecureil> ecureils=new ArrayList<>();


        if (sensValide(x,y+1,matrice)) {
            if(matrice.get(x).get(y + 1) instanceof Ecureil){
                ecureils.add((Ecureil) matrice.get(x).get(y + 1));
            }
        }
        if ( sensValide(x,y-1,matrice)) {
            if(matrice.get(x).get(y - 1) instanceof Ecureil){
                ecureils.add((Ecureil)matrice.get(x).get(y - 1));
            }
        }
        if (sensValide(x-1,y,matrice)) {
            if(matrice.get(x - 1).get(y) instanceof  Ecureil){
                ecureils.add((Ecureil) matrice.get(x - 1).get(y));
            }
        }
        if (sensValide(x+1,y,matrice)) {
            if(matrice.get(x + 1).get(y) instanceof Ecureil){
                ecureils.add((Ecureil)matrice.get(x + 1).get(y));
            }
        }

        return ecureils;
    }
}
