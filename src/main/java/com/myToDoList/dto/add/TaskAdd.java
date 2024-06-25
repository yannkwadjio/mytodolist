package com.myToDoList.dto.add;


import com.myToDoList.entity.Priorite;
import com.myToDoList.entity.Repetition;
import com.myToDoList.entity.StatutTache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAdd {
    private String tache;
    private String details;
    private LocalDate dateDebut;
    private LocalDate dateEcheance;
    private Priorite priorite;
    private Repetition repetition;
    private StatutTache statutTache;
}
