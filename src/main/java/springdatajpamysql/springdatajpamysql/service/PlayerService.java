package springdatajpamysql.springdatajpamysql.service;

import springdatajpamysql.springdatajpamysql.shared.PlayerDto;

public interface PlayerService {

	PlayerDto createUser(PlayerDto playerDto);

	//UserDto updateUser(UserDto userDto, Long Id);

}
