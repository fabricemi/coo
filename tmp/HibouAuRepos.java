package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class HibouAuRepos extends EtatHibou {
    @Override
    public void seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou) {
        hibou.diminuerNbrTr(); // Diminue le compteur
        System.out.println("Hibou au repos à la position " + hibou.getPosition() +
                ", tours restants : " + hibou.getNbTourRestant());

        if (hibou.getNbTourRestant() <= 0) {
            hibou.setNbTourRestant(1); // Réinitialise le compteur
            hibou.setEstRassasie(false); // Repart en mode affamé
            hibou.setEtatHibou(new HibouAffame());
            System.out.println("Hibou devient affamé !");
        }
    }

    @Override
    public String toString() {
        return Colors.BLACK.getCode() + "H" + Colors.RESET.getCode();
    }
}
