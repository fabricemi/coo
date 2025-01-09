package org.example.modele.personnages;

import org.example.modele.ComposantJeu;
import org.example.modele.ZoneVide;

import java.util.List;
import java.util.Map;

public class DepHdPersonnage implements StratDepPersonnage{
    @Override
    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Personnage pers) {
        try {
            int x = pers.getPosition().getX();
            int y = pers.getPosition().getY();
            if (matrice.get(x).get(y + 1) instanceof ZoneVide) {
                matrice.get(x).remove(y);
                matrice.get(x).add(y, new ZoneVide());
                matrice.get(x).remove(y + 1); //s'assurez que y+1<=list.size()-1
                matrice.get(x).add(y + 1, pers);
                pers.setPosition(x, y + 1);
                return true;
            }
            return false;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
