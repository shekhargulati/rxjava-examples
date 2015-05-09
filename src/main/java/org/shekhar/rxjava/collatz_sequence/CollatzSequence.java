package org.shekhar.rxjava.collatz_sequence;

import rx.Observable;
import rx.Subscriber;

import java.util.stream.IntStream;

public class CollatzSequence {

    public static void main(String[] args) {
        Observable<Integer> observable = CollatzSequence.create(19);
        observable.doOnNext(val -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }).subscribe(val -> System.out.println(IntStream.rangeClosed(1, val).mapToObj(number -> String.valueOf(number)).reduce("", (acc, number) -> acc + "*")));
    }

    private static Observable<Integer> create(final int n) {
        return Observable.<Integer>create(subscriber -> {
            subscriber.onNext(n);
            sequence(subscriber, n);
        });
    }


    private static void sequence(Subscriber<? super Integer> subscriber, int number) {
        if (number == 1) {
            subscriber.onCompleted();
        } else if (number % 2 == 0) {
            int next = number / 2;
            subscriber.onNext(next);
            sequence(subscriber, next);
        } else {
            int next = number * 3 + 1;
            subscriber.onNext(next);
            sequence(subscriber, next);
        }
    }

}
