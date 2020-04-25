package lt.projectmanagement.userservice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.model.Task;
import lt.projectmanagement.model.TaskPostModel;


public interface ProjectTaskService {

	List<Project> getAllProjects();

//	List<Project> getProjectByName(String name);

	void deleteProjectById(Long id);

	Project projectUpdate(Long id, ProjectPostModel projectPost);

	Project createProject(ProjectPostModel projectPost);

	Project geProjectById(Long projectId);

	List<Task> getAllTasks(Long projectId);
	
	//void deleteTaskById(Long id);
	
	//Task taskUpdate(Long id, TaskPostModel taskPost);
	
	//Task createTask(TaskPostModel taskPost);

	Task getSpecificTask(Long projectId, Long taskId);

	Task geTaskById(Long taskId);

	Task createTask(@Valid TaskPostModel taskPost, Project project);

	Task taskUpdate(Long taskId, TaskPostModel taskPost, Project projectUpdate);

	void deleteTaskById(Long id, Project project);

	



}
