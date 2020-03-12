package springdatajpamysql.springdatajpamysql.exception;

import java.util.Set;

public class PlayerUnSupportedFieldPatchException extends RuntimeException {

    public PlayerUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
