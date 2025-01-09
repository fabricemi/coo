package org.example.modele.themes;

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
