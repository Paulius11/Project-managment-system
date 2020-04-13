package lt.projectmanagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String taskName;
	// Užduoties aprašymas (User story formatas)
	private String taskDescription;
	private TaskPriorityLevel projectPriority;
	private TaskState taskState;
	private Date taskCreateTime;
	private Date taskModifyTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Project project;

	public Task() {
	}; // :TODO pakeisti i protected? ir Project'e

	public Task(String taskName, String taskDescription, TaskPriorityLevel projectPriority, TaskState taskState,
			Date taskModifyTime) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.projectPriority = projectPriority;
		this.taskState = taskState;
		this.taskModifyTime = taskModifyTime;
		this.taskCreateTime = new Date();
	}

	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	public Date getTaskModifyTime() {
		return taskModifyTime;
	}

	public void setTaskModifyTime(Date taskModifyTime) {
		this.taskModifyTime = taskModifyTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskPriorityLevel getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(TaskPriorityLevel projectPriority) {
		this.projectPriority = projectPriority;
	}

	public TaskState getTaskState() {
		return taskState;
	}

	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", projectPriority=" + projectPriority + ", taskState=" + taskState + "]";
	}

}
