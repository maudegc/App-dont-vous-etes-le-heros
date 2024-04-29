package com.example.tp2.présentation.écran;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.domaine.SourceAventuresJson;
import com.example.tp2.domaine.SourceChapitre.AventureException;
import com.example.tp2.présentation.contrat.IContratVuePresentateurChoixAventure;
import com.example.tp2.présentation.présentateur.Présentateur_choixAventure;
import com.example.tp2.ui.AventureAdapter;
import com.example.tp2.ui.AventureAdapterOff;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_choisir_aventure#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_choisir_aventure extends Fragment implements IContratVuePresentateurChoixAventure.IVueChoixAventure {

    static Présentateur_choixAventure presentateur;

    TextView erreur;
    NavController navController;
    Button magicalBalls;
    RecyclerView listeAventure;
    LinearLayoutManager layoutManager;
    AventureAdapter adapter;
    AventureAdapterOff adapterOff;


    public Ecran_choisir_aventure() {
        presentateur = new Présentateur_choixAventure(this);
    }


    public static Ecran_choisir_aventure newInstance(String param1, String param2) {
        Ecran_choisir_aventure fragment = new Ecran_choisir_aventure();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public static Présentateur_choixAventure getPrésentateur() {
        return presentateur;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ecran_choisir_aventure, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        erreur = view.findViewById(R.id.erreur);
        magicalBalls = view.findViewById(R.id.magicalBalls);
        magicalBalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentateur.traiterRequêteChoisirAventure();
            }
        });


        listeAventure = view.findViewById(R.id.listeAventure);
        listeAventure.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        listeAventure.setLayoutManager(layoutManager);

         adapterOff = new AventureAdapterOff(presentateur.getAventureOFFLINE());
        listeAventure.setAdapter(adapterOff);

        verifierConnexion();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verifierConnexion() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                afficher(true);
            }

            @Override
            public void onLost(Network network) {
                afficher(false);

            }
        });
    }

    public void afficher(boolean estConnecte) {
        if (estConnecte) {

            erreur.setVisibility(View.INVISIBLE);
            telechargerListeAventure();
        } else {
            Thread fil_esclave = new Thread(() -> {
                this.getActivity().runOnUiThread(() -> {

                    erreur.setVisibility(View.VISIBLE);
                    adapter = null;
                    adapterOff = new AventureAdapterOff(presentateur.getAventureOFFLINE());
                    listeAventure.setAdapter(adapterOff);

                });
            });
            fil_esclave.start();

        }

    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void afficherMessageAttente() {

        Toast.makeText(getActivity().getApplicationContext(), "Veuillez attendre le telechargement", Toast.LENGTH_SHORT).show();


    }

    public void naviguerÉcranIntro() {
        navController.navigate(R.id.ecran_intro);
    }

    public void telechargerListeAventure() {
        afficherMessageAttente();
        Thread fil_esclave = new Thread(() -> {
            try {
                final ArrayList<ArrayList<String>> lesInfosAventures = new SourceAventuresJson(getActivity().getApplicationContext(), new URL("https://hugocodestar.github.io/site/adventurelist.json")).getListeAventure();

                this.getActivity().runOnUiThread(() -> {
                    if (lesInfosAventures != null) {
                        adapter = new AventureAdapter(lesInfosAventures);
                        listeAventure.setAdapter(adapter);
                    }


                });
            } catch (AventureException | MalformedURLException e) {
                /*this.getActivity().runOnUiThread(() -> {

                });*/
            }
        });
        fil_esclave.start();


    }


}