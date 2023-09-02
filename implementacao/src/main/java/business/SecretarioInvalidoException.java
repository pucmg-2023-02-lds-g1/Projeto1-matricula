package business;

import javax.management.InvalidAttributeValueException;
public class SecretarioInvalidoException extends InvalidAttributeValueException {

    public SecretarioInvalidoException() {
        super("Valores para secretário inválidos!");
    }
}