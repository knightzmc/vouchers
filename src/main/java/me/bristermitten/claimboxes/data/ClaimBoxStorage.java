package me.bristermitten.claimboxes.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.bristermitten.claimboxes.data.persistence.SQLClaimBoxPersistence;
import me.bristermitten.mittenlib.persistence.CachingPersistence;
import me.bristermitten.mittenlib.util.Unit;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Singleton
public class ClaimBoxStorage extends CachingPersistence<UUID, ClaimBox> implements ClaimBoxPersistence {

    @Inject
    public ClaimBoxStorage(SQLClaimBoxPersistence delegate) {
        super(delegate, ClaimBox::getOwner);
    }

    @Override
    protected void addToCache(UUID id, ClaimBox data) {
        final ClaimBox claimBox = lookupAll().get(id);
        if (claimBox != null) {
            // there's already an element in the cache, so merge them
            claimBox.getMutableVoucherIds().clear();
            claimBox.getMutableVoucherIds().addAll(data.getMutableVoucherIds());
            super.addToCache(id, claimBox);
            return;
        }
        super.addToCache(id, data);
    }

    public ClaimBox createNewBox(UUID id) {
        final ClaimBox claimBox = new ClaimBox(id, Collections.emptyList());
        save(claimBox).exceptionally(t -> {
            t.printStackTrace();
            return null;
        });
        return claimBox;
    }

    public CompletableFuture<ClaimBox> getOrCreate(UUID id) {
        return load(id).thenApply(opt ->
                opt.orElseGet(() ->
                        createNewBox(id)));
    }

    public CompletableFuture<Unit> saveAll() {
        return saveAll(lookupAll().values());
    }
}
