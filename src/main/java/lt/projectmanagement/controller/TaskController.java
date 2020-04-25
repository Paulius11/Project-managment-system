package lt.projectmanagement.controller;

import lt.projectmanagement.model.Task;
import lt.projectmanagement.userservice.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

//: TODO Add Validation
//	Sukurti taską  - POST /projects/{id}/task/
//	Ištrinti taską - DELETE /project/{id}/tasks/{taskID}
//	Pakeisti taską - PUT  /projects/{id}/tasks/{taskID}

	@Autowired
	ProjectTaskService userService;

//	Gauti visus taskus - GET /projects/{id}/tasks
	@GetMapping()
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
		return new ResponseEntity<List<Task>>(userService.getAllTasks(projectId), HttpStatus.OK);
	}

//	Gauti kontretu taską - GET /projects/{id}/tasks/{taskID}
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getSpecificTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		return new ResponseEntity<Task>(userService.getSpecificTask(projectId, taskId), HttpStatus.OK);
	}

}
