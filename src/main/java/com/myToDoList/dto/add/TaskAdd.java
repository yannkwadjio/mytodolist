package com.todolist.todolist.dto.add;

import com.todolist.todolist.entity.Priorite;
import com.todolist.todolist.entity.Repetition;
import com.todolist.todolist.entity.StatutTache;
import jakarta.persistence.GeneratedValue;
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
