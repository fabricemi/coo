package org.example.controlleur;

import org.example.modele.themes.Theme;
import org.example.modele.themes.ThemeForet;
import org.example.modele.themes.ZForetParFichier;
import org.example.modele.themes.ZoneDeJeu;

public class TForetZFichier extends ThemeZoneCreateur{
    @Override
    public ZoneDeJeu createZoneDeJeu() {
        return new ZForetParFichier();
    }

    @Override
    public Theme createTheme() {
        return new ThemeForet();
    }
}
