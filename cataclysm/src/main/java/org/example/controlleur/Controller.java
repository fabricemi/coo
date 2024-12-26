package org.example.controlleur;

import org.example.Vue.Ihm;
import org.example.modele.*;
import org.example.modele.themes.*;

public class Controller {
    private ThemeZoneCreateur createur;
    Jeu jeu;

    private boolean jeu_encours=true;
    private Ihm ihm;

    public Controller() {
        ihm=new Ihm();
    }

    public void jouer(){
        String[] datas=initialiserZone();
        String them=datas[0];
        String typegen=datas[1];
        CtlInitZone(them,typegen);
        jeu=new Jeu(createur);
        jeu.chargerReservePersonnage(them);
        ihm.afficher(jeu.appliquerAffichage());

        ihm.line();
        while (jeu_encours){
            String act=ihm.queVoulezFaire();
            if(act.equalsIgnoreCase("Q")){
                ihm.close();
                break;
            }
            setAction(act);
            jeu.deplacerAnimaux();
            jeu.mettreAjour();
            ihm.afficher(jeu.appliquerAffichage());
            ihm.line();
        }
    }

    public void setAction(String act){
        String dir;
        switch (act){
            case "D":
                dir= ihm.saisirDirection();
                boolean res=jeu.deplacerPersonnage(dir);
                while (!res){
                    ihm.depImpossible();
                    dir= ihm.saisirDirection();
                    res=jeu.deplacerPersonnage(dir);
                }
                break;
            case "LN":
                dir=ihm.saisirDirection();
                boolean resln=jeu.lancerNourriture(dir);
                while (!resln){
                    ihm.impossible();
                    dir=ihm.saisirDirection();
                    resln=jeu.deplacerPersonnage(dir);
                }
                break;
            case "RO":
                dir=ihm.saisirDirection();
                boolean ress=jeu.ramasserObjet(dir);
                if(!ress){
                    ihm.impossible();
                }
                break;
            case "DC":
                boolean resu=jeu.donnerCoup();
                if(!resu){
                    ihm.plusAnimals();
                }
                break;
            default:
                throw new  RuntimeException("Défaillance controle donnée ihm : fonction queVoulezVousFaire");
        }
    }


    public String[] initialiserZone(){
        return ihm.themeModeleGeneration();
    }


    public void CtlInitZone(String theme,String typegen){

        String assoc=theme+typegen;
        switch (assoc){
            case "FFF":
                setCreateur(new TForetZFichier());
                break;
            case "FNF":
                setCreateur(new TForetZAleatoire());
                break;
            case "JNJ": ;
                setCreateur(new TJungleZAleaoire());
                break;
            case "JFJ":
                setCreateur(new TJungleZFichier());
                break;
            default:
                throw new RuntimeException("Mode de generation inconnu");
        }
    }



    public void setCreateur(ThemeZoneCreateur createur) {
        this.createur = createur;
    }
}
