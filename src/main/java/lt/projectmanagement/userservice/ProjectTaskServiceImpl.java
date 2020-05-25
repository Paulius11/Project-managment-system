package lt.projectmanagement.userservice;

import lt.projectmanagement.doa.ProjectRepository;
import lt.projectmanagement.doa.TaskRepository;
import lt.projectmanagement.exceptions.UserNotFoundException;
import lt.projectmanagement.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Main logic for CRUD operations, where all business logic is executed
 *
 */
@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

	private static final Logger log = LoggerFactory.getLogger(ProjectTaskServiceImpl.class);

	@Autowired
	ProjectRepository repositoryProject;

	@Autowired
	TaskRepository repositoryTask;

	@Autowired
	Project projectModel;

	@Autowired
	Task taskModel;

	/**
	 * Creates new project
	 */
	@Override
	public Project createProject(ProjectPostModel project) {
		projectModel.setId(null); // Null'as yra tam, kad sukurtu nauja objekta, nenunulinus atnaujina ta pati
									// objekta
		projectModel.setProjectName(project.getProjectName());
		log.trace("Project name " + project.getProjectName());
		projectModel.setProjectDescription(project.getProjectDescription());
		log.trace("Project description " + project.getProjectDescription());
		projectModel.setProjectStatus(project.getProjectStatus());
		log.trace("Project status " + project.getProjectStatus());

		log.info("Saving new project to database");
		Project save = repositoryProject.save(projectModel);
		log.info(save.toString());
		return save;
	}

	/**
	 * Display list of all projects
	 */
	@Override
	public List<DisplayAllProjectModel> getAllProjects() {
		List<DisplayAllProjectModel> listOfProjects = new ArrayList<>();
		log.info("Getting list of all projects: {}", listOfProjects.getClass().hashCode());

		Iterable<Project> findAllProjects = repositoryProject.findAll();
		findAllProjects.forEach(project -> {
			DisplayAllProjectModel displayAllprojectModel = new DisplayAllProjectModel();
			displayAllprojectModel.setId(project.getId());
			displayAllprojectModel.setProjectName(project.getProjectName());
			displayAllprojectModel.setProjectDescription(project.getProjectDescription());
			displayAllprojectModel.setProjectStatus(project.getProjectStatus());
			displayAllprojectModel.setTotalTasks(project.getListOfTasks().size());
			log.trace("Project id: " + project.getId());
			log.trace("Project name: " + project.getProjectName());
			log.trace("Project description: " + project.getProjectDescription());
			log.trace("Project status: " + project.getProjectStatus());
			log.trace("Project total tasks: " + project.getListOfTasks().size());
			displayAllprojectModel.setIncopleteTasks(project.getListOfTasks().stream()
					.peek((x) -> log.debug(x.toString())).filter(x -> x.getTaskState() != TaskState.DONE).count());
			listOfProjects.add(displayAllprojectModel);

		});
		log.debug("Adding project to a list is completed");
		log.debug("Returning list of projects");
		return listOfProjects;
	}
	/**
	 * Deletes project by id
	 */
	@Override
	public void deleteProjectById(Long id) {
		try {
			log.info("Deleting project by id: " + id);
			repositoryProject.deleteById(id);
		} catch (Exception e) {
			throw new UserNotFoundException("id:" + id);
		}
	}

	/**
	 * Updates project and returns newly updated project
	 */
	@Override
	public Project projectUpdate(Long id, ProjectPostModel projectPost) {
		Optional<Project> foundProject = repositoryProject.findById(id);

		Project projectRequested = foundProject.get();
		projectRequested.setProjectName(projectPost.getProjectName());
		projectRequested.setProjectDescription(projectPost.getProjectDescription());
		projectRequested.setProjectStatus(projectPost.getProjectStatus());
		try {
			repositoryProject.save(projectRequested);
		} catch (Exception e) {
			throw new UserNotFoundException("id:" + id);

		}
		log.info("Updating project: " + id);
		log.debug("Post model {}: ", projectPost);
		return projectRequested;
	}

	/**
	 * Returns project by id
	 */
	@Override
	public Project geProjectById(Long id) {
		Optional<Project> findById = repositoryProject.findById(id);
		log.info("Getting Project by Project ID: " + id);
		return findById.orElseThrow(() -> new UserNotFoundException("id:" + id));
	}

	/**
	 * Returns project by id
	 * 
	 * @param projectId ID of specific project
	 * @return returns specific project
	 */
	private Optional<Project> getProjectById(Long projectId) {
		Optional<Project> findProjectById = Optional.of(
				repositoryProject.findById(projectId).orElseThrow(() -> new UserNotFoundException("id:" + projectId)));
		return findProjectById;
	}

	/**
	 * Returns list of all tasks
	 * 
	 */
	public List<Task> getAllTasks(Long projectId) {
		log.info("Getting all tasks by Project ID: {}", projectId);
		Optional<Project> project = getProjectById(projectId);
		List<Task> listOfTasks = project.get().getListOfTasks();
		log.debug("Getting list of tasks: {}", listOfTasks);
		return listOfTasks;
	}

	/**
	 * Returns specific task
	 */
	@Override
	public Task getSpecificTask(Long projectId, Long taskId) {
		log.info("Getting info about specific task");
		log.info("Project ID: {} \nTask ID: {}", projectId, taskId);
		List<Task> listOfTasks = getAllTasks(projectId);
		Task orElseThrow = listOfTasks.stream().filter(x -> x.getId() == taskId).findFirst()
				.orElseThrow(() -> new UserNotFoundException("Task-id:" + taskId));
		return orElseThrow;

	}

	/**
	 * Creates new task in project
	 */
	public Task createTask(TaskPostModel task, Project project) {
		log.info("Creating new task");
		taskModel.setId(null);
		taskModel.setProject(project);
		taskModel.setTaskPriority(task.getTaskPriority());
		taskModel.setTaskName(task.getTaskName());
		taskModel.setTaskDescription(task.getTaskDescription());
		taskModel.setTaskState(task.getTaskState());
		log.info("Task Model: {}", taskModel);
		return repositoryTask.save(taskModel);
	}

	/**
	 * @return Returns list of all tasks
	 */
	public List<Task> getAllTasks() {
		log.info("Getting all tasks");
		List<Task> listOfTasks = new ArrayList<>();
		Iterable<Task> findAllTasks = repositoryTask.findAll();
		findAllTasks.forEach(listOfTasks::add);
		log.debug("List of tasks:\n{} ", listOfTasks);
		return listOfTasks;
	}

	/**
	 * Deletes task by ID and project
	 */
	@Override
	public void deleteTaskById(Long id, Project project) {
		try {
			repositoryTask.deleteById(id);
			log.info("Deleting task by Task Id : {}", id);
			log.debug("In project: {}", project);
		} catch (Exception e) {
			throw new UserNotFoundException("Id:" + id);
		}
	}

	/**
	 * Updates task field
	 */
	@Override
	public Task taskUpdate(Long id, TaskPostModel taskPost, Project project) {
		log.info("Updating Task by Task Id: {}", id);
		Optional<Task> foundTask = repositoryTask.findById(id);
		Task taskRequested = foundTask.get();
		if (taskPost.getTaskName() != null) {
			taskRequested.setTaskName(taskPost.getTaskName());
		}
		if (taskPost.getTaskDescription() != null) {
			taskRequested.setTaskDescription(taskPost.getTaskDescription());
		}
		if (taskPost.getTaskState() != null) {
			taskRequested.setTaskState(taskPost.getTaskState());
		}
		if (taskPost.getTaskPriority() != null) {
			taskRequested.setTaskPriority(taskPost.getTaskPriority());
		}
		log.debug("Upadating task model: {}", taskPost);
		try {
			repositoryTask.save(taskRequested);
		} catch (Exception e) {
			throw new UserNotFoundException("id:" + id);
		}
		return taskRequested;
	}

	/**
	 * Returns task by id
	 */
	@Override
	public Task geTaskById(Long id) {
		log.info("Getting Task by Id : {}", id);
		Optional<Task> findById = repositoryTask.findById(id);
		log.debug("Found task : {}", findById);
		return findById.orElseThrow(() -> new UserNotFoundException("id:" + id));
	}
}
