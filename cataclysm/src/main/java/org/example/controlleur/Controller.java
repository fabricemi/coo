package org.example.controlleur;

import org.example.Vue.Ihm;
import org.example.modele.*;
import org.example.modele.personnages.Personnage;
import org.example.modele.themes.*;

public class Controller {
    private Theme theme;
    Jeu jeu;

    ZoneDeJeu zoneDeJeu;

    private boolean jeu_encours=true;
    private Ihm ihm;

    public Controller() {
        ihm=new Ihm();
    }

    public void jouer(){
        String[] datas=initialiserZone();
        String them=datas[0];
        String typegen=datas[1];
        CtlInitZone(typegen);
        CtlInitTheme(them);

        jeu=new Jeu(zoneDeJeu);
        jeu.chargerReservePersonnage(them);

        ihm.afficher(theme.appliquerAffichage());
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
            ihm.afficher(theme.appliquerAffichage());
            ihm.line();
        }
    }

    public void setAction(String act){
        String dir;
        switch (act){
            case "D":
                dir= ihm.saisirDirection();
                jeu.deplacerPersonnage(dir);
                break;
            case "AA":
                dir= ihm.saisirDirection();
                jeu.apprivoiserAnimal(dir);
                break;
            case "LN":
                dir=ihm.saisirDirection();
                jeu.lancerNourriture(dir);
                break;
            case "RO":
                dir=ihm.saisirDirection();
                jeu.ramasserObjet(dir);
                break;
            case "DC":
                jeu.donnerCoup();
                break;
            default:
                throw new  RuntimeException("Défaillance controle donnée ihm : fonction queVoulezVousFaire");
        }
    }


    public String[] initialiserZone(){
        return ihm.themeModeleGeneration();
    }

    public void CtlInitTheme(String them){
        switch (them){
            case "F":
                setTheme(new ThemeForet(zoneDeJeu));
                break;
            case "J":
                setTheme(new ThemeJungle(zoneDeJeu));
                break;
            default:
                throw  new RuntimeException("thème inconnu");
        }
    }

    public void CtlInitZone(String typegen){
        switch (typegen){
            case "FF":
                setZoneDeJeu(new ZoneDeJeuForetParFichier());
                break;
            case "NF":
                setZoneDeJeu(new ZoneDeJeuForetNouvelle(80,20));
                break;
            case "NJ": ;
                setZoneDeJeu(new ZoneDeJeuJungleNouvelle(80,20));
                break;
            case "FJ":
                setZoneDeJeu(new ZoneDeJeuJungleParFichier());
                break;
            default:
                throw new RuntimeException("Mode de generation inconnu");
        }
    }

    public void setZoneDeJeu(ZoneDeJeu zoneDeJeu) {
        this.zoneDeJeu = zoneDeJeu;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
