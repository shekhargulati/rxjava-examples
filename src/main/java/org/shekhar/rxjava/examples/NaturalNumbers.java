package org.shekhar.rxjava.examples;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.stream.IntStream;

public class NaturalNumbers {

    public static void main(String[] args) {
        Observable<Integer> naturalNumbers = Observable.<Integer>create(subscriber -> {
            IntStream stream = IntStream.iterate(1, val -> val + 1);
            stream.peek(val -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }).forEach(subscriber::onNext);
        }).subscribeOn(Schedulers.newThread());

        Action1<Integer> first = naturalNumber -> System.out.println("First got " + naturalNumber);
        Action1<Integer> second = naturalNumber -> System.out.println("Second got " + naturalNumber);
        Action1<Integer> third = naturalNumber -> System.out.println("Third got " + naturalNumber);
        naturalNumbers.subscribe(first);
        naturalNumbers.subscribe(second);
        naturalNumbers.subscribe(third);

        while (true) {
        }

    }
}
