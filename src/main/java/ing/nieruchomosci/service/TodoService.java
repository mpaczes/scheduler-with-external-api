package ing.nieruchomosci.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import ing.nieruchomosci.model.CountersForUser;
import ing.nieruchomosci.model.TodosDto;
import ing.nieruchomosci.model.TodosEntity;
import ing.nieruchomosci.repository.TodoRepository;

@Service
public class TodoService {

	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public Iterable<TodosDto> getAllTodos() {
		return convertEntityToDto(todoRepository.findAll());
	}
	
	public TodosEntity saveTodo(TodosDto todosDto) {
		return todoRepository.save(convertDtoToEntity(todosDto));
	}
	
	public List<TodosDto> findByTodoUserId(Integer todoUserId) {
		return convertEntityToDto(todoRepository.findByTodoUserId(todoUserId));
	}
	
	public List<TodosDto> findAllByDownloadDate(Date downloadDate) {
		return convertEntityToDto(todoRepository.findAllByDownloadDate(downloadDate));
	}
	
	public List<TodosDto> findAllByDownloadDateBetween(Date downloadDateStart, Date downloadDateEnd) {
		return convertEntityToDto(todoRepository.findAllByDownloadDateBetween(downloadDateStart, downloadDateEnd));
	}
	
	public List<TodosDto> findAllWithDownloadDateBefore(Date downloadDate) {
		return convertEntityToDto(todoRepository.findAllWithDownloadDateBefore(downloadDate));
	}
	
	public CountersForUser countUserRecords(Integer userId) {
		return todoRepository.countUserRecords(userId);
	}
	
	/**
	 * This method converts date represented as string into the Date data type.
	 * @param dateAsString date as a string in the format 'yyyyMMddHHmm'
	 * @return Date data type
	 * @throws ParseException 
	 */
	public Date createDate(String dateAsString) throws ParseException {
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyyMMddHHmm").parse(dateAsString);
		} catch (ParseException parseException) {
			throw parseException;
		}  
		return date;
	}
	
	private List<TodosDto> convertEntityToDto(List<TodosEntity> todosEntities) {
		List<TodosDto> tdosList = new ArrayList<>();
		for (TodosEntity todosEntity : todosEntities) {
			tdosList.add(new TodosDto(
					todosEntity.getDownloadDate(),
					todosEntity.getTodoUserId(),
					todosEntity.getTodoId(),
					todosEntity.getTodoTitle(),
					todosEntity.getTodoCompleted()));
		}
		return tdosList;
	}
	
	private TodosEntity convertDtoToEntity(TodosDto todosDto) {
		TodosEntity todosEntity = new TodosEntity(
				todosDto.getDownloadDate(),
				todosDto.getTodoUserId(),
				todosDto.getTodoId(),
				todosDto.getTodoTitle(),
				todosDto.getTodoCompleted());
		return todosEntity;
	}

}
