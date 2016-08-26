package com.nullcognition.abstractions;

import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mms on 8/26/16.
 */

// chaining with async request

public class RxChaining {

  abstract class Api{
    abstract Single<String> getKey();
  }
  // answer for
    // http://stackoverflow.com/questions/29679801/chaining-rxjava-observables-with-callbacks-listeners/39156612#39156612
  public void testChaining(){
    Api api = new Api() {
      @Override Single<String> getKey() {
        return Single.just("apiKey");
      }
    };

    api.getKey().flatMap(new Func1<String, Single<String>>() {
      @Override public Single<String> call(String key) {
        return Single.create(new Single.OnSubscribe<String>() {
          @Override public void call(final SingleSubscriber<? super String> singleSubscriber) {
            Foo foo = new Foo();
            foo.setAwesomeCallback(new AwesomeCallback() {
              @Override public void onAwesomeReady(String awesome) {
                try {
                  singleSubscriber.onSuccess(awesome);
                } catch (Exception e) {
                  singleSubscriber.onError(e);
                }
              }
            });
            foo.makeAwesome();
          }
        });
      }
    }).flatMapCompletable(new Func1<String, Completable>() {
      @Override public Completable call(final String string) {
        return Completable.create(new Completable.CompletableOnSubscribe() {
          @Override public void call(Completable.CompletableSubscriber completableSubscriber) {
            try {
              sendAwesome(string);
              completableSubscriber.onCompleted();
            } catch (Exception e) {
              completableSubscriber.onError(e);
            }
          }
        });
      }
    })
        .subscribe(new Action0() {
          @Override public void call() {
            handleAwesomeSent();
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
          }
        });

    // lambda'd version

    //api.getKey()
    //    .flatMap(key -> Single.<String>create( singleSubscriber -> {
    //      Foo foo = new Foo();
    //      foo.setAwesomeCallback(awesome -> {
    //        try { singleSubscriber.onSuccess(awesome);}
    //        catch (Exception e) { singleSubscriber.onError(e); }
    //      });
    //      foo.makeAwesome();
    //
    //    }))
    //    .flatMapCompletable(
    //        string -> Completable.create(completableSubscriber -> {
    //          try {
    //            sendAwesome(string);
    //            completableSubscriber.onCompleted();
    //          } catch (Exception e) { completableSubscriber.onError(e); }
    //        }))
    //    .subscribe(this::handleAwesomeSent, throwable -> { });
  }

  public void sendAwesome(String string) {}

  public void send(String s) throws Exception{ }

  public void handleAwesomeSent(){}

  public class Foo {
    AwesomeCallback awesomeCallback;

    public void setAwesomeCallback(AwesomeCallback awesomeCallback) {
    }

    public void makeAwesome() {
    }
  }

  public interface AwesomeCallback {
    public void onAwesomeReady(String s);
  }
}