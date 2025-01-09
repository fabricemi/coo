package org.example.modele;

import org.example.modele.animaux.Ecureil;
import org.example.modele.animaux.Hibou;
import org.example.modele.themes.ThemeZoneCreateur;
import org.example.modele.aliments.Aliment;
import org.example.modele.aliments.Banane;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.Gland;
import org.example.modele.animaux.Animaux;
import org.example.modele.personnages.Personnage;
import org.example.modele.themes.Theme;
import org.example.modele.themes.ZoneDeJeu;

import java.util.*;

public class Jeu {

    private ZoneDeJeu zoneDeJeu;
    private Theme theme;

    private Map<Integer, List<ComposantJeu>> composants;
    private int nb = 0;
    List<Aliment> aliments_reserves_personnage;

    public Jeu(ThemeZoneCreateur zoneCreateur) {
        this.zoneDeJeu = zoneCreateur.createZoneDeJeu();
        theme = zoneCreateur.createTheme();


        this.zoneDeJeu.generateCarte();
        theme.setZoneDeJeu(zoneDeJeu);

        composants = zoneDeJeu.getMatriceObjet();
        //Historique.getInstance().addState(composants);
    }


    public List<String> appliquerAffichage(Map<Integer, List<ComposantJeu>> e) {
        return theme.appliquerAffichage(e);
    }

    public void setAliments_reserves_personnage(List<Aliment> aliments_reserves_personnage) {
        this.aliments_reserves_personnage = aliments_reserves_personnage;
    }

    public void chargerReservePersonnage(String them) {
        switch (them) {
            case "F":
                setAliments_reserves_personnage(List.of(new Champignon(), new Gland(), new Champignon(), new Gland(), new Gland(), new Gland()));
                break;
            case "J":
                setAliments_reserves_personnage(List.of(new Champignon(), new Banane(), new Champignon(), new Banane(), new Banane(), new Banane()));
                break;
            default:
                throw new RuntimeException("thème inconnu");
        }
    }


    public void setComposants(Map<Integer, List<ComposantJeu>> composants) {
        Map<Integer, List<ComposantJeu>> map = new TreeMap<>();

        for (Map.Entry<Integer, List<ComposantJeu>> entry : composants.entrySet()) {
            Integer key = entry.getKey();

            List<ComposantJeu> originalList = entry.getValue();
            List<ComposantJeu> copiedList = new ArrayList<>(originalList);

            map.put(key,copiedList);
        }
        this.composants = map;
    }

    public void setCarte(ZoneDeJeu zoneDeJeu) {
        this.zoneDeJeu = zoneDeJeu;
    }

    public boolean deplacerPersonnage(String sens) {
        return Personnage.getInstance().seDeplacer(getMatriceCarte(), sens);
    }

    public boolean lancerNourriture(String sens) {
        return lancer(sens);
    }

    private boolean lancer(String sens) {
        try {
            Map<String, ComposantJeu> eltsAutours = Personnage.getInstance().elementAutours(getMatriceCarte());

            if (eltsAutours.containsKey(sens)) {
                ComposantJeu cps = eltsAutours.get(sens);
                if (cps instanceof ZoneVide) {
                    int col = Personnage.getInstance().caseAmodifier(sens)[0];
                    int row = Personnage.getInstance().caseAmodifier(sens)[1];
                    getMatriceCarte().get(col).remove(row);
                    getMatriceCarte().get(col).add(row, aliments_reserves_personnage.get(new Random().nextInt(aliments_reserves_personnage.size())));
                    return true;
                }
            }
            return false;
        } catch (IndexOutOfBoundsException | NullPointerException i) {
            return false;
        }
    }
  /*  public boolean apprivoiserAnimal(String sens){
        return  Personnage.getInstance().apprivoiser(getMatriceCarte(), sens);
    }*/

    public boolean ramasserObjet(String sens) {
        return Personnage.getInstance().ramasserObjet(getMatriceCarte(), sens);
    }

    public Map<Integer, List<ComposantJeu>> getMatriceCarte() {
        return composants;
    }

    public List<Animaux> getAnimals() {
        /*Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator = getMatriceCarte().entrySet().iterator();

        List<Animaux> animaux = new ArrayList<>();

        while (iterator.hasNext()) {
            List<ComposantJeu> composantJeus = iterator.next().getValue();
            for (int i = 0; i < composantJeus.size(); i++) {
                if (composantJeus.get(i) instanceof Animaux) {
                    animaux.add((Animaux) composantJeus.get(i));
                }
            }

        }
        return animaux;*/

        List<Animaux> animaux = new ArrayList<>();
        for (List<ComposantJeu> ligne : composants.values()) {
            for (ComposantJeu composant : ligne) {
                if (composant instanceof Animaux) {
                    animaux.add((Animaux) composant);
                }
            }
        }
        return animaux;
    }

    public void addTour() {
        Historique.getInstance().addState(getMatriceCarte());
    }

    public void deplacerAnimaux() {
        getAnimals().forEach(
                (a) -> {
                    this.setComposants((a).seDeplacer(getMatriceCarte()));
                }
        );
    }

    public boolean reposer(String sens) {
        return Personnage.getInstance().reposer(getMatriceCarte(), sens);
    }

    public int compterHiboux() {
        int compteur = 0;
        for (List<ComposantJeu> ligne : composants.values()) {
            for (ComposantJeu composant : ligne) {
                if (composant instanceof Hibou) {
                    compteur++;
                }
            }
        }
        System.out.println("il y a " + compteur + " hibous");
        return compteur;
    }


    public void compte() {
        Iterator<Map.Entry<Integer, List<ComposantJeu>>> iterator = getMatriceCarte().entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            List<ComposantJeu> composantJeus = iterator.next().getValue();
            for (int i = 0; i < composantJeus.size(); i++) {
                if (composantJeus.get(i) instanceof Ecureil) {
                    n += 1;
                }
            }

        }
        System.out.println("il y a: " + n + " ecureils");

    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public boolean donnerCoup() {
        return Personnage.getInstance().donnerCoup();
    }


    /**
     * applique la mise à jour des animaux en fonction de leur etat
     */
    public void mettreAjour() {
        getAnimals().forEach(
                (a) -> {
                    (a).setApparence();
                }
        );
    }

    public boolean remonterTemps(String sens) {
        int toursARemonter = Personnage.getInstance().ramasserPierre(getMatriceCarte(), sens);
        int size = Historique.getHistorique().getEtats().size();

        if (toursARemonter == 2 && size > 2) {
            composants.clear();
            Historique.getInstance().remonter(2);
            setComposants(Historique.getHistorique().getTopState());

            return true;
        } else if (toursARemonter == 3 && size > 3) {
            composants.clear();
            Historique.getInstance().remonter(3);
            setComposants(Historique.getHistorique().getTopState());
            return true;
        }
        else {
            return false;
        }
    }

}
