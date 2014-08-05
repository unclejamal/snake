package pduda.snake.manager.domain.usecase;

import pduda.snake.manager.domain.model.TourneyCreation;
import pduda.snake.manager.domain.model.TourneyRepository;

public class RegisterTeams {
    private TourneyRepository repository;

    public RegisterTeams(TourneyRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterTeamsRequest request) {
        TourneyCreation tourneyCreation = repository.findTourneyByName(request.tourneyName);
        tourneyCreation.registerTeams(request.teams);
    }
}
