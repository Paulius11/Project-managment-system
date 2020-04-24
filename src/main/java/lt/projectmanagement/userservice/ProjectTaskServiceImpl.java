package lt.projectmanagement.userservice;

import lt.projectmanagement.doa.ProjectRepository;
import lt.projectmanagement.doa.TaskRepository;
import lt.projectmanagement.exceptions.UserNotFoundException;
import lt.projectmanagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

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
	public List<DisplayAllProjectModel> getAllProjects() {
		List<DisplayAllProjectModel> listOfProjects = new ArrayList<>();

		Iterable<Project> findAllProjects = repositoryProject.findAll();
		findAllProjects.forEach(project -> {
			DisplayAllProjectModel displayAllprojectModel = new DisplayAllProjectModel();
			displayAllprojectModel.setId(project.getId());
			displayAllprojectModel.setProjectName(project.getProjectName());
			displayAllprojectModel.setProjectDescription(project.getProjectDescription());
			String state = project.isProjectState() ? "ACTIVE" : "COMPLETED";
			displayAllprojectModel.setProjectState(state);
			displayAllprojectModel.setTotalTasks(project.getListOfTasks().size());
			displayAllprojectModel.setIncopleteTasks(
					project.getListOfTasks().stream().filter(x -> x.getTaskState() != TaskState.DONE).count());

			listOfProjects.add(displayAllprojectModel);
		});

		return listOfProjects;
	}

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

	private Optional<Project> getProjectById(Long projectId) {
		Optional<Project> findProjectById = Optional.of(
				repositoryProject.findById(projectId).orElseThrow(() -> new UserNotFoundException("id:" + projectId)));
		return findProjectById;
	}

	@Override
	public List<Task> getAllTasks(Long projectId) {
		Optional<Project> project = getProjectById(projectId);
		return project.get().getListOfTasks();
	}

	@Override
	public Task getSpecificTask(Long projectId, Long taskId) {
		List<Task> listOfTasks = getAllTasks(projectId);
		Task orElseThrow = listOfTasks.stream().filter(x -> x.getId() == taskId).findFirst()
				.orElseThrow(() -> new UserNotFoundException("Task-id:" + taskId));
		return orElseThrow;

	}

}
