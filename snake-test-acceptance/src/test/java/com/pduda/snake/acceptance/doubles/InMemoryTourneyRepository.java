package com.pduda.snake.acceptance.doubles;

import com.pduda.snake.domain.Tourney;
import com.pduda.snake.domain.TourneyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InMemoryTourneyRepository implements TourneyRepository {
    private Set<Tourney> tourneys = new HashSet<>();

    @Override
    public void add(Tourney tourney) {
        tourneys.add(tourney);
    }

    @Override
    public Set<Tourney> findAll() {
        return new HashSet(tourneys);
    }
}
