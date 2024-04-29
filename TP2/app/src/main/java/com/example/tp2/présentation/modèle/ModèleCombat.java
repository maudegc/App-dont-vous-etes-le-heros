package com.example.tp2.présentation.modèle;

import com.example.tp2.domaine.Personnage;

import java.util.ArrayList;

/**
 * Classe ModèleCombat contenant les méthodes du combat
 **/
public class ModèleCombat {

    static Modèle modèle = Modèle.getModèle();
    private static String perdantTour;
    private static int dommageInfligé;
    private static ArrayList<Personnage> lesEnnemis;
    private static int compteur = 0;
    private static ModèleCombat modèleCombat;
    private int compteurEnnemi;

    public ModèleCombat() {
    }

    public static ModèleCombat getModèleCombat() {
        if (modèleCombat == null) {
            modèleCombat = new ModèleCombat();
        }
        lesEnnemis = modèle.getChapitreCourantEnChapitre().getLesEnnemis();
        compteur = lesEnnemis.size();
        return modèleCombat;
    }

    public static String getPerdantTour() {
        return perdantTour;
    }

    public static int getDommageInfligé() {
        return dommageInfligé;
    }

    /**
     * Méthode permettant de définir le gagnant du combat
     *
     * @return le gagnant du combat
     **/
    public String getGagnantPartie() {
        String gagnantPartie = "";
        if (modèle.getPersonnage().getStatEndurance() > lesEnnemis.get(lesEnnemis.size() - 1).getStatEndurance() && lesEnnemis.get(lesEnnemis.size() - 1).getStatEndurance() == 0) {
            gagnantPartie = modèle.getPersonnage().getNom();
            modèle.getPersonnage().setNbrGems(modèle.getPersonnage().getNbrGems() + 1);
        } else if (lesEnnemis.get(lesEnnemis.size() - 1).getStatEndurance() > modèle.getPersonnage().getStatEndurance() && modèle.getPersonnage().getStatEndurance() == 0) {
            gagnantPartie = lesEnnemis.get(lesEnnemis.size() - 1).getNom();
        }
        return gagnantPartie;
    }

    /**
     * Méthode permettant de jouer un tour
     **/
    public ArrayList<String> jouerTour(int tour) {
        int coefficientAttaquePersonnage;
        int coefficientAttaqueEnnemi;
        int coefficientDéfenseDeAttaqué;
        int déPersonnage;
        int déEnnemi;
        String attaquant = "";
        ArrayList<String> infosCombats = new ArrayList<>();

        Personnage unEnnemi = lesEnnemis.get(tour);
        compteurEnnemi = tour;

        déPersonnage = (int) Math.round((Math.random() * 5) + 1);
        déEnnemi = (int) Math.round((Math.random() * 5) + 1);


        coefficientAttaqueEnnemi = unEnnemi.getStatAgilité() + déEnnemi;
        coefficientAttaquePersonnage = modèle.getPersonnage().getStatAgilité() + déPersonnage;

        if (coefficientAttaqueEnnemi > coefficientAttaquePersonnage) {
            attaquant = unEnnemi.getNom();
            perdantTour = modèle.getPersonnage().getNom();
        } else if (coefficientAttaqueEnnemi == coefficientAttaquePersonnage) {
            perdantTour = null;
        } else {
            attaquant = modèle.getPersonnage().getNom();
            perdantTour = unEnnemi.getNom();
        }

        if (attaquant.equals(unEnnemi.getNom())) {
            coefficientDéfenseDeAttaqué = coefficientAttaqueEnnemi - coefficientAttaquePersonnage;
            dommageInfligé = coefficientDéfenseDeAttaqué * unEnnemi.getStatForce();
            modèle.getPersonnage().setStatEndurance(modèle.getPersonnage().getStatEndurance() - dommageInfligé);
        } else {
            coefficientDéfenseDeAttaqué = coefficientAttaquePersonnage - coefficientAttaqueEnnemi;
            dommageInfligé = coefficientDéfenseDeAttaqué * modèle.getPersonnage().getStatForce();
            unEnnemi.setStatEndurance(unEnnemi.getStatEndurance() - dommageInfligé);
        }


        infosCombats.add(unEnnemi.getNom() + "," + coefficientAttaqueEnnemi);
        infosCombats.add(modèle.getPersonnage().getNom() + "," + coefficientAttaquePersonnage);
        infosCombats.add(attaquant);
        infosCombats.add(getPerdantTour() + "," + dommageInfligé);


        return infosCombats;

    }

    public boolean combatEstTerminé() {
        return lesEnnemis.get(lesEnnemis.size() - 1).getStatEndurance() <= 0 || modèle.getPersonnage().getStatEndurance() <= 0;
    }

    public Personnage getEnnemi() {
        return lesEnnemis.get(compteurEnnemi);
    }
}
