package org.example.modele.themes;

import java.util.List;
import java.util.Map;

public class ZForetNouvelle extends ZoneDeJeuForet {

    private int length;
    private int larger;
    private final String chaine="                                       " +
            " H H   H  EAAAE     EE             C                                                         EE    M MM R MM        RR             " +
            "   RR                                 " +
            "          AAEAA   CCC    AAAAB   BBB       EEE  E         GGGGG" +
            "AAAAA     HHH     R   MM    H     EE   EEE   R" +
            "  AA   AAHRHAA  EEEEE                H                                             " +
            "     MM       AA  AAAA  AAAAAAA" +
            "BBB           BHR HBBBB  C      M  M   MMMM                E                                     " +
            "          HH           R    CCC       CCAAC";

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
