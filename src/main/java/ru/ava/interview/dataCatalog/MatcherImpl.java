package ru.ava.interview.dataCatalog;

import java.util.regex.Pattern;

public class MatcherImpl implements Matcher {
    public boolean matches(String regex, String text) {
        Boolean result = Pattern.compile( regex ).matcher( text ).matches();
        return result;
    }
}
