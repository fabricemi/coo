package org.example.modele.animaux;

import org.example.modele.*;

import java.util.*;

public class Hibou extends Animaux {
    String rep;
    private EtatHibou etatHibou;
    private int nbTourRestant;
    private boolean estRassasie;
    List<ComposantJeu> surLeComposant = new ArrayList<>();

    public Hibou() {
        rep = Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
        setEtatHibou(new HibouAffame());
        nbTourRestant = 1;
        estRassasie = false;
    }

    public List<ComposantJeu> getSurLeComposant() {
        return surLeComposant;
    }

    public void estSurLeComposant(ComposantJeu composantJeu) {
        surLeComposant.add(composantJeu);
    }

    public void supp() {
        surLeComposant.clear();
    }

    public void diminuerNbrTr() {
        nbTourRestant -= 1;
    }

    public int getNbTourRestant() {
        return nbTourRestant;
    }

    public void setEstRassasie(boolean estRassasie) {
        this.estRassasie = estRassasie;
    }

    public void setNbTourRestant(int nbTourRestant) {
        this.nbTourRestant = nbTourRestant;
    }

    public EtatHibou getEtatHibou() {
        return etatHibou;
    }

    public void setEtatHibou(EtatHibou etatHibou) {
        this.etatHibou = etatHibou;
    }

    @Override
    public void setApparence() {
        if (estRassasie) {
            rep = Colors.BLACK.getCode() + "H" + Colors.RESET.getCode();
            setEtatHibou(new HibouAuRepos());
        } else {
            rep = Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
            setEtatHibou(new HibouAffame());
        }

        if (getNbTourRestant() <= 0) {
            setEtatHibou(new HibouAffame());
            setNbTourRestant(1);
        }
    }

    @Override
    public Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        etatHibou.seDeplacer(matrice, this);
        return matrice;
    }

    @Override
    public String toString() {
        return rep;
    }
}
