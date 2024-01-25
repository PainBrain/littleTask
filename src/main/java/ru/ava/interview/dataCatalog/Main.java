package ru.ava.interview.dataCatalog;

public class Main {
    public static void main(String[] args) {
        Matcher matcher = new MatcherImpl();
        boolean m = matcher.matches("\\d", "012345abc");
        System.out.println(m);
    }
}
