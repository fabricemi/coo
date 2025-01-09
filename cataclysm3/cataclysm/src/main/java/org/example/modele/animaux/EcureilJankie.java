package org.example.modele.animaux;

import org.example.modele.ComposantJeu;
import org.example.modele.Position;
import org.example.modele.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EcureilJankie extends EtatEcureil{
    @Override
    public void deplacer(Ecureil ecureuil, Map<Integer, List<ComposantJeu>> matrice) {
        List<Position> positionsList= Utils.possiblePositionsADeuxCases(
                matrice,ecureuil.getPosition().getX(),ecureuil.getPosition().getY()
        );

        if(!positionsList.isEmpty()){
            Position position=positionsList.get(new Random().nextInt(positionsList.size()));
            ecureuil.seDeplacerViaPostion(matrice, position);
            ecureuil.diminuerNbrTour();
        }
        System.out.println("jankie à la position "+ecureuil.getPosition());
    }



}
