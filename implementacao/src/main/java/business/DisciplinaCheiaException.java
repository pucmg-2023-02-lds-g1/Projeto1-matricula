package business;

public class DisciplinaCheiaException extends Exception {
    public DisciplinaCheiaException() {
        super("A disciplina já com o máximo de alunos possível!");
    }
}