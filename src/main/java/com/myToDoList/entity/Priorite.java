package com.myToDoList.entity;


public enum Priorite {
    Important("Important"),
    Pas_important("Pas important");

    final String nomPriorite;

    Priorite(String nomPriorite) {
        this.nomPriorite = nomPriorite;
    }
}
