package springdatajpamysql.springdatajpamysql.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdatajpamysql.springdatajpamysql.model.PlayerEntity;
import springdatajpamysql.springdatajpamysql.repository.PlayerRepository;
import springdatajpamysql.springdatajpamysql.shared.PlayerDto;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository userRepository;

	@Override
	public PlayerDto createUser(PlayerDto playerDto) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PlayerEntity userEntity = modelMapper.map(playerDto, PlayerEntity.class);
		userEntity.setPlayerId(UUID.randomUUID().toString());
		userEntity.setEncryptedpassword("test");
		userRepository.save(userEntity);
		PlayerDto returnedValue = modelMapper.map(userEntity, PlayerDto.class);

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
