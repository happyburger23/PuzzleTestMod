package org.example.exmod.block_entities;

import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

public class TestBlockEntity extends BlockEntity {
    public static Identifier id = Identifier.of(Constants.MOD_ID, "test_block_entity");
    Zone zone;
    int x, y, z;

    public TestBlockEntity(Zone zone, int globalX, int globalY, int globalZ) {
        super(zone, globalX, globalY, globalZ);
    }

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new TestBlockEntity(zone, x, y, z));
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }

    @Override
    public void onInteract(Player player, Zone zone) {
        GameSingletons.openBlockEntityScreen(player, zone, this);

        super.onInteract(player, zone);
    }

    @Override
    public void onCreate(BlockState blockState) {
        setTicking(true);
        super.onCreate(blockState);
    }

    @Override
    public void onRemove() {
        super.onRemove();
        setTicking(false);
    }

    @Override
    public void onTick() {
        BlockPosition above = BlockUtil.getBlockPosAtVec(zone, x, y, z).getOffsetBlockPos(zone, 0, 1, 0);
        BlockState current = above.getBlockState();
        if(current.getBlock() == Block.AIR) {
            above.setBlockState(Block.SAND.getDefaultBlockState());
            above.flagTouchingChunksForRemeshing(zone, false);
        }
    }
}
