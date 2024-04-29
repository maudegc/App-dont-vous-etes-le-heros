package com.example.tp2;

import com.example.tp2.domaine.Personnage;
import com.example.tp2.domaine.SourceChapitre.SourceChapitreTab;
import com.example.tp2.présentation.modèle.Modèle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class ModèleTest {

    @Test
    public void testGénérerStatistiquesAléatoire(){
        Modèle modèle = Modèle.getModèle();
        Personnage personnage = mock(Personnage.class);
        modèle.setPersonnage(personnage);
        modèle.générerStatistiquesAléatoires();
        verify(personnage).générerStatistiquesAléatoire();
    }
    @Test
    public void testCréationnPersonnage(){
        Modèle modèle = Modèle.getModèle();
        Personnage personnage = mock(Personnage.class);
        modèle.setPersonnage(personnage);
        modèle.créationPersonnage("joueur");
        verify(personnage).setNom("joueur");
    }





    @Test
    public void testGetProchainChapitreSiJoueurAPerdu(){
        int résultatAttendu =0;
        int résultatObtenu;
        SourceChapitreTab source = new SourceChapitreTab();
        System.out.println(source.getNbrChapitre());
        Modèle modele = Modèle.getModèle();
        modele.getPersonnage().setNbrGems(2);
        modele.setChapitreCourant(6);
        modele.getProchainChapitre(2);
        résultatObtenu = modele.getChapitreCourant();
        assertEquals(résultatAttendu,résultatObtenu);
    }

    @Test
    public void testGetProchainChapitreSiOption0(){
        int résultatAttendu = 0;
        int résultatObtenu;
        Modèle modele = Modèle.getModèle();
        modele.getProchainChapitre(0);
        résultatObtenu = modele.getChapitreCourant();
        assertEquals(résultatAttendu,résultatObtenu);
    }
    @Test
    public void testGetProchainChapitreAutre(){
        int résultatAttendu = 1;
        int résultatObtenu;
        Modèle modele = Modèle.getModèle();
        modele.getProchainChapitre(1);
        résultatObtenu = modele.getChapitreCourant();
        assertEquals(résultatAttendu,résultatObtenu);
    }
    @Test
    public void testNbrChapitre(){
        SourceChapitreTab source = mock(SourceChapitreTab.class);
        Modèle modele = Modèle.getModèle();
        modele.setSourceChapitre(source);
        modele.getNbrChapitre();

        verify(source).getNbrChapitre();
    }


    @Test
    public void testRéinitialiserJeu(){
        Modèle modèle = Modèle.getModèle();
        int chapitreCourantAttendu =1;
        boolean recommencerAttendu = true;
        int chapitreCourrantObtenu;
        boolean recommencerObtenu;


        modèle.réinitialiserJeu();
        chapitreCourrantObtenu =  modèle.getChapitreCourant();
        recommencerObtenu = modèle.getRecommencer();
        assertEquals(chapitreCourantAttendu,chapitreCourrantObtenu);
        assertEquals(recommencerAttendu,recommencerObtenu);

    }
    @Test
    public void testGetNbrChapitre(){
        Modèle modèle = Modèle.getModèle();
        SourceChapitreTab source = mock(SourceChapitreTab.class);
        modèle.setSourceChapitre(source);
        modèle.getNbrChapitre();
        verify(source).getNbrChapitre();
    }



}
