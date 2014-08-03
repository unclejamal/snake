package com.pduda.snake.acceptance;

import com.pduda.snake.acceptance.doubles.InMemoryTourneyRepository;
import com.pduda.snake.domain.PresentableTourney;
import com.pduda.snake.domain.Tourney;
import com.pduda.snake.domain.TourneyRepository;
import com.pduda.snake.usecase.BrowseTourneys;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.pduda.snake.CollectionUtils.asList;
import static com.pduda.snake.CollectionUtils.asSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

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
    public void browsingManyTourneysInTheChronologicalOrderOfAdding() {
        repository.add(new Tourney("Szeligi"));
        repository.add(new Tourney("Blazejewko"));

        Set<PresentableTourney> presentableTourneys = useCase.execute();
        assertThat(presentableTourneys, is(asSet(
                new PresentableTourney("Szeligi"),
                new PresentableTourney("Blazejewko"))));
    }
}
