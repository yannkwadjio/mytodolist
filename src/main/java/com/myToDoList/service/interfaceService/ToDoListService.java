package com.todolist.todolist.service.interfaceService;

import com.todolist.todolist.dto.add.TaskAdd;
import com.todolist.todolist.dto.add.ToDoListAdd;
import com.todolist.todolist.dto.show.TaskShow;
import com.todolist.todolist.entity.ToDoList;

import java.time.LocalDate;
import java.util.List;

public interface ToDoListService {
    String addToDoList(ToDoListAdd toDoListAdd);

    List<ToDoList> getAllToDoList();

    ToDoList getToDoList(String titreListe);

    ToDoList updateToDoList(String titreList, ToDoListAdd toDoListAdd);

    String deleteToDoList(String titreListe);

    String addTask(String titreListe, TaskAdd taskAdd);

    String updateTask(String titreListe, TaskAdd taskAdd, int idTask);

    TaskShow getTask(String titreListe, int idTask);

    String deleteTask(String titreListe, int idTask);

    List<ToDoList> getByDateList(LocalDate startDate, LocalDate endDate);

    String executeTask(String titreListe, TaskAdd taskAdd, int idTask);

    String archiveTask(String titreListe, TaskAdd taskAdd, int idTask);

    List<TaskShow> getTaskArchived(String titreListe, String statutTask);
}
