package com.example.tp2.domaine;

import java.util.ArrayList;

/**
 * Représente les informations d'un chapitre
 */

public class Chapitre {


    private ArrayList<Choix> lesChoix;
    private ArrayList<Personnage> lesEnnemis;
    private int numero;
    private String description;
    private String messageDeFin;
    private String arrière_plan = "";


    public Chapitre() {
        lesChoix = new ArrayList<>();
        lesEnnemis = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Personnage> getLesEnnemis() {
        return lesEnnemis;
    }

    public void setLesEnnemis(ArrayList<Personnage> lesEnnemis) {
        this.lesEnnemis = lesEnnemis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessageDeFin() {
        return messageDeFin;
    }

    public void setMessageDeFin(String messageDeFin) {
        this.messageDeFin = messageDeFin;
    }

    public Choix getChoix(int numero) {
        return lesChoix.get(numero);
    }

    public ArrayList<Choix> getLesChoix() {
        return lesChoix;
    }

    public void setLesChoix(ArrayList<Choix> lesChoix) {
        this.lesChoix = lesChoix;
    }

    public String getArrière_plan() {
        return arrière_plan;
    }

    public void setArrière_plan(String arrière_plan) {
        this.arrière_plan = arrière_plan;
    }

    public boolean isCombat() {
        return lesEnnemis.size() > 0;
    }

    /**
     * Ajoute un choix à la liste de choix
     *
     * @param unChoix le choix à ajouté
     */
    public void ajouterChoix(Choix unChoix) {
        lesChoix.add(unChoix);
    }

    /**
     * Ajoute unPersonne à la liste d'ennemis
     *
     * @param unPersonnage l'ennemi à ajouté
     */
    public void ajouterEnnemi(Personnage unPersonnage) {
        lesEnnemis.add(unPersonnage);
    }

    @Override
    public String toString() {
        return "Chapitre{" +
                "numero=" + numero +
                ", description='" + description + '\'' +
                "deadend= " + messageDeFin +
                ", lesChoix=" + lesChoix +
                '}' +
                "les ennemis" + lesEnnemis;
    }


}
