package org.example.modele.themes;

public class TJungleZAleaoire extends ThemeZoneCreateur{
    @Override
    public ZoneDeJeu createZoneDeJeu() {
        return new ZJungleNouvelle(80,20);
    }

    @Override
    public Theme createTheme() {
        return new ThemeJungle();
    }
}
