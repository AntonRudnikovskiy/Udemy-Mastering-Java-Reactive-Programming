package org.example.sec03.assignment;

import org.example.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceObserver<T> implements Subscriber<T> {
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private int balance = 1000;
    private int quantity;
    private Subscription subscription;

    public StockPriceObserver(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("Received {}", item);
        if ((int) item < 90 && balance > (int) item) {
            quantity++;
            balance = balance - (int) item;
            log.info("");
        } else if ((int) item > 110 && quantity > 0) {
            balance = balance + (quantity * (int) item);
            quantity = 0;
            subscription.cancel();
            log.info("Total profit: {}", balance);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("completed");
    }
}
