package dev.vyklade;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import dev.vyklade.events.VykladeDamageEvent;

import javax.annotation.Nonnull;

public class VykladeSpellbooks extends JavaPlugin {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public VykladeSpellbooks(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {

        this.getEntityStoreRegistry().registerSystem(new VykladeDamageEvent());
    }
}

