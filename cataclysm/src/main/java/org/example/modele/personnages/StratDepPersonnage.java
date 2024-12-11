package org.example.modele.personnages;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public interface StratDepPersonnage {


    /**
     * deplace le personnage
     * @param matrice la matrice de la zone de jeu
     * @param pers le personnage
     * @return True si le deplacement s'est effectué
     */
    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Personnage pers);

}
