package org.shekhar.rxjava.examples;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Example9 {

    public static void main(String[] args) {
        Observable<Long> naturalNumbers = Observable.timer(0, 100, TimeUnit.MILLISECONDS).skip(1);


        naturalNumbers.take(10).map(val -> factorial(val)).subscribe(val -> System.out.println(String.format("first %s %s", val, Thread.currentThread().getName())));
        naturalNumbers.take(10).map(val -> val + 10).subscribe(val -> System.out.println(String.format("second %s %s", val, Thread.currentThread().getName())));

    }

    private static long factorial(Long number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
