package org.shekhar.rxjava.examples;

import rx.Observable;
import rx.schedulers.Schedulers;

public class Example7 {

    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 10, Schedulers.newThread());
        observable.subscribe(System.out::println);
        observable.subscribe(System.out::println);

        while(true){}

    }
}
