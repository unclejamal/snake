package pduda.snake.manager.acceptance;

import org.junit.Before;
import org.junit.Test;
import pduda.snake.manager.acceptance.doubles.InMemoryTourneyRepository;
import pduda.snake.manager.domain.model.PresentableTourney;
import pduda.snake.manager.domain.model.Tourney;
import pduda.snake.manager.domain.model.TourneyRepository;
import pduda.snake.manager.domain.usecase.BrowseTourneys;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static pduda.snake.manager.domain.CollectionUtils.asSet;

public class BrowseTourneysTest {

    private BrowseTourneys useCase;
    private TourneyRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryTourneyRepository();
        useCase = new BrowseTourneys(repository);
    }

    @Test
    public void browsingNoTourneys() {
        Set<PresentableTourney> presentableTourneys = useCase.execute();
        assertThat(presentableTourneys, empty());
    }

    @Test
    public void browsingManyTourneys() {
        repository.add(new Tourney("Szeligi"));
        repository.add(new Tourney("Blazejewko"));

        Set<PresentableTourney> presentableTourneys = useCase.execute();
        assertThat(presentableTourneys, is(asSet(
                new PresentableTourney("Szeligi"),
                new PresentableTourney("Blazejewko"))));
    }
}
