package me.bristermitten.vouchers.data.voucher.type;

import me.bristermitten.vouchers.actions.Action;
import me.bristermitten.vouchers.config.ItemConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;

public abstract class VoucherType {
    private final String id;
    private final Collection<Action> actions;

    private final @Nullable String defaultData;

    /**
     * Descriptor for the voucher item. If null, then the voucher is a voucher code only
     */
    private final @Nullable ItemConfig itemDescriptor;

    protected VoucherType(String id, Collection<Action> actions, @Nullable String defaultData, @Nullable ItemConfig itemDescriptor) {
        this.id = id;
        this.actions = actions;
        this.defaultData = defaultData;
        this.itemDescriptor = itemDescriptor;
    }

    public @Nullable ItemConfig getItemDescriptor() {
        return itemDescriptor;
    }

    public String getId() {
        return id;
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public Optional<String> getDefaultData() {
        return Optional.ofNullable(defaultData);
    }
}
