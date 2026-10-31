package org.katas.functional.kata02_streams;

import org.katas.functional.kata02_streams.solucion.Kata02StreamsSolution;

public class Kata02StreamsSolutionTest extends Kata02StreamsTestBase {

    @Override
    protected Kata02StreamsService getService() {
        return new Kata02StreamsSolution();
    }
}
