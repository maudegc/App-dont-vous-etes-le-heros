package com.example.tp2.domaine;

/**
 * Classe qui represente les informations d'un choix
 */
public class Choix {
    private int numero;
    private int prochainChapitre;
    private String description;

    public Choix() {

    }

    public Choix(int numero, int prochainChapitre, String description) {
        this.numero = numero;
        this.prochainChapitre = prochainChapitre;
        this.description = description;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getProchainChapitre() {
        return prochainChapitre - 1;
    }

    public void setProchainChapitre(int prochainChapitre) {
        this.prochainChapitre = prochainChapitre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Choix{" +
                "numero=" + numero +
                ", prochainChapitre=" + prochainChapitre +
                ", description='" + description + '\'' +
                '}';
    }
}
