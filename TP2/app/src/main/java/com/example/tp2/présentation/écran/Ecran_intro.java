package com.example.tp2.présentation.écran;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tp2.R;
import com.example.tp2.présentation.contrat.IContratVuePresentateurIntro;
import com.example.tp2.présentation.présentateur.Présentateur_intro;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_intro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_intro extends Fragment implements IContratVuePresentateurIntro.IVueIntro {

    Button btnCommencer;
    Button btnContinuer;
    Button btnRecommencer;
    Button btnNon;
    Button btnOui;
    TextView titre;
    NavController navController;
    Présentateur_intro presentateur;
    ConstraintLayout confirmation;
    ConstraintLayout recom;


    public Ecran_intro() {
        presentateur = new Présentateur_intro(this);
    }

    public static Ecran_intro newInstance() {
        Ecran_intro fragment = new Ecran_intro();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ecran_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titre = view.findViewById(R.id.titreIntro);
        recom = view.findViewById(R.id.ConRec);
        confirmation = view.findViewById(R.id.confirmation);
        btnCommencer = view.findViewById(R.id.boutonCommencer);
        navController = Navigation.findNavController(view);
        btnCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentateur.traiterCommencer();
                naviguerÉcranPersonnage();
            }
        });
        btnContinuer = view.findViewById(R.id.continuer);
        btnContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naviguerÉcranChapitre();
            }
        });
        presentateur.iniatiliserVue();
        btnRecommencer = view.findViewById(R.id.recommencer);
        btnRecommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desactiverRecCom();
                activerConfirmation();
            }
        });
        btnNon = view.findViewById(R.id.non);
        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activerRecCom();
                desactiverConfirmation();
            }
        });
        btnOui = view.findViewById(R.id.oui);
        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentateur.recommencer();
                naviguerÉcranPersonnage();
            }
        });

    }

    @Override
    public void naviguerÉcranPersonnage() {
        navController.navigate(R.id.ecran_personnage);
    }

    @Override
    public void naviguerÉcranChapitre() {
        navController.navigate(R.id.ecran_chapitre);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void activerCommencer() {
        btnCommencer.setVisibility(View.VISIBLE);
    }

    @Override
    public void activerConfirmation() {
        confirmation.setVisibility(View.VISIBLE);
    }

    @Override
    public void activerRecCom() {
        recom.setVisibility(View.VISIBLE);
    }

    @Override
    public void desactiverCommencer() {
        btnCommencer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void desactiverConfirmation() {
        confirmation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void desactiverRecCom() {
        recom.setVisibility(View.INVISIBLE);
    }

    @Override
    public void changerTitre(String unTitre) {

        titre.setText(unTitre);
    }


}