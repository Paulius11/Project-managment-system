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
import lt.projectmanagement.model.TaskPostModel;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

	@Autowired
	ProjectRepository repositoryProject;

	@Autowired
	TaskRepository repositoryTask;

	@Autowired
	Project projectModel;
	
	@Autowired 
	Task taskModel;

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

	private Optional<Project> getProjectById(Long projectId) {
		Optional<Project> findProjectById = Optional.of(
				repositoryProject.findById(projectId).orElseThrow(() -> new UserNotFoundException("id:" + projectId)));
		return findProjectById;
	}

	
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
	public Task createTask(TaskPostModel task, Project project) {
		taskModel.setId(project.getProjectId()); 
		taskModel.setTaskName(task.getTaskName());
		taskModel.setTaskDescription(task.getTaskDescription());
		taskModel.setTaskState(task.getTaskState());
		return repositoryTask.save(taskModel);
}
	public List<Task> getAllTasks() {
		List<Task> listOfTasks = new ArrayList<>();
		Iterable<Task> findAllTasks = repositoryTask.findAll();
		findAllTasks.forEach(listOfTasks::add);
		return listOfTasks;
	}
	@Override
	public void deleteTaskById(Long id, Project project) {
		try {
		repositoryTask.deleteById(id);
		} catch (Exception e) {
			throw new UserNotFoundException("Id:" + id);
		}
		}
		@Override
		public Task taskUpdate(Long id, TaskPostModel taskPost, Project project) {
			Optional<Task> foundTask = repositoryTask.findById(id);
			Task taskRequested = foundTask.get();
			taskRequested.setTaskName(taskPost.getTaskName());
			taskRequested.setTaskDescription(taskPost.getTaskDescription());
			taskRequested.setTaskState(taskPost.getTaskState());
			try {
				repositoryTask.save(taskRequested);
			} catch (Exception e) {
				throw new UserNotFoundException("id:" + id);
			}
			return taskRequested;
		}
		@Override
		public Task geTaskById(Long id) {
			Optional<Task> findById = repositoryTask.findById(id);
			return findById.orElseThrow(() -> new UserNotFoundException("id:" + id));
		}
	}
