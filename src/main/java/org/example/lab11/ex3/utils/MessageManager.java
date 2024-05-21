package org.example.lab11.ex3.utils;

import java.io.PrintWriter;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageManager implements Observable {
    private Map<String, List<User>> userTopics = new HashMap<>();
    private List<User> users = new ArrayList<>();

    @Override
    public void subscribe(User user, String topic) {
        System.out.println(user + " s-a abonat la " + topic);
        userTopics.computeIfAbsent(topic, k -> new ArrayList<>()).add(user);
    }

    @Override
    public void unsubscribe(User user, String topic) {
        System.out.println(user + " s-a dezabonat de la " + topic);
        List<User> users = userTopics.get(topic);
        if (users != null) {
            users.removeIf(u -> u.getName().equals(user.getName()));
        }
    }

    @Override
    public void notify(User notifier, String topic, String message) {
        System.out.println(
                "Userii din topicul"
                        + topic
                        + " au fost notificati de "
                        + notifier
                        + ": "
                        + message);
        List<User> users = userTopics.get(topic);
        if (users != null) {
            for (User u : users) {
                if (!Objects.equals(u, notifier)) {
                    u.update(notifier.getName(), topic, message);
                }
            }
        } else {
            System.out.println("Nu e nimeni abonat la " + topic);
        }
    }

    @Override
    public void notify(User notifier, String message) {
        System.out.println("Userii au fost notificati de " + notifier + ": " + message);
        for (User u : users) {
            if (!Objects.equals(u, notifier)) {
                u.update(notifier.getName(), message);
            }
        }
    }

    public void addWriter(User user, PrintWriter writer) {
        System.out.println(user + " s-a logat");
        user.setWriter(writer);
        users.add(user);
    }

    public void removeWriter(User user) {
        System.out.println(user + " s-a deconectat");
        user.setWriter(null);
        users.remove(user);
    }
}
