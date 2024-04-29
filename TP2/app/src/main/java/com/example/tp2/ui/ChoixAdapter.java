package com.example.tp2.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.domaine.Choix;
import com.example.tp2.présentation.écran.Ecran_chapitre;

import java.util.ArrayList;


public class ChoixAdapter extends RecyclerView.Adapter<ChoixAdapter.ChoixViewHolder> {

    ArrayList<Choix> lesChoix;

    public ChoixAdapter(ArrayList<Choix> data) {
        lesChoix = data;
    }

    @NonNull
    @Override
    public ChoixViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choix_row, parent, false);
        ChoixViewHolder choixViewHolder = new ChoixViewHolder(view);
        return choixViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoixAdapter.ChoixViewHolder holder, int position) {
        Choix unChoix = lesChoix.get(position);
        holder.btn.setText(unChoix.getDescription());
        holder.leChoix = unChoix;
    }

    @Override
    public int getItemCount() {
        return lesChoix.size();
    }

    public static class ChoixViewHolder extends RecyclerView.ViewHolder {

        Button btn;
        Choix leChoix;

        public ChoixViewHolder(View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.liste);
            btn.setOnClickListener(v -> Ecran_chapitre.getPrésentateur().traiterOption(leChoix.getNumero()));


        }
    }
}
