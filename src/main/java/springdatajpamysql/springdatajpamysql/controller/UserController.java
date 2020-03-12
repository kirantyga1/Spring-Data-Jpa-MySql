package springdatajpamysql.springdatajpamysql.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import springdatajpamysql.springdatajpamysql.model.UserEntity;
import springdatajpamysql.springdatajpamysql.model.UserRequestBody;
import springdatajpamysql.springdatajpamysql.model.UserResponseBody;
import springdatajpamysql.springdatajpamysql.repository.UserRepository;
import springdatajpamysql.springdatajpamysql.service.UserService;
import springdatajpamysql.springdatajpamysql.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping
	public List<UserEntity> getAllUsers() {
		return (List<UserEntity>) userRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<UserEntity> getStudents(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	@PostMapping
	public ResponseEntity<UserResponseBody> createUser(@RequestBody UserRequestBody createUser) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto bookDto = modelMapper.map(createUser, UserDto.class);
		UserDto createdBook = userService.createUser(bookDto);
		UserResponseBody userResponseBody = modelMapper.map(createdBook, UserResponseBody.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseBody);
	}

	@PutMapping("{id}")
	public Optional<Object> updateUser(@PathVariable Long id, @RequestBody UserRequestBody updateUser) {

		return userRepository.findById(id).map(x -> {
			x.setFirstName(updateUser.getFirstName());
			x.setLastName(updateUser.getLastName());
			x.setEmail(updateUser.getEmail());

			return userRepository.save(x);
		});
		/*
		 * ModelMapper modelMapper = new ModelMapper();
		 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
		 * ; UserDto userDto = modelMapper.map(updateUser, UserDto.class);
		 * updateUser.setFirstName(userDto.getFirstName());
		 * updateUser.setLastName(userDto.getLastName());
		 * updateUser.setEmail(userDto.getEmail());
		 * 
		 * UserDto updatedUser = userService.createUser(userDto); UserResponseBody
		 * userResponseBody = modelMapper.map(updatedUser, UserResponseBody.class);
		 * return ResponseEntity.status(HttpStatus.CREATED).body(userResponseBody);
		 */
	}

	@DeleteMapping("/{id}")
	public UserEntity deleteBook(@PathVariable long id) throws ServiceNotFoundException {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
		userRepository.deleteById(id);
		return userEntity;
	}

}
