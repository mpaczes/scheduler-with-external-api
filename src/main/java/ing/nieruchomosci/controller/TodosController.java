package ing.nieruchomosci.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ing.nieruchomosci.model.CountersForUser;
import ing.nieruchomosci.model.TodosDto;
import ing.nieruchomosci.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodosController {
	
	private final TodoService todoService;
	
	private static final Logger log = LoggerFactory.getLogger(TodosController.class);

	public TodosController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	// URL -- /api/todos
	@GetMapping("/todos")
	public List<TodosDto> getAllTodos() {
		return (List<TodosDto>) todoService.getAllTodos();
	}
	
	// URL -- /api/todos/1
	@GetMapping("/todos/{userId}")
	public List<TodosDto> getTodosForUserId(@PathVariable("userId") Integer userId) {
		return (List<TodosDto>) todoService.findByTodoUserId(userId);
	}
	
	// URL -- /api/todos/dateRange?dateSince=202105011450&dateUntil=202109101625
	@GetMapping("/todos/dateRange")
	public List<TodosDto> getTodosBetweenDateRange(@RequestParam("dateSince") String dateSince,
			@RequestParam("dateUntil") String dateUntil) throws ParseException {
		Date dateSinceConverted = todoService.createDate(dateSince);
		Date dateUntilConverted = todoService.createDate(dateUntil);
		
		return (List<TodosDto>) todoService.findAllByDownloadDateBetween(dateSinceConverted, dateUntilConverted);
	}
	
	// URL -- /api/todos/countUserRecords?userId=1
	@GetMapping("/todos/countUserRecords")
	public CountersForUser countUserRecords(@RequestParam("userId") Integer userId) {
		return todoService.countUserRecords(userId);
	}
	
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request, Exception exception) {
    	log.error("Request: " + request.getRequestURL() + " raised " + exception);
    }
}
