package business;

import javax.management.InvalidAttributeValueException;
public class CursoInvalidoException extends InvalidAttributeValueException {

    public CursoInvalidoException() {
        super("Valores para curso inválidos!");
    }
}