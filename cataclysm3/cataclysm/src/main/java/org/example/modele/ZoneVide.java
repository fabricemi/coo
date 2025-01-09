package org.example.modele;

public class ZoneVide extends ComposantJeu {
    protected String representation;

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
