package deadbushbonemeal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

/**
 * Bone meal spreading for the dead bush, the firefly bush and the spore blossom. Like vanilla's
 * {@code BonemealableBlock.findSpreadableNeighbourPos}, but the ground and ceilings are rarely flat, so a spot one
 * block higher or lower beside the plant counts as well.
 */
public final class Spreading {
    private Spreading() {
    }

    /** The free spots beside the plant, on its own level and one block above or below, where the plant survives. */
    public static List<BlockPos> targets(LevelReader level, BlockPos pos, BlockState state) {
        List<BlockPos> targets = new ArrayList<>();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos side = pos.relative(direction);
            for (BlockPos target : List.of(side, side.above(), side.below())) {
                if (level.isEmptyBlock(target) && state.canSurvive(level, target)) {
                    targets.add(target);
                }
            }
        }
        return targets;
    }

    public static boolean canSpread(LevelReader level, BlockPos pos, BlockState state) {
        return !targets(level, pos, state).isEmpty();
    }

    /** Places a new plant on one of the {@link #targets} picked at random; does nothing without one. */
    public static void spread(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        List<BlockPos> targets = targets(level, pos, state);
        if (!targets.isEmpty()) {
            level.setBlockAndUpdate(targets.get(random.nextInt(targets.size())), state.getBlock().defaultBlockState());
        }
    }
}
