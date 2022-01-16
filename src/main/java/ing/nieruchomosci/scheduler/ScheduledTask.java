package ing.nieruchomosci.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ing.nieruchomosci.model.TodosDto;
import ing.nieruchomosci.service.TodoService;

@Component
public class ScheduledTask {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private final WebClient todoWebClient;
	private final TodoService todoService;
	
	public ScheduledTask(WebClient todoWebClient, TodoService todoService) {
		this.todoWebClient = todoWebClient;
		this.todoService = todoService;
	}

	@Scheduled(cron = "0 */2 * * * *")
	@Transactional
	public void getTodosFromExternalService() {
		try {
			Date currentDate = new Date();
			log.info("The time is now {}", dateFormat.format(currentDate));
			
			Random randomGenerator = new Random();
			Integer userId = randomGenerator.nextInt(3) + 1;
			
			ArrayNode jsonResponse = this.todoWebClient
		      .get()
		      .uri(uriBuilder -> uriBuilder.path("/todos").queryParam("userId", userId).build())
		      .retrieve()
		      .bodyToMono(ArrayNode.class)
		      .block();
			
			Iterator<JsonNode> elementsFromJsonResponse = jsonResponse.elements();
			while (elementsFromJsonResponse.hasNext()) {
				JsonNode jsonNode = elementsFromJsonResponse.next();
				log.info("wartosc elementu - " 
				+ jsonNode.get("userId") + ", " + jsonNode.get("id") + ", " + jsonNode.get("title"));
			
				TodosDto todosDto = new TodosDto(currentDate, 
						jsonNode.get("userId").asInt(), 
						jsonNode.get("id").asInt(), 
						jsonNode.get("title").asText(), 
						jsonNode.get("completed").asBoolean());
				todoService.saveTodo(todosDto);
			}
		} catch(Exception exception) {
			log.error("" + exception.getMessage() != null ? exception.getMessage() : exception.getStackTrace()[0].toString());
		}
	}
	
}
