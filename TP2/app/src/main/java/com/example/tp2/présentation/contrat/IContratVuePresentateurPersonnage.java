package com.example.tp2.présentation.contrat;

public class IContratVuePresentateurPersonnage {
    public interface IContratVuePersonnage {
        void afficherStatAléatoireForce(int statForce);

        void afficherStatAléatoireAgilité(int statAgilité);

        void afficherStatAléatoireIntelligence(int statIntelligence);

        void afficherStatAléatoireEndurance(int statEndurance);

        void afficherMessageErreurCreationPersonnageNomVide();

        void afficherMessageErreurCreationPersonnageStatistiquesVides();

        void afficherMessageErreurStats();

        void naviguerEcranChapitre();

    }

    public interface IContratPrésentateurPersonnage {
        void permettreCréationPersonnage(String nomPersonnage);

        void permettreGénérationStatsAléatoire();

    }
}
