package com.pduda.snake.domain;

import java.util.List;

public interface TourneyRepository {
    void add(Tourney tourney);

    java.util.Set<Tourney> findAll();
}
