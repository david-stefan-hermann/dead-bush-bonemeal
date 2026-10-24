package deadbushbonemeal.mixin;

import deadbushbonemeal.Spreading;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Makes the dead bush bonemealable the way {@code FireflyBushBlock} is: bone meal places a new dead bush beside it
 * where a dead bush survives, following the ground one block up or down ({@link Spreading}). The dry grasses extend
 * this class and keep their own bone meal behaviour, which overrides these methods.
 */
@Mixin(DryVegetationBlock.class)
public abstract class DryVegetationBlockMixin implements BonemealableBlock {
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
        return state.is(Blocks.DEAD_BUSH) && Spreading.canSpread(level, pos, state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
        Spreading.spread(level, random, pos, state);
    }
}
