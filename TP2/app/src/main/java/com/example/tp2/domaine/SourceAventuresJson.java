package com.example.tp2.domaine;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.tp2.domaine.SourceChapitre.AventureException;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Carte permettant de recuperer les noms et url des aventures d'un fichier JSON en ligne
 */
public class SourceAventuresJson {
    private final Context _ctx;
    private final URL _url;
    private JSONArray _response;

    public SourceAventuresJson(Context ctx, URL url) {
        _ctx = ctx;
        _url = url;

    }

    public JSONArray recuperer(String nom) throws AventureException {
        RequestQueue queue = Volley.newRequestQueue(_ctx);
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, _url.toString(), null, future, future);
        queue.add(request);

        try {
            JSONArray response = future.get();
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new AventureException("Interruption", e);

        }

        return null;

    }

    private ArrayList<ArrayList<String>> téléchargerAventureJSON() {
        ArrayList<ArrayList<String>> listeAventure = new ArrayList<>();
        ArrayList<String> infos = new ArrayList<>();
        for (int i = 0; i < _response.length(); i++) {
            infos = new ArrayList<>();
            try {

                String titre = _response.getJSONObject(i).getString("title");
                infos.add(titre);

                String url = _response.getJSONObject(i).getString("url");
                infos.add(url);

                listeAventure.add(infos);
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return listeAventure;

    }

    public ArrayList<ArrayList<String>> getListeAventure() throws AventureException {
        _response = recuperer("");
        ArrayList<ArrayList<String>> lesAventures = téléchargerAventureJSON();

        return lesAventures;
    }
}
