package pduda.snake.manager.acceptance.doubles;

import pduda.snake.manager.domain.model.Tourney;
import pduda.snake.manager.domain.model.TourneyRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryTourneyRepository implements TourneyRepository {
    private final Set<Tourney> tourneys = new HashSet<>();

    @Override
    public void add(Tourney tourney) {
        tourneys.add(tourney);
    }

    @Override
    public Set<Tourney> findAll() {
        return new HashSet<>(tourneys);
    }

    @Override
    public Tourney findTourneyByName(String tourneyName) {
        return tourneys.stream()
                .filter(t -> tourneyName.equals(t.name()))
                .collect(Collectors.toList())
                .get(0);
    }
}
