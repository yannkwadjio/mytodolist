package com.myToDoList.controller;

import com.myToDoList.dto.add.TaskAdd;
import com.myToDoList.dto.show.TaskShow;
import com.myToDoList.entity.ToDoList;
import com.myToDoList.service.interfaceService.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/todolist/v1/")
public class ToDoListController {
    ToDoListService toDoListService;
    @PostMapping("create")
    public String addToDoList(@RequestBody ToDoList toDoList){
        return toDoListService.addToDoList(toDoList);
    }

    @GetMapping("get-all")
    public List<ToDoList> getAllToDoList(){
        return toDoListService.getAllToDoList();
    }

  @GetMapping("get/{titreListe}")
    public ToDoList getToDoList(@PathVariable("titreListe") String titreListe){
        return toDoListService.getToDoList(titreListe);
  }

  @GetMapping("datebetween")
  public List<ToDoList> getByDateList(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate){
        return toDoListService.getByDateList(startDate,endDate);
  }

  @PutMapping("update/{titreListe}")
    public ToDoList updateToDoList(@PathVariable("titreListe") String titreList, ToDoList toDoList){
        return toDoListService.updateToDoList(titreList, toDoList);
  }

  @DeleteMapping("delete/{titreListe}")
    public String deleteToDoList(@PathVariable("titreListe") String titreListe){
      return toDoListService.deleteToDoList(titreListe);
  }

  @PostMapping("create_task/{titreListe}")
   public String addTask(@PathVariable("titreListe") String titreListe, @RequestBody TaskAdd taskAdd){
        return toDoListService.addTask(titreListe,taskAdd);
  }

    @PutMapping("update_task/{titreListe}")
    public String updateTask(@PathVariable("titreListe") String titreListe, @RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask){
        return toDoListService.updateTask(titreListe,taskAdd,idTask);
    }

    @PutMapping("execute_task/{titreListe}")
    public String executeTask(@PathVariable("titreListe") String titreListe,@RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask ){
        return toDoListService.executeTask(titreListe,taskAdd,idTask);
    }

    @PutMapping("archive_task/{titreListe}")
    public String archiveTask(@PathVariable("titreListe") String titreListe,@RequestBody TaskAdd taskAdd,@RequestParam("idTask") int idTask ){
        return toDoListService.archiveTask(titreListe,taskAdd,idTask);
    }

    @GetMapping("get_task/{titreListe}")
    public TaskShow getTask(@PathVariable("titreListe") String titreListe, @RequestParam("idTask") int idTask){
        return toDoListService.getTask(titreListe,idTask);
    }

    @GetMapping("get_task_archived/{titreListe}")
    public List<TaskShow> getTaskArchived(@PathVariable("titreListe") String titreListe, @RequestParam("statutTask") String statutTask){
        return toDoListService.getTaskArchived(titreListe,statutTask);
    }

    @DeleteMapping("delete_task/{titreListe}")
    public String deleteTask(@PathVariable("titreListe") String titreListe, @RequestParam("idTask") int idTask){
        return toDoListService.deleteTask(titreListe,idTask);
    }



}
