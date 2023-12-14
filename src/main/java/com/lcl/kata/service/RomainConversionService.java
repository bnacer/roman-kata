package com.lcl.kata.service;

import com.lcl.kata.model.NumeralRomain;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author Nacer Bouchentouf
 * Convert the Roman Numeral to numeral number and the other way.
 */
public class RomainConversionService {
    // logger
    private static final Logger logger = LogManager.getLogger(RomainConversionService.class);
   //Regex defining a roman number
    private static final String REGEX_EXPRESSION = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
    /**
     * convert an integer to roman format.
     * @param number : the number to convert to roman format
     * @return the roman value
     */
    public String toRoman(Integer number) {
        logger.info("start conversion");
        // some validations.
        Objects.requireNonNull(number);
        if (number == 0 || number > 3999) {
            logger.error("Invalid number format, value should be between [1,3999]");
            throw new IllegalArgumentException("Invalid number format, value should be between [1,3999]");
        }
        StringBuilder romains = new StringBuilder();
        // init atomic integer value
        final AtomicInteger atomicNumber = new AtomicInteger(number);

        NumeralRomain.stream().forEach(value -> {
            int times = atomicNumber.get() / value.getWeight(); // get the integer division, representing the number of repetition of the roman symbol
            romains.append(value.name().repeat(times)); // add the roman symbol and repeat as many n times
            atomicNumber.set(atomicNumber.get() % value.getWeight()); // get the rest of the division, corresponding to the modulo operation
        });
        // return the roman string resulted
        return romains.toString();
    }

    /**
     * convert the roman numeral to integer
     * @param romanNumeral the roman numeral to convert, should be valid format and not empty.
     * @return the whole number corresponding to the Roman numeral
     * @exception throw IllegalArgumentException if not a valid format
     */
    public Integer fromRoman(String romanNumeral) {
        // some validations
        Objects.requireNonNull(romanNumeral);
        // check if we have a correct expression
        if (!romanNumeral.matches(REGEX_EXPRESSION)) {
            logger.error("Invalid Roman format: {}", romanNumeral);
            throw new IllegalArgumentException("Invalid Roman format : " + romanNumeral);
        }
        logger.info("start conversion roman to integer");
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        IntStream.range(0, romanNumeral.length()).forEach(index -> {
            // get the character at the index
            char first = romanNumeral.charAt(index);
            // get the next character, empty otherwise
            char next = index < romanNumeral.length() - 1 ? romanNumeral.charAt(index + 1) : ' ';
            // if value of the preceding symbol is lower than the next -> subtraction operation, addition otherwise.
            if (NumeralRomain.weightOf(first) < NumeralRomain.weightOf(next)) {
                atomicInteger.set(atomicInteger.get() - NumeralRomain.weightOf(first));
            } else {
                atomicInteger.set(atomicInteger.get() + NumeralRomain.weightOf(first));
            }
        });
        return atomicInteger.get();
    }
}
