package org.shekhar.rxjava.examples;

import rx.Observable;

public class Example6 {

    public static void main(String[] args) {
        Observable<String> xlteam = Observable.just("shekhar", "sameer", "aditya", "ankur");


        xlteam.subscribe(name -> System.out.println("first .. I met " + name), error -> System.out.println("error " + error), () -> System.out.println("********first completed*******"));


        xlteam.subscribe(name -> System.out.println("second .. I met " + name),error -> System.out.println("error " + error), () -> System.out.println("********second completed*******"));

        Observable<String> xlteamWithS = xlteam.map(String::toUpperCase).filter(name -> name.startsWith("S"));
        xlteamWithS.subscribe(name -> System.out.println("I met XL team member with name starting with S. He is " + name),error -> System.out.println("error " + error), () -> System.out.println("********third completed*******"));

        xlteam.mergeWith(xlteamWithS).subscribe(name -> System.out.println("I met all... " + name));
    }
}
