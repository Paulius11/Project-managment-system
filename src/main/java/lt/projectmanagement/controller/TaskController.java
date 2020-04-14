package lt.projectmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.projectmanagement.model.Task;
import lt.projectmanagement.userservice.ProjectTaskService;

@RestController
@RequestMapping("/api/project/{projectId}/task")
public class TaskController {

//	Sukurti taską  - POST /project/{id}/task/
//	Ištrinti taską - DELETE /project/{id}/task/{taskID}
//	Pakeisti taską - PUT  /projects/{id}/task/{taskID}

	@Autowired
	ProjectTaskService userService;

//	Gauti visus taskus - GET /project/{id}/tasks
	@GetMapping()
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
		return new ResponseEntity<List<Task>>(userService.getAllTasks(projectId), HttpStatus.OK);
	}

//	Gauti kontretu taską - GET /project/{id}/task/{taskID}
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getSpecificTask(@PathVariable Long projectId, @PathVariable Long taskId) {
		return new ResponseEntity<Task>(userService.getSpecificTask(projectId, taskId), HttpStatus.OK);
	}

}
