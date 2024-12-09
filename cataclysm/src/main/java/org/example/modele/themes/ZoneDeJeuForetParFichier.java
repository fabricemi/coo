package org.example.modele.themes;

import java.util.List;
import java.util.Map;

public class ZoneDeJeuForetParFichier extends ZoneDeJeuForet {

    @Override
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        GenerateurParFichier generateurParFichier =new GenerateurParFichier("carte.txt");
        return generateurParFichier.genererMatriceCaracteres();
    }

}
