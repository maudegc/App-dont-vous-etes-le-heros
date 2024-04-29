package com.example.tp2.présentation.modèle;

import android.content.Context;
import android.database.Cursor;

import com.example.tp2.domaine.Aventure;
import com.example.tp2.domaine.AventureBD;
import com.example.tp2.domaine.BDContrat;
import com.example.tp2.domaine.Chapitre;
import com.example.tp2.domaine.Personnage;
import com.example.tp2.domaine.RecupererAventure;
import com.example.tp2.domaine.SourceChapitre.AventureException;
import com.example.tp2.domaine.SourceChapitre.SourceChapitre;
import com.google.gson.Gson;

import java.util.ArrayList;

/***
 * Classe permetant de controller les aventures
 */
public class Modèle {

    private static Modèle leModèle;

    private int chapitreCourant = 0;
    private SourceChapitre laSource;
    private Personnage personnage = new Personnage();
    private Aventure aventureEnCours;
    private AventureBD bd;


    private Modèle() {

    }

    public static Modèle getModèle() {
        if (leModèle == null) {
            leModèle = new Modèle();
        }

        return leModèle;
    }

    public void setSourceChapitre(SourceChapitre uneSource) {
        laSource = uneSource;
    }

    public Aventure getAventureEnCours() throws AventureException {

        if (aventureEnCours == null) {
            aventureEnCours = new RecupererAventure(laSource).recuperer();
        }
        return aventureEnCours;
    }


    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public int getChapitreCourant() {
        return chapitreCourant;
    }

    public void setChapitreCourant(int nbrChapitre) {
        chapitreCourant = nbrChapitre;

    }

    /**
     * Permet de créer le personnage avec de manière aléatoire
     */
    public void générerStatistiquesAléatoires() {
        personnage.générerStatistiquesAléatoire();
    }

    public void créationPersonnage(String nomPersonnage) {
        personnage.setNom(nomPersonnage);
    }

    /***
     * Change le chapitre courant pour le prochain chapitre
     * @param option l'index du choix
     */
    public void getProchainChapitre(int option) {

        chapitreCourant = getChapitreCourantEnChapitre().getChoix(option).getProchainChapitre();


    }

    /**
     * Retourne le chapitre courant avec ses informations
     */
    public Chapitre getChapitreCourantEnChapitre() {
        Chapitre leChapitre = aventureEnCours.getChapitre(chapitreCourant);
        return leChapitre;
    }

    public void réinitialiserJeu() {
        chapitreCourant = 0;
        personnage = new Personnage();
        aventureEnCours.setEstCommence(true);
    }

    /**
     * Retourne le nombre de chapitres
     */
    public int getNbrChapitre() {
        return laSource.getNbrChapitre();
    }

    /**
     * Enregistre l'aventure dans la base de données
     *
     * @param context
     */
    public void enregistrerAventure(Context context) {

        if (getAventureNouvelle(aventureEnCours.getTitle(), context)) {
            bd = new AventureBD(context);
            bd.enregistrerAventure(chapitreCourant, getPersonnage(), aventureEnCours);
        }


    }

    /**
     * Initialise l'aventure en cours avec les données de la bd.
     *
     * @return
     */
    public Cursor initialiserAventureEnCours() {


        Cursor csJson = bd.getAventureJson((aventureEnCours.getTitle()));
        if (csJson.moveToFirst()) {
            Gson gson = new Gson();
            String json = csJson.getString(csJson.getColumnIndex(BDContrat.AventureJson.COLONNE_JSON));
            aventureEnCours = gson.fromJson(json, Aventure.class);
        }

        Cursor cs = bd.getAventureEnregistre(aventureEnCours.getTitle());
        if (cs.moveToFirst()) {
            chapitreCourant = cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_CHAPITRE_COURANT));
            boolean commence = cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_CHAPITRE_ESTCOMMENCE)) > 0;
            aventureEnCours.setEstCommence(commence);
            personnage = new Personnage();
            personnage.setNom(cs.getString(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_PERSO_nom)));
            personnage.setStatForce(cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_PERSO_statForce)));
            personnage.setStatAgilité(cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_PERSO_statAgilité)));
            personnage.setStatEndurance(cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_PERSO_statEndurance)));
            personnage.setStatIntelligence(cs.getInt(cs.getColumnIndex(BDContrat.TableAventure.COLONNE_PERSO_statIntelligence)));
        }


        return cs;

    }

    public void setAventureEnregistre(Aventure uneAventure) {
        aventureEnCours = uneAventure;
    }

    /**
     * Modifie les données de l'aventure en cours de la bd.
     */
    public void updateAventure() {

        bd.updateAventure(chapitreCourant, getPersonnage(), aventureEnCours);

    }


    public boolean getAventureEstCommence() {
        return aventureEnCours.getEstCommence();
    }

    public void commencerAventure() {
        aventureEnCours.setEstCommence(true);
    }

    public boolean getAventureNouvelle(String nom, Context context) {
        bd = new AventureBD(context);
        Cursor cs = bd.getAventureEnregistre(nom);
        boolean res = cs.getCount() <= 0;
        return res;

    }

    public ArrayList<Aventure> getALLAventureEnregistre(Context context) {
        ArrayList<Aventure> lesAventures = new ArrayList<>();
        bd = new AventureBD(context);
        Cursor cs = bd.getAllAventureJson();
        if (cs.moveToFirst()) {
            do {
                Aventure uneAventure;
                Gson gson = new Gson();
                String json = cs.getString(cs.getColumnIndex(BDContrat.AventureJson.COLONNE_JSON));
                uneAventure = gson.fromJson(json, Aventure.class);
                if (!uneAventure.getTitle().equals("Magical Balls")) {
                    lesAventures.add(uneAventure);
                }


            } while (cs.moveToNext());
        }
        return lesAventures;

    }


}
