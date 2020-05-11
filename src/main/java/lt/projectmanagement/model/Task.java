package lt.projectmanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component

public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 1, max = 50, message = "Message must be between 1 and 50 characters")
	private String taskName;
	@NotNull
	@Size(min = 0, max = 500, message = "Message must be between 0 and 500 characters")
	private String taskDescription;

	@Column(columnDefinition = "varchar(7) default 'NORMAL'")
	@Enumerated(EnumType.STRING)
	private TaskPriorityLevel taskPriority;

	@Column(columnDefinition = "varchar(12) default 'TO_DO'")
	@Enumerated(EnumType.STRING)
	private TaskState taskState;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(updatable = false)
    @CreationTimestamp
	private LocalDateTime taskCreateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime taskModifyTime = LocalDateTime.now();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;

	public Task() {
		taskCreateTime = LocalDateTime.now();
	}

	public Task(Long id, String taskName, String taskDescription, TaskPriorityLevel taskPriority, TaskState taskState,
			LocalDateTime taskModifyTime) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskPriority = taskPriority;
		this.taskState = taskState;
		this.taskCreateTime = LocalDateTime.now();
		this.taskModifyTime = taskModifyTime;
	}

	public Task(String taskName, String taskDescription, TaskPriorityLevel projectPriority, TaskState taskState,
			LocalDateTime taskModifyTime) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskPriority = projectPriority;
		this.taskState = taskState;
		this.taskModifyTime = taskModifyTime;
		this.taskCreateTime = LocalDateTime.now();

	}

	public LocalDateTime getTaskCreateTime() {
		return taskCreateTime;
	}

	public LocalDateTime getTaskModifyTime() {
		return taskModifyTime;
	}

	public void setTaskModifyTime(LocalDateTime taskModifyTime) {
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

	public TaskPriorityLevel getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(TaskPriorityLevel taskPriority) {
		this.taskPriority = taskPriority;
	}

	public void setTaskCreateTime(LocalDateTime taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
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
				+ ", projectPriority=" + taskPriority + ", taskState=" + taskState + "]";
	}

}
