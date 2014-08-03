package com.pduda.snake.main;

import com.pduda.snake.domain.Tourney;
import com.pduda.snake.domain.TourneyRepository;
import com.pduda.snake.usecase.BrowseTourneys;
import com.pduda.snake.web.SnakeServer;

import java.util.Set;

import static com.pduda.snake.CollectionUtils.asSet;

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
        }));
        try {
            snakeServer.start();
            snakeServer.join();
        } finally {
            snakeServer.stop();
        }
    }
}
