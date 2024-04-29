package com.example.tp2.domaine;

import com.example.tp2.domaine.SourceChapitre.AventureException;
import com.example.tp2.domaine.SourceChapitre.SourceChapitre;

public class RecupererAventure {
    SourceChapitre _source;

    public RecupererAventure(SourceChapitre source) {
        _source = source;
    }

    public Aventure recuperer() throws AventureException {
        return _source.getAventure();
    }
}
