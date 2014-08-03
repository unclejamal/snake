package pduda.snake.manager.acceptance.doubles;

import pduda.snake.manager.domain.model.Tourney;
import pduda.snake.manager.domain.model.TourneyRepository;

import java.util.HashSet;
import java.util.Set;

public class InMemoryTourneyRepository implements TourneyRepository {
    private Set<Tourney> tourneys = new HashSet<>();

    @Override
    public void add(Tourney tourney) {
        tourneys.add(tourney);
    }

    @Override
    public Set<Tourney> findAll() {
        return new HashSet<>(tourneys);
    }
}
