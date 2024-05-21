package org.example.lab11.ex3.utils;

public interface Observable {
    void subscribe(User user, String topic);

    void unsubscribe(User user, String topic);

    void notify(User notifier, String topic, String message);

    void notify(User notifier, String message);
}
