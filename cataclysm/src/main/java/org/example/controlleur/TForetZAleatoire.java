package org.example.controlleur;

import org.example.modele.themes.Theme;
import org.example.modele.themes.ThemeForet;
import org.example.modele.themes.ZForetNouvelle;
import org.example.modele.themes.ZoneDeJeu;

public class TForetZAleatoire extends ThemeZoneCreateur{
    @Override
    public ZoneDeJeu createZoneDeJeu() {
        return new ZForetNouvelle(80,20);
    }

    @Override
    public Theme createTheme() {
        return new ThemeForet();
    }
}
