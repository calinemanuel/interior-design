package controller;

import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ProjectService;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/all")
    public String getProjects(Model model){
        model.addAttribute("projects", projectService.retriveProjects());
        return "projects";
    }

    @GetMapping("/project1")
    public String project1(){
        return "project1";
    }

    @GetMapping("/project2")
    public String project2(){
        return "project2";
    }

    @GetMapping("/{id}")
    public String getProjectsById(@PathVariable String id, Model model){
        Optional<Project> projectOptional = projectService.getProjects(Long.valueOf(id));
        projectOptional.ifPresent(projects -> model.addAttribute("projects", projects));
        return "projects";
    }

    @PostMapping("/addprojects")
    public String addProjects(@ModelAttribute @NotEmpty Project project){
        Project saved = projectService.addProject(project);
        return "redirect:/projects/" + saved.getId();
    }
}
