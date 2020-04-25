package lt.projectmanagement.doa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findByTaskName(String taskName);

	List<Task> findByTaskDescription(String taskName);

	//void deleteById(Long id, Project project);

	//Optional<Task> findById(Long id, Project project);
}
