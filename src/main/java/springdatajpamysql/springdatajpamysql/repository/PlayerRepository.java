package springdatajpamysql.springdatajpamysql.repository;

import org.springframework.data.repository.CrudRepository;

import springdatajpamysql.springdatajpamysql.model.PlayerEntity;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

}
