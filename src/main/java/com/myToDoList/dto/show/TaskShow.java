package com.myToDoList.dto.show;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskShow {
    private String dateCreation;
    private String tache;
    private String details;
    private String dateDebut;
    private String dateEcheance;
    private String priorite;
    private String repetition;
    private String statutTache;
}
