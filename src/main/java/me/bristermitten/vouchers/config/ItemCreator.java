package me.bristermitten.vouchers.config;

import me.bristermitten.mittenlib.lang.format.MessageFormatter;
import me.bristermitten.vouchers.util.Formatting;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemCreator {

    private final MessageFormatter messageFormatter;

    @Inject
    public ItemCreator(MessageFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    public ItemStack toItem(ItemConfig config, OfflinePlayer player) {
        return toItem(messageFormatter, config, player);
    }

    public ItemStack toItem(MessageFormatter formatter, ItemConfig config, @Nullable OfflinePlayer player) {
        final ItemStack itemStack = new ItemStack(config.type(), 1, config.data());
        itemStack.setDurability(config.data());
        final ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            String name = config.name();
            if (name != null) {
                meta.setDisplayName(Formatting.legacyFullyFormat(formatter, name, player));
            }
            List<String> lore = config.lore();
            if (lore != null) {
                meta.setLore(lore.stream()
                        .map(s -> Formatting.legacyFullyFormat(formatter, s, player))
                        .collect(Collectors.toList()));
            }
            itemStack.setItemMeta(meta);

        }
        if (meta instanceof SkullMeta) {
            String playerName = Optional
                    .ofNullable(config.player())
                    .orElseGet(() -> Optional.ofNullable(player).map(OfflinePlayer::getName).orElse(null));
            ((SkullMeta) meta).setOwner(playerName);
        }
        return itemStack;
    }

}
