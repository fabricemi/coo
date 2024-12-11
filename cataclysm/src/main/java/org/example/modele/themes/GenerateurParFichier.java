package org.example.modele.themes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GenerateurParFichier {
    protected Map<Integer, List<String>> matrice;
    protected String fichier;
    public GenerateurParFichier(String fichier) {
        this.matrice = new TreeMap<>();
        this.fichier=fichier;
    }

    /**
     * genere la matrice de caracteres aléatoire via un fichier texte
     * @return Map<Integer, List<String>>
     */
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        File file=new File(fichier);
        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String lines;
            int j=0;
            while ((lines=bufferedReader.readLine())!=null){
                System.out.println(lines.length());
                List<String> list=new ArrayList<>();
                for (int i=0;i<lines.length(); i++) {
                    list.add(String.valueOf(lines.charAt(i)));
                }
                matrice.put(j, list);
                j++;
            }

            System.out.println(matrice);
            return matrice;

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
