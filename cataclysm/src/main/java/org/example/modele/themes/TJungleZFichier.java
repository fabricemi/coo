package org.example.modele.themes;

public class TJungleZFichier extends ThemeZoneCreateur{
    @Override
    public ZoneDeJeu createZoneDeJeu() {
        return new ZJungleParFichier();
    }
    @Override
    public Theme createTheme() {
        return new ThemeJungle();
    }
}
