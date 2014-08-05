package pduda.snake.manager.domain.usecase;

import pduda.snake.manager.domain.model.Tourney;
import pduda.snake.manager.domain.model.TourneyRepository;

public class RegisterTeams {
    private TourneyRepository repository;

    public RegisterTeams(TourneyRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterTeamsRequest request) {
        Tourney tourney = repository.findTourneyByName(request.tourneyName);
        tourney.registerTeams(request.teams);
    }
}
