package org.katas.functional.kata01_composition;

import org.junit.jupiter.api.Disabled;

/**
 * Test para evaluar tu propia solución en {@link Kata01ComposicionExercise}.
 * Remueve la anotación @Disabled cuando comiences a resolver esta Kata.
 */
@Disabled("Remueve esta anotación cuando quieras probar tu implementación en Kata01ComposicionExercise")
public class Kata01ComposicionExerciseTest extends Kata01ComposicionTestBase {

    @Override
    protected Kata01ComposicionService getService() {
        return new Kata01ComposicionExercise();
    }
}
