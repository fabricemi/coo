package org.example.modele.themes;

import org.example.modele.ComposantJeu;

import java.util.*;

public abstract class Theme {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    // Strings for background colors
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m" ;
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

   protected ZoneDeJeu zoneDeJeu;



    /**
     * applique un affichage  fonction de la zone de jeu
     * @return List<String>
     */
    public List<String> appliquerAffichage(){
        Set<Map.Entry<Integer, List<ComposantJeu>>> composantJeuSet= zoneDeJeu.getMatriceObjet().entrySet();
        Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator= composantJeuSet.iterator();
        return specificAlaZone(iterator);
    }

    public void setZoneDeJeu(ZoneDeJeu zoneDeJeu) {
        this.zoneDeJeu = zoneDeJeu;
    }

    /**
     * donne une apprence particulière selon le theme
     * @param iterator l'iterator
     * @return List<String>
     */
    public abstract List<String> specificAlaZone(Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator);
}
