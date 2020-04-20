package lt.projectmanagement;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import lt.projectmanagement.doa.ProjectRepository;
import lt.projectmanagement.doa.TaskRepository;
import lt.projectmanagement.model.Project;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ProjectRepository repositoryProject;

	@MockBean
	TaskRepository repositoryTask;

	@MockBean
	Project projectModel;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/projects")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[")));
	}

	@Test
	public void testAddingProject() throws Exception {
		repositoryProject.save(new Project("Exciting project-0", "Do something fun-0", true));
		this.mockMvc.perform(get("/api/projects")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Exciting project-0")));
	}

	@Test
	@Transactional
	public void projectSaveTest() {

		final String PROJECT_NAME = "ProjektasTest";
		List<Project> findByProjectNameList = repositoryProject.findByProjectName(PROJECT_NAME);
		if (findByProjectNameList.size() == 0) {
			Project project = new Project(PROJECT_NAME, "ProjectTestDescr", true);
			repositoryProject.save(project);
		}

		findByProjectNameList = repositoryProject.findByProjectName(PROJECT_NAME);
		assertTrue(findByProjectNameList.size() > 0);

	}
}
