package org.example.modele.animaux;

import org.example.modele.*;
import org.example.modele.aliments.Champignon;
import org.example.modele.aliments.ChampignonVenimeux;
import org.example.modele.aliments.Gland;

import java.util.*;

public class Renard extends Animaux {
    private String rep;

    public Renard() {
        rep = Colors.BLACK.getCode() + "R" + Colors.RESET.getCode();
    }

    @Override
    public void setApparence() {
        rep = Colors.BLACK.getCode() + "R" + Colors.RESET.getCode();
    }


    @Override
    public Map<Integer, List<ComposantJeu>> seDeplacer(Map<Integer, List<ComposantJeu>> matrice) {

        List<Ecureil> ecureils = Utils.ecureilsAutours(matrice,
                this.getPosition().getX(), this.getPosition().getY());

        if (!ecureils.isEmpty()) {
            Ecureil e=ecureils.get(new Random().nextInt(ecureils.size()));
            if (tenterAttack(matrice, e)) {
                System.out.println("renard a attaqué à la " + this.getPosition());
                System.out.println("presence "+matrice.get(e.getPosition().getX()).contains(e));
                System.out.println("pos pour confirmer la suppression... "+e.getPosition());
            }
        }

        List<String> dirs = getSens(matrice);
        if (!dirs.isEmpty()) {
            String sens = dirs.get(new Random().nextInt(dirs.size()));
            this.appliquerDeplacement(matrice, sens);
        }

        return matrice;
    }

    public List<String> getSens(Map<Integer, List<ComposantJeu>> matrice) {

        Set<Map.Entry<String, ComposantJeu>> tests = elementAutours(matrice).entrySet();
        List<String> sens = new ArrayList<>();

        Iterator<Map.Entry<String, ComposantJeu>> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ComposantJeu> paire = iterator.next();
            ComposantJeu composantJeu = paire.getValue();
            if (composantJeu instanceof ZoneVide) {
                sens.add(paire.getKey());
            }
        }

        return sens;

    }


    public boolean tenterAttack(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureil) {
        if (ecureil.isEstRefugieArbre()) {
            System.out.println("protegé par un arbre...");
            return false;
        }

        List<ComposantJeu> vegetaux = ecureil.estEntoureBouA(matrice);
        if (!vegetaux.isEmpty()) {
            Vegetaux v=(Vegetaux) vegetaux.get(0);
            if (v instanceof Arbre) {
                //ecureil.setEstEffraye(true);
                //ecureil.setEstRefugieArbre(true);
                ecureil.seDeplacer(matrice);
                System.out.println("effrayé situé à: "+ecureil.getPosition());
                return false;
            }
        }

        tuerEcureil(matrice, ecureil);
        return true;


    }

    @Override
    public String toString() {
        return rep;
    }

    public void tuerEcureil(Map<Integer, List<ComposantJeu>> matrice, Ecureil ecureil) {


        int dx = ecureil.getPosition().getX();
        int dy = ecureil.getPosition().getY();

        ZoneVide vide1 = new ZoneVide();
        vide1.initPosition(dx, dy);

        matrice.get(dx).remove(ecureil);
        matrice.get(dx).add(dy, vide1);

    }
}
