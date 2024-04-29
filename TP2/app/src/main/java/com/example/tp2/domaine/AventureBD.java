package com.example.tp2.domaine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

/**
 * Classe qui controle la base de données.
 */
public class AventureBD extends SQLiteOpenHelper {


    public AventureBD(Context context) {
        super(context, BDContrat.DATABASE_NAME, null, BDContrat.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BDContrat.SQL_CREATE_AVENTURE);
        db.execSQL(BDContrat.SQL_CREATE_AVENTUREJSON);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BDContrat.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /***
     * Permet d'enregistrer une aventure dans la base de donnée
     *
     *
     * @param chapitreCourant

     * @param unPersonnage
     * @return
     */
    public void enregistrerAventure(int chapitreCourant, Personnage unPersonnage, Aventure uneAventure) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(BDContrat.TableAventure.COLONNE_NOM_AVENTURE, uneAventure.getTitle());
        values.put(BDContrat.TableAventure.COLONNE_CHAPITRE_COURANT, chapitreCourant);
        values.put(BDContrat.TableAventure.COLONNE_CHAPITRE_ESTCOMMENCE, uneAventure.getEstCommence());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_nom, unPersonnage.getNom());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statIntelligence, unPersonnage.getStatIntelligence());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statForce, unPersonnage.getStatForce());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statAgilité, unPersonnage.getStatAgilité());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statEndurance, unPersonnage.getStatEndurance());

        db.insert(BDContrat.TableAventure.NOM_TABLE, null, values);

        Gson gson = new Gson();
        ContentValues valuesJson = new ContentValues();
        valuesJson.put(BDContrat.AventureJson.COLONNE_NOM_AVENTURE, uneAventure.getTitle());
        valuesJson.put(BDContrat.AventureJson.COLONNE_JSON, gson.toJson(uneAventure, Aventure.class));

        db.insert(BDContrat.AventureJson.NOM_TABLE, null, valuesJson);
    }

    /**
     * Permet de modifier les données d'une aventure dans la base de données
     *
     * @param
     * @param chapitreCourant
     * @param
     * @param unPersonnage
     */
    public void updateAventure(int chapitreCourant, Personnage unPersonnage, Aventure uneAventure) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(BDContrat.TableAventure.COLONNE_NOM_AVENTURE, uneAventure.getTitle());
        values.put(BDContrat.TableAventure.COLONNE_CHAPITRE_COURANT, chapitreCourant);
        values.put(BDContrat.TableAventure.COLONNE_CHAPITRE_ESTCOMMENCE, uneAventure.getEstCommence());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_nom, unPersonnage.getNom());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statIntelligence, unPersonnage.getStatIntelligence());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statForce, unPersonnage.getStatForce());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statAgilité, unPersonnage.getStatAgilité());
        values.put(BDContrat.TableAventure.COLONNE_PERSO_statEndurance, unPersonnage.getStatEndurance());


        String selection = BDContrat.TableAventure.COLONNE_NOM_AVENTURE + " = \"" + uneAventure.getTitle() + "\"";


        db.update(BDContrat.TableAventure.NOM_TABLE, values, selection, null);


    }

    /**
     * Permet de chercher une aventure dans la base de données avec son nom.
     *
     * @param nomAventure le nom de l'aventure que l'on cherche
     * @return l'aventure ayant pour nom nomAventure
     */
    public Cursor getAventureEnregistre(String nomAventure) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String[] projection = {
                BDContrat.TableAventure.COLONNE_NOM_AVENTURE,
                BDContrat.TableAventure.COLONNE_CHAPITRE_COURANT,
                BDContrat.TableAventure.COLONNE_CHAPITRE_ESTCOMMENCE,
                BDContrat.TableAventure.COLONNE_PERSO_nom,
                BDContrat.TableAventure.COLONNE_PERSO_statIntelligence,
                BDContrat.TableAventure.COLONNE_PERSO_statForce,
                BDContrat.TableAventure.COLONNE_PERSO_statAgilité,
                BDContrat.TableAventure.COLONNE_PERSO_statEndurance
        };
        String selection = BDContrat.TableAventure.COLONNE_NOM_AVENTURE + " = '" + nomAventure + "'";
        cursor = db.query(BDContrat.TableAventure.NOM_TABLE, projection, selection, null, null, null, null);

        return cursor;
    }

    public Cursor getAventureJson(String nomAventure) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String[] projection = {
                BDContrat.AventureJson.COLONNE_NOM_AVENTURE,
                BDContrat.AventureJson.COLONNE_JSON

        };
        String selection = BDContrat.AventureJson.COLONNE_NOM_AVENTURE + " = '" + nomAventure + "'";
        cursor = db.query(BDContrat.AventureJson.NOM_TABLE, projection, selection, null, null, null, null);

        return cursor;
    }

    public Cursor getAllAventureJson() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String[] projection = {
                BDContrat.AventureJson.COLONNE_NOM_AVENTURE,
                BDContrat.AventureJson.COLONNE_JSON

        };
        cursor = db.query(BDContrat.AventureJson.NOM_TABLE, projection, null, null, null, null, null);

        return cursor;
    }
}


