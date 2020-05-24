package lt.projectmanagement.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectPostModel {

	@Size(min = 1)
	private String projectName;
	@Size(min = 1)
	private String projectDescription;
	@Enumerated(EnumType.STRING)
	private ProjectStatus projectStatus;

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

	@Override
	public String toString() {
		return "ProjectPostModel [projectName=" + projectName + ", projectDescription=" + projectDescription
				+ ", projectStatus=" + projectStatus + "]";
	}
	
	

}