package com.example.tp2.présentation.présentateur;

import com.example.tp2.domaine.SourceChapitre.AventureException;
import com.example.tp2.présentation.contrat.IContratVuePresentateurIntro;
import com.example.tp2.présentation.modèle.Modèle;

public class Présentateur_intro implements IContratVuePresentateurIntro.IPrésentateurIntro {

    private final IContratVuePresentateurIntro.IVueIntro _vue;
    private final Modèle _modèle;


    public Présentateur_intro(IContratVuePresentateurIntro.IVueIntro vue) {
        _vue = vue;
        _modèle = Modèle.getModèle();
    }


    @Override
    public void iniatiliserVue() {
        try {
            _vue.changerTitre(_modèle.getAventureEnCours().getTitle());
        } catch (AventureException e) {
            e.printStackTrace();
        }
        if (_modèle.getAventureEstCommence() == true) {
            _vue.desactiverCommencer();
            _vue.activerRecCom();
        } else {
            _vue.activerCommencer();
            _vue.desactiverRecCom();
        }

    }

    @Override
    public void recommencer() {
        _modèle.réinitialiserJeu();
    }

    @Override
    public void traiterCommencer() {
        _modèle.commencerAventure();
    }


}
