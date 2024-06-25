package com.myToDoList.entity;


public enum Repetition {
    aucune("Aucune"),
    jour("Jour"),
    semaine("Semaine"),
    mois("Mois"),
    annee("Année");

    final String afficheRepetition;

    Repetition(String afficheRepetition) {
        this.afficheRepetition = afficheRepetition;
    }
}
