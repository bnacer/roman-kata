package com.lcl.kata.service;

import com.lcl.kata.model.NumeralRomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test roman conversion service
 */
class RomainConversionServiceTest {
    private RomainConversionService service;
    private final Map<Integer, String> romanNumeralExamples = Map.of(
            3, "III",
            12, "XII",
            49, "XLIX",
            33, "XXXIII",
            189, "CLXXXIX",
            249, "CCXLIX",
            459, "CDLIX",
            780, "DCCLXXX",
            2021, "MMXXI",
            3999, "MMMCMXCIX");
    @BeforeEach
    public void setUp() {
        this.service = new RomainConversionService();
    }

    @Test
    void shouldConvertIntegerToRomainsNumeral(){
        romanNumeralExamples.entrySet().forEach(entry -> assertEquals(service.toRoman(entry.getKey()), entry.getValue()));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenConvertToRoman(){
        assertThrows(IllegalArgumentException.class, () -> service.toRoman(0));
        assertThrows(IllegalArgumentException.class, () -> service.toRoman(4000));
        assertThrows(NullPointerException.class, () -> service.toRoman(null));
    }

    @Test
    void shouldConvertRomanNumeralToInteger(){
        romanNumeralExamples.entrySet().forEach(entry -> assertEquals(service.fromRoman(entry.getValue()), entry.getKey()));
    }

    @Test
    void shouldThrowExceptionWhenConvertFromRoman(){
        assertThrows(IllegalArgumentException.class, () -> service.fromRoman("FAKE_ROMAN"));
        assertThrows(IllegalArgumentException.class, () -> service.fromRoman(""));
        assertThrows(IllegalArgumentException.class, () -> service.fromRoman("IIIIV"));
        assertThrows(IllegalArgumentException.class, () -> service.fromRoman("MMMMMIIIIV"));
    }

    @Test
    void shouldGetAWeight(){
        assertEquals(500, NumeralRomain.weightOf('D'));
        assertThrows(IllegalArgumentException.class, () -> NumeralRomain.weightOf('H'));
    }

    @Test
    void shouldGetZeroForEmptySymbole(){
        assertEquals(0, NumeralRomain.weightOf(' '));
    }

    @Test
    void shouldTestTheInversedOrder(){
        assertArrayEquals(new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}, NumeralRomain.stream().map(o -> o.name()).toArray());
    }
}
