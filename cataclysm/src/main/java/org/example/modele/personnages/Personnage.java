package org.example.modele.personnages;


import org.example.modele.*;

import java.util.*;

public class Personnage extends ComposantJeu implements Sujet {

    private static Personnage instance;

    private StratDepPersonnage stratDepPersonnage;

    private List<Observateur> amis=new ArrayList<>();

    private PosPersonnage position;
    private List<ComposantJeu> objetRamasser = new ArrayList<>();


    public static Personnage getInstance() {
        if (instance == null) {
            instance = new Personnage();
        }
        return instance;
    }

    public void setStratDepPersonnage(StratDepPersonnage stratDepPersonnage) {
        this.stratDepPersonnage = stratDepPersonnage;
    }

    public void initPosition(int x, int y) {
        this.position = PosPersonnage.getInstance(x, y);
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public PosPersonnage getPosition() {
        return position;
    }

    public boolean seDeplacer(Map<Integer, List<ComposantJeu>> matrice,String sens) {
        switch (sens) {
            case "D":
                this.setStratDepPersonnage(new DepHdPersonnage());
                break;
            case "G":
                this.setStratDepPersonnage(new DepHgPersonnage());
                break;
            case "H":
                this.setStratDepPersonnage(new DepVhPersonnage());
                break;
            case "B":
                this.setStratDepPersonnage(new DepVbPersonnage());
                break;
            default:
                throw new RuntimeException("sens inconnu");
        }
        return stratDepPersonnage.seDeplacer(matrice,this);
    }


    public List<ComposantJeu> getObjetRamasser() {
        return objetRamasser;
    }

    public void seBattre() {
        //TODO
    }

    public Map<String, ComposantJeu> elementAutours(Map<Integer, List<ComposantJeu>> matrice)
            throws IndexOutOfBoundsException, NullPointerException {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        Map<String, ComposantJeu> eltsAutours = new HashMap<>();
        if (y + 1 < matrice.get(x).size()) {
            eltsAutours.put("D", matrice.get(x).get(y + 1));
        }
        if ( y - 1 >= 0) {
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


    public boolean ramasserObjet(Map<Integer, List<ComposantJeu>> matrice, String sens) {
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);
            if(eltsAutours.containsKey(sens)){
                ComposantJeu cps=eltsAutours.get(sens);
                if(cps instanceof Aliment){
                    int col=this.caseAmodifier(sens)[0];
                    int row=this.caseAmodifier(sens)[1];
                    this.objetRamasser.add(cps);
                    matrice.get(col).remove(row);
                    matrice.get(col).add(row,new ZoneVide());

                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }

    private Integer[] caseAmodifier(String sens){
        int x=this.getPosition().getX();
        int y=this.getPosition().getY();
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

    public boolean lancerNourriture(Map<Integer, List<ComposantJeu>> matrice, String sens){
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);
            List<Aliment> aliments=List.of(new Champignon(),new Gland(),new Champignon(), new Gland());
            if(eltsAutours.containsKey(sens)){
                ComposantJeu cps=eltsAutours.get(sens);
                if(cps instanceof ZoneVide){
                    int col=this.caseAmodifier(sens)[0];
                    int row=this.caseAmodifier(sens)[1];
                    matrice.get(col).remove(row);
                    matrice.get(col).add(row,aliments.get(new Random().nextInt(aliments.size())));
                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }
    public boolean apprivoiser(Map<Integer, List<ComposantJeu>> matrice, String sens) {
        try{
            Map<String, ComposantJeu> eltsAutours = this.elementAutours(matrice);

            if(eltsAutours.containsKey(sens)){
                ComposantJeu cps=eltsAutours.get(sens);
                if(cps instanceof Ecureil || cps instanceof Singe){
                    this.attacher((Observateur) cps);
                    return true;
                }
            }
            return false;
        }
        catch (IndexOutOfBoundsException | NullPointerException i){
            return false;
        }
    }


    public void donnerCoup(){
        if(!this.amis.isEmpty()){
            System.out.println(this.getAmis().get(0));
            System.out.println(this.getAmis().get(1));
            Animaux animaux=(Animaux) this.amis.get(new Random().nextInt(this.amis.size()));
            System.out.println(animaux);
            detacher((Observateur) animaux);
            System.out.println(this.amis.contains(animaux));
        }
    }
    public List<Observateur> getAmis() {
        return amis;
    }

    @Override
    public void attacher(Observateur o) {
        if(!this.amis.contains(o)){
            this.amis.add(o);
        }
    }

    @Override
    public void detacher(Observateur o) {
        this.amis.remove(o);
    }


}
