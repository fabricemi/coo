package org.example.modele;

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

    private static boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice){
        try {
            matrice.get(x).get(y);
            return true;
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }
}
