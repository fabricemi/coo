package org.example.modele.themes;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.animaux.Ecureil;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class ThemeForet extends Theme{

    //public ThemeForet(ZoneDeJeu zoneDeJeu) {
    //    super(zoneDeJeu);
    //}


    @Override
    public List<String> specificAlaZone(Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator) {
        List<String> list=new ArrayList<>();
        String line="";
        while (iterator.hasNext()){
            List<ComposantJeu> composantJeuList=iterator.next().getValue();

            for (ComposantJeu jeu:composantJeuList) {
                switch (jeu) {
                    case Personnage personnage -> line += Colors.WHITE_BG.getCode()+ANSI_PURPLE + "@" + ANSI_RESET;
                    case Ecureil ecureil -> line += Colors.YELLOW_BG.getCode()+jeu.toString();
                    case Champignon champignon -> line +=Colors.WHITE_BG.getCode()+ ANSI_RED + "C" + ANSI_RESET;
                    case ZoneVide zoneVide -> line +=Colors.GREEN_BG.getCode()+ " " + ANSI_RESET;
                    case Buisson buisson -> line +=Colors.BLACK_BG.getCode()+ ANSI_GREEN + "B" + ANSI_RESET;
                    case Arbre arbre -> line += Colors.BLACK_BG.getCode()+ ANSI_GREEN + "A" + ANSI_RESET;
                    case null, default -> line += Colors.RED_BG.getCode()+ANSI_YELLOW + "G" + ANSI_RESET;
                }
            }
            //list.add(line);
            line+="\n";
        }
        list.add(line);
        return list;
    }
}
