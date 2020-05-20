package lt.projectmanagement.controller;

import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.Task;
import lt.projectmanagement.model.TaskPostModel;
import lt.projectmanagement.userservice.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

/**
 * Task controller
 */
@CrossOrigin("http://localhost:3000")
@Api(value = "user")
@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

	@Autowired
	ProjectTaskService userService;
	private Task createTask;

	/**
	 * Get all tasks by project
	 * 
	 * @param projectId ID of specific project
	 * @return Registered list of users
	 */
	@ApiOperation(value = "Get users", notes = "Returns registered users.")
	@GetMapping()
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
		return new ResponseEntity<List<Task>>(userService.getAllTasks(projectId), HttpStatus.OK);
	}

	/**
	 * Get specific task from project.
	 * 
	 * @param projectId ID of specific project
	 * @param taskId    ID of specific task
	 * @return returns specific task
	 */
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getSpecificTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		return new ResponseEntity<Task>(userService.getSpecificTask(projectId, taskId), HttpStatus.OK);
	}

	/**
	 * Creates new task in specific project
	 * 
	 * @param projectId ID of specific project
	 * @param taskPost  ID of specific task
	 * @return Location URI of newly created task
	 */
	@PostMapping()
	public ResponseEntity<Object> createTask(@PathVariable Long projectId, @Valid @RequestBody TaskPostModel taskPost) {
		Project project = userService.geProjectById(projectId);
		createTask = userService.createTask(taskPost, project);

		// Adds to response 'Header Location':
		// http://localhost:9090/api/project/{id}/task/
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskID}")
				.buildAndExpand(createTask.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Update specific task
	 * 
	 * @param projectId ID of specific project
	 * @param taskPost  TaskPostModel for getting information from POST method
	 * @param taskId    ID of specific task
	 * @return Updated task data
	 */
	@PutMapping("/{taskId}")
	public Task editProject(@Valid @PathVariable Long projectId, @RequestBody TaskPostModel taskPost,
			@PathVariable Long taskId) {
		Project projectUpdate = userService.geProjectById(projectId);
		Task taskUpdate = userService.taskUpdate(taskId, taskPost, projectUpdate);
		return taskUpdate;
	}

	/**
	 * Deletes task from project
	 * 
	 * @param projectId ID of specific project
	 * @param taskId    ID of specific task
	 * @return HTTP 200 OK status, if deletion is successful
	 */
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		Project project = userService.geProjectById(projectId);
		userService.deleteTaskById(taskId, project);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
