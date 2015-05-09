package org.shekhar.rxjava.sentiment_analysis;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import rx.Observable;
import twitter4j.Status;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SentimentAnalyzer {

    private static final String[] NEGATIVE_WORDS = words("src/main/resources/negative_words.txt");
    private static final String[] POSITIVE_WORDS = words("src/main/resources/positive_words.txt");

    public static void main(String[] args) {
        Observable<Status> observable = TweetObservable.tweetObservable(new String[]{"Salman Khan", "Messi"});
        Observable<String> tweetStream = observable.map(status -> status.getText());

        File positives = new File("positives.txt");
        File negatives = new File("negatives.txt");

        Observable<String> positiveTweets = tweetStream.filter(tweet -> isPositiveTweet(tweet));
        positiveTweets.doOnNext(positiveTweet -> {
            try {
                FileUtils.write(positives, positiveTweet, true);
            } catch (IOException e) {
                // eat exception
            }
        }).subscribe(tweet -> System.out.println("Positive " + tweet));


        Observable<String> negativeTweets = tweetStream.filter(tweet -> isNegativeTweet(tweet));
        negativeTweets.doOnNext(negativeTweet -> {
            try {
                FileUtils.write(negatives, negativeTweet, true);
            } catch (IOException e) {
                // eat exception
            }
        }).subscribe(tweet -> System.out.println("Negative " + tweet));
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
