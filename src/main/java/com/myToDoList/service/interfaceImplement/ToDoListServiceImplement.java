package com.todolist.todolist.service.interfaceImplement;

import com.todolist.todolist.dto.add.TaskAdd;
import com.todolist.todolist.dto.add.ToDoListAdd;
import com.todolist.todolist.dto.show.TaskShow;
import com.todolist.todolist.entity.Repetition;
import com.todolist.todolist.entity.StatutTache;
import com.todolist.todolist.entity.ToDoList;
import com.todolist.todolist.repository.ToDoListRepository;
import com.todolist.todolist.service.interfaceService.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@AllArgsConstructor
@Service
public class ToDoListServiceImplement implements ToDoListService {
    ToDoListRepository toDoListRepository;

    @Override
    public String addToDoList(ToDoListAdd toDoListAdd) {
        ToDoList newToDoList = new ToDoList();
        newToDoList.setTitreList(toDoListAdd.getTitreList());
        ToDoList existingList = toDoListRepository.findByTitreList(newToDoList.getTitreList());
        String state = null;
        if (existingList == null) {
            newToDoList.setDateCreation(LocalDate.now());
            newToDoList.setTache(new ArrayList<>());
            toDoListRepository.save(newToDoList);
            state = "La liste " + newToDoList.getTitreList() + " a été créé avec succès";
        } else {
            state = "Une liste portant ce nom existe déjà";
        }

        return state;
    }

    @Override
    public List<ToDoList> getAllToDoList() {
        return toDoListRepository.findAll();
    }

    @Override
    public ToDoList getToDoList(String titreListe) {
        return toDoListRepository.findByTitreList(titreListe);
    }

    @Override
    public ToDoList updateToDoList(String titreList, ToDoListAdd toDoListAdd) {
        ToDoList existingToDoList = toDoListRepository.findByTitreList(titreList);
        existingToDoList.setTitreList(toDoListAdd.getTitreList());
        return toDoListRepository.save(existingToDoList);
    }

    @Override
    public String deleteToDoList(String titreListe) {
        String state;
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            toDoListRepository.deleteById(existingToDoList.getIdList());
            state = "La liste " + titreListe + " a été supprimé avec succès";
        } else {
            state = "Liste introuvable";
        }
        return state;
    }

    @Override
    public String addTask(String titreListe, TaskAdd taskAdd) {
        ToDoList exisitngToDoList = toDoListRepository.findByTitreList(titreListe);
        List<String> sortedList = exisitngToDoList.getTache().stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        ToDoList saveTask;
        String state;
        String[] tb = new String[8];
        int nbSerie = 0;
        if (sortedList.isEmpty()) {
            nbSerie = 1;
        } else {
            tb = sortedList.get(0).split(",");
            nbSerie = Integer.parseInt(tb[0]) + 1;
        }

        taskAdd.setTache(taskAdd.getTache());
        taskAdd.setDetails(taskAdd.getDetails());
        taskAdd.setPriorite(taskAdd.getPriorite());

        taskAdd.setRepetition(taskAdd.getRepetition());
        if (taskAdd.getRepetition().equals(Repetition.jour) || taskAdd.getRepetition().equals(Repetition.semaine) || taskAdd.getRepetition().equals(Repetition.mois) || taskAdd.getRepetition().equals(Repetition.annee)) {
            taskAdd.setDateDebut(taskAdd.getDateDebut());
            taskAdd.setDateEcheance(taskAdd.getDateEcheance());
            if (taskAdd.getDateDebut().isBefore(taskAdd.getDateEcheance())) {
                String newTache = (nbSerie + 1) + "," + LocalDate.now() + "," + taskAdd.getTache() + "," + taskAdd.getDetails() + "," + taskAdd.getPriorite() + "," + taskAdd.getRepetition() + "," + taskAdd.getDateDebut() + "," + taskAdd.getDateEcheance() + "," + StatutTache.non_execute;
                exisitngToDoList.getTache().add(newTache);
                saveTask = toDoListRepository.save(exisitngToDoList);
                state = "Tâche ajoutée avec succès";
            } else {
                state = "La date d'échéance doit être postérieure à la date de début";
            }

        } else {
            taskAdd.setDateDebut(LocalDate.now());
            taskAdd.setDateEcheance(LocalDate.now());
            String newTache = (nbSerie) + "," + LocalDate.now() + "," + taskAdd.getTache() + "," + taskAdd.getDetails() + "," + taskAdd.getPriorite() + "," + taskAdd.getRepetition() + "," + taskAdd.getDateDebut() + "," + taskAdd.getDateEcheance() + "," + StatutTache.non_execute;
            exisitngToDoList.getTache().add(newTache);
            saveTask = toDoListRepository.save(exisitngToDoList);
            state = "Tâche ajoutée avec succès";
        }


        return state;
    }

    @Override
    public String updateTask(String titreListe, TaskAdd taskAdd, int idTask) {
        String state = null;
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            for (String todoList : existingToDoList.getTache()) {
                String[] tb = todoList.split(",");
                if (Integer.parseInt(tb[0]) == idTask) {
                    todoList = idTask + "," + tb[1] + "," + taskAdd.getTache() + "," + taskAdd.getDetails() + "," + taskAdd.getPriorite() + "," + taskAdd.getRepetition() + "," + taskAdd.getDateDebut() + "," + taskAdd.getDateEcheance();
                    existingToDoList.getTache().set(idTask - 1, todoList);
                    toDoListRepository.save(existingToDoList);
                    state = "Mise à jour avec succès";
                } else {
                    state = "Liste introuvable";
                }
            }

        } else {
            state = "Liste introuvable";
        }
        return state;
    }

    @Override
    public TaskShow getTask(String titreListe, int idTask) {
        TaskShow taskShow = new TaskShow();
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            for (String todoList : existingToDoList.getTache()) {
                String[] tb = todoList.split(",");
                if (Integer.parseInt(tb[0]) == idTask) {
                    taskShow.setDateCreation(tb[1]);
                    taskShow.setTache(tb[2]);
                    taskShow.setDetails(tb[3]);
                    taskShow.setPriorite(tb[4]);
                    taskShow.setRepetition(tb[5]);
                    taskShow.setDateDebut(tb[6]);
                    taskShow.setDateEcheance(tb[7]);
                    taskShow.setStatutTache(tb[8]);
                } else {

                }
            }

        } else {

        }
        return taskShow;
    }

    @Override
    public String deleteTask(String titreListe, int idTask) {
        String state = null;
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            for (String todoList : existingToDoList.getTache()) {
                String[] tb = todoList.split(",");
                if (Integer.parseInt(tb[0]) == idTask) {
                    existingToDoList.getTache().remove((idTask - 1));
                    toDoListRepository.save(existingToDoList);
                    state = "La tâche " + idTask + " a été supprimée";
                    break;
                }
            }

        } else {
            state = "La tâche " + idTask + " est introuvable";
        }
        return state;
    }

    @Override
    public List<ToDoList> getByDateList(LocalDate startDate, LocalDate endDate) {
        return toDoListRepository.findByDateCreationBetween(startDate, endDate);
    }

    @Override
    public String executeTask(String titreListe, TaskAdd taskAdd, int idTask) {
        String state = null;
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            for (String todoList : existingToDoList.getTache()) {
                String[] tb = todoList.split(",");
                if (Integer.parseInt(tb[0]) == idTask) {
                    if (tb[8].equals("non_execute")) {
                        todoList = idTask + "," + tb[1] + "," + tb[2] + "," + tb[3] + "," + tb[4] + "," + tb[5] + "," + tb[6] + "," + tb[7] + "," + StatutTache.execute;
                    } else {
                        todoList = idTask + "," + tb[1] + "," + tb[2] + "," + tb[3] + "," + tb[4] + "," + tb[5] + "," + tb[6] + "," + tb[7] + "," + StatutTache.non_execute;
                    }

                    existingToDoList.getTache().set(idTask - 1, todoList);
                    toDoListRepository.save(existingToDoList);
                    state = "Mise à jour avec succès";
                } else {
                    state = "Liste introuvable";
                }
            }

        } else {
            state = "Liste introuvable";
        }
        return state;
    }

    @Override
    public String archiveTask(String titreListe, TaskAdd taskAdd, int idTask) {
        String state = null;
        if (toDoListRepository.existsByTitreList(titreListe)) {
            ToDoList existingToDoList = toDoListRepository.findByTitreList(titreListe);
            for (String todoList : existingToDoList.getTache()) {
                String[] tb = todoList.split(",");
                if (Integer.parseInt(tb[0]) == idTask) {
                    todoList = idTask + "," + tb[1] + "," + tb[2] + "," + tb[3] + "," + tb[4] + "," + tb[5] + "," + tb[6] + "," + tb[7] + "," + StatutTache.archive;
                    existingToDoList.getTache().set(idTask - 1, todoList);
                    toDoListRepository.save(existingToDoList);
                    state = "Mise à jour avec succès";
                } else {
                    state = "Liste introuvable";
                }
            }

        } else {
            state = "Liste introuvable";
        }
        return state;
    }

    @Override
    public List<TaskShow> getTaskArchived(String titreListe, String statutTask) {
        List<ToDoList> existingToDoList = toDoListRepository.findAll();
        List<TaskShow> archiveTask=new ArrayList<>();
        TaskShow taskShow=new TaskShow();
        for (ToDoList elementList : existingToDoList) {
            for (String element : elementList.getTache()) {
                String[] tb = element.split(",");
                if (tb[8].equals("archive")) {
                    taskShow.setDateCreation(tb[1]);
                    taskShow.setTache(tb[2]);
                    taskShow.setDetails(tb[3]);
                    taskShow.setPriorite(tb[4]);
                    taskShow.setRepetition(tb[5]);
                    taskShow.setDateDebut(tb[6]);
                    taskShow.setDateEcheance(tb[7]);
                    taskShow.setStatutTache(tb[8]);
                    archiveTask.add(taskShow);
                }
            }
        }
        return archiveTask;
    }

}
