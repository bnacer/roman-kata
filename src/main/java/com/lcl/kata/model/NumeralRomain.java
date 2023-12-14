package com.lcl.kata.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * enum Roman Numeral correspondence between roman symbol and integer
 */
public enum NumeralRomain {
    I(1), IV(4), V(5), IX(9),
    X(10), XL(40), L(50), XC(90),
    C(100), CD(400), D(500), CM(900),
    M(1000);
    private int weight;

    NumeralRomain(int weight) {
        this.weight = weight;
    }

    /**
     * retrieve the number associated with the symbol
     * @param character the symbol
     * @return number associated with the symbol, 0 if empty
     */
    public static int weightOf(char character) {
        if (character == ' ') {
            return 0;
        }
        return NumeralRomain.valueOf(String.valueOf(character)).weight;
    }

    /**
     * returns an enumeration stream in order from largest to smallest (reverse order) (1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
     * @return stream of all enum entries.
     */
    public static Stream<NumeralRomain> stream() {
        return Arrays.stream(NumeralRomain.values()).sorted((o1, o2) -> o1.compareTo(o2)).sorted(Collections.reverseOrder());
    }
    public int getWeight(){
        return this.weight;
    }
}
