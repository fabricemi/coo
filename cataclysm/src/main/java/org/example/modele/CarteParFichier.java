package org.example.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarteParFichier extends Carte{
    @Override
    public Map<Integer, List<String>> genererMatriceCaracteres() {
        File file=new File("carte.txt");
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

            return matrice;

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
