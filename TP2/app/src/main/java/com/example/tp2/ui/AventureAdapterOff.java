package com.example.tp2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.domaine.Aventure;
import com.example.tp2.présentation.écran.Ecran_choisir_aventure;

import java.util.ArrayList;

public class AventureAdapterOff extends RecyclerView.Adapter<AventureAdapterOff.AventureViewHolder> {
    ArrayList<Aventure> lesAventures;

    public AventureAdapterOff(ArrayList<Aventure> data) {
        lesAventures = data;
    }

    @NonNull
    @Override
    public AventureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aventure_row, parent, false);
        AventureViewHolder aventureViewHolder = new AventureViewHolder(view);
        return aventureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AventureViewHolder holder, int position) {
        Aventure uneAventure = lesAventures.get(position);
        holder.btn.setText(uneAventure.getTitle());
        holder.lAventure = uneAventure;
    }

    @Override
    public int getItemCount() {
        return lesAventures.size();
    }

    public static class AventureViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        Aventure lAventure;

        public AventureViewHolder(View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.json);
            btn.setOnClickListener(v -> Ecran_choisir_aventure.getPrésentateur().traiterRequeteAventureEnregistre(lAventure));


        }
    }
}
