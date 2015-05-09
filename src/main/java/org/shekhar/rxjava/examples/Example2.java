package org.shekhar.rxjava.examples;

import java.util.Iterator;

/**
 * Write a program that returns first n natural numbers starting from 1
 */
public class Example2 {

    public static void main(String[] args) {
        NaturalNumbers naturalNumbers = NaturalNumbers.naturalNumbers(100);
        for (Long naturalNumber : naturalNumbers) {
            System.out.println(naturalNumber);
        }
    }

    private static class NaturalNumbers implements Iterable<Long> {

        private final long n;

        private NaturalNumbers(long n) {
            this.n = n;
        }

        public static NaturalNumbers naturalNumbers(long n) {
            return new NaturalNumbers(n);
        }

        @Override
        public Iterator<Long> iterator() {
            return new NaturalNumberIterator(n);
        }

        private static class NaturalNumberIterator implements Iterator<Long> {
            private final long n;
            private long current = 0;

            public NaturalNumberIterator(long n) {
                this.n = n;
            }

            @Override
            public boolean hasNext() {
                return current < n;
            }

            @Override
            public Long next() {
                return ++current;
            }
        }
    }

}
