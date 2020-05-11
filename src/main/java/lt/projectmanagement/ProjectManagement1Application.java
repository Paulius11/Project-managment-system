package lt.projectmanagement;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lt.projectmanagement.doa.ProjectRepository;
import lt.projectmanagement.doa.TaskRepository;
import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectStatus;
import lt.projectmanagement.model.Task;
import lt.projectmanagement.model.TaskPriorityLevel;
import lt.projectmanagement.model.TaskState;

@SpringBootApplication
public class ProjectManagement1Application {

	private static final Logger log = LoggerFactory.getLogger(ProjectManagement1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagement1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProjectRepository repositoryProject, TaskRepository taskRepository) {
		return (args) -> {
			// save a few projects
			repositoryProject.save(new Project("Exciting project-0", "Do something fun-0", ProjectStatus.ACTIVE));
			repositoryProject.save(new Project("Exciting project-1", "Do something easy-1", ProjectStatus.COMPLETED));
			repositoryProject.save(new Project("Exciting project-2", "Do something simple-2", ProjectStatus.ACTIVE));
			repositoryProject.save(new Project("TestProjectName", "TestDescrName", ProjectStatus.ACTIVE));

			taskRepository.save(new Task("New task1", "Task description", TaskPriorityLevel.NORMAL, TaskState.TO_DO,
					LocalDateTime.now()));

			log.info("fetch project by name:");
			List<Project> findByProjectName = repositoryProject.findByProjectName("Exciting project-0");
			findByProjectName.forEach(x -> log.info(x.toString()));

			log.info("Projects found with findAll():");

			log.info("-------------------------------");
			for (Project project : repositoryProject.findAll()) {
				log.info(project.toString());
			}

		};
	}

}