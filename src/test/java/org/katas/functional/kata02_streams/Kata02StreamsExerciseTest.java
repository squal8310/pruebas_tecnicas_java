package org.katas.functional.kata02_streams;

import org.junit.jupiter.api.Disabled;

/**
 * Test para evaluar tu propia solución en {@link Kata02StreamsExercise}.
 * Remueve la anotación @Disabled cuando comiences a resolver esta Kata.
 */
@Disabled("Remueve esta anotación cuando quieras probar tu implementación en Kata02StreamsExercise")
public class Kata02StreamsExerciseTest extends Kata02StreamsTestBase {

    @Override
    protected Kata02StreamsService getService() {
        return new Kata02StreamsExercise();
    }
}
