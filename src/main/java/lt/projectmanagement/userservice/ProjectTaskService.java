package lt.projectmanagement.userservice;

import java.util.List;

import javax.validation.Valid;

import lt.projectmanagement.model.DisplayAllProjectModel;
import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.model.Task;
import lt.projectmanagement.model.TaskPostModel;

public interface ProjectTaskService {

	List<DisplayAllProjectModel> getAllProjects();

	void deleteProjectById(Long id);

	Project projectUpdate(Long id, ProjectPostModel projectPost);

	Project createProject(ProjectPostModel projectPost);

	Project geProjectById(Long projectId);

	List<Task> getAllTasks(Long projectId);

	Task getSpecificTask(Long projectId, Long taskId);

	Task geTaskById(Long taskId);

	Task createTask(@Valid TaskPostModel taskPost, Project project);

	Task taskUpdate(Long taskId, TaskPostModel taskPost, Project projectUpdate);

	void deleteTaskById(Long id, Project project);

}
