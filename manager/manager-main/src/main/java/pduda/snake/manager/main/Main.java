package pduda.snake.manager.main;

import pduda.snake.manager.domain.model.Tourney;
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
            public void add(Tourney tourney) {

            }

            @Override
            public Set<Tourney> findAll() {
                return asSet(new Tourney("Tourney 1"), new Tourney("Tourney 2"));
            }

            @Override
            public Tourney findTourneyByName(String szeligi) {
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
