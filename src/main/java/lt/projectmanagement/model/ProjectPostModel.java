package lt.projectmanagement.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectPostModel {
	
	//:TODO Pagerinti validacija, kaip validuoti projectState?
	@Size(min=1)
	private String projectName;
	@Size(min=1)
	private String projectDescription;
	private boolean projectState;

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

	public boolean isProjectState() {
		return projectState;
	}

	public void setProjectState(boolean projectState) {
		this.projectState = projectState;
	}

}
