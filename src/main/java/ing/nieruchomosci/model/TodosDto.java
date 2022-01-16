package ing.nieruchomosci.model;

import java.util.Date;

public class TodosDto {

	private Date downloadDate;
	private Integer todoUserId;
	private Integer todoId;
	private String todoTitle;
	private Boolean todoCompleted;
	
	public TodosDto() {
	}

	public TodosDto(Date downloadDate, Integer todoUserId, Integer todoId, String todoTitle, Boolean todoCompleted) {
		this.downloadDate = downloadDate;
		this.todoUserId = todoUserId;
		this.todoId = todoId;
		this.todoTitle = todoTitle;
		this.todoCompleted = todoCompleted;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}
	
	public Integer getTodoUserId() {
		return todoUserId;
	}

	public Integer getTodoId() {
		return todoId;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public Boolean getTodoCompleted() {
		return todoCompleted;
	}
	
}
