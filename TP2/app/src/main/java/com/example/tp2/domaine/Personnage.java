package com.example.tp2.domaine;

import java.util.Random;

public class Personnage {
    IGénérateurNombresAléatoires _générateur;
    private String nom;
    private int statIntelligence;
    private int statForce;
    private int statAgilité;
    private int statEndurance;
    private int nbrGems;

    public Personnage(IGénérateurNombresAléatoires générateur) {
        _générateur = générateur;
    }

    public Personnage() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getStatIntelligence() {
        return statIntelligence;
    }

    public void setStatIntelligence(int statIntelligence) {
        if (statIntelligence < 0) {
            this.statIntelligence = 0;
        } else {
            this.statIntelligence = statIntelligence;
        }
    }

    public int getStatForce() {
        return statForce;
    }

    public void setStatForce(int statForce) {
        if (statForce < 0) {
            this.statForce = 0;
        } else {
            this.statForce = statForce;
        }
    }

    public int getStatAgilité() {
        return statAgilité;
    }

    public void setStatAgilité(int statAgilité) {
        if (statAgilité < 0) {
            this.statAgilité = 0;
        } else {
            this.statAgilité = statAgilité;
        }
    }

    public int getStatEndurance() {
        return statEndurance;
    }

    public void setStatEndurance(int statEndurance) {
        if (statEndurance < 0) {
            this.statEndurance = 0;
        } else {
            this.statEndurance = statEndurance;
        }
    }

    public int getNbrGems() {
        return nbrGems;
    }

    public void setNbrGems(int nbrGems) {
        if (nbrGems < 0) {
            this.nbrGems = 0;
        } else {
            this.nbrGems = nbrGems;
        }
    }

    //Méthode qui permet de générer les statistiques aléatoire du personnage  entre 5 et 10.
    public void générerStatistiquesAléatoire() {
        int statAléatoire = 0;
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            statAléatoire = random.nextInt(10 - 5 + 1) + 5;

            switch (i) {
                case 0:
                    setStatIntelligence(statAléatoire);
                    break;
                case 1:
                    setStatForce(statAléatoire);
                    break;
                case 2:
                    setStatEndurance(statAléatoire);
                    break;
                case 3:
                    setStatAgilité(statAléatoire);
                    break;
            }
        }

    }

    public void _générerStatistiquesAléatoire() {
        int statAléatoire = 0;


        for (int i = 0; i < 4; i++) {

            statAléatoire = _générateur.prochainEntier(5, 9);

            switch (i) {
                case 0:
                    setStatIntelligence(statAléatoire);
                    break;
                case 1:
                    setStatForce(statAléatoire);
                    break;
                case 2:
                    setStatEndurance(statAléatoire);
                    break;
                case 3:
                    setStatAgilité(statAléatoire);
                    break;
            }
        }

    }


    @Override
    public String toString() {
        return "Personnage{" +
                "nom='" + nom + '\'' +
                ", statForce=" + statForce +
                ", statAgilité=" + statAgilité +
                ", statEndurance=" + statEndurance +
                '}';
    }
}
