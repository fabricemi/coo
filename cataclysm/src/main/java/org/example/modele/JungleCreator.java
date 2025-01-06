package org.example.modele;

import org.example.modele.aliments.Aliment;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.ChampignonH;
import org.example.modele.animaux.Animaux;
import org.example.modele.animaux.Scorpion;
import org.example.modele.animaux.Serpent;
import org.example.modele.animaux.Singe;
import org.example.modele.personnages.Personnage;

public class JungleCreator extends ComposantCreator{

    @Override
    public Animaux createAnimal(int x, int y, String rep) {
        if(rep.equalsIgnoreCase("S")){
            Singe singe=new Singe();
            singe.initPosition(x,y);
            return  singe;
        } else if (rep.equalsIgnoreCase("P")) {
            Scorpion scorpion=new Scorpion();
            scorpion.initPosition(x, y);
            return scorpion;
        }
        else {
            Serpent serpent=new Serpent();
            serpent.initPosition(x, y);
            return  serpent;
        }
    }

    @Override
    public Vegetaux createVegetaux(String rep) {
        Vegetaux jeu;

        switch (rep){
            case "O":
                jeu=new Cocotier();
                break;
            case "R":
                jeu=new PetitRochet();
                break;
            default:
                throw new IllegalStateException("composant inconnu");
        }
        return jeu;
    }

    @Override
    public ZoneVide createZoneVide(int x, int y) {
        ZoneVide vide=new ZoneVide();
        vide.initPosition(x, y);
        return vide;
    }

    @Override
    public Personnage createPersonage(int x, int y) {
        Personnage personnage=Personnage.getInstance();
        personnage.initPosition(x,y);
        return personnage;
    }

    @Override
    public Aliment createAliment(String rep) {
        Aliment jeu;
        switch (rep){
            case "B":
                jeu=new Banane();
                break;
            case "C":
                jeu=this.createChampignon();
                break;
            case "H":
                jeu=new ChampignonH();
                break;
            default:
                throw new IllegalStateException("composant inconnu sur la carte");
        }
        return jeu;
    }


}
