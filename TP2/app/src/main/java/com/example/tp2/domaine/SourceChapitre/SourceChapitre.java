package com.example.tp2.domaine.SourceChapitre;


import com.example.tp2.domaine.Aventure;
import com.example.tp2.domaine.Chapitre;

public interface SourceChapitre {
    /**
     * retourne le chapitre entré en paramètre
     *
     * @param chapitre numéro du chapitre
     */
    Chapitre getInfoChapitre(int chapitre);

    int getNbrChapitre();

    Aventure getAventure() throws AventureException;
}

