package org.example.controlleur;

import org.example.modele.themes.Theme;
import org.example.modele.themes.ZoneDeJeu;

public abstract class ThemeZoneCreateur {
    public abstract ZoneDeJeu createZoneDeJeu();
    public abstract Theme createTheme();
}
