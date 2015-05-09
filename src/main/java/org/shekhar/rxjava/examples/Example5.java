package org.shekhar.rxjava.examples;

import rx.Observable;

public class Example5 {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(subscriber -> subscriber.onNext("Hello world"));

        observable.subscribe(System.out::println);

    }
}
