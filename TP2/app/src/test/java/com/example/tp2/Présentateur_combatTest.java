package com.example.tp2;

import com.example.tp2.présentation.contrat.IContratVuePresentateurCombat;
import com.example.tp2.présentation.modèle.Modèle;
import com.example.tp2.présentation.modèle.ModèleCombat;
import com.example.tp2.présentation.présentateur.Présentateur_combat;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Présentateur_combatTest {

    @Test
    public void TestActualiserStat() {
        Modèle modèle = Modèle.getModèle();
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        IContratVuePresentateurCombat.IVueCombat vue = mock(IContratVuePresentateurCombat.IVueCombat.class);
        Présentateur_combat présentateur = new Présentateur_combat(vue);

        présentateur.actualiserStat();
        verify(vue).afficherStatIntelligence(modèle.getPersonnage().getStatIntelligence());
        verify(vue).afficherStatForce(modèle.getPersonnage().getStatForce());
        verify(vue).afficherStatEndurance(modèle.getPersonnage().getStatEndurance());
        verify(vue).afficherStatAgilité(modèle.getPersonnage().getStatAgilité());
        verify(vue).afficherNbrGem(modèle.getPersonnage().getNbrGems());
        verify(vue).afficherEnduranceEnnemi(modèleCombat.getEnnemi().getStatEndurance());
    }

    @Test
    public void TestActualiserArrièrePlan() {
        Modèle modèle = Modèle.getModèle();
        IContratVuePresentateurCombat.IVueCombat vue =  mock(IContratVuePresentateurCombat.IVueCombat.class);
        Présentateur_combat présentateur = new Présentateur_combat(vue);

        présentateur.actualiserArrièrePlan();
        verify(vue).afficherArrièrePlan(modèle.getChapitreCourantEnChapitre().getArrière_plan());
    }

    @Test
    public void TestTraiterRequêteAllerÀÉcranChapitre() {
        IContratVuePresentateurCombat.IVueCombat vue =  mock(IContratVuePresentateurCombat.IVueCombat.class);
        Présentateur_combat présentateur = new Présentateur_combat(vue);

        présentateur.traiterRequêteAllerÀÉcranChapitre();
        verify(vue).naviguerÉcranChapitre();
    }

}
