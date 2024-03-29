package me.bristermitten.vouchers.config;

import me.bristermitten.mittenlib.config.Config;
import me.bristermitten.mittenlib.config.Source;
import me.bristermitten.mittenlib.config.names.NamingPattern;
import me.bristermitten.mittenlib.config.names.NamingPatterns;
import org.jetbrains.annotations.Nullable;

@Config
@Source("config.yml")
@NamingPattern(NamingPatterns.LOWER_KEBAB_CASE)
public class ClaimBoxesConfigDTO {
    StorageConfigDTO storage;
    GuiConfigDTO gui;
    ConfirmGUIDTO confirmGui;
    int updateInterval = 10;

    @Config
    public static class StorageConfigDTO {
        StorageType type;
        @Nullable DatabaseConfigDTO database;

        public enum StorageType {
            JSON,
            SQL
        }
    }

    @Config
    static class DatabaseConfigDTO {
        @Nullable String tablePrefix;
        String host;
        int port;
        String username;
        String password;
        String database;
    }

    @Config
    static class GuiConfigDTO {
        String title;
        ItemConfigDTO prevPage;
        ItemConfigDTO nextPage;
    }

    @Config
    static class ConfirmGUIDTO {
        String title;
        ItemConfigDTO confirm;
        ItemConfigDTO cancel;
        ItemConfigDTO background;
    }
}
