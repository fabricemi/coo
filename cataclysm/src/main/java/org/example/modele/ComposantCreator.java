package org.example.modele;

import org.example.modele.aliments.Aliment;
import org.example.modele.aliments.Champignon;
import org.example.modele.animaux.Animaux;
import org.example.modele.personnages.Personnage;

public abstract class ComposantCreator {


    public abstract Animaux createAnimal(int x, int y);

    public abstract Vegetaux createVegetaux(String rep);

    public Aliment createChampignon(){
        return new Champignon();
    }

    public abstract ZoneVide createZoneVide(int x, int y);

    public abstract Personnage createPersonage(int x, int y);
    public abstract Aliment createAliment(String rep);

}
