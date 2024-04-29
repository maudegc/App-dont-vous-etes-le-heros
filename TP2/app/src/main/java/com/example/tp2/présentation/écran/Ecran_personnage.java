package com.example.tp2.présentation.écran;

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
import com.example.tp2.présentation.contrat.IContratVuePresentateurPersonnage;
import com.example.tp2.présentation.présentateur.Présentateur_personnage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_personnage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_personnage extends Fragment implements IContratVuePresentateurPersonnage.IContratVuePersonnage {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView nbrIntelligence;
    TextView nbrForce;
    TextView nbrAgilité;
    TextView nbrEndurance;
    TextView nomPersonnage;
    TextView messageErreur;
    View erreur;
    ImageButton créationPersonnage;
    ImageButton générerStatsAléatoire;
    int nbrFois;
    NavController navController;
    Présentateur_personnage présentateur;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Ecran_personnage() {
        présentateur = new Présentateur_personnage(this);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ecran_personnage.
     */
    // TODO: Rename and change types and number of parameters
    public static Ecran_personnage newInstance(String param1, String param2) {
        Ecran_personnage fragment = new Ecran_personnage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ecran_personnage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        créationPersonnage = view.findViewById(R.id.boutonCréation);
        générerStatsAléatoire = view.findViewById(R.id.boutonAléatoire);
        nbrIntelligence = view.findViewById(R.id.nbrIntelligence);
        nbrAgilité = view.findViewById(R.id.nbrAgilité);
        nbrEndurance = view.findViewById(R.id.nbrEndurance);
        nbrForce = view.findViewById(R.id.nbrForce);
        nomPersonnage = view.findViewById(R.id.nomPersonnage);
        erreur = view.findViewById(R.id.viewErreur);
        messageErreur = view.findViewById(R.id.textViewErreur);
        créationPersonnage.setOnClickListener(v -> {
            présentateur.permettreCréationPersonnage(nomPersonnage.getText().toString());
        });

        générerStatsAléatoire.setOnClickListener(v -> {
            nbrFois++;
            présentateur.permettreGénérationStatsAléatoire();
        });

    }

    public void afficherStatAléatoireIntelligence(int statIntelligence) {
        Integer stat = statIntelligence;

        nbrIntelligence.setText(stat.toString());
    }

    public void afficherStatAléatoireForce(int statForce) {
        Integer stat = statForce;
        nbrForce.setText(stat.toString());
    }

    public void afficherStatAléatoireAgilité(int statAgilité) {
        Integer stat = statAgilité;
        nbrAgilité.setText(stat.toString());
    }

    public void afficherStatAléatoireEndurance(int statEndurance) {
        Integer stat = statEndurance;
        nbrEndurance.setText(stat.toString());
    }

    @Override
    public void naviguerEcranChapitre() {
        navController.navigate(R.id.ecran_chapitre);
    }

    public void afficherMessageErreurStats() {
        générerStatsAléatoire.setEnabled(false);
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
                messageErreur.setVisibility(View.VISIBLE);
                erreur.setVisibility(View.VISIBLE);
                messageErreur.setText(R.string.erreurStat2);
            }

            @Override
            public void onFinish() {
                messageErreur.setVisibility(View.INVISIBLE);
                erreur.setVisibility(View.INVISIBLE);

            }
        }.start();


    }

    public void afficherMessageErreurCreationPersonnageNomVide() {
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
                messageErreur.setVisibility(View.VISIBLE);
                erreur.setVisibility(View.VISIBLE);
                messageErreur.setText(R.string.erreurNom);
            }

            @Override
            public void onFinish() {
                messageErreur.setVisibility(View.INVISIBLE);
                erreur.setVisibility(View.INVISIBLE);

            }
        }.start();

    }

    @Override
    public void afficherMessageErreurCreationPersonnageStatistiquesVides() {
        new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
                messageErreur.setVisibility(View.VISIBLE);
                erreur.setVisibility(View.VISIBLE);
                messageErreur.setText(R.string.erreurStat);
            }

            @Override
            public void onFinish() {
                messageErreur.setVisibility(View.INVISIBLE);
                erreur.setVisibility(View.INVISIBLE);

            }
        }.start();
    }

}