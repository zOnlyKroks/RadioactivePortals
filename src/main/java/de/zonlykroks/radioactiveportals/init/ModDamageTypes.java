package de.zonlykroks.radioactiveportals.init;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.server.world.ServerWorld;

public final class ModDamageTypes {

    private static final RegistryKey<DamageType> PORTAL_RADIATION =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("radioactiveportals", "portal_radiation"));

    public static DamageSource portalRadiation(ServerWorld world) {
        return new DamageSource(
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                        .getEntry(PORTAL_RADIATION.getValue()).orElseThrow()
        );
    }

    public static void init() {}
}
