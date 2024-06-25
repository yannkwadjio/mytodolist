package com.myToDoList.repository;


import com.myToDoList.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ToDoListRepository extends JpaRepository<ToDoList,Integer> {
    ToDoList findByTitreList(String titreListe);
    Boolean existsByTitreList(String titreListe);
    List<ToDoList> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);
}
