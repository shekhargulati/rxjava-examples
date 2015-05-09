package org.shekhar.rxjava.collatz_sequence;

import rx.Observable;

import java.util.stream.IntStream;

public class CollatzMain {

    public static void main(String[] args) {
        Observable<Integer> observable = CollatzObservable.create(27);
        observable.doOnNext(val -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }).subscribe(val -> System.out.println(toStars(val)));
    }

    private static String toStars(int number){
        return IntStream.rangeClosed(1,number).mapToObj(val -> String.valueOf(val)).reduce("", (acc, val) -> acc + "*");
    }
}
