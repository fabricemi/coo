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
                    //ecureil.seDeplacer(matrice);
                } else {
                    System.out.println("Avant de manger "+hibou.getPosition());
                    int dx = ecureil.getPosition().getX();
                    int dy = ecureil.getPosition().getY();


                    ZoneVide vide1 = new ZoneVide();
                    vide1.initPosition(hibou.getPosition().getX(), hibou.getPosition().getY());
                    matrice.get(hibou.getPosition().getX()).remove(hibou.getPosition().getY());

                    if (!hibou.getSurLeComposant().isEmpty()) {
                        matrice.get(hibou.getPosition().getX()).add(hibou.getPosition().getY(),
                                hibou.getSurLeComposant().get(0));

                        hibou.supp();
                    } else {
                        matrice.get(hibou.getPosition().getX()).add(hibou.getPosition().getY(), vide1);
                    }



                    matrice.get(dx).remove(ecureil);
                    matrice.get(dx).add(dy, hibou);

                    hibou.setEstRassasie(true);
                    //hibou.setEtatHibou(new HibouAuRepos());

                    hibou.setPosition(dx, dy);
                    //hibou.setApparence();
                    System.out.println("après avoir mnger :" +hibou.getPosition());
                    return;
                }
            }
        }

        List<Position> pos = positions(matrice, hibou);
        if (!pos.isEmpty()) {
            Position position = pos.get(new Random().nextInt(pos.size()));


            ZoneVide vide = new ZoneVide();
            vide.initPosition(hibou.getPosition().getX(), hibou.getPosition().getY());

            matrice.get(hibou.getPosition().getX()).remove(hibou.getPosition().getY());

            if (!hibou.getSurLeComposant().isEmpty()) {
                matrice.get(hibou.getPosition().getX()).add(hibou.getPosition().getY(),
                        hibou.getSurLeComposant().get(0));

                hibou.supp();
            } else {
                matrice.get(hibou.getPosition().getX()).add(hibou.getPosition().getY(), vide);
            }

            if (matrice.get(position.getX()).get(position.getY()) instanceof Arbre
                    || matrice.get(position.getX()).get(position.getY()) instanceof Buisson) {

                hibou.estSurLeComposant(matrice.get(position.getX()).get(position.getY()));

                matrice.get(position.getX()).remove(position.getY());

                matrice.get(position.getX()).add(position.getY(), hibou);

            } else if (matrice.get(position.getX()).get(position.getY()) instanceof ZoneVide) {
                matrice.get(position.getX()).remove(position.getY());
                matrice.get(position.getX()).add(position.getY(), hibou);
            }
            System.out.println("hibou "+hibou.getPosition()+" deplacé à la position :" +position);
            hibou.setPosition(position.getX(), position.getY());
        }

    }

    @Override
    public String toString() {
        return Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
    }
}
