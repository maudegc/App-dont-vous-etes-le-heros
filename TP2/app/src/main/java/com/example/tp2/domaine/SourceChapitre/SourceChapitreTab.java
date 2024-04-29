package com.example.tp2.domaine.SourceChapitre;

import com.example.tp2.domaine.Aventure;
import com.example.tp2.domaine.Chapitre;
import com.example.tp2.domaine.Choix;
import com.example.tp2.domaine.Personnage;

public class SourceChapitreTab implements SourceChapitre {


    private Aventure magicalBalls;

    /**
     * Rempli le tableau lesChapitres avec tous les chapitres de l'histoire
     */
    public SourceChapitreTab() {
        créerAventure();
        créerChapitres();
    }

    public void créerAventure() {
        magicalBalls = new Aventure();
        magicalBalls.setTitle("Magical Balls");
    }

    public void créerChapitres() {
        Choix uneOption;
        Personnage unPersonnage;

        //chapitre 1
        Chapitre chap1 = new Chapitre();
        chap1.setNumero(1);
        chap1.setDescription("description1");
        chap1.setArrière_plan("bgchap1");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setDescription("carte");
        uneOption.setProchainChapitre(2);
        chap1.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setDescription("seBattre");
        uneOption.setProchainChapitre(3);
        chap1.ajouterChoix(uneOption);

        magicalBalls.ajouterChapitre(chap1);

        //chapitre 2
        Chapitre chap2 = new Chapitre();
        chap2.setNumero(2);
        chap2.setDescription("description2");
        chap2.setArrière_plan("bgchap2");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setDescription("seBattre");
        uneOption.setProchainChapitre(3);
        chap2.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setDescription("fuir");
        uneOption.setProchainChapitre(4);
        chap2.ajouterChoix(uneOption);

        magicalBalls.ajouterChapitre(chap2);

        //chapitre 3
        Chapitre chap3 = new Chapitre();
        chap3.setNumero(3);
        chap3.setArrière_plan("backgroundcombatdonjon");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setProchainChapitre(9);
        chap3.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setProchainChapitre(4);
        chap3.ajouterChoix(uneOption);

        unPersonnage = new Personnage();
        unPersonnage.setNom("Gustave");
        unPersonnage.setStatEndurance(8);
        unPersonnage.setStatAgilité(3);
        unPersonnage.setStatForce(2);

        chap3.ajouterEnnemi(unPersonnage);


        magicalBalls.ajouterChapitre(chap3);

        //chapitre 4
        Chapitre chap4 = new Chapitre();
        chap4.setNumero(4);
        chap4.setDescription("description4");
        chap4.setArrière_plan("bgchap4");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setDescription("seBattre");
        uneOption.setProchainChapitre(5);
        chap4.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setDescription("fuir");
        uneOption.setProchainChapitre(6);
        chap4.ajouterChoix(uneOption);

        magicalBalls.ajouterChapitre(chap4);

        //chapitre 5
        Chapitre chap5 = new Chapitre();
        chap5.setNumero(5);
        chap5.setArrière_plan("bgchap4");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setProchainChapitre(9);
        chap5.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setProchainChapitre(6);
        chap5.ajouterChoix(uneOption);

        unPersonnage = new Personnage();
        unPersonnage.setNom("Maximus");
        unPersonnage.setStatEndurance(4);
        unPersonnage.setStatAgilité(6);
        unPersonnage.setStatForce(2);

        chap5.ajouterEnnemi(unPersonnage);

        magicalBalls.ajouterChapitre(chap5);
        //chapitre 6
        Chapitre chap6 = new Chapitre();
        chap6.setNumero(6);
        chap6.setDescription("description6");
        chap6.setArrière_plan("bgchap6");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setDescription("seBattre");
        uneOption.setProchainChapitre(7);
        chap6.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setDescription("mourir");
        uneOption.setProchainChapitre(9);
        chap6.ajouterChoix(uneOption);

        magicalBalls.ajouterChapitre(chap6);

        //chapitre 7
        Chapitre chap7 = new Chapitre();
        chap7.setNumero(7);
        chap7.setArrière_plan("backgroundcombatwater");


        uneOption = new Choix();
        uneOption.setNumero(0);
        uneOption.setProchainChapitre(9);
        chap7.ajouterChoix(uneOption);

        uneOption = new Choix();
        uneOption.setNumero(1);
        uneOption.setProchainChapitre(8);
        chap7.ajouterChoix(uneOption);

        unPersonnage = new Personnage();
        unPersonnage.setNom("Maudibuque");
        unPersonnage.setStatEndurance(9);
        unPersonnage.setStatAgilité(4);
        unPersonnage.setStatForce(2);

        chap7.ajouterEnnemi(unPersonnage);

        unPersonnage = new Personnage();
        unPersonnage.setNom("Maurice");
        unPersonnage.setStatEndurance(4);
        unPersonnage.setStatAgilité(3);
        unPersonnage.setStatForce(4);

        chap7.ajouterEnnemi(unPersonnage);


        magicalBalls.ajouterChapitre(chap7);

        //chapitre 8
        Chapitre chap8 = new Chapitre();
        chap8.setNumero(8);
        chap8.setDescription("description8");
        chap8.setMessageDeFin("fin");
        chap8.setArrière_plan("bgendwin");

        magicalBalls.ajouterChapitre(chap8);

        //chapitre 9
        Chapitre chap9 = new Chapitre();
        chap9.setNumero(9);
        chap9.setDescription("descriptionMort");
        chap9.setMessageDeFin("fin");
        chap9.setArrière_plan("bgchap8");

        magicalBalls.ajouterChapitre(chap9);


    }

    @Override
    public Aventure getAventure() {
        System.out.println(magicalBalls);
        return magicalBalls;

    }

    @Override
    public Chapitre getInfoChapitre(int chapitre) {
        return magicalBalls.getChapitre(chapitre);
    }

    @Override
    public int getNbrChapitre() {
        return 0;
    }
}
