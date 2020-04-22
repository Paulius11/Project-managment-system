package lt.projectmanagement.model;

public class TaskRespModel {

	private String name;
	private String description;
	private boolean taskState;
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
	public boolean isTasktate() {
		return taskState;
	}
	public void setProjectState(boolean taskState) {
		this.taskState = taskState;
	}
	public static TaskRespModel from(Task createTask) {
		// TODO Auto-generated method stub
		return null;
	}



}