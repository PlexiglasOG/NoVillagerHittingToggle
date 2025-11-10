package io.github.plexiglasog.novillagerhitting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NoVillagerHittingConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE =
            new File("config/novillagerhitting.json");

    public boolean blockVillagerHits = true; // default value

    public static NoVillagerHittingConfig load() {
        try {
            if (CONFIG_FILE.exists()) {
                try (FileReader reader = new FileReader(CONFIG_FILE)) {
                    return GSON.fromJson(reader, NoVillagerHittingConfig.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new NoVillagerHittingConfig();
    }

    public void save() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
            }
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
