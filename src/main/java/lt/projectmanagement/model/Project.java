package lt.projectmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String projectName;
	private String projectDescription;
	@Enumerated(EnumType.STRING)
	private ProjectStatus projectStatus;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
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
