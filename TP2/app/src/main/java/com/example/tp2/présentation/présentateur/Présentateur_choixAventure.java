package com.example.tp2.présentation.présentateur;

import androidx.fragment.app.Fragment;

import com.example.tp2.domaine.Aventure;
import com.example.tp2.domaine.SourceChapitre.AventureException;
import com.example.tp2.domaine.SourceChapitre.SourceChapitreHTTP;
import com.example.tp2.domaine.SourceChapitre.SourceChapitreTab;
import com.example.tp2.présentation.contrat.IContratVuePresentateurChoixAventure;
import com.example.tp2.présentation.modèle.Modèle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Présentateur_choixAventure implements IContratVuePresentateurChoixAventure.IPrésentateurChoixAventure {

    private final IContratVuePresentateurChoixAventure.IVueChoixAventure _vue;
    private final Modèle _modèle;


    public Présentateur_choixAventure(IContratVuePresentateurChoixAventure.IVueChoixAventure vue) {
        _vue = vue;
        _modèle = Modèle.getModèle();
    }


    @Override
    public void traiterRequêteChoisirAventure() {

        _modèle.setSourceChapitre(new SourceChapitreTab());

        try {
            _modèle.getAventureEnCours();
        } catch (AventureException e) {
            e.printStackTrace();
        }
        _modèle.enregistrerAventure(_vue.getContext());
        _modèle.initialiserAventureEnCours();


        _vue.naviguerÉcranIntro();


    }

    @Override
    public void traiterRequêteChoisirAventure(String nom, String url) {
        try {

            _modèle.setSourceChapitre(new SourceChapitreHTTP(_vue.getContext(),
                    new URL(url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        Thread fil_esclave = new Thread(() -> {
            try {
                final Aventure aventure = _modèle.getAventureEnCours();
                _modèle.enregistrerAventure(_vue.getContext());
                _modèle.initialiserAventureEnCours();
                _vue.naviguerÉcranIntro();
                ((Fragment) _vue).getActivity().runOnUiThread(() -> {


                });
            } catch (AventureException e) {
                ((Fragment) _vue).getActivity().runOnUiThread(() -> {

                });
            }
        });
        fil_esclave.start();
    }

    @Override
    public void traiterRequeteAventureEnregistre(Aventure uneAventure) {
        _modèle.setAventureEnregistre(uneAventure);
        _modèle.initialiserAventureEnCours();
        _vue.naviguerÉcranIntro();
    }

    @Override
    public boolean estDansBD(String nom) {
        return _modèle.getAventureNouvelle(nom, _vue.getContext());
    }

    @Override
    public ArrayList<Aventure> getAventureOFFLINE() {
        return _modèle.getALLAventureEnregistre(_vue.getContext());
    }
}
