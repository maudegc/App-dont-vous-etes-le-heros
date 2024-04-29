package com.example.tp2.présentation.présentateur;

import com.example.tp2.présentation.contrat.IContratVuePresentateurCombat;
import com.example.tp2.présentation.modèle.Modèle;
import com.example.tp2.présentation.modèle.ModèleCombat;

import java.util.ArrayList;

/**
 * Classe représentant le présentateur de combat
 **/
public class Présentateur_combat implements IContratVuePresentateurCombat.IPrésentateurCombat {

    private final IContratVuePresentateurCombat.IVueCombat _vue;
    private final ModèleCombat _modèleCombat;
    private final Modèle _modèle;
    private int compteur = 0;

    public Présentateur_combat(IContratVuePresentateurCombat.IVueCombat vue) {
        _vue = vue;
        _modèleCombat = ModèleCombat.getModèleCombat();
        _modèle = Modèle.getModèle();
    }

    @Override
    public void traiterRequêteJouer() {


        if (!_modèleCombat.combatEstTerminé()) {
            ArrayList<String> infosCombat;
            boolean aGagne = false;
            boolean egalite = false;
            infosCombat = _modèleCombat.jouerTour(compteur);


            if (ModèleCombat.getPerdantTour() == null) {
                egalite = true;
            } else {
                aGagne = !ModèleCombat.getPerdantTour().equals(Modèle.getModèle().getPersonnage().getNom());
            }

            _vue.afficherDescriptionCombat(infosCombat, aGagne, egalite);

            if (_modèleCombat.getEnnemi().getStatEndurance() <= 0) {

                compteur++;
            }

        } else {
            if (_modèleCombat.getGagnantPartie().equals(Modèle.getModèle().getPersonnage().getNom())) {
                Modèle.getModèle().getProchainChapitre(1);
            } else {
                Modèle.getModèle().getProchainChapitre(0);
            }
            _vue.naviguerÉcranChapitre();
        }


    }

    @Override
    public void actualiserStat() {
        _vue.afficherEnduranceEnnemi(_modèleCombat.getEnnemi().getStatEndurance());
        _vue.afficherNom(_modèle.getPersonnage().getNom());
        _vue.afficherStatAgilité(_modèle.getPersonnage().getStatAgilité());
        _vue.afficherStatEndurance(_modèle.getPersonnage().getStatEndurance());
        _vue.afficherStatForce(_modèle.getPersonnage().getStatForce());
        _vue.afficherStatIntelligence(_modèle.getPersonnage().getStatIntelligence());
    }


    @Override
    public void actualiserArrièrePlan() {
        _vue.afficherArrièrePlan(_modèle.getChapitreCourantEnChapitre().getArrière_plan());
    }

    @Override
    public void traiterRequêteAllerÀÉcranChapitre() {
        _vue.naviguerÉcranChapitre();
    }


}


