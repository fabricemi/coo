package org.example.modele.themes;

import java.util.List;
import java.util.Map;

public class ZForetNouvelle extends ZoneDeJeuForet {

    private int length;
    private int larger;
    private final String chaine="                                                          " +
            "      EAAAE                                                                        AAEAAAAAAAAB   BBBBB EEE  E         GGGGG" +
            "AAAAA                           AAAAAAA                     AAAAAAAAAAAAA" +
            "BBBBBBBBBB  C                      E                                 " +
            "                     CCC       CCAAC";

    public ZForetNouvelle(int length, int larger) {
        this.length = length;
        this.larger = larger;
    }

    @Override
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        GenerateurAleatoire generateurAleatoire =new GenerateurAleatoire(length,larger,chaine);
        return generateurAleatoire.genererMatriceCaracteres();
    }

}
