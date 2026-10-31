package org.katas.functional.kata01_composition;

import org.katas.functional.kata01_composition.solucion.Kata01ComposicionSolution;

public class Kata01ComposicionSolutionTest extends Kata01ComposicionTestBase {

    @Override
    protected Kata01ComposicionService getService() {
        return new Kata01ComposicionSolution();
    }
}
