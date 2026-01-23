package dev.vyklade.events;

import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerRefEvent;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatValue;
import com.hypixel.hytale.server.core.modules.entitystats.RegeneratingValue;
import com.hypixel.hytale.server.core.modules.entitystats.asset.EntityStatType;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatsModule;
import com.hypixel.hytale.server.core.modules.entitystats.modifier.Modifier;
import com.hypixel.hytale.server.core.modules.entitystats.modifier.StaticModifier;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.vyklade.VykladeSpellbooks;
import dev.vyklade.managers.VykladeConfig;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.UUID;

public class VykladePlayerConnectEvent extends PlayerReadyEvent {


    public VykladePlayerConnectEvent(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl Player player, int readyId) {
        super(ref, player, readyId);
    }

    public static void onPlayerConnect(PlayerReadyEvent event, VykladeConfig config) {
        Ref<EntityStore> index = event.getPlayerRef();

        World world = event.getPlayer().getWorld();

        assert world != null;

        Store<EntityStore> store = world.getEntityStore().getStore();

        assert store != null;

        EntityStatMap statMap = store.getComponent(index, EntityStatMap.getComponentType());

        assert statMap != null;

        int manaIndex = EntityStatType.getAssetMap().getIndex("Mana");

        StaticModifier modifier = new StaticModifier(Modifier.ModifierTarget.MAX, StaticModifier.CalculationType.ADDITIVE, config.getMaxMana());

        if (config.isEnableMana()) {
            statMap.putModifier(manaIndex,"Vyklade_Max", modifier);

            statMap.setStatValue(manaIndex, config.getMaxMana());
        } else {
            Modifier mod = statMap.getModifier(manaIndex,"Vyklade_Max");

            if (mod != null) {
                statMap.removeModifier(manaIndex,"Vyklade_Max");
            }
            statMap.resetStatValue(manaIndex);
        }

        statMap.update();
    }
}
