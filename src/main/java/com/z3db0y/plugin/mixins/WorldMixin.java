package com.z3db0y.plugin.mixins;

import com.z3db0y.plugin.Config;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(World.class)
public abstract class WorldMixin {;

    @Shadow public abstract RegistryKey<World> getRegistryKey();

    @ModifyVariable(at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;thread:Ljava/lang/Thread;"), method = "<init>", argsOnly = true)
    private long init(long biomeAccess) {
        if (this.getRegistryKey() == World.NETHER) {
            return BiomeAccess.hashSeed(Config.getInstance().getNetherSeed());
        } else if (this.getRegistryKey() == World.END) {
            return BiomeAccess.hashSeed(Config.getInstance().getEndSeed());
        }

        return biomeAccess;
    }

}
