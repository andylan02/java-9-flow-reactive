package com.thepracticaldeveloper;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReactiveFlowApp {

  private static final int NUMBER_OF_MAGAZINES = 20;
  private static final long MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE = 2;
  private static final Logger log =
    LoggerFactory.getLogger(ReactiveFlowApp.class);

  public static void main(String[] args) throws Exception {
    final ReactiveFlowApp app = new ReactiveFlowApp();

    log.info("1.The subscribers are so fast that there are no problems related to buffering.");
    log.info("\n\n### CASE 1: Subscribers are fast, buffer size is not so " +
      "important in this case.");
    app.magazineDeliveryExample(100L, 100L, 8);

    log.info("2.One of the subscribers is very slow so that buffer starts getting full. However, the buffer is big enough to hold all the items so the subscriber doesn’t experience drops.");
    log.info("\n\n### CASE 2: A slow subscriber, but a good enough buffer " +
      "size on the publisher's side to keep all items until they're picked up");
    app.magazineDeliveryExample(1000L, 3000L, NUMBER_OF_MAGAZINES);

    log.info("3.One of the subscribers is very slow and the buffer is not big enough to hold all the items. In this case, the handler gets invoked in several times and the subscriber doesn’t receive all the items");
    log.info("\n\n### CASE 3: A slow subscriber, and a very limited buffer " +
      "size on the publisher's side so it's important to keep the slow " +
      "subscriber under control");
    app.magazineDeliveryExample(1000L, 3000L, 8);

  }

  void magazineDeliveryExample(final long sleepTimeJack,
                               final long sleepTimePete,
                               final int maxStorageInPO) throws Exception {
    final SubmissionPublisher<Integer> publisher =
      new SubmissionPublisher<>(ForkJoinPool.commonPool(), maxStorageInPO);

    final MagazineSubscriber jack = new MagazineSubscriber(
      sleepTimeJack,
      MagazineSubscriber.JACK
    );
    final MagazineSubscriber pete = new MagazineSubscriber(
      sleepTimePete,
      MagazineSubscriber.PETE
    );

    publisher.subscribe(jack);
    publisher.subscribe(pete);

    log.info("Printing 20 magazines per subscriber, with room in publisher for "
      + maxStorageInPO + ". They have " + MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE +
      " seconds to consume each magazine.");
    IntStream.rangeClosed(1, 20).forEach((number) -> {
      log.info("Offering magazine " + number + " to consumers");

      //Just with submit and buffer block with maxStorageInPO
      publisher.submit(number);

      //Use
      final int lag = publisher.offer(
        //The item to make available to subscribers.
        number,

        //The maximum amount of time to wait for each subscriber to pick that item (arguments two and three).
        MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE,
        TimeUnit.SECONDS,

        //A handler for us to control what happens if a given subscriber doesn’t get the item.
        // In our case, we send an error to that subscriber and, by returning false, we indicate we don’t want to retry and wait again.
        (subscriber, msg) -> {
          subscriber.onError(
            new RuntimeException("Hey " + ((MagazineSubscriber) subscriber)
              .getSubscriberName() + "! You are too slow getting magazines" +
              " and we don't have more space for them! " +
              "I'll drop your magazine: " + msg));
          return false; // don't retry, we don't believe in second opportunities
        });
      if (lag < 0) {
        //When a drop happens, the offer method returns a negative number.
        // Otherwise, it returns the estimated maximum number of items pending to be collected by the slowest subscriber (lag).
        // We just log that number to the console.
        log("Dropping " + -lag + " magazines");
      } else {
        log("The slowest consumer has " + lag +
          " magazines in total to be picked up");
      }
    });

    // Blocks until all subscribers are done (this part could be improved
    // with latches, but this way we keep it simple)
    while (publisher.estimateMaximumLag() > 0) {
      Thread.sleep(500L);
    }

    // Closes the publisher, calling the onComplete() method on every subscriber
    publisher.close();
    // give some time to the slowest consumer to wake up and notice
    // that it's completed
    Thread.sleep(Math.max(sleepTimeJack, sleepTimePete));
  }

  private static void log(final String message) {
    log.info("===========> " + message);
  }

}
