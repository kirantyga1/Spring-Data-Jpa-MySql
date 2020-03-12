package springdatajpamysql.springdatajpamysql.service;

import java.util.Optional;
import java.util.UUID;

import javax.management.ServiceNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdatajpamysql.springdatajpamysql.model.UserEntity;
import springdatajpamysql.springdatajpamysql.repository.UserRepository;
import springdatajpamysql.springdatajpamysql.shared.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		userDto.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userRepository.save(userEntity);
		UserDto returnedValue = modelMapper.map(userEntity, UserDto.class);

		return returnedValue;
	}

	/*
	 * @Override public UserDto updateUser(UserDto userDto, Long Id) {
	 * 
	 * Optional<UserEntity> userEntity = userRepository.findById(Id); ModelMapper
	 * modelMapper = new ModelMapper();
	 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
	 * ; userDto.setUserId(UUID.randomUUID().toString()); UserEntity userEntity1 =
	 * modelMapper.map(userDto, UserEntity.class); userRepository.save(userEntity1);
	 * UserDto returnedValue = modelMapper.map(userEntity1, UserDto.class);
	 * 
	 * return returnedValue; }
	 */

}
