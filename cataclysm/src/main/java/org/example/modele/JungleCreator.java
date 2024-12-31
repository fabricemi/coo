package org.example.modele;

import org.example.modele.aliments.Aliment;
import org.example.modele.aliments.Banane;
import org.example.modele.animaux.Animaux;
import org.example.modele.animaux.Singe;
import org.example.modele.personnages.Personnage;

public class JungleCreator extends ComposantCreator{

    @Override
    public Animaux createAnimal(int x, int y) {
        Singe singe=new Singe();
        singe.initPosition(x,y);
        return singe;
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
            default:
                throw new IllegalStateException("composant inconnu sur la carte");
        }
        return jeu;
    }


}
