package com.tss.FileHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHandling {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\om.kariya\\Documents\\tss swabhav training\\FileHandling\\FileHandling");
        if (file.exists()) {
            if (file.isFile()) {
                readFile(file);
            }
            else if (file.isDirectory()) {
               readDirectory(file);
            }
            else {
                System.out.println("path does not exist");
            }
        }
    }
    private static void readDirectory(File file){
        System.out.println("Reading directory"+ file.getName() );
        String[] names = file.list();
        if (names != null) {
            for (String name : names) {
                File file1 = new File(file, name);
                if (file1.isDirectory()) {
                    readDirectory(file1);
                } else {
                    readFile(file1);
                }
            }
        }
    }
    private static void readFile(File file){
        if (file.isFile()) {
            System.out.println("Reading : " + file.getName());
            int content;
            try (FileInputStream fis = new FileInputStream(file)) {
                while ((content = fis.read()) != -1) {
                    System.out.println((char) content);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
