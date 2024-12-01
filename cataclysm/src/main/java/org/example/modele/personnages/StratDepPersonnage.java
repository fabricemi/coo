package org.example.modele.personnages;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public interface StratDepPersonnage {

    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Personnage pers);

}
