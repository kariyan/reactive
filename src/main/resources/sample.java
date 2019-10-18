package com.example.reactive.chapter2.rx;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("ResultOfMethodCallIgnored")
public class RxObserver {

    public interface SearchEngine {
        List<URL> search(String query, int limit);
    }

    public interface IterableSearchEngine {
        Iterable<URL> search(String query, int limit);
    }

    public interface FutureSearchEngine {
        CompletableFuture<List<URL>> search(String query, int limit);
    }

    public interface RxSearchEngine {
        Observable<URL> search(String query);
    }

    public static void main(String[] args) {

        String query = "...";
        Observable.fromCallable(() -> doSlowSyncRequest(query))
                .subscribeOn(Schedulers.io())
                .subscribe(s -> log.info("success"));
    }

    private static Object processResult() {
        return null;
    }

    private static <T> T doSlowSyncRequest(String query) {
        return null;
    }

}
