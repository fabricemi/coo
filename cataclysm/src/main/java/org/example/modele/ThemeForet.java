package org.example.modele;

import org.example.modele.personnages.Personnage;

import java.util.*;

public class ThemeForet extends Theme{

    public List<String> appliquerAffichage(Map<Integer,List<ComposantJeu>> composants){
        List<String> list=new ArrayList<>();

        Set<Map.Entry<Integer, List<ComposantJeu>>> composantJeuSet=composants.entrySet();

        Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator= composantJeuSet.iterator();

        while (iterator.hasNext()){
            List<ComposantJeu> composantJeuList=iterator.next().getValue();
            String line="";
            for (ComposantJeu jeu:composantJeuList) {
               if(jeu instanceof Personnage){
                   line+=ANSI_PURPLE+"@"+ANSI_RESET;
               } else if (jeu instanceof Ecureil) {
                   line+=jeu.toString();
               } else if (jeu instanceof Champignon) {
                   line+=ANSI_RED+"C"+ANSI_RESET;
               }
               else if (jeu instanceof ZoneVide) {
                   line+=" "+ANSI_RESET;
               }
               else if (jeu instanceof Buisson) {
                   line+=ANSI_GREEN+"B"+ANSI_RESET;
               }else if (jeu instanceof Arbre) {
                   line+=ANSI_GREEN+"A"+ANSI_RESET;
               }
               else {
                   line+=ANSI_YELLOW+"G"+ANSI_RESET;
               }
            }
            list.add(line+"\n__________________________________________________________________________________________________________");
        }

        return list;

    }

  /*  public List<String> appliquerAffichage(Map<Integer,List<ComposantJeu>> composants){
        List<String> list=new ArrayList<>();

        Set<Map.Entry<Integer, List<ComposantJeu>>> composantJeuSet=composants.entrySet();

        Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator= composantJeuSet.iterator();

        while (iterator.hasNext()){
            List<ComposantJeu> composantJeuList=iterator.next().getValue();
            String line="";
            for (ComposantJeu jeu:composantJeuList) {
                if(jeu instanceof Personnage){
                    line+=ANSI_WHITE_BACKGROUND+ANSI_PURPLE+"@"+ANSI_RESET;
                } else if (jeu instanceof Ecureil) {
                    line+=ANSI_WHITE_BACKGROUND+ANSI_PURPLE+jeu.toString()+ANSI_RESET;
                } else if (jeu instanceof Champignon) {
                    line+=ANSI_WHITE_BACKGROUND+ANSI_RED+"C"+ANSI_RESET;
                }
                else if (jeu instanceof ZoneVide) {
                    line+=ANSI_GREEN_BACKGROUND+" "+ANSI_RESET;
                }
                else if (jeu instanceof Buisson) {
                    line+=ANSI_BLACK_BACKGROUND+ANSI_GREEN+"B"+ANSI_RESET;
                }else if (jeu instanceof Arbre) {
                    line+=ANSI_BLACK_BACKGROUND+ANSI_GREEN+"A"+ANSI_RESET;
                }
                else {
                    line+=ANSI_RED_BACKGROUND+ANSI_YELLOW+"G"+ANSI_RESET;
                }
            }
            list.add(line);
        }

        return list;

    }*/
}
