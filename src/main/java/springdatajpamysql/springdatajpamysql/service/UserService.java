package springdatajpamysql.springdatajpamysql.service;

import springdatajpamysql.springdatajpamysql.shared.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	//UserDto updateUser(UserDto userDto, Long Id);

}
