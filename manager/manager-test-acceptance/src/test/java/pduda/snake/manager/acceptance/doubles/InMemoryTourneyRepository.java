package pduda.snake.manager.acceptance.doubles;

import pduda.snake.manager.domain.model.TourneyCreation;
import pduda.snake.manager.domain.model.TourneyRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryTourneyRepository implements TourneyRepository {
    private final Set<TourneyCreation> tourneyCreations = new HashSet<>();

    @Override
    public void add(TourneyCreation tourneyCreation) {
        tourneyCreations.add(tourneyCreation);
    }

    @Override
    public Set<TourneyCreation> findAll() {
        return new HashSet<>(tourneyCreations);
    }

    @Override
    public TourneyCreation findTourneyByName(String tourneyName) {
        return tourneyCreations.stream()
                .filter(t -> tourneyName.equals(t.name()))
                .collect(Collectors.toList())
                .get(0);
    }
}
