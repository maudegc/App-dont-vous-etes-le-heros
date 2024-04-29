package com.example.tp2;

import com.example.tp2.présentation.contrat.IContratVuePresentateurChapitre;
import com.example.tp2.présentation.modèle.Modèle;
import com.example.tp2.présentation.présentateur.Présentateur_chapitre;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class Présentateur_chapitreTest {


    @Test
    public void TestInitialiser(){
        IContratVuePresentateurChapitre.IContratVueChapitre vue =  mock(IContratVuePresentateurChapitre.IContratVueChapitre.class);
        Présentateur_chapitre presentateur_chapitre = new Présentateur_chapitre(vue);
        presentateur_chapitre.initialiser();
        Modèle leModèle= Modèle.getModèle();
        verify(vue).afficherDescriptionChapitre(leModèle.getChapitreCourantEnChapitre().getDescription());

        verify(vue).afficherArrièrePlanChapitre(leModèle.getChapitreCourantEnChapitre().getArrière_plan());
        verify(vue).afficherNbrGem(leModèle.getPersonnage().getNom());
        verify(vue).afficherStatAgilité(leModèle.getPersonnage().getStatAgilité());
        verify(vue).afficherStatEndurance(leModèle.getPersonnage().getStatEndurance());
        verify(vue).afficherStatForce(leModèle.getPersonnage().getStatForce());
        verify(vue).afficherStatIntelligence(leModèle.getPersonnage().getStatIntelligence());
        Modèle.getModèle().réinitialiserJeu();
    }
    @Test
    public void TestTraiterOptionRetourneNaviguerChapitreCombatQuandChapitreCombat(){
        /*SourceChapitre laSource = mock(SourceChapitre.class);
        when(laSource.getInfoChapitre(2))
        .thenReturn(new Chapitre(2,true,""));
        Modèle.getModèle().setSourceChapitre(laSource);

        IContratVuePresentateurChapitre.IContratVueChapitre vue =  mock(IContratVuePresentateurChapitre.IContratVueChapitre.class);
        Présentateur_chapitre presentateur_chapitre = new Présentateur_chapitre(vue);

        presentateur_chapitre.traiterOption(1);
        verify(vue).naviguerÉranCombat();
        Modèle.getModèle().réinitialiserJeu();*/

    }
    @Test
    public void TestTraiterOptionRetourneNaviguerProchainChapitreQuandPasCombat(){

        /*SourceChapitre laSource = mock(SourceChapitre.class);
        when(laSource.getInfoChapitre(2))
                .thenReturn(new Chapitre(2,"","","",""));
        Modèle.getModèle().setSourceChapitre(laSource);

        IContratVuePresentateurChapitre.IContratVueChapitre vue =  mock(IContratVuePresentateurChapitre.IContratVueChapitre.class);
        Présentateur_chapitre presentateur_chapitre = new Présentateur_chapitre(vue);

        presentateur_chapitre.traiterOption(1);
        Modèle leModèle= Modèle.getModèle();
        assertEquals(2, leModèle.getChapitreCourantEnChapitre().getNumero());
        Modèle.getModèle().réinitialiserJeu();*/

    }
    @Test
    public void TestTraiterOptionRetourneNaviguerAcceuilQuandOnRecommence(){
       /* Modèle leModèle  = mock(Modèle.class);
        when(leModèle.getRecommencer())
            .thenReturn(true);
        when(leModèle.getChapitreCourantEnChapitre())
                .thenReturn(new Chapitre(2,"","","",""));
        IContratVuePresentateurChapitre.IContratVueChapitre vue =  mock(IContratVuePresentateurChapitre.IContratVueChapitre.class);
        Présentateur_chapitre presentateur_chapitre = new Présentateur_chapitre(vue);
        presentateur_chapitre.setLeModèle(leModèle);
        presentateur_chapitre.traiterOption(1);
        verify(vue).naviguerÉranAcceuil();*/
    }


}

