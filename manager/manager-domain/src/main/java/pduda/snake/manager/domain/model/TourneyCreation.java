package pduda.snake.manager.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pduda.snake.manager.domain.usecase.RegisterTeamsRequest;

import java.util.Set;

public class TourneyCreation {
    private String name;
    private Set<RegisterTeamsRequest.Team> teams;

    public TourneyCreation(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void registerTeams(Set<RegisterTeamsRequest.Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
