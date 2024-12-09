package org.example.modele.themes;

import java.util.*;

public class GenerateurAleatoire {
    private int length;
    private int larger;
    Map<Integer, List<String>> matrice;
    private String chaine;
    public GenerateurAleatoire(int length, int larger, String chaine) {
        this.length = length;
        this.larger = larger;
        this.chaine=chaine;
        matrice=new TreeMap<>();
    }

    public Map<Integer, List<String>> genererMatriceCaracteres() {

        for(int i=0; i<larger;i++){
            List<String> line=new ArrayList<>();
            for (int j = 0; j <length ; j++) {
                line.add(String.valueOf(chaine.charAt(new Random().nextInt(chaine.length()))));
            }
            matrice.put(i,line);
        }
        matrice.get((int)larger/2).remove((int) length/2);
        matrice.get((int)matrice.size()/2).add((int) length/2, "@");
        return matrice;
    }
}
