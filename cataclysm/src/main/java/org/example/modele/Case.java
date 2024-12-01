package org.example.modele;

public class Case {
    ComposantJeu composantJeu;
    Pos position;



    public Case(ComposantJeu composantJeu, Pos position) {
        this.composantJeu = composantJeu;
        this.position = position;
    }

    public ComposantJeu getComposantJeu() {
        return composantJeu;
    }

    public void setComposantJeu(ComposantJeu composantJeu) {
        this.composantJeu = composantJeu;
    }

    public Pos getPosition() {
        return position;
    }

    public void setPosition(Pos position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return composantJeu.toString();
    }
}
