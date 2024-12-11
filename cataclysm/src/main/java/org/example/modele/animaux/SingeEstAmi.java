package org.example.modele.animaux;

import org.example.modele.Colors;

public class SingeEstAmi extends EtatSinge{

    @Override
    public String gererEtat()  {
        return Colors.PURPLE.getCode() + "S" + Colors.RESET.getCode();
    }

}
