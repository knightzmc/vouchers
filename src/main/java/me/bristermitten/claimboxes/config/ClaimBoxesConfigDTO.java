package me.bristermitten.claimboxes.config;

import me.bristermitten.mittenlib.config.Config;
import me.bristermitten.mittenlib.config.Source;
import me.bristermitten.mittenlib.config.names.NamingPattern;
import me.bristermitten.mittenlib.config.names.NamingPatterns;
import org.bukkit.Material;

@Config
@Source("config.yml")
@NamingPattern(NamingPatterns.LOWER_KEBAB_CASE)
public class ClaimBoxesConfigDTO {
    DatabaseConfigDTO database;
    GuiConfigDTO gui;
    ConfirmGUIDTO confirmGui;
    int updateInterval = 10;

    @Config
    static class DatabaseConfigDTO {
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
    static class ItemConfigDTO {
        Material type;
        String name;
        short data = 0;
    }

    @Config
    static class ConfirmGUIDTO {
        String title;
        ItemConfigDTO confirm;
        ItemConfigDTO cancel;
        ItemConfigDTO background;
    }
}
