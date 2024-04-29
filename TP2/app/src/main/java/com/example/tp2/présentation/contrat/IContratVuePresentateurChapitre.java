package com.example.tp2.présentation.contrat;

import com.example.tp2.domaine.Choix;

import java.util.ArrayList;

public class IContratVuePresentateurChapitre {
    public interface IContratVueChapitre {
        void afficherDescriptionChapitre(String uneDescription, String messageDeFin);

        void afficherNbrGem(String nom);

        void afficherStatForce(int statForce);

        void afficherStatAgilité(int statAgilité);

        void afficherStatEndurance(int statEndurance);

        void afficherStatIntelligence(int statIntelligence);

        void naviguerÉranCombat();

        void naviguerÉranAcceuil();

        void afficherArrièrePlanChapitre(String arrièrePlan);

        void afficherChoix(ArrayList<Choix> lesChoix);

    }

    public interface IContratPrésentateurChapitre {
        void traiterOption(int option);

        void initialiser();

    }
}
