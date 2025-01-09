package org.example.modele;

import org.example.modele.aliments.*;
import org.example.modele.animaux.Animaux;
import org.example.modele.animaux.Ecureil;
import org.example.modele.animaux.Hibou;
import org.example.modele.animaux.Renard;
import org.example.modele.personnages.Personnage;

public class ForetCreator extends ComposantCreator {

    @Override
    public Animaux createAnimal(int x, int y, String rep) {

        if (rep.equals("E")) {
            Ecureil ecureil = new Ecureil(x, y);
            //ecureil.initPosition(x, y);
            return ecureil;
        } else if (rep.equals("H")) {
            Hibou hibou = new Hibou(x, y);
            //hibou.initPosition(x, y);
            return hibou;
        }
        else if (rep.equals("R")){
            Renard renard = new Renard(x, y);
            //renard.initPosition(x, y);
            return renard;
        }
        else {
            throw new IllegalStateException("caractère iconnu");
        }
    }

    @Override
    public Vegetaux createVegetaux(String rep) {
        Vegetaux vegetaux;

        switch (rep) {
            case "A":
                vegetaux = new Arbre();
                break;
            case "B":
                vegetaux = new Buisson();
                break;

            default:
                throw new IllegalStateException("composant inconnu");
        }
        return vegetaux;
    }

    @Override
    public ZoneVide createZoneVide(int x, int y) {
        ZoneVide vide = new ZoneVide();
        vide.initPosition(x, y);
        return vide;
    }

    @Override
    public Personnage createPersonage(int x, int y) {
        Personnage personnage = Personnage.getInstance();
        personnage.initPosition(x, y);
        return personnage;
    }

    @Override
    public Aliment createAliment(String rep) {
        Aliment jeu;
        switch (rep) {
            case "G":
                jeu = new Gland();
                break;
            case "C":
                jeu = new Champignon();
                break;
            case "M":
                jeu=new ChampignonVenimeux();
                break;
            default:
                throw new IllegalStateException("composant inconnu sur la carte");
        }
        return jeu;
    }
}
