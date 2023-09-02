package business;

import javax.management.InvalidAttributeValueException;
public class ProfessorInvalidoException extends InvalidAttributeValueException {

    public ProfessorInvalidoException() {
        super("Valores para professor inválidos!");
    }
}