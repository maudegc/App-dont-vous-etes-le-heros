package com.example.tp2.présentation.contrat;

import android.content.Context;

import com.example.tp2.domaine.Aventure;

import java.util.ArrayList;

public interface IContratVuePresentateurChoixAventure {
    interface IVueChoixAventure {

        void naviguerÉcranIntro();

        Context getContext();


        void afficherMessageAttente();
    }

    interface IPrésentateurChoixAventure {

        void traiterRequêteChoisirAventure();

        void traiterRequêteChoisirAventure(String nom, String url);

        void traiterRequeteAventureEnregistre(Aventure uneAventure);

        boolean estDansBD(String nom);

        ArrayList<Aventure> getAventureOFFLINE();
    }
}
