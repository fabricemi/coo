package org.example.modele;

import org.example.modele.personnages.Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Carte {
    private int col;
    private  int line;

    public List<Ecureil> animauxList=new ArrayList<>();

    protected Map<Integer, List<String>> matrice;
    public Map<Integer, List<ComposantJeu>> matriceObjet;
    public Carte() {
        this.matrice = new TreeMap<>();
        matriceObjet = new TreeMap<>();
        col=0;
        line=0;
    }


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
                    case "E":
                        Ecureil ecureil=new Ecureil();
                        ecureil.initPosition(i,j);
                        list.add(ecureil);
                        System.out.println(ecureil.getId());
                        animauxList.add(ecureil);
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
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setLine(int line) {
        this.line = line;
    }


}
