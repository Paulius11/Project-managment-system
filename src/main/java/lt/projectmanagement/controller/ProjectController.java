package lt.projectmanagement.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lt.projectmanagement.model.DisplayAllProjectModel;
import lt.projectmanagement.model.Project;
import lt.projectmanagement.model.ProjectPostModel;
import lt.projectmanagement.userservice.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Project controller
 */
@CrossOrigin(origins = { "http://localhost:3000" })
@Api(value = "user")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	ProjectTaskService userService;
	private Project createProject;

	/**
	 * Returns list of project names
	 * 
	 * @return if list is return show HTTP OK code
	 */
	@ApiOperation(value = "Get users", notes = "Returns project list.")
	@GetMapping()
	public ResponseEntity<List<DisplayAllProjectModel>> getAllProject() {
		return new ResponseEntity<List<DisplayAllProjectModel>>(userService.getAllProjects(), HttpStatus.OK);
	}

	/**
	 * Gets project by ID
	 * 
	 * @param projectId ID of project
	 * @return Returns project by projectId
	 */
	@ApiOperation(value = "Get project by projectId", notes = "Returns project by projectId.")
	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable Long projectId) {
		return userService.geProjectById(projectId);
	}

	/**
	 * Creates new project via POST method Adds to response 'Header Location':
	 * http://localhost:9090/api/projects/{id}
	 * 
	 * @param projectPost data from ProjectPostModel
	 * @return Location URI of newly created project
	 */
	@ApiOperation(value = "Create projecs", notes = "Creates new project.")
	@PostMapping
	public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectPostModel projectPost) {
		createProject = userService.createProject(projectPost);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createProject.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Edits project by project ID
	 * 
	 * @param projectPost data from ProjectPostModel
	 * @param projectId   ID of project name
	 * @return return data of updated object
	 */
	@ApiOperation(value = "Edit project by projectID", notes = "Returns project by projectId.")
	@PutMapping("/{projectId}")
	public Project editProject(@Valid @RequestBody ProjectPostModel projectPost, @PathVariable Long projectId) {
		Project projectUpdate = userService.projectUpdate(projectId, projectPost);
		return projectUpdate;
	}

	/**
	 * Deletes project by project ID
	 * 
	 * @param projectId ID of project
	 * @return Returns OK status code if deletion is successful
	 */
	@ApiOperation(value = "Delete project", notes = "Delete project by projectId.")
	@DeleteMapping("{projectId}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
		userService.deleteProjectById(projectId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Generate CSV file
	 * 
	 * @param projectId ID of project name
	 * @param response
	 * @throws Exception
	 */
	@GetMapping(path = "{projectId}/export-projects")
	public void getProjectsCSV(@PathVariable Long projectId, HttpServletResponse response) throws Exception {

		// set file name and content type
		String filename = "projects.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

		// create a csv writer
		StatefulBeanToCsv<Project> writer = new StatefulBeanToCsvBuilder<Project>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false).build();

		// write all users to csv file
		writer.write(userService.geProjectById(projectId));
	}
}
