package com.z3db0y.plugin.mixins;

import com.z3db0y.plugin.Config;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.GeneratorOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/GeneratorOptions;getSeed()J"), method = "<init>")
    private long setSeed(GeneratorOptions instance) {
        World w = (World) (Object) this;

        if (w.getDimensionEntry() == World.NETHER) {
            return Config.getInstance().getNetherSeed();
        } else if (w.getDimensionEntry() == World.END) {
            return Config.getInstance().getEndSeed();
        }

        return instance.getSeed();
    }

    @Inject(at = @At("RETURN"), method = "getSeed", cancellable = true)
    private void getSeed(CallbackInfoReturnable<Long> cir) {
        World w = (World) (Object) this;

        if (w.getRegistryKey() == World.NETHER) {
            cir.setReturnValue(Config.getInstance().getNetherSeed());
        } else if (w.getRegistryKey() == World.END) {
            cir.setReturnValue(Config.getInstance().getEndSeed());
        }
    }

}
