package pduda.snake.manager.acceptance;

import org.junit.Before;
import org.junit.Test;
import pduda.snake.manager.acceptance.doubles.InMemoryTourneyRepository;
import pduda.snake.manager.domain.model.Tourney;
import pduda.snake.manager.domain.model.TourneyRepository;
import pduda.snake.manager.domain.usecase.RegisterTeams;
import pduda.snake.manager.domain.usecase.RegisterTeamsRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static pduda.snake.manager.domain.CollectionUtils.asSet;

public class RegisterTeamsTest {

    private RegisterTeams useCase;
    private TourneyRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryTourneyRepository();
        useCase = new RegisterTeams(repository);
    }

    @Test
    public void registeringASingleTeam() {
        repository.add(new Tourney("Szeligi"));

        useCase.execute(
                new RegisterTeamsRequest(
                        "Szeligi",
                        asSet(
                                new RegisterTeamsRequest.Team("Team A"),
                                new RegisterTeamsRequest.Team("Team B")
                        )
                )
        );

        Tourney expected = new Tourney("Szeligi");
        expected.registerTeams(asSet(
                new RegisterTeamsRequest.Team("Team A"),
                new RegisterTeamsRequest.Team("Team B")
        ));
        assertThat(repository.findTourneyByName("Szeligi"), equalTo(expected));
    }

}
