package pduda.snake.manager.domain.model;

public interface TourneyRepository {
    void add(Tourney tourney);

    java.util.Set<Tourney> findAll();

    Tourney findTourneyByName(String szeligi);
}
