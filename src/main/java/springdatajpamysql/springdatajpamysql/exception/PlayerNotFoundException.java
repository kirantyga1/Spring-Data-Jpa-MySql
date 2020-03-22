package springdatajpamysql.springdatajpamysql.exception;

public class PlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2939141488729399476L;

	public PlayerNotFoundException(String message) {
		super("Player id not found : " + message);
	}

}
