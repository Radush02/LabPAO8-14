package org.example.lab12.ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GitInit {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java Init calea/catre/director");
            System.exit(1);
        }
        String dir = args[0];
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            if (!dirFile.mkdirs()) {
                System.err.println("Eroare la crearea directorului");
                System.exit(1);
            }
        }
        File git = new File(dir + "/.git");
        if (!git.exists()) {
            if (!git.mkdir()) {
                System.err.println("Eroare la crearea directorului .git");
                System.exit(1);
            }
        }
        File objects = new File(git + "/objects");
        if (!objects.exists()) {
            if (!objects.mkdir()) {
                System.err.println("Eroare la crearea directorului objects");
                System.exit(1);
            }
        }
        File refs = new File(git + "/refs");
        if (!refs.exists()) {
            if (!refs.mkdir()) {
                System.err.println("Eroare la crearea directorului refs");
                System.exit(1);
            }
        }
        File headFile = new File(git, "HEAD");
        try (FileWriter writer = new FileWriter(headFile)) {
            writer.write("ref: refs/heads/main\n");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea in fisier");
            System.exit(1);
        }
        System.out.println("Repository initializat cu succes " + git.getAbsolutePath());
    }
}
