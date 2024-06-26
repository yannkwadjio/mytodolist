package com.myToDoList.controller;

import com.myToDoList.dto.add.ToDoListAdd;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    @GetMapping("/formulaire")
    public String showForm(Model model) {
        model.addAttribute("toDoListAdd", new ToDoListAdd());
        return "formulaire";
    }

    @PostMapping("/new")
    public String createagence(@ModelAttribute ToDoListAdd toDoListAdd, Model model) {
        toDoListController.addToDoList(toDoListAdd);
        return "redirect:/index";
    }


}
