package org.example;



import org.example.modele.*;
import org.example.modele.personnages.Personnage;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ThemeForet theme = new ThemeForet();
        Carte carte = new CarteParFichier();

        Map<Integer, List<ComposantJeu>> matrice = carte.generateCarte();


        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );

       Personnage personnage = Personnage.getInstance();

        boolean res = personnage.seDeplacer(matrice,"H");


        for (int i = 0; i < 122; i++) {
            boolean ress = personnage.seDeplacer(matrice,"G");

        }
        for (int i = 0; i < 5; i++) {
            personnage.seDeplacer(matrice,"B");

        }
        for (int i = 0; i < 8; i++) {
            personnage.seDeplacer(matrice,"G");

        }
        personnage.seDeplacer(matrice,"B");
        personnage.seDeplacer(matrice,"G");
        personnage.seDeplacer(matrice,"G");

        personnage.ramasserObjet(matrice, "B");
        System.out.println("****************");

        personnage.lancerNourriture(matrice,"H");
        personnage.seDeplacer(matrice,"B");
        personnage.seDeplacer(matrice,"D");
        personnage.ramasserObjet(matrice,"D");


        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );
        System.out.println(personnage.getObjetRamasser());
        System.out.println("****************");
        for (int i = 0; i < 3; i++) {
            personnage.seDeplacer(matrice,"B");

        }
        for (int i = 0; i < 20; i++) {
            personnage.seDeplacer(matrice,"G");
        }

        personnage.apprivoiser(matrice,"G");
        personnage.lancerNourriture(matrice,"D");
        personnage.lancerNourriture(matrice,"H");
        personnage.lancerNourriture(matrice,"B");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );

        Ecureil ecureil=(Ecureil) personnage.getAmis().get(0);
        System.out.println(personnage.getAmis());
        System.out.println(ecureil.estAmi);

        personnage.ramasserObjet(matrice,"D");
        personnage.ramasserObjet(matrice,"H");
        personnage.ramasserObjet(matrice,"B");
        System.out.println("****************");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );
        System.out.println(personnage.getObjetRamasser());
        for (int i = 0; i < 2; i++) {
            personnage.seDeplacer(matrice,"B");
        }
        personnage.seDeplacer(matrice,"D");
        personnage.apprivoiser(matrice,"D");
        System.out.println("****************");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );

        personnage.donnerCoup();
        System.out.println(personnage.getAmis());


        System.out.println("****************");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );
        System.out.println(personnage.getAmis());


        carte.animauxList.forEach(
                i-> System.out.println(i.getNbrTour())
        );
        carte.animauxList.forEach(
                i-> {
                    i.seDeplacer(matrice);
                }
        );
        carte.animauxList.forEach(
                i-> System.out.println(i.getNbrTour())
        );
        System.out.println("****************");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );
        carte.animauxList.forEach(
                i-> {
                    i.seDeplacer(matrice);
                }
        );
        System.out.println("****************");
        theme.appliquerAffichage(matrice).forEach(
                string -> {
                    System.out.println(string);
                }
        );
    }
}