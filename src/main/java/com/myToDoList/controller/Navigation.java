package com.myToDoList.controller;

import com.myToDoList.dto.add.TaskAdd;
import com.myToDoList.dto.add.ToDoListAdd;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class Navigation {

    public final ToDoListController toDoListController;

    @GetMapping("/index")
    public String getindex(Model model){
       model.addAttribute("liste",toDoListController.getAllToDoList());
        return  "index";
    }



    @PostMapping("/new")
    public String createListe(@ModelAttribute ToDoListAdd toDoListAdd, Model model){
        toDoListController.addToDoList(toDoListAdd);
        return "redirect:/index";
    }




}
