package business;

import javax.management.InvalidAttributeValueException;
public class CobrancaInvalidaException extends InvalidAttributeValueException {

    public CobrancaInvalidaException() {
        super("Valores para cobrança inválidos!");
    }
}