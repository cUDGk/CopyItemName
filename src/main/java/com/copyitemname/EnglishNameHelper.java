package com.copyitemname;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EnglishNameHelper {
    private static final Map<String, String> ENGLISH = new HashMap<>();

    public static void loadTranslations(ResourceManager manager) {
        ENGLISH.clear();
        Map<Identifier, Resource> resources = manager.findResources("lang",
                id -> id.getPath().endsWith("en_us.json"));

        for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
            try (InputStream is = entry.getValue().getInputStream()) {
                JsonObject json = JsonParser.parseReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
                for (Map.Entry<String, JsonElement> e : json.entrySet()) {
                    if (e.getValue().isJsonPrimitive()) {
                        ENGLISH.put(e.getKey(), e.getValue().getAsString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getEnglishName(String translationKey) {
        return ENGLISH.getOrDefault(translationKey, translationKey);
    }
}
