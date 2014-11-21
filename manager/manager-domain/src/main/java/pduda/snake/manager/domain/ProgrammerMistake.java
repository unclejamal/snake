package pduda.snake.manager.domain;

public class ProgrammerMistake extends RuntimeException {

    public ProgrammerMistake(Exception e) {
        super(e);
    }

    public ProgrammerMistake() {
        super();
    }
}
