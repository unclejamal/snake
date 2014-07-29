package com.pduda.snake.main;

import com.pduda.snake.web.SnakeServer;

public class Main {
    public static void main(String[] args) {
            new Main().start();
    }

    private void start() {
        final SnakeServer timeExpert = new SnakeServer();
        try {
            timeExpert.start();
            timeExpert.join();
        } finally {
            timeExpert.stop();
        }
    }
}
