package pduda.snake.manager.main;

import pduda.snake.manager.domain.model.TourneyCreation;
import pduda.snake.manager.domain.model.TourneyRepository;
import pduda.snake.manager.domain.usecase.BrowseTourneys;
import pduda.snake.manager.web.SnakeServer;

import java.util.Set;

import static pduda.snake.manager.domain.CollectionUtils.asSet;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        SnakeServer snakeServer = new SnakeServer(new BrowseTourneys(new TourneyRepository() {
            @Override
            public void add(TourneyCreation tourney) {

            }

            @Override
            public Set<TourneyCreation> findAll() {
                return asSet(new TourneyCreation("Tourney 1"), new TourneyCreation("Tourney 2"));
            }

            @Override
            public TourneyCreation findTourneyByName(String szeligi) {
                return null;
            }
        }));
        try {
            snakeServer.start();
            snakeServer.join();
        } finally {
            snakeServer.stop();
        }
    }
}
