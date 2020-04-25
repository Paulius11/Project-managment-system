package lt.projectmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	// Information about project
	private boolean projectState;

	// TODO: Projekto bendras užduočių kiekis
	// TODO: Projekto neatliktų užduočių kiekis

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Task> listOfTasks;

	protected Project() {
	};

	public Project(String name, String description, boolean projectState) {
		super();
		this.projectName = name;
		this.projectDescription = description;
		this.projectState = projectState;

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

	public void setProjectState(boolean projectState) {
		this.projectState = projectState;
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

	public boolean isProjectState() {
		return projectState;
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
				+ ", projectState=" + projectState + "]";
	}

}
