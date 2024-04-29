package com.example.tp2.présentation.contrat;

import android.content.Context;

public interface IContratVuePresentateurIntro {
    interface IVueIntro {
        void naviguerÉcranPersonnage();

        void naviguerÉcranChapitre();

        Context getContext();

        void activerCommencer();

        void activerConfirmation();

        void activerRecCom();

        void desactiverCommencer();

        void desactiverConfirmation();

        void desactiverRecCom();

        void changerTitre(String unTitre);
    }

    interface IPrésentateurIntro {

        void iniatiliserVue();

        void recommencer();

        void traiterCommencer();
    }

}
