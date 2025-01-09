package org.example.modele.themes;

import java.util.List;
import java.util.Map;

public class ZJungleNouvelle extends ZoneDeJeuJungle {
    private int length;
    private int larger;

    private final String chaine="         SSS                  S                RRO" +
            "RRRRRR    BB BBB RRRR   C         R          " +
            "                      " +
            "      CR     CC        OOOO OO OOO BBB  SS    C   S    S                          " +
            "                                       " +
            "          BB                  BCCCBB          " +
            "                                        " +
            "              BB                         " +
            "                                         " +
            "     R R        RRR    R   ";

    public ZJungleNouvelle(int length, int larger) {
        this.length = length;
        this.larger = larger;
    }



    @Override
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        GenerateurAleatoire generateurAleatoire =new GenerateurAleatoire(length,larger,chaine);
        return generateurAleatoire.genererMatriceCaracteres();
    }
}
