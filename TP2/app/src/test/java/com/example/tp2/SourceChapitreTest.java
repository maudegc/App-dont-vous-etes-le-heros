package com.example.tp2;

import com.example.tp2.domaine.Chapitre;
import com.example.tp2.domaine.SourceChapitre.SourceChapitre;
import com.example.tp2.domaine.SourceChapitre.SourceChapitreTab;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SourceChapitreTest {
    SourceChapitre source = new SourceChapitreTab();
    @Test
    public void testGetChapitre(){
        Chapitre chap = source.getInfoChapitre(5);
        assertEquals(chap.getNumero(),5);
    }
    @Test
    public void testGetDernierChapitre(){
        int nbrChapitre = source.getNbrChapitre();
        assertEquals(nbrChapitre,8);
    }
}
