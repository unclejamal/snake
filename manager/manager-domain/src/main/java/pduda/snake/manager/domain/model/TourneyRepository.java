package pduda.snake.manager.domain.model;

public interface TourneyRepository {
    void add(TourneyCreation tourneyCreation);

    java.util.Set<TourneyCreation> findAll();

    TourneyCreation findTourneyByName(String szeligi);
}
