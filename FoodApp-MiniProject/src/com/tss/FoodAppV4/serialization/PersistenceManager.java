package com.tss.FoodAppV4.serialization;

import java.io.*;

public class PersistenceManager {

    private static final String DATA_DIR = "dataV4/";

    public static void save(String filename, Object data) {
        try {
           File file = new File(DATA_DIR);
           file.mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_DIR + filename))) {
                oos.writeObject(data);
                System.out.println("💾 Saved: " + filename);
                System.out.println("📁 File path: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("❌ Save failed: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(String filename, T defaultValue) {
        File file = new File(DATA_DIR + filename);
        if (!file.exists()) return defaultValue;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            System.out.println("📂 Loaded: " + filename);
            System.out.println("📁 File path: " + file.getAbsolutePath());

            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ Load failed, starting fresh: " + e.getMessage());
            return defaultValue;
        }
    }
}