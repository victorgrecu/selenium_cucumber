package org.training.selenium.cucumber.translate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class LanguageUtils {

    private final static Map<String, String> languageShortcuts = getLanguage();

    public static String getShortName(String longName){
        return languageShortcuts.get(longName);
    }

    private static Map<String, String> getLanguage() {
        Map<String, String> languages;
        try (InputStream is = LanguageUtils.class.getClassLoader().getResourceAsStream("language.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            languages = properties.entrySet().stream().collect(Collectors.toMap(k -> k.getKey().toString(), k -> k.getValue().toString(), (a, b) -> b));
            return languages;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
