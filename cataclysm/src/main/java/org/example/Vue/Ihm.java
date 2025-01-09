package org.example.Vue;


import java.util.*;

public class Ihm {
    Scanner scanner=new Scanner(System.in);
    List<String> directions;
    List<String> themes;

    Map<String,List<String>> generation;
    List<String> typegen;
    List<String> typegen2;

    List<String> actionsPersonnage;

    public void depImpossible(){
        System.out.println("Vous ne pouvez pas effectuer ce deplacement");
    }

    public void plusAnimals(){
        System.out.println("vous n'avez pas d'amis");
    }

    public void impossible(){
        System.out.println("impossible");
    }
    public Ihm() {
        directions=new ArrayList<>();
        themes=new ArrayList<>();
        generation=new HashMap<>();
        typegen=new ArrayList<>();
        typegen2=new ArrayList<>();
        actionsPersonnage=new ArrayList<>();

        actionsPersonnage.add("D");
        actionsPersonnage.add("LN");
        //actionsPersonnage.add("AA");
        actionsPersonnage.add("RO");
        actionsPersonnage.add("RP");
        actionsPersonnage.add("DC");
        actionsPersonnage.add("R");
        actionsPersonnage.add("Q");

        directions.add("H");
        directions.add("G");
        directions.add("D");
        directions.add("B");


        themes.add("F");
        themes.add("J");

        typegen.add("FF");
        typegen.add("NF");

        typegen2.add("FJ");
        typegen2.add("NJ");

        generation.put("F", typegen);
        generation.put("J", typegen2);
    }

    /**
     * recupere la direction dans laquelle le joeur veut recuperer l'action
     * @return String la direction
     */
    public String saisirDirection(){
        System.out.println("Saisir direction : haut(H/h) bas(B/b) gauche(G/g) droite(D/d) ");
        String string=scanner.nextLine();

        while (!directions.contains(string.toUpperCase())){
            System.out.println("Saisir direction : ");
            string= scanner.nextLine();
        }
        return string.toUpperCase();
    }

    public void close(){
        scanner.close();
    }

    /**
     * recuperer le nom de l'action que le joeur veut faire
     * @return String le nom de l'action
     */
    public String queVoulezFaire(){
        System.out.println("Que voullez vous faire?");
        String msg="seDeplacer(D/d)|LancerNourriture(LN/ln)|" +
                "ramasserObjet(RO/ro)|donnerCoup(DC/dc)|quittez le jeu(Q/q)|deposer animal(R/r)|pierre (RP/rp)";
        System.out.println(msg);
        String string=scanner.nextLine();
        while (!actionsPersonnage.contains(string.toUpperCase())){
            System.out.println("INVALIDE");
            System.out.println(msg);
            string= scanner.nextLine();
        }
        return string.toUpperCase();

    }

    /**
     * recuperer le thème ainsi le mode de generation de la zone de jeu que veut le joueur
     * @return String[] contenant le nom du theme et de mode generation
     */
    public String[] themeModeleGeneration(){
        System.out.println("Veuillez choisir le thème Forêt(saisir F/f) ou Jungle(saisir J/j");
        String string=scanner.nextLine();
        while (!themes.contains(string.toUpperCase())){
            System.out.println("CHOIX INVALIDE");
            System.out.println("Forêt(saisir F/f) ou Jungle(saisir J/j");
            string= scanner.nextLine();
        }

        String string1;

        if(string.equalsIgnoreCase("F")){
            string1="Saisir FF pour charger la zone de jeu depuis un fichier ou NF pour créer une zonz aléatoire";
        }
        else {
            string1="Saisir FJ/fj pour charger la zone de jeu depuis un fichier ou NJ/nj pour créer une zonz aléatoire";
        }

        System.out.println("***********************************************************************************");
        System.out.println(string1);
        String string2=scanner.nextLine();

        while (!generation.get(string.toUpperCase()).contains(string2.toUpperCase())){
            System.out.println("CHOIX INVALIDE");
            System.out.println(string1);
            string2= scanner.nextLine();
        }

        String[] string3=new String[2];
        string3[0]=string.toUpperCase();
        string3[1]=string2.toUpperCase();
        System.out.println(string3[0]+"    "+string3[1]);
        return string3;
    }

    /**
     * affiche la zone de jeu
     * @param list la liste des lignes
     */
    public void afficher(List<String> list){
        list.forEach(
                (i)-> {
                    System.out.println(i);
                }
        );
    }

    public void line(){
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

}
