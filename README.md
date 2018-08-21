# Java 9 Flow Example - TPD.io

![The Reactive Magazine Publisher ](img/thereactivepublisher.png)

## Description

This is a simple project that shows how to use Java 9's Flow API to create a Reactive Programming example. It's based on a story: a Magazine Publisher with two different Subscribers, one of them being too slow to pick items. 

The guide [Reactive Programming with Java 9 Flow](https://thepracticaldeveloper.com/2018/01/31/reactive-programming-java-9-flow/) explains this code and the fictional use case with detail.

## Code Structure

* `ReactiveFlowApp` creates a Publisher using Java 9's `SubmissionPublisher`. It implements backpressure and dropping of items by setting a timeout for subscribers.
* `MagazineSubscriber` implements `Flow.Subscriber` and let you experiment what happens when subscribers are slow.
* `ColorConsoleAppender` has nothing to do with Java 9 or reactive programming, is just a utility class to set colors to log messages so the result can be understood easier.

## Feedback and Contribution

If you find something wrong, feel free to create an issue. Same if you have questions. If you want to help me creating more code examples you can [buy me a coffee](https://www.buymeacoffee.com/ZyLJNUR).



## About retry, when both Pete sbuscriber sleep and offer timeout is 3 second, retry =true here, would not drop magzine 13 here, which is retry to send
* return false; // don't retry, we don't believe in second opportunities
* return true;  //Add retry, we believe in second opportunities
## retry = true, publisher say would drop magazine 3, but infact it retry one time, so just drop 2 magazine
* [INFO]2018-08-21 15:43:08,045 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 13
* [INFO]2018-08-21 15:43:08,045 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:08,045 Offering magazine 14 to consumers
* [INFO]2018-08-21 15:43:08,076 <=========== [Jack] : I'll get another magazine now, next one should be: 13
* [INFO]2018-08-21 15:43:08,076 <=========== [Jack] : Great! I got a new magazine: 13
* [INFO]2018-08-21 15:43:09,089 <=========== [Jack] : I'll get another magazine now, next one should be: 14
* [INFO]2018-08-21 15:43:09,089 <=========== [Jack] : Great! I got a new magazine: 14
* [INFO]2018-08-21 15:43:10,091 <=========== [Jack] : I'll get another magazine now, next one should be: 15
* [INFO]2018-08-21 15:43:11,055 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 14
* [INFO]2018-08-21 15:43:11,056 <=========== [Pete] : I'll get another magazine now, next one should be: 6
* [INFO]2018-08-21 15:43:11,057 <=========== [Pete] : Great! I got a new magazine: 6
* [INFO]2018-08-21 15:43:11,057 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:43:11,058 Offering magazine 15 to consumers
* [INFO]2018-08-21 15:43:11,061 <=========== [Jack] : Great! I got a new magazine: 15
* [INFO]2018-08-21 15:43:11,062 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:11,063 Offering magazine 16 to consumers
* [INFO]2018-08-21 15:43:12,076 <=========== [Jack] : I'll get another magazine now, next one should be: 16
* [INFO]2018-08-21 15:43:12,076 <=========== [Jack] : Great! I got a new magazine: 16
* [INFO]2018-08-21 15:43:13,091 <=========== [Jack] : I'll get another magazine now, next one should be: 17
* [INFO]2018-08-21 15:43:14,061 <=========== [Pete] : I'll get another magazine now, next one should be: 7
* [INFO]2018-08-21 15:43:14,061 <=========== [Pete] : Great! I got a new magazine: 7
* [INFO]2018-08-21 15:43:14,061 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:14,061 Offering magazine 17 to consumers
* [INFO]2018-08-21 15:43:14,061 <=========== [Jack] : Great! I got a new magazine: 17
* [INFO]2018-08-21 15:43:15,073 <=========== [Jack] : I'll get another magazine now, next one should be: 18
* [INFO]2018-08-21 15:43:17,062 <=========== [Pete] : I'll get another magazine now, next one should be: 8
* [INFO]2018-08-21 15:43:17,062 <=========== [Pete] : Great! I got a new magazine: 8
* [INFO]2018-08-21 15:43:17,062 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:17,062 Offering magazine 18 to consumers
* [INFO]2018-08-21 15:43:17,062 <=========== [Jack] : Great! I got a new magazine: 18
* [INFO]2018-08-21 15:43:18,074 <=========== [Jack] : I'll get another magazine now, next one should be: 19
* [INFO]2018-08-21 15:43:20,075 <=========== [Pete] : I'll get another magazine now, next one should be: 9
* [INFO]2018-08-21 15:43:20,075 <=========== [Pete] : Great! I got a new magazine: 9
* [INFO]2018-08-21 15:43:20,075 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:20,075 Offering magazine 19 to consumers
* [INFO]2018-08-21 15:43:20,075 <=========== [Jack] : Great! I got a new magazine: 19
* [INFO]2018-08-21 15:43:21,089 <=========== [Jack] : I'll get another magazine now, next one should be: 20
* [INFO]2018-08-21 15:43:23,076 <=========== [Pete] : I'll get another magazine now, next one should be: 10
* [INFO]2018-08-21 15:43:23,079 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:43:23,079 <=========== [Pete] : Great! I got a new magazine: 10
* [INFO]2018-08-21 15:43:23,079 Offering magazine 20 to consumers
* [INFO]2018-08-21 15:43:23,081 <=========== [Jack] : Great! I got a new magazine: 20
* [INFO]2018-08-21 15:43:24,089 <=========== [Jack] : I'll get another magazine now, next one should be: 21
* [INFO]2018-08-21 15:43:26,091 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 20
* [INFO]2018-08-21 15:43:26,092 <=========== [Pete] : I'll get another magazine now, next one should be: 11
* [INFO]2018-08-21 15:43:26,092 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:43:26,092 <=========== [Pete] : Great! I got a new magazine: 11
* [INFO]2018-08-21 15:43:26,092 ===========> Jack finished
* [INFO]2018-08-21 15:43:29,094 <=========== [Pete] : I'll get another magazine now, next one should be: 12
* [INFO]2018-08-21 15:43:29,094 <=========== [Pete] : Great! I got a new magazine: 12
* [INFO]2018-08-21 15:43:32,096 <=========== [Pete] : I'll get another magazine now, next one should be: 13
* [INFO]2018-08-21 15:43:32,097 <=========== [Pete] : Great! I got a new magazine: 13
* [INFO]2018-08-21 15:43:35,111 <=========== [Pete] : I'll get another magazine now, next one should be: 14
* [INFO]2018-08-21 15:43:35,111 <=========== [Pete] : Oh no! I missed the magazine 14
* [INFO]2018-08-21 15:43:35,111 <=========== [Pete] : Great! I got a new magazine: 15
* [INFO]2018-08-21 15:43:38,125 <=========== [Pete] : I'll get another magazine now, next one should be: 16
* [INFO]2018-08-21 15:43:38,125 <=========== [Pete] : Great! I got a new magazine: 16
* [INFO]2018-08-21 15:43:41,127 <=========== [Pete] : I'll get another magazine now, next one should be: 17
* [INFO]2018-08-21 15:43:41,127 <=========== [Pete] : Great! I got a new magazine: 17
* [INFO]2018-08-21 15:43:44,128 <=========== [Pete] : I'll get another magazine now, next one should be: 18
* [INFO]2018-08-21 15:43:44,128 <=========== [Pete] : Great! I got a new magazine: 18
* [INFO]2018-08-21 15:43:47,144 <=========== [Pete] : I'll get another magazine now, next one should be: 19
* [INFO]2018-08-21 15:43:47,144 <=========== [Pete] : Great! I got a new magazine: 19
* [INFO]2018-08-21 15:43:47,144 ===========> Pete finished
* [INFO]2018-08-21 15:43:47,144 <=========== [Jack] : Finally! I completed the subscription, I got in total 20 magazines.
* [INFO]2018-08-21 15:43:50,159 <=========== [Pete] : I'll get another magazine now, next one should be: 20
* [INFO]2018-08-21 15:43:50,159 <=========== [Pete] : Finally! I completed the subscription, I got in total 18 magazines.

## retry = false, publisher say would drop magazine 5, and infact it not do retry action, so drop 5 magazine

* [INFO]2018-08-21 15:51:42,885 <=========== [Pete] : I'll get another magazine now, next one should be: 3
* [INFO]2018-08-21 15:51:42,885 <=========== [Pete] : Great! I got a new magazine: 3
* [INFO]2018-08-21 15:51:42,885 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 11
* [INFO]2018-08-21 15:51:42,885 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:51:42,885 Offering magazine 12 to consumers
* [INFO]2018-08-21 15:51:42,885 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:51:42,885 Offering magazine 13 to consumers
* [INFO]2018-08-21 15:51:42,932 <=========== [Jack] : I'll get another magazine now, next one should be: 7
* [INFO]2018-08-21 15:51:42,932 <=========== [Jack] : Great! I got a new magazine: 7
* [INFO]2018-08-21 15:51:43,937 <=========== [Jack] : I'll get another magazine now, next one should be: 8
* [INFO]2018-08-21 15:51:43,937 <=========== [Jack] : Great! I got a new magazine: 8
* [INFO]2018-08-21 15:51:44,938 <=========== [Jack] : I'll get another magazine now, next one should be: 9
* [INFO]2018-08-21 15:51:44,938 <=========== [Jack] : Great! I got a new magazine: 9
* [INFO]2018-08-21 15:51:45,890 <=========== [Pete] : I'll get another magazine now, next one should be: 4
* [INFO]2018-08-21 15:51:45,891 <=========== [Pete] : Great! I got a new magazine: 4
* [INFO]2018-08-21 15:51:45,891 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:51:45,892 Offering magazine 14 to consumers
* [INFO]2018-08-21 15:51:45,939 <=========== [Jack] : I'll get another magazine now, next one should be: 10
* [INFO]2018-08-21 15:51:45,940 <=========== [Jack] : Great! I got a new magazine: 10
* [INFO]2018-08-21 15:51:46,944 <=========== [Jack] : I'll get another magazine now, next one should be: 11
* [INFO]2018-08-21 15:51:46,944 <=========== [Jack] : Great! I got a new magazine: 11
* [INFO]2018-08-21 15:51:47,949 <=========== [Jack] : I'll get another magazine now, next one should be: 12
* [INFO]2018-08-21 15:51:47,949 <=========== [Jack] : Great! I got a new magazine: 12
* [INFO]2018-08-21 15:51:48,903 <=========== [Pete] : I'll get another magazine now, next one should be: 5
* [INFO]2018-08-21 15:51:48,903 <=========== [Pete] : Great! I got a new magazine: 5
* [INFO]2018-08-21 15:51:48,903 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 14
* [INFO]2018-08-21 15:51:48,903 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:51:48,903 Offering magazine 15 to consumers
* [INFO]2018-08-21 15:51:48,903 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:51:48,903 Offering magazine 16 to consumers
* [INFO]2018-08-21 15:51:48,950 <=========== [Jack] : I'll get another magazine now, next one should be: 13
* [INFO]2018-08-21 15:51:48,950 <=========== [Jack] : Great! I got a new magazine: 13
* [INFO]2018-08-21 15:51:49,952 <=========== [Jack] : I'll get another magazine now, next one should be: 14
* [INFO]2018-08-21 15:51:49,952 <=========== [Jack] : Great! I got a new magazine: 14
* [INFO]2018-08-21 15:51:50,966 <=========== [Jack] : I'll get another magazine now, next one should be: 15
* [INFO]2018-08-21 15:51:50,966 <=========== [Jack] : Great! I got a new magazine: 15
* [INFO]2018-08-21 15:51:51,910 <=========== [Pete] : I'll get another magazine now, next one should be: 6
* [INFO]2018-08-21 15:51:51,910 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 16
* [INFO]2018-08-21 15:51:51,910 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:51:51,910 <=========== [Pete] : Great! I got a new magazine: 6
* [INFO]2018-08-21 15:51:51,910 Offering magazine 17 to consumers
* [INFO]2018-08-21 15:51:51,910 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:51:51,910 Offering magazine 18 to consumers
* [INFO]2018-08-21 15:51:51,970 <=========== [Jack] : I'll get another magazine now, next one should be: 16
* [INFO]2018-08-21 15:51:51,970 <=========== [Jack] : Great! I got a new magazine: 16
* [INFO]2018-08-21 15:51:52,974 <=========== [Jack] : I'll get another magazine now, next one should be: 17
* [INFO]2018-08-21 15:51:52,974 <=========== [Jack] : Great! I got a new magazine: 17
* [INFO]2018-08-21 15:51:53,983 <=========== [Jack] : I'll get another magazine now, next one should be: 18
* [INFO]2018-08-21 15:51:53,983 <=========== [Jack] : Great! I got a new magazine: 18
* [INFO]2018-08-21 15:51:54,917 <=========== [Pete] : I'll get another magazine now, next one should be: 7
* [INFO]2018-08-21 15:51:54,917 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 18
* [INFO]2018-08-21 15:51:54,917 <=========== [Pete] : Great! I got a new magazine: 7
* [INFO]2018-08-21 15:51:54,917 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:51:54,917 Offering magazine 19 to consumers
* [INFO]2018-08-21 15:51:54,917 ===========> The slowest consumer has 9 magazines in total to be picked up
* [INFO]2018-08-21 15:51:54,917 Offering magazine 20 to consumers
* [INFO]2018-08-21 15:51:54,992 <=========== [Jack] : I'll get another magazine now, next one should be: 19
* [INFO]2018-08-21 15:51:54,992 <=========== [Jack] : Great! I got a new magazine: 19
* [INFO]2018-08-21 15:51:55,993 <=========== [Jack] : I'll get another magazine now, next one should be: 20
* [INFO]2018-08-21 15:51:55,993 <=========== [Jack] : Great! I got a new magazine: 20
* [INFO]2018-08-21 15:51:56,994 <=========== [Jack] : I'll get another magazine now, next one should be: 21
* [INFO]2018-08-21 15:51:57,928 <=========== [Pete] : I'll get another magazine now, next one should be: 8
* [INFO]2018-08-21 15:51:57,928 <=========== [Pete] : Oops I got an error from the Publisher: Hey Pete! You are too slow getting magazines and we don't have more space for them! I'll drop your magazine: 20
* [INFO]2018-08-21 15:51:57,928 <=========== [Pete] : Great! I got a new magazine: 8
* [INFO]2018-08-21 15:51:57,928 ===========> Dropping 1 magazines
* [INFO]2018-08-21 15:51:57,928 ===========> Jack finished
* [INFO]2018-08-21 15:52:00,928 <=========== [Pete] : I'll get another magazine now, next one should be: 9
* [INFO]2018-08-21 15:52:00,928 <=========== [Pete] : Great! I got a new magazine: 9
* [INFO]2018-08-21 15:52:03,940 <=========== [Pete] : I'll get another magazine now, next one should be: 10
* [INFO]2018-08-21 15:52:03,940 <=========== [Pete] : Great! I got a new magazine: 10
* [INFO]2018-08-21 15:52:06,941 <=========== [Pete] : I'll get another magazine now, next one should be: 11
* [INFO]2018-08-21 15:52:06,950 <=========== [Pete] : Oh no! I missed the magazine 11
* [INFO]2018-08-21 15:52:06,950 <=========== [Pete] : Great! I got a new magazine: 12
* [INFO]2018-08-21 15:52:09,964 <=========== [Pete] : I'll get another magazine now, next one should be: 13
* [INFO]2018-08-21 15:52:09,964 <=========== [Pete] : Great! I got a new magazine: 13
* [INFO]2018-08-21 15:52:12,965 <=========== [Pete] : I'll get another magazine now, next one should be: 14
* [INFO]2018-08-21 15:52:12,965 <=========== [Pete] : Oh no! I missed the magazine 14
* [INFO]2018-08-21 15:52:12,965 <=========== [Pete] : Great! I got a new magazine: 15
* [INFO]2018-08-21 15:52:15,979 <=========== [Pete] : I'll get another magazine now, next one should be: 16
* [INFO]2018-08-21 15:52:15,979 <=========== [Pete] : Oh no! I missed the magazine 16
* [INFO]2018-08-21 15:52:15,979 <=========== [Pete] : Great! I got a new magazine: 17
* [INFO]2018-08-21 15:52:18,986 <=========== [Pete] : I'll get another magazine now, next one should be: 18
* [INFO]2018-08-21 15:52:18,986 <=========== [Pete] : Oh no! I missed the magazine 18
* [INFO]2018-08-21 15:52:18,986 <=========== [Pete] : Great! I got a new magazine: 19
* [INFO]2018-08-21 15:52:21,998 <=========== [Pete] : I'll get another magazine now, next one should be: 20
* [INFO]2018-08-21 15:52:21,998 ===========> Pete finished
* [INFO]2018-08-21 15:52:21,998 <=========== [Pete] : Finally! I completed the subscription, I got in total 15 magazines.
* [INFO]2018-08-21 15:52:21,998 <=========== [Jack] : Finally! I completed the subscription, I got in total 20 magazines.
