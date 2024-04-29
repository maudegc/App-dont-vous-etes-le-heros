package com.example.tp2.présentation.écran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tp2.R;
import com.example.tp2.présentation.contrat.IContratVuePresentateurAccueil;
import com.example.tp2.présentation.présentateur.Présentateur_accueil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_accueil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_accueil extends Fragment implements IContratVuePresentateurAccueil.IVueAccueil {

    ImageButton btnJouer;
    NavController navController;
    Présentateur_accueil presentateur;
    TextView test;


    public Ecran_accueil() {

    }

    public static Ecran_accueil newInstance() {
        Ecran_accueil fragment = new Ecran_accueil();
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
        return inflater.inflate(R.layout.fragment_ecran_accueil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnJouer = view.findViewById(R.id.btnJouer);
        navController = Navigation.findNavController(view);
        test = view.findViewById(R.id.testA);
        btnJouer.setOnClickListener(v -> naviguerÉcranChoisirAventure());

    }

    @Override
    public void naviguerÉcranChoisirAventure() {
        navController.navigate(R.id.ecran_choisir_aventure);
    }


}