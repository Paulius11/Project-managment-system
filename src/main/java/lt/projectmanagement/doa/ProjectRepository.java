package lt.projectmanagement.doa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lt.projectmanagement.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findByProjectName(String projectName);

	List<Project> findByProjectDescription(String projectName);

}
