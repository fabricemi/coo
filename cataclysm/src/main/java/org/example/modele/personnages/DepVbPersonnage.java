package org.example.modele.personnages;

import org.example.modele.ComposantJeu;
import org.example.modele.ZoneVide;

import java.util.List;
import java.util.Map;

public class DepVbPersonnage implements StratDepPersonnage {

    @Override
    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Personnage pers) {
        try {
            int x = pers.getPosition().getX();
            int y = pers.getPosition().getY();

            if (matrice.get(x+1).get(y) instanceof ZoneVide) {
                matrice.get(x).remove(y);
                matrice.get(x).add(y, new ZoneVide());
                matrice.get(x + 1).remove(y); //s'assurez que y+1<=list.size()-1
                matrice.get(x + 1).add(y, pers);
                pers.setPosition(x + 1, y);
                return true;
            }
            return false;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
