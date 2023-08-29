package business;

import javax.management.InvalidAttributeValueException;
public class UsuarioInvalidoException extends InvalidAttributeValueException {

    public UsuarioInvalidoException(String message) {
        super(message);
    }
}