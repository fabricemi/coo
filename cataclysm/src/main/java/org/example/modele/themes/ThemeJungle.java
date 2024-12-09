package org.example.modele.themes;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.animaux.Singe;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class ThemeJungle extends Theme{


    public ThemeJungle(ZoneDeJeu zoneDeJeu) {
        super(zoneDeJeu);
    }


    public List<String> appliquerAffichage(){
        List<String> list=new ArrayList<>();

        Set<Map.Entry<Integer, List<ComposantJeu>>> composantJeuSet= zoneDeJeu.getMatriceObjet().entrySet();

        Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator= composantJeuSet.iterator();

        while (iterator.hasNext()){
            List<ComposantJeu> composantJeuList=iterator.next().getValue();
            String line="";
            for (ComposantJeu jeu:composantJeuList) {
                if(jeu instanceof Personnage){
                    line+=ANSI_PURPLE+"@"+ANSI_RESET;
                } else if (jeu instanceof Singe) {
                    line+=jeu.toString();
                } else if (jeu instanceof Champignon) {
                    line+=ANSI_RED+"C"+ANSI_RESET;
                }
                else if (jeu instanceof ZoneVide) {
                    line+=" "+ANSI_RESET;
                }
                else if (jeu instanceof PetitRochet) {
                    line+=ANSI_GREEN+"R"+ANSI_RESET;
                }else if (jeu instanceof Arbre) {
                    line+=ANSI_GREEN+"A"+ANSI_RESET;
                } else if (jeu instanceof Cocotier) {
                    line+=ANSI_GREEN+"O"+ANSI_RESET;
                } else {
                    line+=ANSI_YELLOW+"B"+ANSI_RESET;
                }
            }
            list.add(line);
        }

        return list;

    }


}
