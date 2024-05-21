package org.example.lab11.ex3.utils;


public interface Observer {
    void update(String user, String message, String topic);
    void update(String user, String message);
}
