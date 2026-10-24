package deadbushbonemeal.mixin;

import deadbushbonemeal.Spreading;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Makes the spore blossom bonemealable like the dead bush: bone meal places a new spore blossom beside it where one
 * survives (under a block with a sturdy bottom face), following the ceiling one block up or down ({@link Spreading}).
 */
@Mixin(SporeBlossomBlock.class)
public abstract class SporeBlossomBlockMixin implements BonemealableBlock {
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return Spreading.canSpread(level, pos, state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        Spreading.spread(level, random, pos, state);
    }
}
