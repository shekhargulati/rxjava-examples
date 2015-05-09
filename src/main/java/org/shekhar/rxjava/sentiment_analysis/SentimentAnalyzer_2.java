package org.shekhar.rxjava.sentiment_analysis;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import rx.Observable;
import rx.observables.ConnectableObservable;
import twitter4j.Status;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SentimentAnalyzer_2 {

    private static final String[] NEGATIVE_WORDS = words("src/main/resources/negative_words.txt");
    private static final String[] POSITIVE_WORDS = words("src/main/resources/positive_words.txt");

    public static void main(String[] args) {
        ConnectableObservable<Status> observable = TweetObservable.tweetObservable(new String[]{"Salman Khan", "Messi"}).publish();
        observable.connect();
        Observable<String> tweetStream = observable.map(status -> status.getText());

        Observable<String> positiveTweets = tweetStream.filter(SentimentAnalyzer_2::isPositiveTweet);

        positiveTweets.window(30, TimeUnit.SECONDS).subscribe(val -> val.count().subscribe(count -> System.out.println("Positive tweets in 30 seconds " + count)));

        Observable<String> negativeTweets = tweetStream.filter(SentimentAnalyzer_2::isNegativeTweet);

        negativeTweets.window(30, TimeUnit.SECONDS).subscribe(val -> val.count().subscribe(count -> System.out.println("Negative tweets in 30 seconds " + count)));

        negativeTweets.filter(tweet -> tweet.toLowerCase().contains("bad")).window(1, TimeUnit.MINUTES).subscribe(val -> val.count().subscribe(count -> System.out.println("bad tweets per one minute " + count)));
    }

    private static boolean isNegativeTweet(String tweet) {
        String[] words = tweet.toLowerCase().split("\\s");
        return Arrays.stream(words).anyMatch(word -> ArrayUtils.contains(NEGATIVE_WORDS, word));
    }

    private static boolean isPositiveTweet(String tweet) {
        String[] words = tweet.toLowerCase().split("\\s");
        return Arrays.stream(words).anyMatch(word -> ArrayUtils.contains(POSITIVE_WORDS, word));
    }


    private static String[] words(String file) {
        try {
            return FileUtils.readLines(new File(file)).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
