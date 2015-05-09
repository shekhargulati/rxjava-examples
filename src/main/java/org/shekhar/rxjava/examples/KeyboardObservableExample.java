package org.shekhar.rxjava.examples;

import rx.Observable;

import java.util.Scanner;

public class KeyboardObservableExample {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Observable<String> keyboardObservable = Observable.<String>create(subscriber -> {
            while (!subscriber.isUnsubscribed()) {
                String input = keyboard.nextLine();
                if (input.equals("exit")) {
                    subscriber.unsubscribe();
                } else {
                    subscriber.onNext(input);
                }
            }
        });
        keyboardObservable.subscribe(val -> System.out.println("received -> " + val));
        keyboardObservable.subscribe(val -> System.out.println("received -> " + val));
    }
}
