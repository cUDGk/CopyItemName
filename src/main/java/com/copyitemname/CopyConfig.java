package com.copyitemname;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("CopyItemName");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "copyitemname.json");

    private static CopyConfig INSTANCE;

    public CopyMode copyMode = CopyMode.ENGLISH_NAME;

    public static CopyConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = load();
        }
        return INSTANCE;
    }

    public static CopyConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                CopyConfig config = GSON.fromJson(json, CopyConfig.class);
                if (config != null) {
                    LOGGER.info("[CopyItemName] Loaded config: mode={}", config.copyMode);
                    return config;
                }
            } catch (Exception e) {
                LOGGER.error("[CopyItemName] Failed to load config", e);
            }
        }
        CopyConfig config = new CopyConfig();
        config.save();
        return config;
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(this));
        } catch (IOException e) {
            LOGGER.error("[CopyItemName] Failed to save config", e);
        }
    }
}
