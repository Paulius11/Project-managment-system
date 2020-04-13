package lt.projectmanagement.userservice;

import java.util.List;

import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.model.Task;

public interface UserService {

	List<Project> getAllProjects();

//	List<Project> getProjectByName(String name);

	void deleteProjectById(Long id);

	Project projectUpdate(Long id, ProjectPostModel projectPost);

	Project createProject(ProjectPostModel projectPost);

	Project geProjectById(Long id);

	List<Task> getAllTasks(Long projectId);

}
