package com.example.tp2.présentation.présentateur;

import com.example.tp2.présentation.contrat.IContratVuePresentateurPersonnage;
import com.example.tp2.présentation.modèle.Modèle;

public class Présentateur_personnage implements IContratVuePresentateurPersonnage.IContratPrésentateurPersonnage {

    private final IContratVuePresentateurPersonnage.IContratVuePersonnage vue;
    private Modèle modèle;
    private int nbrFois = 0;

    public Présentateur_personnage(IContratVuePresentateurPersonnage.IContratVuePersonnage vue) {

        this.modèle = Modèle.getModèle();
        this.vue = vue;
    }

    public void setLeModèle(Modèle unModèle) {
        modèle = unModèle;
    }

    public void setNbrFois(int nbrFois) {
        this.nbrFois = nbrFois;
    }

    /**
     * Méthode qui empêche l'utilisateur de créer un personnage si le nom de son personnage est vide
     * l'utilisateure recoit un message d'erreur lui indiquant, si le nom est présent, l'utilisateur
     * peut créer son personnage.
     *
     * @param nomPersonnage le nom que l'utilisateur veut donner à son personnage.
     */
    @Override
    public void permettreCréationPersonnage(String nomPersonnage) {
        if (nomPersonnage.equals("")) {
            vue.afficherMessageErreurCreationPersonnageNomVide();

        } else if (modèle.getPersonnage().getStatIntelligence() == 0) {
            vue.afficherMessageErreurCreationPersonnageStatistiquesVides();
        } else {

            modèle.créationPersonnage(nomPersonnage);
            vue.naviguerEcranChapitre();
        }
    }

    /**
     * Méthode qui empêche l'utilisateur de générer plus de 3 fois les statistiques de son personnage,
     * l'utilisateur recoit un message d'erreur lui indiquant cela
     */
    @Override
    public void permettreGénérationStatsAléatoire() {
        nbrFois++;
        if (nbrFois == 3) {
            vue.afficherMessageErreurStats();
            vue.afficherStatAléatoireAgilité(modèle.getPersonnage().getStatAgilité());
            vue.afficherStatAléatoireEndurance(modèle.getPersonnage().getStatEndurance());
            vue.afficherStatAléatoireForce(modèle.getPersonnage().getStatForce());
            vue.afficherStatAléatoireIntelligence(modèle.getPersonnage().getStatIntelligence());
        } else {
            modèle.générerStatistiquesAléatoires();
            vue.afficherStatAléatoireAgilité(modèle.getPersonnage().getStatAgilité());
            vue.afficherStatAléatoireEndurance(modèle.getPersonnage().getStatEndurance());
            vue.afficherStatAléatoireForce(modèle.getPersonnage().getStatForce());
            vue.afficherStatAléatoireIntelligence(modèle.getPersonnage().getStatIntelligence());
        }

    }
}
