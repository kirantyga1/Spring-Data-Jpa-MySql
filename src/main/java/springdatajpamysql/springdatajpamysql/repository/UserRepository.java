package springdatajpamysql.springdatajpamysql.repository;

import org.springframework.data.repository.CrudRepository;

import springdatajpamysql.springdatajpamysql.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
