package org.example.modele.animaux;

import org.example.modele.Arbre;
import org.example.modele.Buisson;
import org.example.modele.ComposantJeu;
import org.example.modele.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class EtatHibou {


    public abstract void seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou);


    public List<Ecureil> ecureilsAutours(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou) {
        int x = hibou.getPosition().getX();
        int y = hibou.getPosition().getY();
        List<Ecureil> ecureils = new ArrayList<>();

        for (int i = y + 1; i < y + 4; i++) {
            if (sensValide(x, i, matrice)) {
                if (matrice.get(x).get(i) instanceof Ecureil) {
                    ecureils.add((Ecureil) matrice.get(x).get(i));
                }
            }
        }
        for (int i = y - 3; i < y; i++) {
            if (sensValide(x, i, matrice)) {
                if (matrice.get(x).get(i) instanceof Ecureil) {
                    ecureils.add((Ecureil) matrice.get(x).get(i));
                }
            }
        }
        for (int i = x + 1; i < x + 4; i++) {
            if (sensValide(i, y, matrice)) {
                if (matrice.get(i).get(y) instanceof Ecureil) {
                    ecureils.add((Ecureil) matrice.get(i).get(y));
                }
            }
        }

        for (int i = x - 3; i < x; i++) {
            if (sensValide(i, y, matrice)) {
                if (matrice.get(i).get(y) instanceof Ecureil) {
                    ecureils.add((Ecureil) matrice.get(i).get(y));
                }
            }
        }

        return ecureils;

    }


    private boolean caseValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice) {
        return x >= 0 && y >= 0 && matrice.containsKey(x * 100 + y);
    }

    /**
     * Méthode pour vérifier si une case est dégagée (pas d'arbre ni de buisson)
     *
     * @param composants
     * @return
     */
    private boolean caseDegagee(List<ComposantJeu> composants) {
        for (ComposantJeu composant : composants) {
            if (composant instanceof Arbre || composant instanceof Buisson) {
                return false;
            }
        }
        return true;
    }


    public boolean sensValide(int x, int y, Map<Integer, List<ComposantJeu>> matrice) {
        try {
            matrice.get(x).get(y);
            return true;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void deplacerHibou(Hibou hibou, Map<Integer, List<ComposantJeu>> matrice) {

    }

    public List<Position> positions(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou) {
        int x = hibou.getPosition().getX();
        int y = hibou.getPosition().getY();
        List<Position> positions = new ArrayList<>();
        if (sensValide(x, y-2, matrice)) {
            positions.add(new Position(x, y-2));
        }
        if (sensValide(x-2, y, matrice)) {
            positions.add(new Position(x-2, y));
        }
        if (sensValide(x, y+2, matrice)) {
           positions.add(new Position(x, y+2));
        }




        if (sensValide(x+2, y, matrice)) {
            positions.add(new Position(x+2, y));
        }


        return positions;

    }
}
