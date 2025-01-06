package org.example.modele.animaux;

import org.example.modele.*;

import java.util.*;

public class Hibou extends Animaux {
    String rep;
    private EtatHibou etatHibou;
    private int nbTourRestant;
    private boolean estRassasie;
    List<ComposantJeu> surLeComposant=new ArrayList<>();
    public Hibou() {
        rep = Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
        setEtatHibou(new HibouAffame());
        nbTourRestant=1;
        estRassasie=false;

    }

    public List<ComposantJeu> getSurLeComposant() {
        return surLeComposant;
    }

    public void estSurLeComposant(ComposantJeu composantJeu){
        surLeComposant.add(composantJeu);
    }

    public void supp(){
        surLeComposant.clear();
    }
    public  void  dimunierNbrTr(){
        nbTourRestant-=1;
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
        if(estRassasie){
            rep= Colors.BLACK.getCode() + "H" + Colors.RESET.getCode();
        }
        else {
            rep=Colors.WHITE.getCode() + "H" + Colors.RESET.getCode();
        }

        if(getNbTourRestant()<=0){
            setEtatHibou(new HibouAffame());
            setNbTourRestant(1);
        }
        System.out.println("hibou "+this.getPosition()+" "+rep);

        //rep=etatHibou.toString();
    }

    @Override
    public Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        if(estRassasie){
            setEtatHibou(new HibouAuRepos());
        }
        else {
            setEtatHibou(new HibouAffame());
        }
        etatHibou.seDeplacer(matrice, this);
        return matrice;
    }

    public List<Position> possibleHPositionsADeuxCases(Map<Integer, List<ComposantJeu>> matrice)
            throws IndexOutOfBoundsException, NullPointerException {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        List<Position> positionList = new ArrayList<>();
        if (Utils.sensValide(x, y + 2, matrice)) {

            positionList.add(new Position(x, y + 2));

        }
        if (Utils.sensValide(x, y - 2, matrice)) {
            positionList.add(new Position(x, y - 2));
        }
        if (Utils.sensValide(x - 2, y, matrice)) {
            positionList.add(new Position(x - 2, y));
        }
        if (Utils.sensValide(x + 2, y, matrice)) {
            positionList.add(new Position(x + 2, y));
        }
        return positionList;
    }

    @Override
    public String toString() {
        return rep;
    }
}
