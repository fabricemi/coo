package org.example.modele.themes;


import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.Gland;
import org.example.modele.animaux.Ecureil;
import org.example.modele.personnages.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class ZoneDeJeuForet extends ZoneDeJeu {
    Map<Integer, List<String>> matrice;
    ComposantCreator creator;
    public ZoneDeJeuForet() {
        this.matrice = new TreeMap<>();
        matriceObjet = new TreeMap<>();
        creator=new ForetCreator();
    }

    /**
     * genere la matrice de caractere de la zone foret via un fichier ou une chaine de caractere
     * @return Map<Integer, List<String>> contanant les caracteres
     */
    public abstract Map<Integer, List<String>> genererMatriceCaracteres();

    /*@Override
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
                    case "E":
                        Ecureil ecureil=new Ecureil();
                        ecureil.initPosition(i,j);
                        list.add(ecureil);
                        break;
                    case " ":
                        ZoneVide zoneVide=new ZoneVide();
                        zoneVide.initPosition(i,j);
                        list.add(zoneVide);
                        break;
                    case "G":
                        list.add(new Gland());
                        break;
                    case "C":
                        list.add(new Champignon());
                        break;
                    case "A":
                        list.add(new Arbre());
                        break;
                    case "B":
                        list.add(new Buisson());
                        break;
                    default:
                        throw new RuntimeException("caracteres inconnue");
                }
            }
            matriceObjet.put(i,list);
        }
        return matriceObjet;
    }*/

    @Override
    public Map<Integer, List<ComposantJeu>> generateCarte(){
        Map<Integer, List<String>> matriceCaractere =genererMatriceCaracteres();
        for (int i = 0; i < matriceCaractere.size(); i++) {
            List<String> stringList = matriceCaractere.get(i);
            List<ComposantJeu> list = new ArrayList<>();
            for (int j=0;j<stringList.size();j++) {
                switch (stringList.get(j)) {
                    case "@":
                        list.add(j,creator.createPersonage(i,j));
                        break;
                    case "E","H","R":
                        list.add(j,creator.createAnimal(i,j,stringList.get(j)));
                        break;
                    case " ":
                        list.add(j,creator.createZoneVide(i,j));
                        break;
                    case "G", "C","M":
                        list.add(j, creator.createAliment(stringList.get(j)));
                        break;
                    case "A","B":
                        list.add(j, creator.createVegetaux(stringList.get(j)));
                        break;
                    case "L":
                        list.add(j, creator.createPierre2());
                        break;
                    case "S":
                        list.add(j, creator.createPierre3());
                        break;
                    default:
                        throw new RuntimeException("caracteres inconnue");
                }
            }
            matriceObjet.put(i,list);
        }
        return matriceObjet;
    }


}
