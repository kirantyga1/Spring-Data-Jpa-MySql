package springdatajpamysql.springdatajpamysql.exception;

public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2939141488729399476L;

	public PlayerNotFoundException(Long id) {
		super("Book id not found : " + id);
	}

}