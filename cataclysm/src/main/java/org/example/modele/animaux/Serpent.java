package org.example.modele.animaux;

import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class Serpent extends Animaux{
    @Override
    public void setApparence() {

    }

    @Override
    public Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        return matrice;
    }


    public void directionPrioritaire(Map<Integer, List<ComposantJeu>> matrice) {

    }


    public boolean aMangerApproximite(Map<Integer, List<ComposantJeu>> matrice) {
        return false;
    }


    public void manger(Map<Integer, List<ComposantJeu>> matrice, String sens) {

    }


    public void seDeplacerCaseVide(Map<Integer, List<ComposantJeu>> matrice) {

    }
}
