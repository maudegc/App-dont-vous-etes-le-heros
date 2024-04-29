package com.example.tp2.domaine;

import android.provider.BaseColumns;

public class BDContrat {

    public static final String DATABASE_NAME = "MesAventures.db";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_AVENTURE =
            "CREATE TABLE " + TableAventure.NOM_TABLE + " (" +
                    TableAventure._ID + " INTEGER PRIMARY KEY," +
                    TableAventure.COLONNE_NOM_AVENTURE + " TEXT," +
                    TableAventure.COLONNE_CHAPITRE_COURANT + " INTEGER," +
                    TableAventure.COLONNE_CHAPITRE_ESTCOMMENCE + " BOOLEAN," +
                    TableAventure.COLONNE_PERSO_nom + " TEXT," +
                    TableAventure.COLONNE_PERSO_statIntelligence + " INTEGER," +
                    TableAventure.COLONNE_PERSO_statForce + " INTEGER," +
                    TableAventure.COLONNE_PERSO_statAgilité + " INTEGER," +
                    TableAventure.COLONNE_PERSO_statEndurance + " INTEGER)";

    public static final String SQL_CREATE_AVENTUREJSON =
            "CREATE TABLE " + AventureJson.NOM_TABLE + " (" +
                    AventureJson.COLONNE_NOM_AVENTURE + " TEXT," +
                    AventureJson.COLONNE_JSON + " TEXT)";


    public static final String SQL_DELETE_ENTRIES =

            "DROP TABLE IF EXISTS " + TableAventure.NOM_TABLE;


    private BDContrat() {
    }

    public static class TableAventure implements BaseColumns {
        public static final String NOM_TABLE = "Aventure";
        public static final String COLONNE_NOM_AVENTURE = "Nom";
        public static final String COLONNE_CHAPITRE_COURANT = "ChapitreCourant";
        public static final String COLONNE_CHAPITRE_ESTCOMMENCE = "estCommence";
        public static final String COLONNE_PERSO_nom = "NomPerso";
        public static final String COLONNE_PERSO_statIntelligence = "StatIntelligence";
        public static final String COLONNE_PERSO_statForce = "StatForce";
        public static final String COLONNE_PERSO_statAgilité = "StatAgilité";
        public static final String COLONNE_PERSO_statEndurance = "StatEndurance";

    }

    public static class AventureJson implements BaseColumns {
        public static final String NOM_TABLE = "AventureJson";
        public static final String COLONNE_NOM_AVENTURE = "Nom";
        public static final String COLONNE_JSON = "Json";
    }


}
