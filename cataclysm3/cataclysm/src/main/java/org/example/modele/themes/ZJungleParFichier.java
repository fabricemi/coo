package org.example.modele.themes;

import java.util.List;
import java.util.Map;

public class ZJungleParFichier extends ZoneDeJeuJungle {
    @Override
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        GenerateurParFichier generateurParFichier =new GenerateurParFichier("jungle.txt");
        return generateurParFichier.genererMatriceCaracteres();
    }

}
