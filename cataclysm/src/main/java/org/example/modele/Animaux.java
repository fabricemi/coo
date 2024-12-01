package org.example.modele;

public abstract class Animaux extends ComposantJeu implements Observateur{
    protected Position position;

    public void initPosition(int x, int y) {
        this.position = new Position(x,y);
    }

    public void setPosition(int x, int y){
        this.position.x=x;
        this.position.y=y;
    }

    public Position getPosition() {
        return position;
    }
}
