package lt.projectmanagement.model;

import org.springframework.stereotype.Component;

@Component
public class DisplayAllProjectModel {

	private long id;
	private String projectName;
	private String projectDescription;
	private String projectState;
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

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
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
