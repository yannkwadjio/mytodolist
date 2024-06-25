package com.myToDoList.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
private int idList;
private LocalDate dateCreation;
private String titreList;
private List<String> tache;
}
