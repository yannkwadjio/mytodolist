package com.myToDoList.controller;


import com.myToDoList.entity.ToDoList;
import com.myToDoList.service.interfaceImplement.Implement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class TemplatePath {

    public final Implement implement;

@GetMapping("/index")
    public String showIndex(Model model){
    model.addAttribute("liste",implement.getAllToDoList());
    return "index";
}

@GetMapping("/create")
    public String showForm(Model model){
    ToDoList todolist=new ToDoList();
    model.addAttribute("liste",implement.getAllToDoList());
    model.addAttribute("todolist",todolist);
    return ("newlist");
}


@PostMapping("/create")
    public String createList(@ModelAttribute ToDoList todoList, Model model){
    implement.addToDoList(todoList);
    return "redirect:/index";
}

    @GetMapping("/getbylist/{titreList}")
    public String showByList(Model model, @PathVariable("titreList") String titreList){
        model.addAttribute("titreList",titreList);
        model.addAttribute("liste",implement.getToDoList(titreList));
        return ("bylist");
    }

    @GetMapping("/delete/{titreList}")
    public String deleteList(Model model, @PathVariable("titreList") String titreList){
        model.addAttribute("titreList",titreList);
        implement.deleteToDoList(titreList);
        return ("redirect:index");
    }


}
