package org.example.modele.animaux;

import org.example.modele.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class HibouAffame extends EtatHibou {
    @Override
    public void seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou) {
        List<Ecureil> ecureils = ecureilsAutours(matrice, hibou);
        if (!ecureils.isEmpty()) {
            Ecureil ecureil = ecureils.get(0);

            if (!(ecureil.isEstRefugieBuisson() || ecureil.isEstRefugieArbre())) {
                List<ComposantJeu> vegetaux = ecureil.estEntoureBuisson(matrice);
                if (!vegetaux.isEmpty()) {
                    ecureil.setEstEffraye(true);
                    ecureil.setEstRefugieBuisson(true);
                    ecureil.setEstRefugieArbre(false);
                } else {
                    System.out.println("Avant de manger " + hibou.getPosition());
                    int dx = ecureil.getPosition().getX();
                    int dy = ecureil.getPosition().getY();

                    ZoneVide vide1 = new ZoneVide();
                    vide1.initPosition(hibou.getPosition().getX(), hibou.getPosition().getY());

                    // Remplace l'ancien hibou par une zone vide
                    matrice.get(hibou.getPosition().getX()).set(hibou.getPosition().getY(), vide1);

                    // Ajoute le hibou à la position de l'écureuil
                    matrice.get(dx).set(dy, hibou);

                    hibou.setEstRassasie(true);
                    hibou.setPosition(dx, dy);

                    System.out.println("après avoir mangé : " + hibou.getPosition());
                    return;
                }
            }
        }

        List<Position> pos = positions(matrice, hibou);
        if (!pos.isEmpty()) {
            Position position = pos.get(new Random().nextInt(pos.size()));

            ZoneVide vide = new ZoneVide();
            vide.initPosition(hibou.getPosition().getX(), hibou.getPosition().getY());

            // Remplace l'ancien hibou par une zone vide ou un autre composant
            if (!hibou.getSurLeComposant().isEmpty()) {
                matrice.get(hibou.getPosition().getX())
                        .set(hibou.getPosition().getY(), hibou.getSurLeComposant().get(0));
                hibou.supp();
            } else {
                matrice.get(hibou.getPosition().getX()).set(hibou.getPosition().getY(), vide);
            }

            // Ajoute le hibou à la nouvelle position
            matrice.get(position.getX()).set(position.getY(), hibou);
            hibou.setPosition(position.getX(), position.getY());

            System.out.println("hibou déplacé à la position : " + position);
        }
    }

    @Override
    public String toString() {
        return Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
    }
}