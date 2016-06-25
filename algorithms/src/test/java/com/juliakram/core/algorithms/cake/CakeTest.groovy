package com.juliakram.core.algorithms.cake

import spock.lang.Specification

class CakeTest extends Specification {

    def "run all those guys"() {
        given:
        Fibonacci.main();
        AppleStocks.main();
        IntervalUnion.main();
        TempTracker.main();
        WordCloud.main();
    }
}
