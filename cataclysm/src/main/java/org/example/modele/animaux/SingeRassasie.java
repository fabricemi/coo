package org.example.modele.animaux;

import org.example.modele.Colors;

public class SingeRassasie extends EtatSinge{
    @Override
    public String gererEtat() {
        return Colors.BLUE.getCode() + "S" + Colors.RESET.getCode();
    }
}
