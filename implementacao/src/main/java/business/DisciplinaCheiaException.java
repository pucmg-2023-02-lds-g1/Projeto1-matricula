package business;

public class DisciplinaCheiaException extends Exception {
    public DisciplinaCheiaException() {
        super("A disciplina deve ter entre 3 a 60 alunos!");
    }
}