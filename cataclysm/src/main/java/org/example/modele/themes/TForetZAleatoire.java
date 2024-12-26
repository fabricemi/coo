package org.example.modele.themes;

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
