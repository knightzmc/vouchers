package me.bristermitten.vouchers.data.claimbox;

import com.google.inject.AbstractModule;
import me.bristermitten.vouchers.data.claimbox.persistence.ClaimBoxPersistence;

public class ClaimBoxDataModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(ClaimBoxPersistence.class).to(ClaimBoxStorage.class);
    }
}
