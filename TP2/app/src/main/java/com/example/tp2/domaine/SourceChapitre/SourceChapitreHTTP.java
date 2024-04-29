package com.example.tp2.domaine.SourceChapitre;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.tp2.domaine.Aventure;
import com.example.tp2.domaine.Chapitre;
import com.example.tp2.domaine.Choix;
import com.example.tp2.domaine.Personnage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SourceChapitreHTTP implements SourceChapitre {

    private final Context _ctx;
    private final URL _url;
    private final Aventure aventure;
    private JSONObject _response;


    public SourceChapitreHTTP(Context ctx, URL url) {
        this._ctx = ctx;
        this._url = url;
        aventure = new Aventure();
    }


    public JSONObject recuperer(String nom) throws AventureException {
        RequestQueue queue = Volley.newRequestQueue(_ctx);
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, _url.toString(), null, future, future);
        queue.add(request);

        try {
            JSONObject response = future.get();
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new AventureException("Interruption", e);
        }

        return null;


    }

    @Override
    public Chapitre getInfoChapitre(int chapitre) {
        return aventure.getChapitre(chapitre);
    }

    @Override
    public int getNbrChapitre() {
        return 0;
    }

    public void télécharger() {


    }

    @Override
    public Aventure getAventure() throws AventureException {
        _response = recuperer("");

        return téléchargerChapitre();
    }

    private Aventure téléchargerChapitre() {
        JSONArray lesChapitres = null;
        Chapitre unChapitre = null;

        try {
            aventure.setTitle(_response.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            lesChapitres = _response.getJSONArray("chapters");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lesChapitres.length(); i++) {
            try {
                if (lesChapitres != null) {
                    unChapitre = new Chapitre();
                    unChapitre.setNumero(lesChapitres.getJSONObject(i).getInt("id"));
                    unChapitre.setDescription(lesChapitres.getJSONObject(i).getString("description"));
                    unChapitre.setMessageDeFin(lesChapitres.getJSONObject(i).getString("deadend"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (unChapitre.getMessageDeFin() == null) {
                try {
                    téléchargerChoix(lesChapitres.getJSONObject(i), unChapitre);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    téléchargerCombat(lesChapitres.getJSONObject(i), unChapitre);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            aventure.ajouterChapitre(unChapitre);


        }
        return aventure;
    }

    private void téléchargerChoix(JSONObject chapitreJson, Chapitre unChapitre) {
        JSONArray lesChoix = null;
        JSONArray lesChoixDescriptions = null;
        Choix unChoix = null;

        try {
            lesChoix = chapitreJson.getJSONArray("choices");
            lesChoixDescriptions = chapitreJson.getJSONArray("choices_description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (lesChoix != null && lesChoixDescriptions != null) {
            for (int j = 0; j < lesChoix.length(); j++) {
                unChoix = new Choix();
                unChoix.setNumero(j);
                try {
                    unChoix.setProchainChapitre(lesChoix.getInt(j));
                    unChoix.setDescription(lesChoixDescriptions.getString(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                unChapitre.ajouterChoix(unChoix);
            }
        }

    }

    private void téléchargerCombat(JSONObject chapitreJson, Chapitre unChapitre) {
        JSONArray combat = null;
        Personnage unPersonnage = null;

        try {
            combat = chapitreJson.getJSONArray("combats");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (combat != null) {
            for (int g = 0; g < combat.length(); g++) {
                unPersonnage = new Personnage();
                try {
                    unPersonnage.setNom(combat.getJSONObject(g).getString("enemy"));
                    unPersonnage.setStatForce(combat.getJSONObject(g).getInt("force"));
                    unPersonnage.setStatEndurance(combat.getJSONObject(g).getInt("endurance"));
                    unPersonnage.setStatAgilité(combat.getJSONObject(g).getInt("combatskill"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                unChapitre.ajouterEnnemi(unPersonnage);

            }
        }
    }
}
