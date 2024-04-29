package com.example.tp2;
import com.example.tp2.présentation.contrat.IContratVuePresentateurPersonnage;
import com.example.tp2.présentation.modèle.Modèle;
import com.example.tp2.présentation.présentateur.Présentateur_personnage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Test;
public class Présentateur_personnageTest {

    @Test
    public void  testPermettreCréationPersonnageSiAucunNom(){

        IContratVuePresentateurPersonnage.IContratVuePersonnage vue = mock(IContratVuePresentateurPersonnage.IContratVuePersonnage.class);
        Présentateur_personnage présentateur = new Présentateur_personnage(vue);
        présentateur.permettreCréationPersonnage("");
        verify(vue).afficherMessageErreurCreationPersonnageNomVide();

    }
    @Test
    public void testPermettreCréationPersonnageSiAucuneStatistiqueAEteGenerer(){
        Modèle modèle = Modèle.getModèle();
        IContratVuePresentateurPersonnage.IContratVuePersonnage vue = mock(IContratVuePresentateurPersonnage.IContratVuePersonnage.class);
        Présentateur_personnage présentateur = new Présentateur_personnage(vue);
        présentateur.setLeModèle(modèle);

        modèle.getPersonnage().setStatIntelligence(0);
        présentateur.permettreCréationPersonnage("joueur");
        verify(vue).afficherMessageErreurCreationPersonnageStatistiquesVides();
    }
    @Test
    public void testPermettreCréationPersonnageSiAucuneErreur(){
        Modèle modèle = Modèle.getModèle();
        IContratVuePresentateurPersonnage.IContratVuePersonnage vue = mock(IContratVuePresentateurPersonnage.IContratVuePersonnage.class);
        Présentateur_personnage présentateur = new Présentateur_personnage(vue);
        présentateur.setLeModèle(modèle);

        modèle.getPersonnage().setStatIntelligence(5);
        présentateur.permettreCréationPersonnage("joueur");
        verify(vue).naviguerEcranChapitre();
    }

    @Test
    public void testPermettreGénérationStatsAléatoireAppelerUnefois(){
        Modèle modèle = Modèle.getModèle();
        IContratVuePresentateurPersonnage.IContratVuePersonnage vue = mock(IContratVuePresentateurPersonnage.IContratVuePersonnage.class);
        Présentateur_personnage présentateur = new Présentateur_personnage(vue);

            présentateur.permettreGénérationStatsAléatoire();
            présentateur.setNbrFois(1);


        verify(vue).afficherStatAléatoireAgilité(modèle.getPersonnage().getStatAgilité());
        verify(vue).afficherStatAléatoireEndurance(modèle.getPersonnage().getStatEndurance());
        verify(vue).afficherStatAléatoireForce(modèle.getPersonnage().getStatForce());
        verify(vue).afficherStatAléatoireIntelligence(modèle.getPersonnage().getStatIntelligence());
    }
    @Test
    public void testPermettreGénérationStatsAléatoireAppelerTroisFois(){
        Modèle modèle = Modèle.getModèle();
        IContratVuePresentateurPersonnage.IContratVuePersonnage vue = mock(IContratVuePresentateurPersonnage.IContratVuePersonnage.class);
        Présentateur_personnage présentateur = new Présentateur_personnage(vue);

        présentateur.permettreGénérationStatsAléatoire();
        présentateur.setNbrFois(2);

        vue.afficherMessageErreurStats();
        verify(vue).afficherStatAléatoireAgilité(modèle.getPersonnage().getStatAgilité());
        verify(vue).afficherStatAléatoireEndurance(modèle.getPersonnage().getStatEndurance());
        verify(vue).afficherStatAléatoireForce(modèle.getPersonnage().getStatForce());
        verify(vue).afficherStatAléatoireIntelligence(modèle.getPersonnage().getStatIntelligence());
    }
}
