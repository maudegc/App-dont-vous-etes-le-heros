package com.example.tp2.présentation.écran;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.domaine.Choix;
import com.example.tp2.présentation.contrat.IContratVuePresentateurChapitre;
import com.example.tp2.présentation.présentateur.Présentateur_chapitre;
import com.example.tp2.ui.ChoixAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ecran_chapitre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ecran_chapitre extends Fragment implements IContratVuePresentateurChapitre.IContratVueChapitre {


    static Présentateur_chapitre présentateur;

    String mParam1;
    String mParam2;
    TextView description;
    TextView nbrGem;
    TextView statForce;
    TextView statAgilité;
    TextView statIntelligence;
    TextView statEndurance;
    View arrièrePlan;
    NavController navController;
    RecyclerView listeChoix;
    LinearLayoutManager layoutManager;
    ChoixAdapter adapter;

    public Ecran_chapitre() {
        présentateur = new Présentateur_chapitre(this);
    }

    public static Présentateur_chapitre getPrésentateur() {
        return présentateur;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ecran_chapitre.
     */
    // TODO: Rename and change types and number of parameters
    public static Ecran_chapitre newInstance(String param1, String param2) {
        Ecran_chapitre fragment = new Ecran_chapitre();
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
        return inflater.inflate(R.layout.fragment_ecran_chapitre, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        arrièrePlan = view.findViewById(R.id.bg);
        description = view.findViewById(R.id.description);

        nbrGem = view.findViewById(R.id.nom);
        statForce = view.findViewById(R.id.statNumForce);
        statAgilité = view.findViewById(R.id.statNumAgilité);
        statEndurance = view.findViewById(R.id.statNumEndurance);
        statIntelligence = view.findViewById(R.id.statNumIntelligence);

        listeChoix = view.findViewById(R.id.choix);
        listeChoix.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        listeChoix.setLayoutManager(layoutManager);


        présentateur.initialiser();


    }


    @Override
    public void afficherDescriptionChapitre(String uneDescription, String messageFin) {
        String text;
        int resID = getResources().getIdentifier(uneDescription, "string", getActivity().getPackageName());
        if (resID > 0) {
            text = getResources().getString(resID);
        } else {
            text = uneDescription;
        }

        String message = text;
        if (messageFin != null) {
            message += " " + messageFin;
        }

        description.setText(message);
    }

    @Override
    public void afficherNbrGem(String _nbrGem) {
        nbrGem.setText("" + _nbrGem);
    }

    @Override
    public void afficherStatForce(int _statForce) {

        statForce.setText("" + _statForce);
    }

    @Override
    public void afficherStatAgilité(int _statAgilité) {
        statAgilité.setText("" + _statAgilité);
    }

    @Override
    public void afficherStatEndurance(int _statEndurance) {
        statEndurance.setText("" + _statEndurance);
    }

    @Override
    public void afficherStatIntelligence(int _statIntelligence) {
        statIntelligence.setText("" + _statIntelligence);
    }


    @Override
    public void naviguerÉranCombat() {
        navController.navigate(R.id.ecran_combat);
    }

    @Override
    public void naviguerÉranAcceuil() {
        navController.navigate(R.id.ecran_accueil);
    }


    @Override
    public void afficherArrièrePlanChapitre(String _arrièrePlan) {
        String bg = _arrièrePlan;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bg += "land";
        }
        int resID = getResources().getIdentifier(bg, "drawable", getActivity().getPackageName());
        if (resID > 0) {
            arrièrePlan.setBackgroundResource(resID);
        } else {
            arrièrePlan.setBackgroundResource(R.drawable.backgroundintro);
        }


    }

    @Override
    public void afficherChoix(ArrayList<Choix> lesChoix) {
        for (Choix unChoix : lesChoix) {
            int resID = getResources().getIdentifier(unChoix.getDescription(), "string", getActivity().getPackageName());
            if (resID > 0) {
                String text = getResources().getString(resID);
                unChoix.setDescription(text);
            }

        }
        adapter = new ChoixAdapter(lesChoix);
        listeChoix.setAdapter(adapter);
    }
}