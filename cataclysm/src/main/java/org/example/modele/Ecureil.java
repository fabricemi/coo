package org.example.modele;

import org.example.modele.personnages.Personnage;

import java.util.*;

public class Ecureil extends Animaux {

    private static int globalIdCounter = 0;
    private final int id;
    Map<String, ComposantJeu> eltsAutours = new TreeMap<>();

    {
        id = ++globalIdCounter;
    }

    String priorite;
    public boolean priority_gland = false;
    public String sens_assoc_gland;
    public boolean priority_champignon = false;
    public String sens_assoc_champignon;

    public boolean priority_standard = false;
    public String sens_assoc_standard;
    private boolean estRassasie;
    private int nbrTour;

    public int getId() {
        return id;
    }

    Personnage personnage;
    public boolean estAmi;
    private String rep;

    public Ecureil() {
        rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
        estAmi = false;
        nbrTour = 5;
        estRassasie = true;
    }

    @Override
    public String toString() {
        mettreAjouter();
        return rep;
    }

    private void directionPrioritaire() {
        Set<Map.Entry<String, ComposantJeu>> tests = eltsAutours.entrySet();
        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, ComposantJeu> paire = iterator.next();
            String sens = paire.getKey();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof Gland) {
                priority_gland = true;
                sens_assoc_gland = sens;
            }
            if (composantJeu instanceof Champignon) {
                priority_champignon = true;
                sens_assoc_champignon = sens;
            }

            else {
                priority_standard=true;
                sens_assoc_standard=sens;
            }
        }
    }

    private Integer[] caseAmodifier(String sens) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int col;
        int row;
        switch (sens) {
            case "D" -> {
                col = x;
                row = y + 1;
            }
            case "G" -> {
                col = x;
                row = y - 1;
            }
            case "H" -> {
                col = x - 1;
                row = y;
            }
            default -> {
                col = x + 1;
                row = y;
            }
        }

        return new Integer[]{col, row};
    }

    public void seNourir() {
        estRassasie = true;
    }

    public int seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {
        this.elementAutours(matrice);
        this.directionPrioritaire();

        if (priority_gland) {
            int col = caseAmodifier(sens_assoc_gland)[0];
            int row = caseAmodifier(sens_assoc_gland)[1];

            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), new ZoneVide());

            matrice.get(col).remove(row);
            matrice.get(col).add(row, this);
            seNourir();
            priority_gland = false;
            return 0;
        }
        if (priority_champignon) {
            int col = caseAmodifier(sens_assoc_champignon)[0];
            int row = caseAmodifier(sens_assoc_champignon)[1];

            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), new ZoneVide());

            matrice.get(col).remove(row);
            matrice.get(col).add(row, this);
           seNourir();

            priority_champignon = false;


            return 1;
        }
        if(priority_standard) {
            int col = caseAmodifier(sens_assoc_standard)[0];
            int row = caseAmodifier(sens_assoc_standard)[1];

            matrice.get(this.getPosition().getX()).remove(this.getPosition().getY());
            matrice.get(this.getPosition().getX()).add(this.getPosition().getY(), new ZoneVide());

            matrice.get(col).remove(row);
            matrice.get(col).add(row, this);
            nbrTour--;
        }

        this.eltsAutours.clear();
        return 2;

    }


    public boolean isEstRassasie() {
        return estRassasie;
    }

    public void setEstRassasie(boolean estRassasie) {
        this.estRassasie = estRassasie;
    }

    public int getNbrTour() {
        return nbrTour;
    }

    public void setNbrTour(int nbrTour) {
        this.nbrTour = nbrTour;
    }

    public boolean estAffame() {
        return nbrTour == 0;
    }

    private Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice)
            throws IndexOutOfBoundsException, NullPointerException {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if (y + 1 < matrice.get(x).size()) {
            eltsAutours.put("D", matrice.get(x).get(y + 1));
        }
        if (y - 1 >= 0) {
            eltsAutours.put("G", matrice.get(x).get(y - 1));
        }
        if (matrice.containsKey(x - 1)) {
            eltsAutours.put("H", matrice.get(x - 1).get(y));
        }
        if (matrice.containsKey(x + 1)) {
            eltsAutours.put("B", matrice.get(x + 1).get(y));
        }
        return eltsAutours;
    }


    @Override
    public void mettreAjouter() {
        personnage = Personnage.getInstance();
        estAmi = personnage.getAmis().contains(this);
        if (estAmi) {
            rep = Colors.PURPLE.getCode() + "E" + Colors.RESET.getCode();
        } else {
            rep = Colors.BLUE.getCode() + "E" + Colors.RESET.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ecureil ecureil = (Ecureil) o;
        return id == ecureil.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
