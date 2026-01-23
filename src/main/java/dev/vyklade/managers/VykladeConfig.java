package dev.vyklade.managers;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class VykladeConfig {
    public static final BuilderCodec<VykladeConfig> CODEC =
            BuilderCodec.builder(VykladeConfig.class, VykladeConfig::new)
                    .append(new KeyedCodec<Boolean>("EnableMana", BuilderCodec.BOOLEAN),
                            (exConfig, value, extraInfo) -> exConfig.EnableMana = value,
                            (exConfig, extraInfo) -> exConfig.isEnableMana())
                    .documentation("Whether or not this mod should change the starting maximum mana for all users.")
                    .add()
                    .append(new KeyedCodec<Float>("MaxMana", BuilderCodec.FLOAT),
                            (exConfig, value, extraInfo) -> exConfig.MaxMana = value,
                            (exConfig, extraInfo) -> exConfig.getMaxMana())
                    .documentation("Starting Maximum Mana for all users. DEFAULT: 100f. Does not affect the 1/mana sec recover baked into the game.")
                    .add()
                    .build();

    private boolean EnableMana = false;
    private float MaxMana = 100f;
    private float SpellBookCost = -20f;

    public VykladeConfig() {

    }

    public boolean isEnableMana() { return EnableMana; }
    public float getMaxMana() { return Math.max(0f, MaxMana); }
    public float getSpellBookCostCost() { return Math.max(0f, SpellBookCost); }
}