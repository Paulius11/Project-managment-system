package lt.projectmanagement.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.projectmanagement.model.DisplayAllProjectModel;
import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.userservice.ProjectTaskService;

@CrossOrigin(origins = { "http://localhost:3000" })
@Api(value = "user")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	ProjectTaskService userService;
	private Project createProject;

	@ApiOperation(value = "Get users", notes = "Returns registered users.")
	@GetMapping()
	public ResponseEntity<List<DisplayAllProjectModel>> getAllProject() {
		return new ResponseEntity<List<DisplayAllProjectModel>>(userService.getAllProjects(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get project by projectId", notes = "Returns project by projectId.")
	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable Long projectId) {
		return userService.geProjectById(projectId);
	}

	@ApiOperation(value = "Create projecs", notes = "Creates new project.")
	@PostMapping
	public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectPostModel projectPost) {
		createProject = userService.createProject(projectPost);

		// Adds to response 'Header Location': http://localhost:9090/api/projects/{id}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createProject.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Edit project by projectID", notes = "Returns project by projectId.")
	@PutMapping("/{projectId}")
	public Project editProject(@Valid @RequestBody ProjectPostModel projectPost, @PathVariable Long projectId) {
		Project projectUpdate = userService.projectUpdate(projectId, projectPost);
		return projectUpdate;
	}

	@ApiOperation(value = "Delete project", notes = "Delete project by projectId.")
	@DeleteMapping("{projectId}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
		userService.deleteProjectById(projectId);
		return new ResponseEntity<Void>(HttpStatus.OK);
//	    return ResponseEntity.noContent().build();
	}

}
