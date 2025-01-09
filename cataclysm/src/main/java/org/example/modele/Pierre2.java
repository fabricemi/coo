package org.example.modele;

public class Pierre2 extends Pierre{

    @Override
    public String toString() {
        return Colors.BLACK.getCode() + "L" + Colors.RESET.getCode();
    }
}
