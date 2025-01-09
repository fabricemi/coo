package org.example.modele.animaux;

import org.example.modele.Colors;
import org.example.modele.ComposantJeu;

import java.util.List;
import java.util.Map;

public class HibouAuRepos extends EtatHibou{
    @Override
    public void seDeplacer(Map<Integer, List<ComposantJeu>> matrice, Hibou hibou) {
        hibou.dimunierNbrTr();
        System.out.println("hibou au repos à la position "+hibou.getPosition());
        if(hibou.getNbTourRestant()<=0){
            hibou.setNbTourRestant(1);
            hibou.setEstRassasie(false);
        }

        int x=hibou.getPosition().getX();
        int y=hibou.getPosition().getY();

        matrice.get(x).remove(y);
        matrice.get(x).add(y, hibou);
    }

    @Override
    public String toString() {
        return Colors.BLACK.getCode() + "H" + Colors.RESET.getCode();
    }
}
