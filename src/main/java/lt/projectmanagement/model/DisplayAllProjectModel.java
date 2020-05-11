package lt.projectmanagement.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.stereotype.Component;

@Component
public class DisplayAllProjectModel {

	private long id;
	private String projectName;
	private String projectDescription;
	@Enumerated(EnumType.STRING)
	private ProjectStatus projectStatus;
	private long totalTasks;
	private long incopleteTasks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public long getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public long getIncopleteTasks() {
		return incopleteTasks;
	}

	public void setIncopleteTasks(long incopleteTasks) {
		this.incopleteTasks = incopleteTasks;
	}

}
