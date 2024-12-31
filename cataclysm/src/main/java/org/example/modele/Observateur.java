package org.example.modele;

import org.example.modele.aliments.Aliment;
import org.example.modele.animaux.Animaux;
import org.example.modele.animaux.Ecureil;
import org.example.modele.personnages.Personnage;

public interface Observateur {
    public void mettreAjouter();

    class ForetCreator extends ComposantCreator {
        @Override
        public Animaux createAnimal(int x, int y) {
            Ecureil ecureil=new Ecureil();
            ecureil.initPosition(x,y);
            return ecureil;
        }

        @Override
        public Vegetaux createVegetaux(String rep) {
            return null;
        }

        @Override
        public ZoneVide createZoneVide(int x, int y) {
            return null;
        }

        @Override
        public Personnage createPersonage(int x, int y) {
            return null;
        }

        @Override
        public Aliment createAliment(String rep) {
            return null;
        }
    }
}
