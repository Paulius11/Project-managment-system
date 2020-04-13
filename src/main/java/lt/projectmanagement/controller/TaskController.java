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
import lt.projectmanagement.userservice.UserService;

@RestController
@RequestMapping("/api/project/{projectId}/tasks")
public class TaskController {

//	Gauti visus taskus - GET /projects/{id}/tasks
//	Gauti kontretu taską - GET /projects/{id}/tasks/{taskID}
//	Sukurti taską  - POST /projects/{id}/tasks/
//	Ištrinti taską - DELETE /projects/{id}/tasks/{taskID}
//	Pakeisti taską - PUT  /projects/{id}/tasks/{taskID}

	@Autowired
	UserService userService;

	@GetMapping()
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
		return new ResponseEntity<List<Task>>(userService.getAllTasks(projectId), HttpStatus.OK);
	}

}
