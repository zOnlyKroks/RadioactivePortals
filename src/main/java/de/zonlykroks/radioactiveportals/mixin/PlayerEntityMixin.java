package de.zonlykroks.radioactiveportals.mixin;

import de.zonlykroks.radioactiveportals.Radioactiveportals;
import de.zonlykroks.radioactiveportals.init.ModDamageTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow
    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    @Unique
    private int radioactiveportals$ticksInPortal = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    public void checkPortalCondition(CallbackInfo ci) {
        final PlayerEntity player = (PlayerEntity) (Object) this;

        if (!(player.getWorld() instanceof ServerWorld serverWorld)) return;

        final Block inside = player.getWorld().getBlockState(player.getBlockPos()).getBlock();

        if (inside == Blocks.NETHER_PORTAL) {
            radioactiveportals$ticksInPortal++;

            if (radioactiveportals$ticksInPortal >= 2400) { // 2 minutes
                this.damage(serverWorld, ModDamageTypes.portalRadiation(serverWorld), Float.MAX_VALUE);

                radioactiveportals$ticksInPortal = 0;
            }
        } else {
            radioactiveportals$ticksInPortal = 0;
        }
    }
}
