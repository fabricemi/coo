package org.example.modele;

import java.util.*;

public class Historique {
    private static Historique historique;
    private Stack<Map<Integer, List<ComposantJeu>>> etats=new Stack<>();

    private Historique() {
    }

    public static Historique getInstance(){
        if(historique==null){
            historique=new Historique();
        }
        return historique;
    }

    public void addState(Map<Integer, List<ComposantJeu>> e) {
        Map<Integer, List<ComposantJeu>> map = new TreeMap<>();

        for (Map.Entry<Integer, List<ComposantJeu>> entry : e.entrySet()) {
            Integer key = entry.getKey();

            List<ComposantJeu> originalList = entry.getValue();
            List<ComposantJeu> copiedList = new ArrayList<>(originalList);

            map.put(key,copiedList);
        }

        etats.push(map);  // Ajouter le nouvel état à l'historique
    }

    public void remonter(int tr) {
        for (int i = 0; i <tr ; i++) {
            etats.pop();
        }

    }

    public Stack<Map<Integer, List<ComposantJeu>>> getEtats() {

        return etats;
    }
    public Map<Integer, List<ComposantJeu>> getTopState(){
        return etats.pop();
    }
    public static Historique getHistorique() {
        return historique;
    }


}
