package com.example.tp2;

import com.example.tp2.domaine.Personnage;
import com.example.tp2.domaine.RandomStub;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersonnageTest {

    @Test
    public void testSetEnduranceÀ10(){
        Personnage personnage = new Personnage();
        int résultatAttendu = 10;
        int résultatObtenu;
        personnage.setStatEndurance(10);
        résultatObtenu = personnage.getStatEndurance();
        assertEquals(résultatAttendu,résultatObtenu);
    }
    @Test
    public void testSetEnduranceÀMoins5(){
        Personnage personnage = new Personnage();
        int résultatAttendu = 0;
        int résultatObtenu;
        personnage.setStatEndurance(-5);
        résultatObtenu = personnage.getStatEndurance();
        assertEquals(résultatAttendu,résultatObtenu);
    }
    @Test
    public void testSetNbrGemsÀMoins5(){
        Personnage personnage = new Personnage();
        int résultatAttendu = 0;
        int résultatObtenu;

        personnage.setNbrGems(-5);
        résultatObtenu = personnage.getNbrGems();
        assertEquals(résultatAttendu, résultatObtenu);
    }
    @Test
    public void testSetNbrGemsÀ10(){
        Personnage personnage = new Personnage();
        int résultatAttendu = 10;
        int résultatObtenu;
        personnage.setNbrGems(10);
        résultatObtenu = personnage.getNbrGems();
        assertEquals(résultatAttendu,résultatObtenu);
    }

    @Test
    public void testGénérerStatistiquesAléatoire(){
        int enduranceAttendu = 6;
        int intelligenceAttendu =6;
        int agiliteAttendu = 9;
        int forceAttendu = 7;
        int enduranceObtenu;
        int intelligenceObtenu;
        int agiliteObtenu;
        int forceObtenu;
        RandomStub randomStub = new RandomStub(new int[]{6,7,6,9});

        Personnage personnage = new Personnage(randomStub);

        personnage._générerStatistiquesAléatoire();
        enduranceObtenu = personnage.getStatEndurance();
        forceObtenu = personnage.getStatForce();
        agiliteObtenu = personnage.getStatAgilité();
        intelligenceObtenu =personnage.getStatIntelligence();

        assertEquals(enduranceAttendu,enduranceObtenu);
        assertEquals(intelligenceAttendu,intelligenceObtenu);
        assertEquals(agiliteAttendu,agiliteObtenu);
        assertEquals(forceAttendu,forceObtenu);
    }



}
