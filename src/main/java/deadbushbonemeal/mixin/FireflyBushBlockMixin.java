package deadbushbonemeal.mixin;

import deadbushbonemeal.Spreading;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.FireflyBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Lets bone meal spread firefly bushes one block up or down as well, like the dead bush ({@link Spreading}). */
@Mixin(FireflyBushBlock.class)
public abstract class FireflyBushBlockMixin {
    @Inject(method = "isValidBonemealTarget", at = @At("HEAD"), cancellable = true)
    private void deadbushbonemeal$canSpread(LevelReader level, BlockPos pos, BlockState state, BonemealSource source,
            CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(Spreading.canSpread(level, pos, state));
    }

    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void deadbushbonemeal$spread(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source,
            CallbackInfo ci) {
        Spreading.spread(level, random, pos, state);
        ci.cancel();
    }
}
