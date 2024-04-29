package com.example.tp2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.présentation.écran.Ecran_choisir_aventure;

import java.util.ArrayList;

public class AventureAdapter extends RecyclerView.Adapter<AventureAdapter.AventureViewHolder> {
    ArrayList<ArrayList<String>> lesAventures;

    public AventureAdapter(ArrayList<ArrayList<String>> data) {
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
        ArrayList<String> uneAventure = lesAventures.get(position);
        holder.btn.setText(uneAventure.get(0));
        if (Ecran_choisir_aventure.getPrésentateur().estDansBD(uneAventure.get(0)) == true) {
            holder.nouveau.setVisibility(View.VISIBLE);
        }

        holder.lAventure = uneAventure;
    }

    @Override
    public int getItemCount() {
        return lesAventures.size();
    }

    public static class AventureViewHolder extends RecyclerView.ViewHolder {
        Button btn;
        ImageView nouveau;
        ArrayList<String> lAventure;

        public AventureViewHolder(View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.json);
            nouveau = itemView.findViewById(R.id.nouveau);
            btn.setOnClickListener(v -> Ecran_choisir_aventure.getPrésentateur().traiterRequêteChoisirAventure(lAventure.get(0), lAventure.get(1)));


        }
    }
}
