package pduda.snake.manager.main;

import pduda.snake.manager.web.JettySnakeServer;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        JettySnakeServer snakeServer = new JettySnakeServer();
        try {
            snakeServer.start();
            snakeServer.join();
        } finally {
            snakeServer.stop();
        }
    }
}
