package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null, email = null;
        int age = 0;
        long phone = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {
            String line = bufferedReader.readLine();
            final String nameLabel = "Name: ";
            final String ageLabel = "Age: ";
            final String emailLabel = "Email: ";
            final String phoneLabel = "Phone: ";
            while (line != null) {
                if (line.startsWith(nameLabel)) {
                    name = line.substring(nameLabel.length());
                } else if (line.startsWith(ageLabel)) {
                    age = Integer.parseInt(line.substring(ageLabel.length()));
                } else if (line.startsWith(emailLabel)) {
                    email = line.substring(emailLabel.length());
                } else {
                    phone = Long.parseLong(line.substring(phoneLabel.length()));
                }
                line = bufferedReader.readLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("not a number");
            return null;
        } catch (IOException e) {
            System.out.println("error");
            return null;
        }
        return new Profile(name, age, email, phone);
    }
}
