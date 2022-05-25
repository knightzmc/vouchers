package me.bristermitten.vouchers.persist;

import me.bristermitten.vouchers.config.ClaimBoxesConfig;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Optional;

public class TableNameFactory {

    private final Provider<ClaimBoxesConfig> config;

    @Inject
    public TableNameFactory(Provider<ClaimBoxesConfig> config) {
        this.config = config;
    }

    public @NotNull String getTableName(@NotNull String name) {
        return Optional.ofNullable(config.get().storage().database())
                .flatMap(d -> Optional.ofNullable(d.tablePrefix()))
                .map(prefix -> prefix + name)
                .orElse(name);
    }
}
