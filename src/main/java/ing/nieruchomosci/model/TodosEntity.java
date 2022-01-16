package ing.nieruchomosci.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TodosEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long dbRecordId;
	// @Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date downloadDate;
	private Integer todoUserId;
	private Integer todoId;
	private String todoTitle;
	private Boolean todoCompleted;
	
	public TodosEntity() {
	}

	public TodosEntity(Date downloadDate, Integer todoUserId, Integer todoId, String todoTitle, Boolean todoCompleted) {
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
