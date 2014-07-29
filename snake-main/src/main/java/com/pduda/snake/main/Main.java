package com.pduda.snake.main;

import com.pduda.snake.web.SnakeServer;

public class Main {
    public static void main(String[] args) {
            new Main().start();
    }

    private void start() {
        final SnakeServer snakeServer = new SnakeServer();
        try {
            snakeServer.start();
            snakeServer.join();
        } finally {
            snakeServer.stop();
        }
    }
}
