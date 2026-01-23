package dev.vyklade;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import dev.vyklade.events.VykladeDamageEvent;
import dev.vyklade.events.VykladePlayerConnectEvent;
import dev.vyklade.managers.VykladeConfig;

import javax.annotation.Nonnull;

public class VykladeSpellbooks extends JavaPlugin {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    public static VykladeConfig CONFIG;
    private final Config<VykladeConfig> config;

    public VykladeSpellbooks(@Nonnull JavaPluginInit init) {
        super(init);
        this.config = this.withConfig("VykladeSpellbooks",VykladeConfig.CODEC);
    }

    @Override
    protected void setup() {
        this.config.save();

        this.getEntityStoreRegistry().registerSystem(new VykladeDamageEvent());

        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, (event) -> VykladePlayerConnectEvent.onPlayerConnect(event, getConfig()));
    }
    @Nonnull
    public VykladeConfig getConfig() {
        return this.config.get();
    }
}

