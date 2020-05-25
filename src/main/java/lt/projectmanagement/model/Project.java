package lt.projectmanagement.model;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Component
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvBindByPosition(position = 1)
	private Long id;
	@CsvBindByPosition(position = 2)
	private String projectName;
	@CsvBindByPosition(position = 3)
	private String projectDescription;
	@Enumerated(EnumType.STRING)
	@CsvBindByPosition(position = 4)
	private ProjectStatus projectStatus;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	@CsvBindByPosition(position = 5)
	private List<Task> listOfTasks;

	protected Project() {
	};

	public Project(String name, String description, ProjectStatus projectStatus) {
		super();
		this.projectName = name;
		this.projectDescription = description;
		this.projectStatus = projectStatus;

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Long getId() {
		return id;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<Task> getListOfTasks() {
		return listOfTasks;
	}

	public void setListOfTasks(List<Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}

	@Override
	public String toString() {
		return "Projectas [id=" + id + ", projectName=" + projectName + ", projectDescription=" + projectDescription
				+ ", projectState=" + projectStatus + "]";
	}

}
