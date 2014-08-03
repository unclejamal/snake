package com.pduda.snake.acceptance.doubles;

import com.pduda.snake.domain.Tourney;
import com.pduda.snake.domain.TourneyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryTourneyRepository implements TourneyRepository {
    private Set<Tourney> tourneys = new HashSet<>();

    @Override
    public void add(Tourney tourney) {
        tourneys.add(tourney);
    }

    @Override
    public List<Tourney> findAll() {
        return new ArrayList(tourneys);
    }
}
