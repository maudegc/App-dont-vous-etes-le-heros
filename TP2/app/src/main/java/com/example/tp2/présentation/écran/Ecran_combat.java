package com.example.tp2.présentation.écran;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.tp2.présentation.contrat.IContratVuePresentateurCombat;
import com.example.tp2.présentation.modèle.ModèleCombat;
import com.example.tp2.présentation.présentateur.Présentateur_combat;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_combat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_combat extends Fragment implements IContratVuePresentateurCombat.IVueCombat {

    NavController navController;
    ImageButton btnRouler;
    Présentateur_combat présentateur;

    View arrièrePlan;

    TextView nom;
    TextView statForce;
    TextView statAgilité;
    TextView statIntelligence;
    TextView statEndurance;
    TextView descriptionCombat;
    TextView statEnduranceEnnemi;

    ModèleCombat combat = ModèleCombat.getModèleCombat();

    public Ecran_combat() {
        présentateur = new Présentateur_combat(this);
    }

    public static Ecran_combat newInstance() {
        Ecran_combat fragment = new Ecran_combat();
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
        return inflater.inflate(R.layout.fragment_ecran_combat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        btnRouler = view.findViewById(R.id.btnRouler);
        arrièrePlan = view.findViewById(R.id.bgCombat);

        nom = view.findViewById(R.id.nom);
        statForce = view.findViewById(R.id.statNumForce);
        statAgilité = view.findViewById(R.id.statNumAgilité);
        statIntelligence = view.findViewById(R.id.statNumIntelligence);
        statEndurance = view.findViewById(R.id.statNumEndurance);
        descriptionCombat = view.findViewById(R.id.texteCombat);
        statEnduranceEnnemi = view.findViewById(R.id.nbrEnduranceEnnemi);

        présentateur.actualiserStat();
        présentateur.actualiserArrièrePlan();

        btnRouler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                présentateur.traiterRequêteJouer();


            }
        });
    }


    @Override
    public void afficherDescriptionCombat(ArrayList<String> infos, boolean aGagne, boolean egalite) {

        new CountDownTimer(infos.size() * 2000, 2000) {
            int index = 0;
            String description;

            @Override
            public void onTick(long millisUntilFinished) {

                btnRouler.setEnabled(false);
                if (index == 0) {
                    String[] tab = infos.get(index).split(",");
                    description = getResources().getString(R.string.descriptionCombat1).replace("x", tab[0]).replace("y", String.valueOf(tab[1]));
                } else if (index == 1) {
                    String[] tab = infos.get(index).split(",");
                    description = getResources().getString(R.string.descriptionCombat1).replace("x", tab[0]).replace("y", String.valueOf(tab[1]));
                } else if (index == 2) {
                    if (egalite == true) {
                        description = "Personne attaque";
                    } else {
                        description = getResources().getString(R.string.descriptionCombat3).replace("x", infos.get(index));
                    }

                } else if (index == 3) {
                    String[] tab = infos.get(index).split(",");
                    if (egalite == true) {
                        description = "Egalite";
                    } else {
                        description = getResources().getString(R.string.descriptionCombat4).replace("x", tab[0]).replace("y", String.valueOf(tab[1]));
                    }
                }
                descriptionCombat.setText(description);
                index++;

            }

            @Override
            public void onFinish() {
                présentateur.actualiserStat();
                String messageFin;

                if (egalite == true) {
                    messageFin = "egalite";
                } else {
                    if (aGagne) {
                        messageFin = getResources().getString(R.string.combatGagnant);

                    } else {
                        messageFin = getResources().getString(R.string.combatPerdant);
                    }
                }

                descriptionCombat.setText(messageFin + "\n" + getResources().getString(R.string.combatFin));
                btnRouler.setEnabled(true);

            }
        }.start();

    }


    @Override
    public void afficherStatForce(int unStatForce) {
        statForce.setText(String.valueOf(unStatForce));
    }

    @Override
    public void afficherStatAgilité(int unStatAgilité) {
        statAgilité.setText(String.valueOf(unStatAgilité));
    }

    @Override
    public void afficherStatEndurance(int unStatEndurance) {
        statEndurance.setText(String.valueOf(unStatEndurance));
    }

    @Override
    public void afficherStatIntelligence(int unStatIntelligence) {
        statIntelligence.setText(String.valueOf(unStatIntelligence));
    }

    @Override
    public void afficherEnduranceEnnemi(int unStatEnduranceEnnemi) {
        statEnduranceEnnemi.setText(String.valueOf(unStatEnduranceEnnemi));
    }

    @Override
    public void naviguerÉcranChapitre() {
        navController.navigate(R.id.ecran_chapitre);
    }

    @Override
    public void afficherArrièrePlan(String _arrièrePlan) {
        String bg = _arrièrePlan;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bg += "land";
        }
        int resID = getResources().getIdentifier(bg, "drawable", getActivity().getPackageName());
        if (resID > 0) {
            arrièrePlan.setBackgroundResource(resID);
        } else {
            arrièrePlan.setBackgroundResource(R.drawable.backgroundcombatdonjon);
        }

    }

    @Override
    public void afficherNom(String unNom) {
        nom.setText(unNom);
    }


}