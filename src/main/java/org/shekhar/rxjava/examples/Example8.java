package org.shekhar.rxjava.examples;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.stream.LongStream;

public class Example8 {

    public static void main(String[] args) {
        Observable<Long> longObservable = Observable.<Long>create(subscriber -> {
            LongStream longStream = LongStream.iterate(1, val -> val + 1);
            longStream.forEach(val -> subscriber.onNext(val));
        }).subscribeOn(Schedulers.newThread());

        longObservable.take(10).subscribe(naturalNumber -> System.out.println(String.format("first {%s} -- {%s}" ,naturalNumber,Thread.currentThread().getName())));
        longObservable.skip(10).take(10).subscribe(naturalNumber -> System.out.println(String.format("second {%s} -- {%s}", naturalNumber, Thread.currentThread().getName())));
        longObservable.skip(20).take(10).subscribe(naturalNumber -> System.out.println(String.format("third {%s} -- {%s}", naturalNumber, Thread.currentThread().getName())));


        while(true){}
    }
}
