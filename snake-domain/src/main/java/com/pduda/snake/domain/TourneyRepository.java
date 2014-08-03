package com.pduda.snake.domain;

import java.util.List;

public interface TourneyRepository {
    void add(Tourney tourney);

    List<Tourney> findAll();
}
