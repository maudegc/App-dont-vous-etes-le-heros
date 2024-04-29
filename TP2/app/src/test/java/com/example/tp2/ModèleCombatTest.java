package com.example.tp2;

import com.example.tp2.domaine.Personnage;
import com.example.tp2.présentation.modèle.Modèle;
import com.example.tp2.présentation.modèle.ModèleCombat;

import net.bytebuddy.implementation.bytecode.Throw;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ModèleCombatTest {
    @Test
    public void TestGagantDeLaPartieEstTroll() {
        String résultatAttendu = "Troll";
        String résultatObtenu;
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        Modèle modèle = Modèle.getModèle();

        //modèle.getPersonnage().créationDuPersonnage("Joueur");
        modèle.getPersonnage().setNom("Joueur");
        modèle.getPersonnage().générerStatistiquesAléatoire();
        modèle.getPersonnage().setStatEndurance(0);

        résultatObtenu = modèleCombat.getGagnantPartie();
        assertEquals(résultatAttendu, résultatObtenu);
    }

    @Test
    public void TestGagantDeLaPartieEstJoueur() {
        String résultatAttendu = "Joueur";
        String résultatObtenu;
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        Modèle modèle = Modèle.getModèle();

        modèle.getPersonnage().setNom("Joueur");
        modèle.getPersonnage().générerStatistiquesAléatoire();
        modèleCombat.getEnnemi().setStatEndurance(0);

        résultatObtenu = modèleCombat.getGagnantPartie();
        assertEquals(résultatAttendu, résultatObtenu);
    }

    @Test
    public void TestJouerUnTour() {
        ModèleCombat modèlecombat = mock(ModèleCombat.class);
        modèlecombat.jouerTour();
        verify(modèlecombat).jouerTour();
    }

    @Test
    public void TestLeCombatEstTerminéQuandJoueurEstÀ0() {
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        Modèle modèle = Modèle.getModèle();

        modèle.getPersonnage().générerStatistiquesAléatoire();
        modèle.getPersonnage().setStatEndurance(0);

        boolean résultatObtenu = modèleCombat.combatEstTerminé();
        assertTrue(résultatObtenu);
    }

    @Test
    public void TestLeCombatEstTerminéQuandEnnemiEstÀ0() {
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        Modèle modèle = Modèle.getModèle();

        modèle.getPersonnage().générerStatistiquesAléatoire();
        modèleCombat.getEnnemi().setStatEndurance(0);

        boolean résultatObtenu = modèleCombat.combatEstTerminé();
        assertTrue(résultatObtenu);
    }

    @Test
    public void TestLeCombatEstPasTerminé() {
        ModèleCombat modèleCombat = ModèleCombat.getModèleCombat();
        Modèle modèle = Modèle.getModèle();

        modèle.getPersonnage().générerStatistiquesAléatoire();

        boolean résultatObtenu = modèleCombat.combatEstTerminé();
        assertFalse(résultatObtenu);
    }
}
