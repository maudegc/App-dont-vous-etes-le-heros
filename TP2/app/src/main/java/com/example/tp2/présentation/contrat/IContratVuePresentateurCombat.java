package com.example.tp2.présentation.contrat;

import java.util.ArrayList;

public interface IContratVuePresentateurCombat {
    interface IVueCombat {
        void afficherDescriptionCombat(ArrayList<String> strings, boolean gagne, boolean aGagne);

        void afficherStatForce(int statForce);

        void afficherStatAgilité(int statAgilité);

        void afficherStatEndurance(int statEndurance);

        void afficherStatIntelligence(int statIntelligence);

        void afficherEnduranceEnnemi(int statEnduranceEnnemi);

        void naviguerÉcranChapitre();

        void afficherArrièrePlan(String arrière_plan);

        void afficherNom(String nom);
    }

    interface IPrésentateurCombat {
        void traiterRequêteJouer();

        void actualiserStat();

        void actualiserArrièrePlan();

        void traiterRequêteAllerÀÉcranChapitre();
    }
}
