package org.shekhar.rxjava.examples;

import rx.Observable;
import rx.functions.Action1;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class PrimeNumbers {

    public static void main(String[] args) {
        System.out.println("Started at " + LocalTime.now());
        Observable<Long> primeNumbers = Observable.timer(5, 1, TimeUnit.SECONDS).skip(1).filter(number -> factors(number) == 0);

        Action1<Long> first = primeNumber -> System.out.println(LocalTime.now() + " first got " + primeNumber);
        Action1<Long> second = primeNumber -> System.out.println(LocalTime.now() + " second got " + primeNumber);
        Action1<Long> third = primeNumber -> System.out.println(LocalTime.now() + " third got " + primeNumber);

        primeNumbers.filter(pn -> pn < 10).subscribe(first);
        primeNumbers.filter(pn -> pn > 10 && pn < 50).subscribe(second);
        primeNumbers.filter(pn -> pn > 50).subscribe(third);

        while (true) {
        }


    }

    private static long factors(Long number) {
        return LongStream.range(2, number / 2 + 1).filter(divisor -> number % divisor == 0).count();
    }
}

