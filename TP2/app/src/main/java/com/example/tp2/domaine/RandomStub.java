package com.example.tp2.domaine;

public class RandomStub implements IGénérateurNombresAléatoires {

    int[] _nombres;
    int _prochain = 0;

    public RandomStub(int[] nombres) {
        _nombres = nombres;
    }

    @Override
    public int prochainEntier(int min, int max) {
        return _nombres[_prochain++];
    }
}
