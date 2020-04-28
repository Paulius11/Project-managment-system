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

@CrossOrigin("http://localhost:3000")
@Api(value = "user")
@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

//: TODO Add Validation
//	Sukurti taską  - POST /projects/{id}/task/
//	Ištrinti taską - DELETE /project/{id}/tasks/{taskID}
//	Pakeisti taską - PUT  /projects/{id}/tasks/{taskID}

	@Autowired
	ProjectTaskService userService;
	private Task createTask;
//	Gauti visus taskus - GET /projects/{id}/tasks
	@ApiOperation(value = "Get users", notes = "Returns registered users.")
	@GetMapping()
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
		return new ResponseEntity<List<Task>>(userService.getAllTasks(projectId), HttpStatus.OK);
	}

//	Gauti kontretu taską - GET /projects/{id}/tasks/{taskID}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getSpecificTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		return new ResponseEntity<Task>(userService.getSpecificTask(projectId, taskId), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object>createTask(@PathVariable Long projectId, @Valid @RequestBody TaskPostModel taskPost) {
	Project project	= userService.geProjectById(projectId);
	createTask = userService.createTask(taskPost, project);
	

		// Adds to response 'Header Location': http://localhost:9090/api/project/{id}/task/
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskID}")
				.buildAndExpand(createTask.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	
	@PutMapping("/{taskId}")
	public Task editProject(@Valid @PathVariable Long projectId, @RequestBody TaskPostModel taskPost, @PathVariable Long taskId) {
		Project projectUpdate = userService.geProjectById(projectId);
		Task taskUpdate = userService.taskUpdate(taskId, taskPost, projectUpdate);
		return taskUpdate;
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		Project project = userService.geProjectById(projectId);
		userService.deleteTaskById(taskId, project);
		return new ResponseEntity<Void>(HttpStatus.OK);
//	    return ResponseEntity.noContent().build();
	}

}
