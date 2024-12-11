package org.example.controlleur;

import org.example.modele.themes.Theme;
import org.example.modele.themes.ThemeJungle;
import org.example.modele.themes.ZJungleNouvelle;
import org.example.modele.themes.ZoneDeJeu;

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
