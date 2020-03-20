package springdatajpamysql.springdatajpamysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import springdatajpamysql.springdatajpamysql.model.PlayerEntity;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

	@Query(value = "SELECT * FROM player WHERE first_name = :firstName", nativeQuery = true)
	List<PlayerEntity> findByFirstName(String firstName);

}
