package org.shekhar.rxjava.collatz_sequence;

import rx.Observable;
import rx.Subscriber;

public class CollatzObservable {
    public static Observable<Integer> create(final int n) {
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
