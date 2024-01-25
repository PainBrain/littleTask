package ru.ava.interview.dataCatalog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


class LittleTaskTest {
    static private Matcher matcher;
    @BeforeAll
    static void setUp(){
     matcher = new MatcherImpl();
    }

    @ParameterizedTest
    @CsvSource({
     ", , false",
     "'', '', true",
     ", 30, false",
     "\\d+, , false",
     "\\d+, 30, true",
     "\\d+, 30, true",
     "\\d+, 3a0, false",
     "\\d\\w\\d, 3a0, true"
    })
    void testMatches( String regex, String text, boolean expectation ){
        assertEquals( expectation, matcher.matches( regex, text ) );
    }


}

