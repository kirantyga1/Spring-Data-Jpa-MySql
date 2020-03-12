package springdatajpamysql.springdatajpamysql.controller;

import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springdatajpamysql.springdatajpamysql.model.PlayerEntity;
import springdatajpamysql.springdatajpamysql.model.PlayerRequestBody;
import springdatajpamysql.springdatajpamysql.model.PlayerResponseBody;
import springdatajpamysql.springdatajpamysql.repository.PlayerRepository;
import springdatajpamysql.springdatajpamysql.service.PlayerService;
import springdatajpamysql.springdatajpamysql.shared.PlayerDto;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayerService playerservice;

	@GetMapping
	public List<PlayerEntity> getAllplayers() {
		return (List<PlayerEntity>) playerRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<PlayerEntity> getStudents(@PathVariable Long id) {
		return playerRepository.findById(id);
	}

	@PostMapping
	public ResponseEntity<PlayerResponseBody> createUser(@RequestBody PlayerRequestBody createPlayer) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PlayerDto bookDto = modelMapper.map(createPlayer, PlayerDto.class);
		PlayerDto createdBook = playerservice.createUser(bookDto);
		PlayerResponseBody userResponseBody = modelMapper.map(createdBook, PlayerResponseBody.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseBody);
	}

	@PutMapping("{id}")
	public Optional<Object> updateUser(@PathVariable Long id, @RequestBody PlayerRequestBody updatePlayer) {

		return playerRepository.findById(id).map(x -> {
			x.setFirstName(updatePlayer.getFirstName());
			x.setLastName(updatePlayer.getLastName());
			x.setEmail(updatePlayer.getEmail());

			return playerRepository.save(x);
		});
		/*
		 * ModelMapper modelMapper = new ModelMapper();
		 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
		 * ; UserDto userDto = modelMapper.map(updateUser, UserDto.class);
		 * updateUser.setFirstName(userDto.getFirstName());
		 * updateUser.setLastName(userDto.getLastName());
		 * updateUser.setEmail(userDto.getEmail());
		 * 
		 * UserDto updatedUser = playerservice.createUser(userDto); UserResponseBody
		 * userResponseBody = modelMapper.map(updatedUser, UserResponseBody.class);
		 * return ResponseEntity.status(HttpStatus.CREATED).body(userResponseBody);
		 */
	}

	@DeleteMapping("/{id}")
	public PlayerEntity deleteBook(@PathVariable long id) throws ServiceNotFoundException {
		PlayerEntity userEntity = playerRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
		playerRepository.deleteById(id);
		return userEntity;
	}

}
