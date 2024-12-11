package org.example.modele.themes;

import org.example.modele.*;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.animaux.Singe;
import org.example.modele.animaux.SingeRassasie;
import org.example.modele.personnages.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class ZoneDeJeuJungle extends ZoneDeJeu {

    protected Map<Integer, List<String>> matrice;
    public ZoneDeJeuJungle() {
        this.matrice = new TreeMap<>();
        matriceObjet = new TreeMap<>();

    }


    /**
     * genere la matrice de caractere de la zone jungle via un fichier ou une chaine de caractere
     * @return Map<Integer, List<String>> contanant les caracteres
     */
    public abstract Map<Integer, List<String>> genererMatriceCaracteres();

    public Map<Integer, List<ComposantJeu>> generateCarte(){
        Map<Integer, List<String>> matriceCaractere =genererMatriceCaracteres();
        for (int i = 0; i < matriceCaractere.size(); i++) {
            List<String> stringList = matriceCaractere.get(i);
            List<ComposantJeu> list = new ArrayList<>();
            for (int j=0;j<stringList.size();j++) {
                switch (stringList.get(j)) {
                    case "@":
                        Personnage p=Personnage.getInstance();
                        p.initPosition(i, j);
                        list.add(p);
                        break;
                    case "S":
                        Singe singe=new Singe();
                        singe.initPosition(i,j);
                        //singe.setEtatSinge(SingeRassasie.getInstance(singe));
                        list.add(singe);
                        break;
                    case " ":
                        ZoneVide zoneVide=new ZoneVide();
                        zoneVide.initPosition(i,j);
                        list.add(zoneVide);
                        break;
                    case "B":
                        list.add(new Banane());
                        break;
                    case "O":
                        list.add(new Cocotier());
                        break;
                    case "C":
                        list.add(new Champignon());
                        break;
                    case "R":
                        list.add(new PetitRochet());
                        break;
                    default:
                        System.out.println(stringList.get(j)+" icciii");
                        throw new RuntimeException("caracteres inconnue");
                }
            }
            matriceObjet.put(i,list);
        }
        return matriceObjet;
    }


}
