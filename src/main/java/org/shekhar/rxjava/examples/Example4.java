package org.shekhar.rxjava.examples;

import java.util.stream.LongStream;

public class Example4 {

    public static void main(String[] args) {
        NaturalNumbersStream.naturalNumbers().forEach(System.out::println);
    }

    private static class NaturalNumbersStream {
        public static LongStream naturalNumbers() {
            return LongStream.iterate(1, val -> val + 1);
        }
    }
}
