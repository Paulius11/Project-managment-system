package lt.projectmanagement.model;

import javax.validation.constraints.Size;

public class TaskPostModel {

		public TaskState getTaskState() {
		return taskState;
	}

	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

		@Size(min=1)
		private String taskName;
		@Size(min=1)
		private String taskDescription;
		private TaskState taskState;

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

		
		}

	