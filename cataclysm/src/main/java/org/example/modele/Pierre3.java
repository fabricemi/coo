package org.example.modele;

public class Pierre3 extends Pierre{
    @Override
    public String toString() {
        return Colors.BLACK.getCode() + "S" + Colors.RESET.getCode();
    }
}
