package ru.ava.interview.dataCatalog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class LittleTaskTest {
    static Matcher matcher;
    @BeforeAll
    public static void setUp() throws Exception {
     matcher = new MatcherImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "\\d+, 30, true",
            "\\d+, 3a0, true",
            "\\d\\w\\d, 3a0, true"
    })
    public void testMatches(String regex, String text, boolean expectation){
        boolean result = matcher.matches(regex, text);
        assertEquals(expectation, result);
    }


}

