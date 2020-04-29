package springdatajpamysql.springdatajpamysql.controller;

import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springdatajpamysql.springdatajpamysql.exception.PlayerNotFoundException;
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
		Optional<PlayerEntity> playerEntity = playerRepository.findById(id);
		return playerEntity;
	}

	/*
	 * @GetMapping("{firstName}") public List<PlayerEntity>
	 * getStudents(@PathVariable String firstName) { return
	 * playerRepository.findByFirstName(firstName); }
	 */

	@PostMapping
	public ResponseEntity<PlayerResponseBody> createUser(@Valid @RequestBody PlayerRequestBody createPlayer) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PlayerDto bookDto = modelMapper.map(createPlayer, PlayerDto.class);
		PlayerDto createdBook = playerservice.createPlayer(bookDto);
		PlayerResponseBody userResponseBody = modelMapper.map(createdBook, PlayerResponseBody.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseBody);
	}

	@PutMapping("{id}")
	public Optional<Object> updateUser(@PathVariable Long id, @RequestBody PlayerRequestBody updatePlayer) {

		return playerRepository.findById(id).map(x -> {
			x.setFirstName(updatePlayer.getFirstName());
			x.setLastName(updatePlayer.getLastName());
			x.setLastUpdated(updatePlayer.getLastUpdated());
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

	@PatchMapping("{id}")
	public Optional<Object> updateUserSpecificColumn(@PathVariable Long id,
			@RequestBody PlayerRequestBody updatePlayer) {

		return playerRepository.findById(id).map(x -> {
			x.setFirstName(updatePlayer.getFirstName());
			return playerRepository.save(x);
		});
	}

	@DeleteMapping("/{id}")
	public PlayerEntity deleteBook(@PathVariable long id) throws ServiceNotFoundException {

		PlayerEntity playerEntity = playerRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
		playerRepository.deleteById(id);

		return playerEntity;
	}

}
