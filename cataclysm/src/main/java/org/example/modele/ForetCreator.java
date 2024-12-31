package org.example.modele;

import org.example.modele.aliments.Aliment;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.Gland;
import org.example.modele.animaux.Animaux;
import org.example.modele.animaux.Ecureil;
import org.example.modele.personnages.Personnage;

public class ForetCreator extends ComposantCreator {

    @Override
    public Animaux createAnimal(int x, int y) {
        Ecureil ecureil=new Ecureil();
        ecureil.initPosition(x,y);

        return ecureil;
    }

    @Override
    public Vegetaux createVegetaux(String rep) {
        Vegetaux vegetaux;

        switch (rep){
            case "A":
                vegetaux=new Arbre();
                break;
            case "B":
                vegetaux=new Buisson();
                break;
            default:
                throw new IllegalStateException("composant inconnu");
        }
        return vegetaux;
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
            case "G":
                jeu=new Gland();
                break;
            case "C":
                jeu=new Champignon();
                break;
            default:
                throw new IllegalStateException("composant inconnu sur la carte");
        }
        return jeu;
    }
}
