package org.example.modele.themes;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public abstract class ZoneDeJeu {

    protected Map<Integer, List<ComposantJeu>> matriceObjet;
    /**
     *genere la matrice du jeu en fonction de la d'une matrice des caracteres
     * @return Map<Integer, List<ComposantJeu>> la matrice representant la zone de jeu
     */
    public abstract Map<Integer, List<ComposantJeu>> generateCarte();
    public Map<Integer, List<ComposantJeu>> getMatriceObjet() {
        return matriceObjet;
    }


}
