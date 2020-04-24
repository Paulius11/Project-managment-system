package lt.projectmanagement.model;

import org.springframework.stereotype.Component;


public class ProjectRespModel {
	private String name;
	private String description;
	private boolean projectState;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isProjectState() {
		return projectState;
	}
	public void setProjectState(boolean projectState) {
		this.projectState = projectState;
	}



}
