package pduda.snake.manager.domain.usecase;

import pduda.snake.manager.domain.model.PresentableTourney;
import pduda.snake.manager.domain.model.TourneyRepository;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class BrowseTourneys {
    private TourneyRepository tourneyRepository;

    public BrowseTourneys(TourneyRepository tourneyRepository) {
        this.tourneyRepository = tourneyRepository;
    }

    public Set<PresentableTourney> execute() {
        return tourneyRepository.findAll()
                .stream()
                .map(t -> new PresentableTourney(t.name()))
                .collect(toSet());
    }


}
