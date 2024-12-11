package org.example.modele.animaux;

import org.example.modele.Colors;

public class SingeAffame extends EtatSinge{

    @Override
    public String gererEtat() {
        return  Colors.BLACK.getCode() + "S" + Colors.RESET.getCode();
    }
}
