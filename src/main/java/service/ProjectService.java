package service;

import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project){
        if (project != null){
            return projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("cannot save news");
        }
    }

    public Optional<Project> getProjects(Long id){
        return projectRepository.findById(id);
    }

    public List<Project> retriveProjects(){
        return  projectRepository.findAll();
    }
}
