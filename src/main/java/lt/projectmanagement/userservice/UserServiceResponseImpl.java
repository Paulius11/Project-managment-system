package lt.projectmanagement.userservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.projectmanagement.doa.ProjectRepository;
import lt.projectmanagement.doa.TaskRepository;
import lt.projectmanagement.exceptions.UserNotFoundException;
import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.model.Task;

@Service
public class UserServiceResponseImpl implements UserService {

	@Autowired
	ProjectRepository repositoryProject;

	@Autowired
	TaskRepository repositoryTask;

	@Autowired
	Project projectModel;

	@Override
	public Project createProject(ProjectPostModel project) {
		projectModel.setId(null); // Null'as yra tam, kad sukurtu nauja objekta, nenunulinus atnaujina ta pati
									// objekta
		projectModel.setProjectName(project.getProjectName());
		projectModel.setProjectDescription(project.getProjectDescription());
		projectModel.setProjectState(project.isProjectState());
		return repositoryProject.save(projectModel);
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> listOfProjects = new ArrayList<>();
		Iterable<Project> findAllProjects = repositoryProject.findAll();
		findAllProjects.forEach(listOfProjects::add);
		return listOfProjects;
	}

//	@Override
//	public List<Project> getProjectByName(String projectName) {
//		return repositoryProject.findByProjectName(projectName);
//	}

	@Override
	public void deleteProjectById(Long id) {
		try {
			repositoryProject.deleteById(id);
		} catch (Exception e) {
			throw new UserNotFoundException("id:" + id);
		}
	}

	@Override
	public Project projectUpdate(Long id, ProjectPostModel projectPost) {
		Optional<Project> foundProject = repositoryProject.findById(id);
		Project projectRequested = foundProject.get();
		projectRequested.setProjectName(projectPost.getProjectName());
		projectRequested.setProjectDescription(projectPost.getProjectDescription());
		projectRequested.setProjectState(projectPost.isProjectState());
		try {
			repositoryProject.save(projectRequested);
		} catch (Exception e) {
			throw new UserNotFoundException("id:" + id);
		}
		return projectRequested;
	}

	@Override
	public Project geProjectById(Long id) {
		Optional<Project> findById = repositoryProject.findById(id);
		return findById.orElseThrow(() -> new UserNotFoundException("id:" + id));
	}

	// TASKS

	@Override
	public List<Task> getAllTasks(Long projectId) {
		Optional<Project> findAllTasks = Optional.of(
				repositoryProject.findById(projectId).orElseThrow(() -> new UserNotFoundException("id:" + projectId)));
		return findAllTasks.get().getListOfTasks();
	}

}
