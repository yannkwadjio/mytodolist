package com.todolist.todolist.entity;

public enum StatutTache {
    non_execute("Non exécutée"),
    execute("Exécutée"),
    archive("Archivée");

    final String statutTache;

    StatutTache(String statutTache) {
        this.statutTache = statutTache;
    }
}
