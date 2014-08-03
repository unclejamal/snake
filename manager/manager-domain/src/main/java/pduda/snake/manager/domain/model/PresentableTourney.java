package pduda.snake.manager.domain.model;

public class PresentableTourney {
    private String name;

    public PresentableTourney(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresentableTourney that = (PresentableTourney) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PresentableTourney{" +
                "name='" + name + '\'' +
                '}';
    }
}
