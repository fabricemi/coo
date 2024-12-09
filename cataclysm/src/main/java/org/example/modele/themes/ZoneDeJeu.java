package org.example.modele.themes;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public abstract class ZoneDeJeu {

    protected Map<Integer, List<ComposantJeu>> matriceObjet;
    public abstract Map<Integer, List<ComposantJeu>> generateCarte();


    public Map<Integer, List<ComposantJeu>> getMatriceObjet() {
        return matriceObjet;
    }


}
