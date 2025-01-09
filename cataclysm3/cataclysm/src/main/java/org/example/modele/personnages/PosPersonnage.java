package org.example.modele.personnages;

public class PosPersonnage {

    private int x;
    private int y;

    private static PosPersonnage instance;

    private PosPersonnage(int x, int y){
        this.x=x;
        this.y=y;
    }

    public static PosPersonnage getInstance(int x, int y) {
        if(instance==null){
            instance=new PosPersonnage(x,y);
        }
        return instance;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
