package lt.projectmanagement.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

public class TaskPostModel {

	public TaskState getTaskState() {
		return taskState;
	}

	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

	@Size(min = 1)
	private String taskName;
	@Size(min = 1)
	private String taskDescription;
	@Column(columnDefinition = "varchar(7) default 'NORMAL'")
	@Enumerated(EnumType.STRING)
	private TaskPriorityLevel taskPriority;

	@Column(columnDefinition = "varchar(12) default 'TO_DO'")
	@Enumerated(EnumType.STRING)
	private TaskState taskState;

	public TaskPriorityLevel getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(TaskPriorityLevel taskPriority) {
		this.taskPriority = taskPriority;
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

	@Override
	public String toString() {
		return "TaskPostModel [taskName=" + taskName + ", taskDescription=" + taskDescription + ", taskPriority="
				+ taskPriority + ", taskState=" + taskState + "]";
	}

}