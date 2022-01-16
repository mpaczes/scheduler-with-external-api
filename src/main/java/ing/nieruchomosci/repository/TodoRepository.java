package ing.nieruchomosci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ing.nieruchomosci.model.CountersForUser;
import ing.nieruchomosci.model.TodosEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodosEntity, Long> {
	
	List<TodosEntity> findByTodoUserId(Integer todoUserId);
	List<TodosEntity> findAllByDownloadDate(Date downloadDate);
	List<TodosEntity> findAllByDownloadDateBetween(Date downloadDateStart, Date downloadDateEnd);

    @Query("select a from TodosEntity a where a.downloadDate <= :downloadDate")
    List<TodosEntity> findAllWithDownloadDateBefore(@Param("downloadDate") Date downloadDate);
    
    @Query("select new ing.nieruchomosci.model.CountersForUser(count(a.todoUserId)) from TodosEntity a where a.todoUserId = :userId")
    CountersForUser countUserRecords(@Param("userId") Integer userId);
	
}
