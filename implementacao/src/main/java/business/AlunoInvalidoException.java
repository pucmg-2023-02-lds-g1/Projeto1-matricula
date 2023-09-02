package business;

import javax.management.InvalidAttributeValueException;
public class AlunoInvalidoException extends InvalidAttributeValueException {

    public AlunoInvalidoException() {
        super("Valores para aluno inválidos!");
    }
}