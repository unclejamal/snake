package pduda.snake.manager.domain;

import java.util.*;

public class CollectionUtils {
    public static <T> Set<T> asSet(T... elements) {
        Set<T> toReturn = new HashSet<>();
        toReturn.addAll(Arrays.asList(elements));
        return toReturn;
    }

    public static <T> List<T> asList(T... elements) {
        List<T> toReturn = new ArrayList<>();
        toReturn.addAll(Arrays.asList(elements));
        return toReturn;
    }

    public static <T> T any(Collection<T> elements) {
        for (T element : elements) {
            return element;
        }

        throw new ProgrammerMistake();
    }
}
