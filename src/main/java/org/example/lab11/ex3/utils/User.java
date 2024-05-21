package org.example.lab11.ex3.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.PrintWriter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Observer {
    private String name;
    @Setter
    private PrintWriter writer;
    public User(String name){
        this.name=name;
        this.writer=null;
    }

    public String toString(){
        return name;
    }
    @Override
    public void update(String user, String topic, String message){
        writer.println("["+name+"] "+user+" @ "+topic+": "+message);
    }

    @Override
    public void update(String user, String message) {
        writer.println("["+name+"] "+user+" : "+message);
    }
}
