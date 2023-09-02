package business;

import javax.management.InvalidAttributeValueException;
public class UsuarioInvalidoException extends InvalidAttributeValueException {

    public UsuarioInvalidoException() {
        super("Valores para usuário inválidos!");
    }
}