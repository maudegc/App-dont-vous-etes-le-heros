package com.example.tp2.domaine;

import java.util.ArrayList;

public class Aventure {
    private String title;


    private ArrayList<Chapitre> lesChapitres;
    private boolean estCommence;


    public Aventure() {
        lesChapitres = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void ajouterChapitre(Chapitre leChapitre) {
        lesChapitres.add(leChapitre);
    }

    public Chapitre getChapitre(int unNumero) {
        return lesChapitres.get(unNumero);
    }


    @Override
    public String toString() {
        return "Aventure{" +
                "title='" + title +
                "size= " + lesChapitres.size();
    }

    public boolean getEstCommence() {
        return estCommence;
    }

    public void setEstCommence(boolean b) {
        estCommence = b;

    }
}
