package business;

import javax.management.InvalidAttributeValueException;
public class DisciplinaInvalidaException extends InvalidAttributeValueException {

    public DisciplinaInvalidaException() {
        super("Valores para disciplina inválidos!");
    }
}