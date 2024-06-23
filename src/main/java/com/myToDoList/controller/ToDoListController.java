package com.todolist.todolist.controller;


import com.todolist.todolist.dto.add.TaskAdd;
import com.todolist.todolist.dto.add.ToDoListAdd;
import com.todolist.todolist.dto.show.TaskShow;
import com.todolist.todolist.entity.ToDoList;
import com.todolist.todolist.repository.ToDoListRepository;
import com.todolist.todolist.service.interfaceService.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/todolist/")
public class ToDoListController {
    ToDoListService toDoListService;
    @PostMapping("add-list")
    public String addToDoList(@RequestBody ToDoListAdd toDoListAdd){
        return toDoListService.addToDoList(toDoListAdd);
    }

    @GetMapping("get-alllist")
    public List<ToDoList> getAllToDoList(){
        return toDoListService.getAllToDoList();
    }

  @GetMapping("get-list/{titreListe}")
    public ToDoList getToDoList(@PathVariable("titreListe") String titreListe){
        return toDoListService.getToDoList(titreListe);
  }

  @GetMapping("getbydate-list")
  public List<ToDoList> getByDateList(@RequestParam("startDate") LocalDate startDate,@RequestParam("endDate") LocalDate endDate){
        return toDoListService.getByDateList(startDate,endDate);
  }

  @PutMapping("update-list/{titreListe}")
    public ToDoList updateToDoList(@PathVariable("titreListe") String titreList, ToDoListAdd toDoListAdd){
        return toDoListService.updateToDoList(titreList, toDoListAdd);
  }

  @DeleteMapping("delete-list/{titreListe}")
    public String deleteToDoList(@PathVariable("titreListe") String titreListe){
      return toDoListService.deleteToDoList(titreListe);
  }

  @PostMapping("add-task/{titreListe}")
   public String addTask(@PathVariable("titreListe") String titreListe, @RequestBody TaskAdd taskAdd){
        return toDoListService.addTask(titreListe,taskAdd);
  }

    @PutMapping("update-task/{titreListe}")
    public String updateTask(@PathVariable("titreListe") String titreListe, @RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask){
        return toDoListService.updateTask(titreListe,taskAdd,idTask);
    }

    @PutMapping("execute-task/{titreListe}")
    public String executeTask(@PathVariable("titreListe") String titreListe,@RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask ){
        return toDoListService.executeTask(titreListe,taskAdd,idTask);
    }

    @PutMapping("archive-task/{titreListe}")
    public String archiveTask(@PathVariable("titreListe") String titreListe,@RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask ){
        return toDoListService.archiveTask(titreListe,taskAdd,idTask);
    }

    @GetMapping("get-task/{titreListe}")
    public TaskShow getTask(@PathVariable("titreListe") String titreListe, @RequestParam("idTask") int idTask){
        return toDoListService.getTask(titreListe,idTask);
    }

    @GetMapping("get-task-archived/{titreListe}")
    public List<TaskShow> getTaskArchived(@PathVariable("titreListe") String titreListe, @RequestParam("statutTask") String statutTask){
        return toDoListService.getTaskArchived(titreListe,statutTask);
    }

    @DeleteMapping("delete-task/{titreListe}")
    public String deleteTask(@PathVariable("titreListe") String titreListe, @RequestParam("idTask") int idTask){
        return toDoListService.deleteTask(titreListe,idTask);
    }



}
