package com.example.tp2.présentation.présentateur;

import com.example.tp2.présentation.contrat.IContratVuePresentateurChapitre;
import com.example.tp2.présentation.modèle.Modèle;

public class Présentateur_chapitre implements IContratVuePresentateurChapitre.IContratPrésentateurChapitre {
    IContratVuePresentateurChapitre.IContratVueChapitre vue;
    Modèle leModèle;

    public Présentateur_chapitre(IContratVuePresentateurChapitre.IContratVueChapitre _vue) {
        vue = _vue;
        leModèle = Modèle.getModèle();
    }

    public void setLeModèle(Modèle unModèle) {
        leModèle = unModèle;
    }

    /**
     * Traiter option
     *
     * @param option choix de l'option 1 ou 2
     *               Navigue vers l'écran combat si c'est un combat
     *               Navigue vers le prochain chapitre si ce n'est pas un combat
     *               Navigue vers l'acceuil pour rejouer
     */
    @Override
    public void traiterOption(int option) {
        leModèle.getProchainChapitre(option);
        if (leModèle.getChapitreCourantEnChapitre().isCombat() == true) {
            vue.naviguerÉranCombat();
        } else {
            changerVue();
        }


    }


    private void changerVue() {
        vue.afficherDescriptionChapitre(leModèle.getChapitreCourantEnChapitre().getDescription(), leModèle.getChapitreCourantEnChapitre().getMessageDeFin());
        vue.afficherArrièrePlanChapitre(leModèle.getChapitreCourantEnChapitre().getArrière_plan());
        vue.afficherNbrGem(leModèle.getPersonnage().getNom());
        vue.afficherStatAgilité(leModèle.getPersonnage().getStatAgilité());
        vue.afficherStatEndurance(leModèle.getPersonnage().getStatEndurance());
        vue.afficherStatForce(leModèle.getPersonnage().getStatForce());
        vue.afficherStatIntelligence(leModèle.getPersonnage().getStatIntelligence());
        vue.afficherChoix(leModèle.getChapitreCourantEnChapitre().getLesChoix());
    }

    /**
     * Initialiser
     * Initialise l'écran avec le bon chapitre lors de sa création
     */
    @Override
    public void initialiser() {
        changerVue();
    }


}
